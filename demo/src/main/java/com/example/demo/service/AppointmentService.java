package com.example.demo.service;

import java.io.IOException;
import java.time.*;
import java.time.chrono.ChronoLocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.example.demo.dto.*;
import com.example.demo.model.*;
import com.example.demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.CreateAppointmentDTO;
import com.example.demo.dto.FutureAppointmentDTO;
import com.example.demo.dto.FutureAppointmentsBBDTO;
import com.example.demo.model.enumerations.AppointmentStatus;
import com.example.email.EmailDetails;
import com.example.email.EmailService;
import com.google.zxing.WriterException;

import javax.mail.MessagingException;

@Service
public class AppointmentService {
	private final AppointmentRepository appRepo;
	private final QuestionnairuService questionnairuService;
	private final BloodBankRepository bloodRepo;
	private final CenterRepository bbRepo;
	private final UserRepository userRepo;
	private final CanceledAppointmentService canceledAppointmentService;
	private final EmailService emailService;
	private final QRCodeGenerator qrCodeGenerator;
	private final QRCodeService qrCodeService;
	private final UserService userService;
	private final EmailServiceImpl emailServiceImple;


	private static final String QR_CODE_IMAGE_PATH = "./src/main/resources/images/QRCode.png";
	private final QRCodeRepository qRCodeRepository;

	@Autowired
	public AppointmentService(AppointmentRepository repo, BloodBankRepository bloodRepo, CenterRepository bbRepo, UserRepository userRepo, CanceledAppointmentService canceledAppointmentService, EmailServiceImpl emailService, QuestionnairuService questionnairuService, QRCodeGenerator qr, QRCodeService qrCodeService, UserService userService, EmailServiceImpl emailServiceImple,
							  QRCodeRepository qRCodeRepository)
	{
		this.appRepo = repo;
		this.canceledAppointmentService = canceledAppointmentService;
		this.questionnairuService = questionnairuService;
		this.bbRepo = bbRepo;
		this.userRepo = userRepo;
		this.emailService = emailService;
		this.bloodRepo = bloodRepo;
		this.qrCodeGenerator = qr;
		this.qrCodeService = qrCodeService;
		this.userService = userService;
		this.emailServiceImple = emailServiceImple;
		this.qRCodeRepository = qRCodeRepository;
	}
	
	public String getMessageAboutAvailability(Appointment app) {
		List<Appointment> appointments = appRepo.findAll();

		if(app.isAppointmentInThePast()) {
			return("Can not schedule appointment in the past!");
		}

//		if(!app.isAppointmentInWorkTime()) {
//			return("Time of appointment is out of blood bank's work time!");
//		}

		for(Appointment appointment : appointments) {
			if(app.getDate().getDate() == appointment.getDate().getDate()) {
				if(app.isAppointmentOverlapsWithOtherAppointment(appointment)) {
					return("Appointment overlaps with other appointments!");
				}
			}
		}

		return("Available");
	}

	public Appointment saveAppointment(Appointment app) {
		return appRepo.save(app);
	}

	@SuppressWarnings("deprecation")
	public Appointment convertCreateAppointmentDTOtoAppointment(CreateAppointmentDTO appDTO) {
		BloodBank bb = bbRepo.getOne(appDTO.getBloodBankID());
        LocalTime time = LocalTime.parse(appDTO.getTime());
        Appointment app = new Appointment(bb, appDTO.getDate(), time, appDTO.getDuration(), null, appDTO.getStatus(), null);

        return app;
	}

	public Appointment scheduleAppointment(Appointment app) {
		User u = userRepo.getOne((long) 1);
		app.setUser(u);
		String qrCodeData = generateAppointmentDetails(app);
		try {
			QRCodeGenerator.generateQRCodeImage(qrCodeData,250,250, QR_CODE_IMAGE_PATH);
		}catch (WriterException | IOException e) {
            //e.printStackTrace();
        }

		EmailDetails emailDetails = new EmailDetails("dejangloginjic@gmail.com", generateEmailMessage(app), "Successfuly scheduled appointment", QR_CODE_IMAGE_PATH);
		emailService.sendMailWithAttachment(emailDetails);
		return appRepo.save(app);
	}

