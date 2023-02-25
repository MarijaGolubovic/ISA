package com.example.demo.repository;

import com.example.demo.model.Complaint;
import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComplaintRepository extends JpaRepository<Complaint, Long> {

    /*Query("select c from Complaint c where c.status like '0'")
    List<Complaint> findAllOnPending();*/

}
