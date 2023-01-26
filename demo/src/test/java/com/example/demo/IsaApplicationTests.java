/*package com.example.demo;


import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.test.context.junit4.SpringRunner;
import java.time.LocalTime;

import com.example.demo.model.Address;
import com.example.demo.model.Appointment;
import com.example.demo.model.BloodBank;
import com.example.demo.model.Questionnaire;
import com.example.demo.model.User;
import com.example.demo.model.enumerations.AppointmentStatus;
import com.example.demo.model.enumerations.Gender;
import com.example.demo.model.enumerations.UserStatus;
import com.example.demo.model.enumerations.UserType;
import com.example.demo.service.AppointmentService;
import com.example.demo.service.BloodBankService;
import com.example.demo.service.UserService;

@RunWith(SpringRunner.class)
//@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
class IsaApplicationTests {

	@Autowired
	private AppointmentService appService;
	@Autowired
	private BloodBankService bloodBankService;
	@Autowired
	private UserService userService;
	
	private User user1;
	private User user2;
	
	@Before
	public void setUp() throws Exception {
		BloodBank bb = bloodBankService.GetById(1L); 
		appService.save(new Appointment(100L, bb, new Date(2023, 8, 23), null, 20, null, AppointmentStatus.FREE, null));
		
		user1 = new User("markomarkovic@gmail.com", "123", "Marko", "Markovic", new Address(), "123456", "1234567899876", Gender.MALE , "Ekonomista", "Informacije 123", UserType.REGISTERED, UserStatus.ACTIVATED , 0, 0, null, null);
		user2 = new User("marinamarkovic@gmail.com", "123", "Marina", "Markovic", new Address(), "123456", "1234567892876", Gender.FEMALE , "Pravnik", "Informacije 213", UserType.REGISTERED, UserStatus.ACTIVATED , 0, 0, null, null);
	}
	
	@Test(expected = ObjectOptimisticLockingFailureException.class)
	public void manyClientsSchedulingSamePredefinedAppointmentInSameTime() throws Throwable{
		
		ExecutorService executor = Executors.newFixedThreadPool(2);
		Future<?> future1 = executor.submit(new Runnable() {
			
			@Override
			public void run() {
		        System.out.println("Startovan Thread 1");
				Appointment appointmentToUpdate = appService.findById(100L);
				appointmentToUpdate.setStatus(AppointmentStatus.BUSY);
				appointmentToUpdate.setUser(user1);
				try { Thread.sleep(3000); } catch (InterruptedException e) {}
				appService.save(appointmentToUpdate);
				
			}
		});
		executor.submit(new Runnable() {
			
			@Override
			public void run() {
		        System.out.println("Startovan Thread 2");
				Appointment appointmentToUpdate = appService.findById(100L);
				appointmentToUpdate.setStatus(AppointmentStatus.BUSY);
				appointmentToUpdate.setUser(user2);
				appService.save(appointmentToUpdate);
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

}*/
package com.example.demo;

import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.example.demo.model.*;
import com.example.demo.model.enumerations.*;
import com.example.demo.service.ComplaintService;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.orm.ObjectOptimisticLockingFailureException;

import com.example.demo.service.AppointmentService;
import com.example.demo.service.BloodBankService;
import com.example.demo.service.UserService;

@SpringBootTest
class IsaApplicationTests {
	
	@Autowired
	private AppointmentService appService;
	@Autowired
	private BloodBankService bloodBankService;
	@Autowired
	private UserService userService;
	@Autowired
	private ComplaintService compService;
	
	private User user1;
	private User user2;
	
	@Before
	public void setUp() throws Exception {
		user1 = new User("markomarkovic@gmail.com", "123", "Marko", "Markovic", new Address(), "123456", "1234567899876", Gender.MALE , "Ekonomista", "Informacije 123", UserType.REGISTERED, UserStatus.ACTIVATED , 0, 0, null, null);
		user2 = new User("marinamarkovic@gmail.com", "123", "Marina", "Markovic", new Address(), "123456", "1234567892876", Gender.FEMALE , "Pravnik", "Informacije 213", UserType.REGISTERED, UserStatus.ACTIVATED , 0, 0, null, null);
	}
	

	@Test
	void contextLoads() {
		ExecutionException thrown = Assertions.assertThrows(ExecutionException.class, () -> {
			ExecutorService executor = Executors.newFixedThreadPool(2);
			Future<?> future1 = executor.submit(new Runnable() {
				
				@Override
				public void run() {
			        System.out.println("Startovan Thread 1");
					Appointment appointmentToUpdate = appService.findById(30L);
					appointmentToUpdate.setStatus(AppointmentStatus.BUSY);
					appointmentToUpdate.setUser(user1);
					try { Thread.sleep(3000); } catch (InterruptedException e) {}
					appService.save(appointmentToUpdate);
					
				}
			});
			executor.submit(new Runnable() {
				
				@Override
				public void run() {
			        System.out.println("Startovan Thread 2");
					Appointment appointmentToUpdate = appService.findById(30L);
					appointmentToUpdate.setStatus(AppointmentStatus.BUSY);
					appointmentToUpdate.setUser(user2);
					appService.save(appointmentToUpdate);
				}
			});
			    
			future1.get(); 
			
	  });
	}

	@Test
	void contextLoads1() {
		ExecutionException thrown = Assertions.assertThrows(ExecutionException.class, () -> {
			ExecutorService executor = Executors.newFixedThreadPool(2);
			Future<?> future2 = executor.submit(new Runnable() {

				@Override
				public void run() {
					System.out.println("Startovan Thread 1");
					Complaint complaintToUpdate = compService.findById(14);
					complaintToUpdate.setReply("Ispravicemo gresku.");
					complaintToUpdate.setStatus(ComplaintStatus.REPLIED);
					try { Thread.sleep(10000); } catch (InterruptedException e) {}
					compService.save(complaintToUpdate);

				}
			});
			executor.submit(new Runnable() {

				@Override
				public void run() {
					System.out.println("Startovan Thread 2");
					Complaint complaintToUpdate = compService.findById(14);
					complaintToUpdate.setReply("Poboljsacemo, hvala na sugestiji.");
					complaintToUpdate.setStatus(ComplaintStatus.REPLIED);
					compService.save(complaintToUpdate);
				}
			});

			future2.get();

		});
	}

}