	private String generateAppointmentDetails(Appointment app) {
		String date[] = app.getDate().toString().split(" ");
		String appDetails = "Appointment details \n"
				+ "Patient: " + app.getUser().getName() + " " + app.getUser().getSurname() + "\n"
				+ "Blood bank: " + app.getBloodBank().getName() + "\n"
				+ "Address: " + app.getBloodBank().getAddress().getCity() + ", " + app.getBloodBank().getAddress().getStreet() + ", " + app.getBloodBank().getAddress().getNumber() + "\n"
				+ "Date: " + date[0] + ", " + date[1] + ", " + date[2] + "\n"
				+ "Time: " + app.getTime();

		return appDetails;
	}

	private String generateEmailMessage(Appointment app) {
		String date[] = app.getDate().toString().split(" ");
		String message = "Hi " + app.getUser().getName() + " " + app.getUser().getSurname() + ". You are successfuly scheduled appointment in our center."
				+ " You appointment are on " + date[0] + " " + date[1] + " " + date[2] + " " + " at " + app.getTime().toString() + ".";
		return message;
	}

	public List<FutureAppointmentDTO> getAllFutureAppointmentsForLoggedUser(long l) {
		List<Appointment> apps = this.appRepo.getByUserId(l);
		List<FutureAppointmentDTO> retList = new ArrayList<>();

		for(Appointment a : apps) {
			if(a.getDate().after(new Date())) {
				retList.add(convertAppointmentToFutureAppointmentDTO(a));
			}
		}

		return retList;
	}

	public List<AppointmentResponse> getAllFutureAppointmentResponsesForLoggedUser() {
		List<Appointment> apps = this.appRepo.findAll();
		List<AppointmentResponse> retList = new ArrayList<>();

		for(Appointment a : apps) {
			if(a.getDate().after(new Date())) {
				AppointmentResponse appointmentResponse = new AppointmentResponse();
				appointmentResponse.setTime(a.getDate());
				appointmentResponse.setDuration(a.getDuration());
				appointmentResponse.setUserName(a.getUser().getName());
				appointmentResponse.setUserSurname(a.getUser().getSurname());

				retList.add(appointmentResponse);
			}
		}

		return retList;
	}



	public List<FutureAppointmentsBBDTO> getAllFutureAppointmentsBB(long l) {
		List<Appointment> apps = this.appRepo.getAppointmentsByBloodBankID(l);
		List<FutureAppointmentsBBDTO> retList = new ArrayList<>();

		for(Appointment a : apps) {
			if(a.getDate().after(new Date()) && a.getStatus().equals(AppointmentStatus.BUSY)) {
				retList.add(new FutureAppointmentsBBDTO(a.getDate().toString().split(" ")[0], a.getTime().toString(), a.getUser().getName(), a.getUser().getSurname(), a.getId(), a.getUser().getId()));
			}
		}

		return retList;
	}


	private FutureAppointmentDTO convertAppointmentToFutureAppointmentDTO(Appointment a) {
		return new FutureAppointmentDTO(a.getBloodBank().getName(), a.getDate().toString().split(" ")[0], a.getTime().toString());
	}

	@Transactional(readOnly = false)
	public void save(Appointment appointment){
		bloodRepo.save(appointment.getBloodBank());
		appRepo.save(appointment);
	}

	public List<Appointment> findByAdminCenter(String email){
		return appRepo.findByAdminCenter(email);
	}


	@Transactional(readOnly = false)
	public Appointment findById(Long id) {
		return appRepo.getById(id);
	}


