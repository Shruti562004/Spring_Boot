package com.rays.form;

import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.rays.common.BaseDTO;
import com.rays.common.BaseForm;
import com.rays.dto.DonationDTO;

public class DonationForm  extends BaseForm{
	
	@NotEmpty(message = " name is required")
	
	private String name;
	
	@NotNull(message = "dob is required")
	private Date  dob;
	
	@NotEmpty(message = " organizer is required")
	private  String organizer;
	
	public String getName() { 
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public String getOrganizer() {
		return organizer;
	}
	public void setOrganizer(String organizer) {
		this.organizer = organizer;
	}
	
	
	public BaseDTO getDTO() {
		
		DonationDTO dto=(DonationDTO) initDTO(new DonationDTO());
		dto.setName(name);
		dto.setDob(dob);
		dto.setOrganizer(organizer);
		
		return dto;
		
	}
	
	

	
	
	
}
