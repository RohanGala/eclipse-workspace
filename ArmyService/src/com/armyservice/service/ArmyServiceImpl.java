package com.armyservice.service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.armyservice.entity.Soldier;

public class ArmyServiceImpl implements ArmyService {

	public Map<Integer, Soldier> mngrEmpMap = new HashMap<>();

	private static final Logger log = LoggerFactory.getLogger(ArmyServiceImpl.class);
	
	@Override
	public int add(int id, String name, int supervisorId) {

		Soldier soldier = new Soldier();
		soldier.setSupervisorId(supervisorId);
		soldier.setName(name);
		soldier.setId(id);
		if (supervisorId == 0) {
			for (Entry<Integer, Soldier> entry : mngrEmpMap.entrySet()) {
				Soldier s = entry.getValue();
				if(s.getSupervisorId() == 0) {
					log.error("Only one Army General Allowed");
					return 0;
				}
			}
			
			
		} else if (supervisorId != 0 && mngrEmpMap.containsKey(supervisorId)) {
			Soldier supervisor = mngrEmpMap.get(supervisorId);
			if (supervisor.getAllSubordinates() == null)
				supervisor.setAllSubordinates(new ArrayList<>());
			supervisor.getAllSubordinates().add(id);
		} else {
			log.error("Supervisor Does not exist");
			return 0;
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
						mngrEmpMap.put(subSoldiers, subOrdinateSoldier);
					}
				}

			

			mngrEmpMap.remove(id);
		} else {
			log.error("Soldier Does not exist");
		}
	}

}
