package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.BloodBank;
import com.example.demo.repository.BloodBankRepository;

@Service
public class BloodBankService {

	private final BloodBankRepository BlooBankRepository;
	
	@Autowired
	public BloodBankService(BloodBankRepository bbRepository) {
		this.BlooBankRepository = bbRepository;
	}
	
	@SuppressWarnings("deprecation")
	public BloodBank GetById(Long id) {
		return this.BlooBankRepository.getById(id);
	}
}
