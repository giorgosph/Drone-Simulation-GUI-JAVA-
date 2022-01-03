package DroneSimulationGUI;

import java.util.Random;

/**
 * @author gphil(28000050)
 */
public enum Direction {
	NE, ES, SW, WN;

	/**
	 * 
	 * @return a random direction
	 */
	public static Direction getRandomDirection() {
		Random random = new Random();
		return values()[random.nextInt(values().length)];
	}

	/**
	 * 
	 * @param d current direction
	 * @return next direction
	 */
	public static Direction nextDirection(Direction d) {
		switch (d) {
		case NE:
			d = ES;
			break;
		case ES:
			d = SW;
			break;
		case SW:
			d = WN;
			break;
		case WN:
			d = NE;
			break;
		}
		return d;
	}

	/**
	 * 
	 * @param d current direction
	 * @return previous direction
	 */
	public static Direction prevDirection(Direction d) {
		switch (d) {
		case ES:
			d = NE;
			break;
		case SW:
			d = ES;
			break;
		case WN:
			d = SW;
			break;
		case NE:
			d = WN;
			break;
		}
		return d;
	}
}
