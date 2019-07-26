import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map.Entry;

import org.junit.jupiter.api.Test;

public class ArmyServiceJunit {

	@Test
	void test() throws NumberFormatException, Exception {
		// fail("Not yet implemented");
		String str = "Junit is working fine";
		assertEquals("Junit is working fine", str);
		ArmyServiceImpl armyServiceImpl = new ArmyServiceImpl();
		//

		try (BufferedReader br = new BufferedReader(
				new FileReader("/home/ehfl/eclipse-workspace/ArmyService/src/Data.properties"))) {
			String line = br.readLine();

			while (line != null) {
				String[] a = line.split(",");
				armyServiceImpl.add(Integer.valueOf(a[0]), a[1], Integer.valueOf(a[2]));
				line = br.readLine();
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		System.out.println(armyServiceImpl.mngrEmpMap);
		for (Entry<Integer, Soldier> entry: armyServiceImpl.mngrEmpMap.entrySet()) 
        { 
			Integer id = entry.getKey(); 
			//System.out.print("ID:"+id +" ->");
			Soldier soldier = entry.getValue(); 
			System.out.println(id+"("+soldier.getName()+")" +" ->" +soldier.getAllSubordinates());
			
			
			
			
        }
		armyServiceImpl.remove(2);
		for (Entry<Integer, Soldier> entry: armyServiceImpl.mngrEmpMap.entrySet()) 
        { 
			Integer id = entry.getKey(); 
			//System.out.print("ID:"+id +" ->");
			Soldier soldier = entry.getValue(); 
			System.out.println(id+"("+soldier.getName()+")" +" ->" +soldier.getAllSubordinates());
			
			
			
			
        }
		

	}

}