	public List<Appointment> getAll(){return appRepo.findAll();}
	public Appointment getById(Long iD){
		return appRepo.getById(iD);
	}

//	public  AppointmentUserDTO convertAppointmentToAppointmentUserDTO(Appointment a) {
//		return new AppointmentUserDTO(a.getId(),a.getDate(), a.getTime(), a.getDuration(), a.getStatus(),a.getUser().getId(), new QuestionnairuDTO(questionnairuService.getLastQuestionnaire(a.getUser().getId()).isQuestion1(),questionnairuService.getLastQuestionnaire(a.getUser().getId()).isQuestion2(),questionnairuService.getLastQuestionnaire(a.getUser().getId()).isQuestion3(),questionnairuService.getLastQuestionnaire(a.getUser().getId()).isQuestion4(),questionnairuService.getLastQuestionnaire(a.getUser().getId()).isQuestion5(),questionnairuService.getLastQuestionnaire(a.getUser().getId()).isQuestion6(),questionnairuService.getLastQuestionnaire(a.getUser().getId()).isQuestion7(),questionnairuService.getLastQuestionnaire(a.getUser().getId()).isQuestion8(),questionnairuService.getLastQuestionnaire(a.getUser().getId()).isQuestion9(),questionnairuService.getLastQuestionnaire(a.getUser().getId()).isQuestion10(),questionnairuService.getLastQuestionnaire(a.getUser().getId()).isQuestion11(),questionnairuService.getLastQuestionnaire(a.getUser().getId()).isQuestion12()));
//	}

	public  Survey convertSurveyDTOToSurvey(SurveyDTO s) {
		return new Survey(s.getBloodType(), s.getGeneralCondition(), s.getSystolicBP(),s.getDiastolicBP(), s.getPulse(), s.getUsedBags());
	}

	public void update(Appointment appointment){
		appRepo.save(appointment);
	}

	public List<Appointment> getDoneAppointmentsByBloodBankID(Long id) {
		return appRepo.getDoneAppointmentsByBloodBankID(id);
	}

	public  List<Appointment>getFreeAppointments(){

		List<Appointment> appointments = appRepo.getFreeAppointments();
		for(Appointment appointment : appointments)
			appointment.setUser(null);
		return  appointments;

	}

	public boolean appointmentInLastSixMonth(Long userId){
		List<Appointment> appointments = appRepo.getDoneAppointmentsForUser(userId);
		LocalDate currentDate = LocalDate.now();
		for (Appointment appointment: appointments){
			Period period = Period.between(appointment.getDate().toInstant()
					.atZone(ZoneId.systemDefault())
					.toLocalDate(), currentDate);
			if (period.getMonths() < 6 || (period.getMonths() == 6 && period.getDays() == 0)) {
				return true;
			}

		}
		return false;
	}
	public boolean isMoreThan3Penals(Long userId){
		User user = userRepo.findById(userId).get();
		if(user.getPenalsNumber() > 3)
			return true;
		else
			return false;
	}

	public boolean isUserAlreadyBusy(Long userID){
		List<Appointment> appointments = appRepo.getBusyAppointmentsForUser(userID);
		if(appointments.size() == 0)
			return false;
		else
			return true;
	}
	public int takeAppointment(Long userId, Long appointmentID) throws IOException, WriterException, MessagingException {

		if(isMoreThan3Penals(userId) == true){
			setQuestion(userId);
			return 2;
		}
		if(appointmentInLastSixMonth(userId) == true){
			setQuestion(userId);
			return 3;
		}
		if(isUserAlreadyBusy(userId) == true){
			setQuestion(userId);
			return 4;
		}
		if(questionnairuService.getUserQuestionairy(userId).size() == 0)
			return 1;

		Optional<Appointment> appointmentOptional = appRepo.findById(appointmentID);
		Appointment appointment = appointmentOptional.get();
		appointment.setUser(userRepo.findById(userId).get());
		appointment.setStatus(AppointmentStatus.BUSY);
		appRepo.save(appointment);
		setQuestion(userId);
		generateQRCodeForAppointment(userId, appointment.getId());
		return 0;
	}

	public void setQuestion(Long userId){
		List<Questionnaire> questionnaires = questionnairuService.getUserQuestionairy(userId);
		for(Questionnaire q: questionnaires){
			q.setUser(null);
			questionnairuService.save(q);
		}
	}

