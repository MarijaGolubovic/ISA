package com.example.demo.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.AppointmentUserDTO;
import com.example.demo.dto.CreateAppointmentDTO;
import com.example.demo.dto.FutureAppointmentDTO;
import com.example.demo.dto.FutureAppointmentsBBDTO;
import com.example.demo.model.Address;
import com.example.demo.dto.QuestionnairuDTO;
import com.example.demo.dto.SurveyDTO;
import com.example.demo.model.Appointment;
import com.example.demo.model.BloodBank;
import com.example.demo.model.Survey;
import com.example.demo.model.User;
import com.example.demo.model.enumerations.AppointmentStatus;
import com.example.demo.model.enumerations.Gender;
import com.example.demo.model.enumerations.UserStatus;
import com.example.demo.model.enumerations.UserType;
import com.example.demo.repository.AppointmentRepository;
import com.example.demo.repository.BloodBankRepository;
import com.example.demo.repository.CenterRepository;
import com.example.demo.repository.UserRepository;
import com.example.email.EmailDetails;
import com.example.email.EmailService;

@Service
public class AppointmentService {
	private final AppointmentRepository appRepo;
	private final QuestionnairuService questionnairuService;
	private final BloodBankRepository bloodRepo;
	private final CenterRepository bbRepo;
	private final UserRepository userRepo;
	private final EmailService emailService;
	
	@Autowired
	public AppointmentService(AppointmentRepository repo, BloodBankRepository bloodRepo, CenterRepository bbRepo, UserRepository userRepo, EmailServiceImpl emailService, QuestionnairuService questionnairuService) {
		this.appRepo = repo;
		this.questionnairuService = questionnairuService;
		this.bbRepo = bbRepo;
		this.userRepo = userRepo;
		this.emailService = emailService;
		this.bloodRepo = bloodRepo;
	}
	
	public String getMessageAboutAvailability(Appointment app) {
		List<Appointment> appointments = appRepo.findAll();
		
		if(app.isAppointmentInThePast()) {
			return("Can not schedule appointment in the past!");
		}
		
		if(!app.isAppointmentInWorkTime()) {
			return("Time of appointment is out of blood bank's work time!");
		}
		
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
		EmailDetails emailDetails = new EmailDetails(u.getEmail(), generateEmailMessage(app), "Successfuly scheduled appointment", null);
		emailService.sendSimpleMail(emailDetails);
		return appRepo.save(app);
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
	
	public void demonstrateConcurentAccessToDataBase() throws Throwable {
		User user1 = new User("markomarkovic@gmail.com", "123", "Marko", "Markovic", new Address(), "123456", "1234567899876", Gender.MALE , "Ekonomista", "Informacije 123", UserType.REGISTERED, UserStatus.ACTIVATED , 0, 0, null, null);
		User user2 = new User("marinamarkovic@gmail.com", "123", "Marina", "Markovic", new Address(), "123456", "1234567892876", Gender.FEMALE , "Pravnik", "Informacije 213", UserType.REGISTERED, UserStatus.ACTIVATED , 0, 0, null, null);
	
		ExecutorService executor = Executors.newFixedThreadPool(2);
		Future<?> future1 = executor.submit(new Runnable() {
			
			@Override
			public void run() {
		        System.out.println("Startovan Thread 1");
				Appointment appointmentToUpdate = findById(100L);
				appointmentToUpdate.setStatus(AppointmentStatus.BUSY);
				appointmentToUpdate.setUser(user1);
				try { Thread.sleep(3000); } catch (InterruptedException e) {}
				save(appointmentToUpdate);
				
			}
		});
		executor.submit(new Runnable() {
			
			@Override
			public void run() {
		        System.out.println("Startovan Thread 2");
				Appointment appointmentToUpdate = findById(100L);
				appointmentToUpdate.setStatus(AppointmentStatus.BUSY);
				appointmentToUpdate.setUser(user2);
				save(appointmentToUpdate);
			}
		});
		
		try {
		    future1.get(); 
		} catch (ExecutionException e) {
		    System.out.println("Exception from thread " + e.getCause().getClass()); // u pitanju je bas ObjectOptimisticLockingFailureException
		    throw e.getCause();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		executor.shutdown();
	}
	
	public Appointment getById(Long iD){
		return appRepo.getById(iD);
	}
	
	public  AppointmentUserDTO convertAppointmentToAppointmentUserDTO(Appointment a) {
		return new AppointmentUserDTO(a.getId(),a.getDate(), a.getTime(), a.getDuration(), a.getStatus(),a.getUser().getId(), new QuestionnairuDTO(questionnairuService.getLastQuestionnaire(a.getUser().getId()).isQuestion1(),questionnairuService.getLastQuestionnaire(a.getUser().getId()).isQuestion2(),questionnairuService.getLastQuestionnaire(a.getUser().getId()).isQuestion3(),questionnairuService.getLastQuestionnaire(a.getUser().getId()).isQuestion4(),questionnairuService.getLastQuestionnaire(a.getUser().getId()).isQuestion5(),questionnairuService.getLastQuestionnaire(a.getUser().getId()).isQuestion6(),questionnairuService.getLastQuestionnaire(a.getUser().getId()).isQuestion7(),questionnairuService.getLastQuestionnaire(a.getUser().getId()).isQuestion8(),questionnairuService.getLastQuestionnaire(a.getUser().getId()).isQuestion9(),questionnairuService.getLastQuestionnaire(a.getUser().getId()).isQuestion10(),questionnairuService.getLastQuestionnaire(a.getUser().getId()).isQuestion11(),questionnairuService.getLastQuestionnaire(a.getUser().getId()).isQuestion12()));
	}
	
	public  Survey convertSurveyDTOToSurvey(SurveyDTO s) {
		return new Survey(s.getBloodType(), s.getGeneralCondition(), s.getSystolicBP(),s.getDiastolicBP(), s.getPulse(), s.getUsedBags());
	}
	
	public void update(Appointment appointment){
		appRepo.save(appointment);
	}
	
	public List<Appointment> getDoneAppointmentsByBloodBankID(Long id) {
		return appRepo.getDoneAppointmentsByBloodBankID(id);
	}
	

	
}
