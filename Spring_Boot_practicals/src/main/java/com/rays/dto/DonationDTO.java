package com.rays.dto;

import java.util.Date;

import com.rays.common.BaseDTO;

public class DonationDTO  extends BaseDTO{
	
	private String Name;
	private Date dob;
	private String organizer;
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
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
	@Override
	public String getValue() {
		
		return Name;
	}
	

}
