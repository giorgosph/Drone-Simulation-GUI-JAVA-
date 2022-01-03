package DroneSimulationGUI;

import java.util.ArrayList;
import java.util.Random;
import javafx.scene.control.ListView;

/**
 * @author gphil(28000050)
 */
public class DroneArena {
	private static int xmax, ymax;
	private static int valx, valy;
	public int wall;
	private Drone remd; 
	public Drone remt;
	private NormalDrone d;
	private MonsterDrone md;
	private TargetDrone td;
	public ArrayList<Drone> allDrones;
	private String score = "0";
	private double scoreCount = 0, scoreValue = 0;

	/**
	 * constructor for the drone arena
	 * contains the arraylists for the associated drones
	 * 
	 * @param width
	 * @param height dimensions
	 */
	public DroneArena(int width, int height) {
		xmax = width;
		ymax = height;
		allDrones = new ArrayList<Drone>(); // array constructor for drones
	}
	
	// getters for x and y size of the arena
	public static int getXSize() {
		return xmax;
	}

	public static int getYSize() {
		return ymax;
	}
	
	/**
	 * creates random values
	 */
	private void rNum() {
		Random rx = new Random();
		valx = rx.nextInt(xmax - 40);
		Random ry = new Random();
		valy = ry.nextInt(ymax - 40);
	}

	/**
	 * creates new NormalDrone with random position and direction
	 * adds the drone to the arraylist and the ListView 
	 * 
	 * @param mc 		MyCanvas
	 * @param Objects	ListView
	 */
	public void addDrone(MyCanvas mc, ListView<Drone> Objects) {
		if(maxNormalDrone() == 0) { 	//restriction of 1 NormalDrone
		rNum();
		d = new NormalDrone(valx, valy, Direction.getRandomDirection());
		allDrones.add(d);
		mc.showDrone(this);				//draws the drone in current arena
		Animation.fillList(Objects);
		}else {
			return;
		}

	}

	/**
	 * creates new NormalDrone with random position and direction
	 * adds the drone to the arraylist and the ListView 
	 * 
	 * @param mc 		MyCanvas
	 * @param Objects	ListView
	 */
	public void addMonsterDrone(MyCanvas mc, ListView<Drone> Objects) {
		if(maxMonsterDrone() <= 7) {	//restriction of 8 MonsterDrone
		rNum();
		md = new MonsterDrone(valx, valy, Direction.getRandomDirection());
		allDrones.add(md); 
		mc.showDrone(this);				//draws the drone in current arena
		Animation.fillList(Objects);	
		}else {
			return;
		}
	}

	/**
	 * creates new NormalDrone with random position and direction
	 * adds the drone to the arraylist and the ListView 
	 * 
	 * @param mc 		MyCanvas
	 * @param Objects	ListView
	 */
	public void addTargetDrone(MyCanvas mc, ListView<Drone> Objects) {
		if(maxTargetDrone() <= 1) {		//restriction of 8 MonsterDrone
		rNum();
		td = new TargetDrone(valx, valy, Direction.getRandomDirection());
		allDrones.add(td); 				
		mc.showDrone(this);				//draws the drone in current arena
		Animation.fillList(Objects);
		}else {
			return;
		}
		
	}

	/**
	 * checks if there are any tasks that must be done for each drone
	 */
	public void checkDrones() {
		for (Drone d : allDrones)
			d.checkDrone(this);		
	}

  /**
   * set warnings and counts the drones of a type
   * 
   * @return k how many drones have been created
   */
	public int maxNormalDrone() {
		int k = 0;
		for(Drone d : allDrones) {	
			if(d instanceof NormalDrone) {
				k++;
				Animation.warn = 1;
			}
		}
		return k;
	}
	
	/**
	   * set warnings and counts the drones of a type
	   * 
	   * @return k how many drones have been created
	   */
	public int maxMonsterDrone() {
		int k = 0;
		for(Drone d : allDrones) {	
			if(d instanceof MonsterDrone) {
				k++;
			}
		}
		return k;
	}
	
