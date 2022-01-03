package DroneSimulationGUI;


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

/**
 * @author gphil(28000050)
 */
public class MyCanvas {
	private int xCanvasSize = 1300; 
	private int yCanvasSize = 750;
	private Image Drone = new Image(getClass().getResourceAsStream("drone.png"));
	private Image Monster = new Image(getClass().getResourceAsStream("monster.png"));
	private Image Target = new Image(getClass().getResourceAsStream("burger.png"));
	GraphicsContext gc;

	/**
	 * constructor assigns dimensions and graphic context
	 * 
	 * @param g
	 * @param xcs
	 * @param ycs
	 */
	public MyCanvas(GraphicsContext g, int xcs, int ycs) {
		gc = g;
		xCanvasSize = xcs;
		yCanvasSize = ycs;
	}

	/**
	 * creates the canvas
	 * 
	 * @param width
	 * @param height  dimensions
	 */
	public void fillCanvas(int width, int height) {
		gc.setFill(Color.ANTIQUEWHITE); 	//set colour background
		gc.fillRect(0, 0, width, height);	//set  size
		gc.setStroke(Color.BLACK);
		gc.strokeRect(0, 0, width, height);
	}
	
	/**
	 * redraw the arena with drones in appropriate position
	 * 
	 * @param myArena DroneArena
	 */
	public void updateCanvas(DroneArena myArena){
		gc.clearRect(0, 0, xCanvasSize, yCanvasSize);
		fillCanvas(xCanvasSize, yCanvasSize);
		showDrone(myArena); 	//draws the dornes
	}
	
	/**
	 * gets the positions to draw each drone with their image
	 * 
	 * @param myArena DroneArena
	 */
	public void showDrone(DroneArena myArena) {
		for (Drone d : myArena.allDrones) {  		//loops through all drones
			if(d instanceof NormalDrone) {
				gc.drawImage(Drone, d.getX(), d.getY(), 45, 45);	//draws the appropriate image
			}else if(d instanceof MonsterDrone) {					
				gc.drawImage(Monster,  d.getX(), d.getY(), 30, 30); //position and size
			}else if(d instanceof TargetDrone) {
				gc.drawImage(Target,  d.getX(), d.getY(), 25, 25);
			}
		}
	}
}
