package DroneSimulationGUI;

/**
 * @author gphil(28000050)
 */
public abstract class Drone {
	protected int x;
	protected int y;
	protected Direction travel;
	protected DroneArena myArena = new DroneArena(DroneArena.getXSize(), DroneArena.getYSize());
	protected int speed;

	/**
	 * Constructor creates Drone giving them position and direction
	 * 
	 * @param a x-position
	 * @param b y-position
	 * @param d direction
	 */
	public Drone(int dx, int dy, Direction d) {
		x = dx;
		y = dy;
		travel = d;
	}

	/**
	 * getters for x and y position of the Drone
	 * 
	 * @return x, y
	 */
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	/**
	 * The Drone object test if there is space to move on the current Direction
	 * Otherwise it will change Directions
	 */
	public void tryToMove() {
		int tx = 0;
		int ty = 0;

		switch (travel) {
		case NE:
			ty = y - speed;
			tx = x + speed;
			if (myArena.canMoveHere(tx, ty)) {
				y -= speed;
				x += speed;
				break;
			} else {
				if (myArena.wall == 0) { // produce a logical movement
					travel = Direction.prevDirection(travel);
				} else {
					travel = Direction.nextDirection(travel);
				}
				tryToMove();
				break;
			}
		case ES:
			tx = x + speed;
			ty = y + speed;
			if (myArena.canMoveHere(tx, ty)) {
				x += speed;
				y += speed;
				break;
			} else {
				if (myArena.wall == 1) {
					travel = Direction.prevDirection(travel);
				} else {
					travel = Direction.nextDirection(travel);
				}
				tryToMove();
				break;
			}
		case SW:
			ty = y + speed;
			tx = x - speed;
			if (myArena.canMoveHere(tx, ty)) {
				y += speed;
				x -= speed;
				break;
			} else {
				if (myArena.wall == 0) {
					travel = Direction.prevDirection(travel);
				} else {
					travel = Direction.nextDirection(travel);
				}
				tryToMove();
				break;
			}
		case WN:
			tx = x - speed;
			ty = y - speed;
			if (myArena.canMoveHere(tx, ty)) {
				x -= speed;
				y -= speed;
				break;
			} else {
				if (myArena.wall == 1) {
					travel = Direction.prevDirection(travel);
				} else {
					travel = Direction.nextDirection(travel);
				}
				tryToMove();
				break;
			}
		}
	}

	/**
	 * Is the drone at this x,y position
	 * 
	 * @param sx x position
	 * @param sy y position
	 * @return true if drone is at sx,sy, false otherwise
	 */
	public boolean isHere(int sx, int sy) {
		if (sx == x && sy == y) {
			return true;
		} else
			return false;
	}

	/**
	 * checks if two drones are touching each other
	 * 
	 * @param sx x-position
	 * @param sy y-position
	 * @return
	 */
	private boolean isTouching(int sx, int sy) {
		int radius = 20; // radius for the extension of the image
		if ((x - sx < radius && x - sx > -radius) && (y - sy < radius && y - sy > -radius)) {
			return true;
		}
		return false;
	}

	/**
	 * Calls the previous method giving the appropriate type of drone
	 * 
	 * @param d type of drone
	 * @return the method with specific values
	 */
	public boolean isTouching(Drone d) {
		return isTouching(d.getX(), d.getY());
	}

	/**
	 * Declaration of the method that is used in NormalDrone class
	 * 
	 * @param str shows the direction
	 */
	public void tryToMoveNormal(String str) {
	}

	/**
	 * Declaration of abstract method
	 * 
	 * @param droneArena
	 */
	protected abstract void checkDrone(DroneArena droneArena);

	/**
	 * Declaration of abstract method
	 */
	public abstract String toString();
}