	public void generateQRCodeForAppointment(Long userId, Long appointmentId) throws IOException, WriterException, MessagingException {
		User user = userRepo.findById(userId).get();
		Appointment appointment = appRepo.findById(appointmentId).get();
		BloodBank bloodBank =  appointment.getBloodBank();

//		String qrCodeContent = "User: " + user.getName() + " " + user.getSurname() + "\n" +
//				"Blood Bank: " + bloodBank.getName() + " - " + bloodBank.getAddress() + "\n" +
//				"Date: " + appointment.getDate() + "\n" +
//				"Time: " + appointment.getTime() + "\n" +
//				"Duration: " + appointment.getDuration() + " minutes";

		String qrCodeContent = "Appointment Details:\n" +
				"User: " + user.getName() + " " + user.getSurname() + "\n" +
				"Blood Bank: " + bloodBank.getName() + "\n" +
				"Address: " + bloodBank.getAddress().getStreet() + " " + bloodBank.getAddress().getNumber() + "\n" +
				"         " + bloodBank.getAddress().getCity() + ", " + bloodBank.getAddress().getCountry() + "\n" +
				"Date: " + appointment.getDate() + "\n" +
				"Time: " + appointment.getTime() + "\n" +
				"Duration: " + appointment.getDuration() + " minutes";


		String QRPath = "./src/main/resources/images/"+appointment.getId()+".png";
		qrCodeGenerator.generateQRCodeImage(qrCodeContent,150,150, QRPath);
		emailServiceImple.sendQRCodeEmailWithAttachment(user.getEmail(), appointment.getId(), QRPath);
		qrCodeService.save(new QRCode(user.getId(), appointment.getId(), appointment.getDate(), AppointmentStatus.BUSY));
	}


	public List<Appointment> getHistoryForUser(Long userId){
		List<Appointment> appointments = appRepo.getHistoryForUser(userId);
		for(Appointment appointment : appointments)
			appointment.setUser(null);
		return  appointments;
	}

	public List<Appointment> getBusyAppointments(Long userId){
		return appRepo.getBusyAppointmentsForUser(userId);
	}

	public boolean canCancelAppointment(Appointment appointment){
		LocalDateTime currentDateTime = LocalDateTime.now();
		LocalDate appointmentDate = appointment.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDateTime twentyFourHoursBefore = currentDateTime.plus(24, ChronoUnit.HOURS);
		return appointmentDate.isAfter(ChronoLocalDate.from(twentyFourHoursBefore));
	}

	public void setQRStatus( Long appointmentId){
		QRCode qrCode = qrCodeService.findByApointmentId(appointmentId);
		qrCode.setAppointmentStatus(AppointmentStatus.CANCELD);
		qRCodeRepository.save(qrCode);
	}
	public boolean cancelApointment(Long appointmentId){
		Appointment appointment = appRepo.getById(appointmentId);
		Date currentDate = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
		Long userId = userService.getCurrentUser().getId();
		if(canCancelAppointment(appointment) == false)
			return false;
		appointment.setStatus(AppointmentStatus.CANCELD);
		canceledAppointmentService.save(new CanceledApointments(userId, appointmentId, currentDate));
		setQRStatus(appointmentId);
		int penals = userService.getCurrentUser().getPenalsNumber() + 1;
		User user =userService.getCurrentUser();
		user.setPenalsNumber(penals);
		userService.saveUser(user);

		appRepo.save(appointment);
		return  true;
	}

	public List<PenalsNumberDTO> getPenalsForUser(User user){
		List<CanceledApointments> canceledApointments = canceledAppointmentService.findByUserId(user.getId());
		List<PenalsNumberDTO>retList = new ArrayList<>();
		for(CanceledApointments canceleed:canceledApointments){
			PenalsNumberDTO penalsNumberDTO = new PenalsNumberDTO();
			penalsNumberDTO.setPenalNum(user.getPenalsNumber());
			Appointment appointment = findById(canceleed.getAppointmentId());
			penalsNumberDTO.setDate(appointment.getDate());
			penalsNumberDTO.setTime(appointment.getTime());
			penalsNumberDTO.setDuration(appointment.getDuration());
			penalsNumberDTO.setCancekingTime(canceleed.getCancelingDate());
			penalsNumberDTO.setBloodBankName(appointment.getBloodBank().getName());
			retList.add(penalsNumberDTO);
		}
		return retList;
	}
}
