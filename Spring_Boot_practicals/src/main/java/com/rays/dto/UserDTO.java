package com.rays.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.rays.common.BaseDTO;
@Entity
@Table(name = "st_user")
public class UserDTO  extends BaseDTO{

	@Column(name = "firstName", length = 50)
	private String firstName;
	

	@Column(name = "lastName", length = 50)
	private String lastName;
	

	@Column(name = "loginId", length = 50)
	private String loginId;
	

	@Column(name = "password", length = 50)
	private String password;

	@Column(name = "dob")
	private Date dob;
	

	@Column(name = "roleId")
	private Long roleId;
	

	@Column(name = "roleName", length = 50)
	private String roleName;
	
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	
	

}
