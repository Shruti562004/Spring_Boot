package com.rays.dto;

import com.rays.common.BaseDTO;

public class DeviceDTO  extends BaseDTO{
	
	private String name;
	private String room;
	private boolean status;
	private int usage;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRoom() {
		return room;
	}
	public void setRoom(String room) {
		this.room = room;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public int getUsage() {
		return usage;
	}
	public void setUsage(int usage) {
		this.usage = usage;
	}
	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return null;
	}

}
