package com.example.demo;

import com.example.demo.model.Appointment;
import com.example.demo.model.Questionnaire;
import com.example.demo.model.User;
import com.example.demo.repository.AppointmentRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.AppointmentService;
import com.example.demo.service.QuestionnairuService;
import com.google.zxing.WriterException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ServiceTests {
    @Autowired
    private AppointmentService appointmentService;
    @MockBean
    private UserRepository userRepository;

    @MockBean
    private AppointmentRepository appointmentRepository;

    @MockBean
    private QuestionnairuService questionnaireService;

    @Test
    @Transactional
    void testTakeAppointmentWithOptimisticLocking() throws MessagingException, IOException, WriterException {
        Long userId = 1L;
        Long appointmentId = 2L;

        // Simulacija svih uslova koji će vraćati true
        when(userRepository.findById(userId)).thenReturn(Optional.of(new User()));
        when(appointmentRepository.findById(appointmentId)).thenReturn(Optional.of(new Appointment()));
        List<Questionnaire> mockQuestionnaires = new ArrayList<>();
        mockQuestionnaires.add(new Questionnaire());
        when(questionnaireService.getUserQuestionairy(userId)).thenReturn(mockQuestionnaires);

        // Simulacija privremenog bacanja ObjectOptimisticLockingFailureException
        when(appointmentRepository.save(any(Appointment.class))).thenThrow(ObjectOptimisticLockingFailureException.class);

        // Pozivanje funkcije
        int result = appointmentService.takeAppointment(userId, appointmentId);

        // Provera da li je vraćena vrednost 5 zbog ObjectOptimisticLockingFailureException
        assertEquals(5, result);
    }

    @Test
    void testSaveAppointmentWithConflict() {
        Appointment newAppointment = new Appointment();
        newAppointment.setId(1L);
        newAppointment.setVersion(1L);
        newAppointment.setDate(Date.valueOf(LocalDate.now()));
        newAppointment.setTime(LocalTime.of(14, 0));
        newAppointment.setDuration(60);

        List<Appointment> existingAppointments = new ArrayList<>();
        Appointment existingAppointment = new Appointment();
        existingAppointment.setId(2L);
        existingAppointment.setVersion(2L);
        existingAppointment.setDate(Date.valueOf(LocalDate.now()));
        existingAppointment.setTime(LocalTime.of(13, 30));
        existingAppointment.setDuration(45);
        existingAppointments.add(existingAppointment);

        when(appointmentRepository.findAppointmentsInTimeRange(
                newAppointment.getDate(),
                newAppointment.getTime(),
                newAppointment.getTime().plusMinutes(newAppointment.getDuration())
        )).thenReturn(existingAppointments);

        assertThrows(IllegalArgumentException.class, () -> {
            appointmentService.saveApp(newAppointment);
        });
    }

    @Test
    void testSaveAppointmentWithoutConflict() {
        Appointment newAppointment = new Appointment();
        newAppointment.setId(1L);
        newAppointment.setVersion(1L);
        newAppointment.setDate(Date.valueOf(LocalDate.now()));
        newAppointment.setTime(LocalTime.of(14, 0));
        newAppointment.setDuration(60);

        List<Appointment> existingAppointments = new ArrayList<>();

        when(appointmentRepository.findAppointmentsInTimeRange(
                newAppointment.getDate(),
                newAppointment.getTime(),
                newAppointment.getTime().plusMinutes(newAppointment.getDuration())
        )).thenReturn(existingAppointments);

        assertDoesNotThrow(() -> appointmentService.saveApp(newAppointment));
    }

}
