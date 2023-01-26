package com.example.demo;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.example.demo.model.Complaint;
import com.example.demo.model.enumerations.ComplaintStatus;
import com.example.demo.service.ComplaintService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class  ComplaintApplicationTests {

    @Autowired
    private ComplaintService compService;

    @Test(expected = ObjectOptimisticLockingFailureException.class)
    public void testOptimisticLockingScenario() throws Throwable{
        ExecutorService executor = Executors.newFixedThreadPool(2);
        Future<?> future2 = executor.submit(new Runnable() {

            @Override
            public void run() {
                System.out.println("Startovan Thread 1");
                Complaint complaintToUpdate = compService.findById(14);
                complaintToUpdate.setReply("Ispravicemo gresku.");
                complaintToUpdate.setStatus(ComplaintStatus.REPLIED);
                try { Thread.sleep(3000); } catch (InterruptedException e) {}
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

        try {
            future2.get(); // podize ExecutionException za bilo koji izuzetak iz prvog child threada
        } catch (ExecutionException e) {
            System.out.println("Exception from thread " + e.getCause().getClass()); // u pitanju je bas ObjectOptimisticLockingFailureException
            throw e.getCause();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executor.shutdown();
    }

}
