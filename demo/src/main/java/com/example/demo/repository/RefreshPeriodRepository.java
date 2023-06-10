package com.example.demo.repository;

import com.example.demo.model.QRCode;
import com.example.demo.model.RefreshConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface RefreshPeriodRepository extends JpaRepository<RefreshConfig,Long> {
    @Query("SELECT rc FROM RefreshConfig rc WHERE rc.userId = ?1")
    RefreshConfig findByUserId(Long userId);
}
