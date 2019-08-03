package com.armyservice.service;

import java.util.List;

import com.armyservice.entity.Soldier;

public interface ArmyService {
	int add(int id, String name, int supervisorId); // create a new soldier.

	String get(int id); // return all details of the soldier as a String.

	List<String> getSubordinates(int id); // return the names of subordinates

	void remove(int id) throws Exception; // Remove a soldier. subordinates will be assigned to the removed soldier's
							// supervisor.

	Soldier getArmyGeneral();  //get the army general
}