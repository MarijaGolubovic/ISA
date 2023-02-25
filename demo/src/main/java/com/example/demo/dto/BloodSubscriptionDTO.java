package com.example.demo.dto;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import com.example.demo.model.*;

public class BloodSubscriptionDTO {

		private String bloodBankName;
		private Date dateAndTimeOfSubscription;
		private List<AmountOfBloodTypeDTO> amountOfBloodTypes;
		
		public BloodSubscriptionDTO() {
			super();
			// TODO Auto-generated constructor stub
		}
		
		public BloodSubscriptionDTO(String bloodBankName, Date dateAndTimeOfSubscription,
				List<AmountOfBloodTypeDTO> amountOfBloodTypes) {
			super();
			this.bloodBankName = bloodBankName;
			this.dateAndTimeOfSubscription = dateAndTimeOfSubscription;
			this.amountOfBloodTypes = amountOfBloodTypes;
		}
		
		public String getBloodBankName() {
			return bloodBankName;
		}
		public void setBloodBankName(String bloodBankName) {
			this.bloodBankName = bloodBankName;
		}
		public Date getDateAndTimeOfSubscription() {
			return dateAndTimeOfSubscription;
		}
		public void setDateAndTimeOfSubscription(Date dateAndTimeOfSubscription) {
			this.dateAndTimeOfSubscription = dateAndTimeOfSubscription;
		}
		public List<AmountOfBloodTypeDTO> getAmountOfBloodTypes() {
			return amountOfBloodTypes;
		}
		public void setAmountOfBloodTypes(List<AmountOfBloodTypeDTO> amountOfBloodTypes) {
			this.amountOfBloodTypes = amountOfBloodTypes;
		}
		
		
}
