package com.armyservice.entity;

import java.util.ArrayList;
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

	public void addSubordinates(List<Integer> list) {

		if (getAllSubordinates() == null)
			setAllSubordinates(new ArrayList<>());
		for (Integer soldierId : list) {
			getAllSubordinates().add(soldierId);
		}

	}

	public void removeSubordinates(Integer... soldierIds) throws Exception {
		for (Integer soldierId : soldierIds) {
			if (getAllSubordinates().contains(soldierId)) {
				getAllSubordinates().remove(soldierId);
			} else {
				throw new Exception("cannot Remove Subordinate as Subordinate does not exist");
			}
		}
	}

}
