package com.armyservice.repository;

import java.util.HashMap;
import java.util.Map;

import com.armyservice.entity.Soldier;

public class SoldierDTO {
	public Map<Integer, Soldier> mngrEmpMap = new HashMap<>();
	public Soldier armyGeneral = new Soldier();
	public Map<Integer, Soldier> getMngrEmpMap() {
		return mngrEmpMap;
	}
	public void setMngrEmpMap(Map<Integer, Soldier> mngrEmpMap) {
		this.mngrEmpMap = mngrEmpMap;
	}
	public Soldier getArmyGeneral() {
		return armyGeneral;
	}
	public void setArmyGeneral(Soldier armyGeneral) {
		this.armyGeneral = armyGeneral;
	}
	
	
	
}
