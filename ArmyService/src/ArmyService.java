import java.util.List;

public interface ArmyService {
	int add(int id, String name, int supervisorId); // create a new soldier.

	String get(int id); // return all details of the soldier as a String.

	List<String> getSubordinates(int id); // return the names of subordinates

	void remove(int id); // Remove a soldier. subordinates will be assigned to the removed soldier's
							// supervisor.
}