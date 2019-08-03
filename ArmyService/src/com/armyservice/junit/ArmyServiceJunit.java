package com.armyservice.junit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

import com.armyservice.service.ArmyServiceImpl;

public class ArmyServiceJunit {

	public ArmyServiceImpl armyServiceImpl = null;

	@BeforeClass
	void loadSoldierData() {
		ArmyServiceImpl armyServiceImpl = new ArmyServiceImpl();

		try (BufferedReader br = new BufferedReader(
				new FileReader("../ArmyService/src/com/armyservice/junit/Data.properties"))) {

			String line = br.readLine();

			while (line != null) {
				String[] a = line.split(",");
				assertEquals(Integer.valueOf(a[0]),
						armyServiceImpl.add(Integer.valueOf(a[0]), a[1], Integer.valueOf(a[2])));
				line = br.readLine();
			}
		} catch (IOException ex) {
			fail("Unable to Read File");
		}
		this.armyServiceImpl = armyServiceImpl;
	}

	@Test
	void addSoldiers() {

	}

	@Test
	void addSoldiersWithSuperVisorNotPresent() throws Exception {

		assertEquals(-1, armyServiceImpl.add(88, "SAMEER", 55));

	}

	@Test
	void addSoldierswithtwoArmyGenerals() throws Exception {

		assertEquals(0, armyServiceImpl.add(100, "ROHAN", 0));

	}

	@Test
	void getSoldier() throws Exception {

		if (armyServiceImpl.get(7) == null) {
			fail("Soldier not found with id 7");
		}
		assertEquals("G", armyServiceImpl.get(7));

	}

	@Test
	void getArmyGeneral() throws Exception {

		if (armyServiceImpl.get(7) == null) {
			fail("Soldier not found with id 7");
		}
		assertEquals("G", armyServiceImpl.get(7));

	}

	@Test
	void getSoldierNotFound() {

		assertEquals(null, armyServiceImpl.get(90));

	}

	@Test
	void removeSupervisor() throws NumberFormatException, Exception {

		armyServiceImpl.remove(1);
		assertEquals(null, armyServiceImpl.get(1));
	//	assertEquals(0, armyServiceImpl.mngrEmpMap.get(2).getSupervisorId());

	}

	@Test
	void removeSoldier() throws NumberFormatException, Exception {

		armyServiceImpl.remove(2);
		assertEquals(null, armyServiceImpl.get(2));

	}

	@Test
	void getAllSubordinates() throws NumberFormatException, Exception {

		assertEquals(new ArrayList<String>(Arrays.asList("C", "D", "E")), armyServiceImpl.getSubordinates(2));

	}

}
