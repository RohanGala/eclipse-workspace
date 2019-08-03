package com.armyservice.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.armyservice.entity.Soldier;
import com.armyservice.repository.SoldierDTO;

public class ArmyServiceImpl implements ArmyService {

	public SoldierDTO soldierDTO = new SoldierDTO();

	@Override
	public int add(int id, String name, int supervisorId) {

		Soldier soldier = new Soldier();
		soldier.setSupervisorId(supervisorId);
		soldier.setName(name);
		soldier.setId(id);

		// Assuming supervisorId as 0 for the the Army General
		if (supervisorId == 0) {
			if (soldierDTO.getArmyGeneral().getName() != null) {
				throw new IllegalArgumentException("Only one Army General Allowed");
			}

			soldierDTO.setArmyGeneral(soldier);

		} else if (supervisorId != 0 && soldierDTO.getMngrEmpMap().containsKey(supervisorId)) {
			Soldier supervisor = soldierDTO.getMngrEmpMap().get(supervisorId);
			supervisor.addSubordinates(Arrays.asList(id));

		} else {
			throw new IllegalArgumentException("Supervisor Does not exist");
		}

		soldierDTO.getMngrEmpMap().put(id, soldier);
		return id;

	}

	@Override
	public String get(int id) {
		if (soldierDTO.getMngrEmpMap().containsKey(id)) {
			return soldierDTO.getMngrEmpMap().get(id).getName();
		}

		return null;

	}

	@Override
	public Soldier getArmyGeneral() {
		return soldierDTO.getArmyGeneral();

	}

	@Override
	public List<String> getSubordinates(int id) {
		List<String> listOfSubordinates = new ArrayList<>();
		if (soldierDTO.getMngrEmpMap().containsKey(id)) {
			List<Integer> listOfSub = soldierDTO.getMngrEmpMap().get(id).getAllSubordinates();
			for (Integer sub : listOfSub) {
				listOfSubordinates.add(soldierDTO.getMngrEmpMap().get(sub).getName());
			}
		}
		return listOfSubordinates;
	}

	@Override
	public void remove(int id) throws Exception {

		if (soldierDTO.getMngrEmpMap().containsKey(id) && soldierDTO.getArmyGeneral() != null &&  soldierDTO.getArmyGeneral().getId() == id) {
			if(soldierDTO.getArmyGeneral().getAllSubordinates() != null && soldierDTO.getArmyGeneral().getAllSubordinates().size() > 1) {
				throw new IllegalArgumentException("Cant remove the Soldier as Soldier is Army General and since it has 2 subordinates there cant be 2 Army Genral");
			}
		}
		
		if (soldierDTO.getMngrEmpMap().containsKey(id)) {
			Soldier soldierToBeRemoved = soldierDTO.getMngrEmpMap().get(id);
			if (soldierDTO.getMngrEmpMap().containsKey(soldierToBeRemoved.getSupervisorId())) {
				Soldier supervisorOfSoldierToBeRemoved = soldierDTO.getMngrEmpMap()
						.get(soldierToBeRemoved.getSupervisorId());
				supervisorOfSoldierToBeRemoved.addSubordinates(soldierToBeRemoved.getAllSubordinates());
				supervisorOfSoldierToBeRemoved.removeSubordinates(Integer.valueOf(id));

			}
			else if(soldierDTO.getArmyGeneral() != null && soldierDTO.getArmyGeneral().getId() == id) {
				soldierDTO.setArmyGeneral(soldierDTO.getMngrEmpMap()
						.get(soldierToBeRemoved.getAllSubordinates().get(0)));
			}
			for (Integer subSoldiers : soldierToBeRemoved.getAllSubordinates()) {
				if (soldierDTO.getMngrEmpMap().containsKey(subSoldiers)) {
					Soldier subOrdinateSoldier = soldierDTO.getMngrEmpMap().get(subSoldiers);
					subOrdinateSoldier.setSupervisorId(soldierToBeRemoved.getSupervisorId());
				}
			}

			soldierDTO.getMngrEmpMap().remove(id);
		} else {
			throw new IllegalArgumentException("Soldier Does not exist");
		}
	}

}
