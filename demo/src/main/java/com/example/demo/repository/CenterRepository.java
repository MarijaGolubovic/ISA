package com.example.demo.repository;

import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.BloodBank;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CenterRepository extends JpaRepository<BloodBank, Long> {
    @Query("select bb from BloodBank bb where bb.id = (select u from User u where u.id=?1)")
    BloodBank findByUserId(Long idUser);
}
