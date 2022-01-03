package DroneSimulationGUI;

public class MonsterDrone extends Drone {
	private int ids; // unique id for each MonsterDrone
	private static int id = 0; // counter

	/**
	 * Constructor creates TargetDrone giving them position and directionS
	 * 
	 * @param dy x-position
	 * @param dx y-position
	 * @param d  direction
	 */
	public MonsterDrone(int dx, int dy, Direction d) {
		super(dx, dy, d); // call of the superclass
		id++;
		ids = id;
		speed = 4;
	}

	/**
	 * Calling appropriate methods related with the drone
	 * 
	 * @param dar the current arena we are using
	 */
	@Override
	protected void checkDrone(DroneArena dar) {
		if (dar.checkDeath(this)) {
			dar.Death();
		}
	}

	/**
	 * Takes all the information in a String
	 */
	@Override
	public String toString() {
		return "Monster Drone " + ids + " is at ( " + x / 10 + " , " + y / 10 + " ) moving at " + travel;
	}

}
