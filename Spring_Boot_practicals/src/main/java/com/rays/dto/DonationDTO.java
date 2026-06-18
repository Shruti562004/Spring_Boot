package com.rays.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.rays.common.BaseDTO;

@Entity
@Table(name = "st_donation")
public class DonationDTO extends BaseDTO {


	@Column(name = "Name", length = 50)
	private String Name;
	

	@Column(name = "dob")
	private Date dob;
	

	@Column(name = "organizer", length = 50)
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
