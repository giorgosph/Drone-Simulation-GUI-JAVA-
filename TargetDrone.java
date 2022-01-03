package DroneSimulationGUI;

/**
 * @author gphil(28000050)
 */
public class TargetDrone extends Drone {
	private int ids; // unique id for each MonsterDrone
	private static int id = 0; // counter

	/**
	 * Constructor creates TargetDrone giving them position and directionS
	 * 
	 * @param dy x-position
	 * @param dx y-position
	 * @param d  direction
	 */
	public TargetDrone(int dx, int dy, Direction d) {
		super(dx, dy, d); // call of superclass
		id++;
		ids = id;
		speed = 1;
	}

	/**
	 * nothing to do at the moment
	 */
	@Override
	protected void checkDrone(DroneArena dar) {
	}

	/**
	 * Takes all the information in a String
	 */
	@Override
	public String toString() {
		return "Target Drone " + ids + " is at ( " + x / 10 + " , " + y / 10 + " ) moving at " + travel;
	}

}
