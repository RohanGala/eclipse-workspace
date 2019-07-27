package com.armyservice.entity;

import java.util.List;

public class Soldier {

	private int id;
	private String name;
	private int supervisorId;
	private List<Integer> allSubordinates;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSupervisorId() {
		return supervisorId;
	}

	public void setSupervisorId(int supervisorId) {
		this.supervisorId = supervisorId;
	}

	public List<Integer> getAllSubordinates() {
		return allSubordinates;
	}

	public void setAllSubordinates(List<Integer> allSubordinates) {
		this.allSubordinates = allSubordinates;
	}
	
	

}
