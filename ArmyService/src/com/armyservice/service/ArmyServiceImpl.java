package com.armyservice.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.armyservice.entity.Soldier;

public class ArmyServiceImpl implements ArmyService {

	public Map<Integer, Soldier> mngrEmpMap = new HashMap<>();
	public Soldier armyGeneral = new Soldier();

	private static final Logger log = LoggerFactory.getLogger(ArmyServiceImpl.class);

	@Override
	public int add(int id, String name, int supervisorId) {

		Soldier soldier = new Soldier();
		soldier.setSupervisorId(supervisorId);
		soldier.setName(name);
		soldier.setId(id);
		// Assuming supervisorId as 0 for the the Army General
		if (supervisorId == 0) {
			if (armyGeneral.getName() != null) {
				throw new IllegalArgumentException("Only one Army General Allowed");
			}

			armyGeneral = soldier;

		} else if (supervisorId != 0 && mngrEmpMap.containsKey(supervisorId)) {
			Soldier supervisor = mngrEmpMap.get(supervisorId);
			if (supervisor.getAllSubordinates() == null)
				supervisor.setAllSubordinates(new ArrayList<>());
			supervisor.getAllSubordinates().add(id);
		} else {
			throw new IllegalArgumentException("Supervisor Does not exist");
		}

		mngrEmpMap.put(id, soldier);
		return id;

	}

	@Override
	public String get(int id) {
		if (mngrEmpMap.containsKey(id)) {
			return mngrEmpMap.get(id).getName();
		}

		return null;

	}

	@Override
	public Soldier getArmyGeneral() {
		return armyGeneral;

	}

	@Override
	public List<String> getSubordinates(int id) {
		List<String> listOfSubordinates = new ArrayList<>();
		if (mngrEmpMap.containsKey(id)) {
			List<Integer> listOfSub = mngrEmpMap.get(id).getAllSubordinates();
			for (Integer sub : listOfSub) {
				listOfSubordinates.add(mngrEmpMap.get(sub).getName());
			}
		}
		return listOfSubordinates;
	}

	@Override
	public void remove(int id) {

		if (mngrEmpMap.containsKey(id)) {
			Soldier soldierToBeRemoved = mngrEmpMap.get(id);
			if (mngrEmpMap.containsKey(soldierToBeRemoved.getSupervisorId())) {
				Soldier supervisorOfSoldierToBeRemoved = mngrEmpMap.get(soldierToBeRemoved.getSupervisorId());
				supervisorOfSoldierToBeRemoved.getAllSubordinates().addAll(soldierToBeRemoved.getAllSubordinates());
				supervisorOfSoldierToBeRemoved.getAllSubordinates().remove(Integer.valueOf(id));

			}
			for (Integer subSoldiers : soldierToBeRemoved.getAllSubordinates()) {
				if (mngrEmpMap.containsKey(subSoldiers)) {
					Soldier subOrdinateSoldier = mngrEmpMap.get(subSoldiers);
					subOrdinateSoldier.setSupervisorId(soldierToBeRemoved.getSupervisorId());
				}
			}

			mngrEmpMap.remove(id);
		} else {
			log.error("Soldier Does not exist");
		}
	}

}