	/**
	   * set warnings and counts the drones of a type
	   * 
	   * @return k how many drones have been created
	   */
	public int maxTargetDrone() {
		int k = 0;
		for(Drone d : allDrones) {	
			if(d instanceof TargetDrone) {
				k++;
				Animation.warn = 2;
			}
		}
		return k;
	}
		
	/**
	 * declares the ratio of points gained by TargetDrone 
	 * with the number of MonsterDrones in the game
	 * 
	 * @return scoreValue ratio value
	 */
	private double setPointsValue() {
		int k = 0;
		for(Drone d: allDrones) {
			if(d instanceof MonsterDrone) {
				k++;  //counter of MonsterDrones
			}
		}
		switch(k) {
		case 0:
		case 1:
		case 2:
		case 3:
			scoreValue = 0; break;
		case 4:
			scoreValue = 1; break;
		case 5:
			scoreValue = 1.25; break;
		case 6:
			scoreValue = 1.63; break;
		case 7:
			scoreValue = 1.92; break;
		case 8:
			scoreValue = 2.65; break;
		}
		return scoreValue;
	}
	
	/**
	 * checks if a TargetDrone is touching a NormalDrone 
	 * 
	 * @param normal 	NormalDrone
	 * @return boolean
	 */
	public boolean checkPoint(Drone normal) {
		boolean ans = false;
		for (Drone d : allDrones) {
			if (d instanceof TargetDrone && d.isTouching(normal)) {
				remt = d;
				ans = true;
			}
		}
		return ans;
	}
	
	/**
	 * removes the TargetDrone and adds the points gained
	 */
	public void Point() {
		allDrones.remove(remt);
		scoreCount += setPointsValue();
		score = Integer.toString((int) scoreCount); //convert integer to string to be print
	}
	
	/**
	 * checks if a NormalDrone is touching a MonsterDrone 
	 * 
	 * @param monster MonsterDrone
	 * @return boolean
	 */
	public boolean checkDeath(Drone monster) {
		boolean ans = false;
		for (Drone d : allDrones) {
			if (d instanceof NormalDrone && d.isTouching(monster)) {
				remd = d;
				ans = true;
			}
		}
		return ans;
	}
	
	/**
	 * removes the NormalDrone
	 */
	public void Death() {
		allDrones.remove(remd);
		Animation.warn = 5;
	}
	
	/**
	 * reset the values of the score
	 */
	public void restartScore() {
		scoreCount = 0;
		score = "0";
	}
	
	/**
	 * getter function for String score
	 */
	public String getScore() {
		return score;
	}
		
	/**
	 * Test if drone can move to the next position
	 * 
	 * @param x
	 * @param y
	 * @return true if it can, false if not
	 */
	public boolean canMoveHere(int x, int y) {
		 if (x <= 0 || x >= xmax - 40) { //bountaries
				wall = 0; // defines if is a vertical or horizontal wall
			return false;
		} else if (y <= 0 || y >= ymax - 40) {
			wall = 1;
			return false;
		} else {
			return true;
		}
	}


	/**
	 * moves MonsterDrone and TargetDrone drones
	 */
	public void moveAllDrones(MyCanvas mc) {
		for (Drone d : allDrones) {  // loops through all drones
			if(d instanceof MonsterDrone || d instanceof TargetDrone ) {
				d.tryToMove();  //check for space availability
				mc.updateCanvas(this);
			}
		}
	}

	/**
	 * moves NormalDrone drone
	 */
 	public void moveNormal(MyCanvas mc, String str) {
 		for (Drone d : allDrones) {  // loops through all drones
			if(d instanceof NormalDrone ) {
				d.tryToMoveNormal(str);  //check for space availability
				mc.updateCanvas(this);
			}
 		}
 	}
 		
 }
