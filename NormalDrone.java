package DroneSimulationGUI;

public class NormalDrone extends Drone {
	private static String direct;

	/**
	 * Constructor creates TargetDrone giving them position and directionS
	 * 
	 * @param dy x-position
	 * @param dx y-position
	 * @param d  direction
	 */
	public NormalDrone(int dx, int dy, Direction d) {
		super(dx, dy, d); // call of superclass
		speed = 2;
	}

	/**
	 * The Drone object test if there is space to move on the current Direction
	 * 
	 * @param str String that specifies the direction from the user
	 */
	public void tryToMoveNormal(String str) {
		int tx = 0;
		int ty = 0;
		direct = str;
		switch (direct) {
		case "NORTH":
			ty = y - speed;
			if (myArena.canMoveHere(x, ty)) {
				y -= speed;
			}
			break;
		case "EAST":
			tx = x + speed;
			if (myArena.canMoveHere(tx, y)) {
				x += speed;
			}
			break;
		case "SOUTH":
			ty = y + speed;
			if (myArena.canMoveHere(x, ty)) {
				y += speed;
			}
			break;
		case "WEST":
			tx = x - speed;
			if (myArena.canMoveHere(tx, y)) {
				x -= speed;
			}
			break;
		case "NE":
			ty = y - speed;
			tx = x + speed;
			if (myArena.canMoveHere(tx, ty)) {
				y -= speed;
				x += speed;
			}
			break;
		case "ES":
			tx = x + speed;
			ty = y + speed;
			if (myArena.canMoveHere(tx, ty)) {
				x += speed;
				y += speed;
			}
			break;
		case "SW":
			ty = y + speed;
			tx = x - speed;
			if (myArena.canMoveHere(tx, ty)) {
				y += speed;
				x -= speed;
			}
			break;
		case "WN":
			tx = x - speed;
			ty = y - speed;
			if (myArena.canMoveHere(tx, ty)) {
				x -= speed;
				y -= speed;
			}
			break;
		}
	}

	/**
	 * Calling appropriate methods related with the drone
	 * 
	 * @param dar the current arena we are using
	 */
	@Override
	protected void checkDrone(DroneArena dar) {
		if (dar.checkPoint(this)) {
			dar.Point();
		}
	}

	/**
	 * Takes all the information in a String
	 */
	@Override
	public String toString() {
		return "Your Drone is at ( " + x / 10 + " , " + y / 10 + " ) moving at " + direct;
	}

}
