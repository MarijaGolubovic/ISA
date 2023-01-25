package com.example.demo.repository;

import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    @Query("select u from User u where u.name like %:name% and surname like %:surname%")
    List<User> findByNameAndSurname(String name, String surname);

    @Query("select u from User u where u.bloodBank.id = (select bb from BloodBank bb where bb.id=?1)")
    List<User> findByBloodBank(Long id);

    @Query("select u from User u where u.bloodBank.id=?1")
    List<User> findByCenterID(Long idCenter);

    @Query("select u from User u where u.userType like 'ADMIN_CENTER' and u.bloodBank is null")
    List<User> findAdminCentersWithoutBB();

    @Query("select u from User u where u.email=?1")
    User findByEmail(String email);
     

}