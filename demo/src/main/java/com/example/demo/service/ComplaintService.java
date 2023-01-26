package com.example.demo.service;
import com.example.demo.model.Complaint;
import com.example.demo.model.enumerations.ComplaintStatus;
import com.example.demo.repository.BloodBankRepository;
import com.example.demo.repository.ComplaintRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class ComplaintService {

    private final ComplaintRepository ComplaintRepository;
    private final BloodBankRepository BloodBankRepository;
    private final UserRepository UserRepository;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public ComplaintService(ComplaintRepository ComplaintRepository, BloodBankRepository BloodBankRepository, UserRepository UserRepository){
        this.ComplaintRepository = ComplaintRepository;
        this.BloodBankRepository = BloodBankRepository;
        this.UserRepository = UserRepository;
    }

    public List<Complaint> getAll() {return ComplaintRepository.findAll();}

    public List<Complaint> getAllOnPending() {
        logger.info("> findAll");
        List<Complaint> list = ComplaintRepository.findAll();
        logger.info("< findAll");
        List<Complaint> listPending = new ArrayList<>();
        for (Complaint complaint: list) {
            if (complaint.getStatus() == ComplaintStatus.PENDING){
                listPending.add(complaint);
            }
        }
        return listPending;
    }

    public Complaint findById(long id) {

        logger.info("> findById id:{}", id);
        Complaint complaint = ComplaintRepository.findById(id).get();
        logger.info("< findById id:{}", id);
        return complaint;
    }

    @Transactional(readOnly = false)
    public Complaint save(Complaint complaint){
        logger.info("> create");
        this.BloodBankRepository.save(complaint.getBloodBank());
        this.UserRepository.save(complaint.getUser());
        Complaint savedComplaint = this.ComplaintRepository.save(complaint);
        logger.info("< create");
        return savedComplaint;
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public Complaint update(Complaint complaint) throws Exception{

        logger.info("> update id:{}", complaint.getId());
        Complaint complaintForUpdate = ComplaintRepository.getOne(complaint.getId());
        complaintForUpdate.setStatus(complaint.getStatus());
        complaintForUpdate.setReply(complaint.getReply());
        ComplaintRepository.save(complaintForUpdate);
        logger.info(complaintForUpdate.toString());
        logger.info("< update id:{}", complaint.getId());
        return complaintForUpdate;
    }

}
