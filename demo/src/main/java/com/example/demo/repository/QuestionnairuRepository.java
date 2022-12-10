package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Questionnaire;

@Repository
public interface QuestionnairuRepository extends JpaRepository<Questionnaire, Long> {

	@Query("select q from Questionnaire q where q.user.id = ?1")
	List<Questionnaire> findAllByUserId(Long id);

}
