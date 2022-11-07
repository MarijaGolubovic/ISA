package com.example.demo.repository;


import com.example.demo.model.BloodSupply;
import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    @Query("select u from User u where u.name like %:name% and surname like %:surname%")
    List<User> findByNameAndSurname(String name, String surname);
}