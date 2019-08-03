package com.armyservice.junit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import com.armyservice.service.ArmyServiceImpl;

@RunWith(JUnitPlatform.class)
public class ArmyServiceJunit{

	public ArmyServiceImpl armyServiceImpl = new ArmyServiceImpl();
	
	
	@BeforeEach
	public void setUp() {
		try (BufferedReader br = new BufferedReader(
				new FileReader("../ArmyService/src/com/armyservice/junit/Data.properties"))) {

			String line = br.readLine();

			while (line != null) {
				String[] a = line.split(",");
				assertEquals(Integer.valueOf(a[0]),
						getArmyServiceImpl().add(Integer.valueOf(a[0]), a[1], Integer.valueOf(a[2])));
				line = br.readLine();
			}
		} catch (IOException ex) {
			fail("Unable to Read File");
		}
	}
	 
	 

	@Test
	public void addSoldiers() {
		
	}

	@Test
	public void addSoldiersWithSuperVisorNotPresent() throws Exception {
		
		assertThrows(IllegalArgumentException.class, () -> {
			getArmyServiceImpl().add(88, "SAMEER", 55);
	    });

	}

	@Test
	public void addSoldierswithtwoArmyGenerals() throws Exception {
		assertThrows(IllegalArgumentException.class, () -> {
			getArmyServiceImpl().add(100, "ROHAN", 0);
	    });


	}

	@Test
	public void getSoldier() throws Exception {
		if (getArmyServiceImpl().get(7) == null) {
			fail("Soldier not found with id 7");
		}
		assertEquals("G", getArmyServiceImpl().get(7));

	}

	@Test
	public void getArmyGeneral() throws Exception {
		
		assertEquals("A", getArmyServiceImpl().getArmyGeneral().getName());

	}


	@Test
	public void getSoldierNotFound() {
		assertEquals(null, getArmyServiceImpl().get(90));

	}

	@Test
	public void removeSupervisor() throws Exception    {
		getArmyServiceImpl().remove(1);
		assertEquals(null, getArmyServiceImpl().get(1));
		assertEquals(0, getArmyServiceImpl().soldierDTO.mngrEmpMap.get(2).getSupervisorId());

	}
	
	@Test
	public void removeSupervisorWith2Subordinates() throws Exception    {
		getArmyServiceImpl().remove(1);
		assertEquals(null, getArmyServiceImpl().get(1));
		
		assertThrows(IllegalArgumentException.class, () -> {
			getArmyServiceImpl().remove(2);;
	    });

	}

	@Test
	public void removeSoldier() throws  Exception {
		getArmyServiceImpl().remove(2);
		assertEquals(null, getArmyServiceImpl().get(2));

	}

	@Test
	public void getAllSubordinates() throws NumberFormatException, Exception {
		assertEquals(new ArrayList<String>(Arrays.asList("C", "D", "E")), getArmyServiceImpl().getSubordinates(2));

	}



	public ArmyServiceImpl getArmyServiceImpl() {
		return armyServiceImpl;
	}



	public  void setArmyServiceImpl(ArmyServiceImpl armyServiceImpl) {
		this.armyServiceImpl = armyServiceImpl;
	}
	

}
