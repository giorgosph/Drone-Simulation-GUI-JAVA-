package DroneSimulationGUI;

import javafx.scene.control.Label;
import javafx.util.Duration;
import javax.swing.JOptionPane;
import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * @author gphil(28000050)
 */
public class Animation extends Application {
	private int CanvasX = 750, CanvasY = 500;
	private MyCanvas mc;
	private static DroneArena myArena;
	public static AnimationTimer timer;
	ListView<Drone> Objects;
	Label l, w = new Label("Good Luck!!");
	public static int warn = 0;
	VBox vbList = new VBox();
	Scene scene;
	FadeTransition ft;
	private boolean N, S, E, W;

	/**
	 * insert the drones in ListView
	 * 
	 * @param objects drones
	 */
	public static void fillList(ListView<Drone> objects) {
		objects.getItems().clear(); // clear the list view
		for (Drone d : myArena.allDrones) {
			objects.getItems().add(d);
		}
	}

	/**
	 * moves NormalDrone while the user press the according key
	 */
	private void getKeyboard() {
		if (N)
			myArena.moveNormal(mc, "NORTH");
		if (S)
			myArena.moveNormal(mc, "SOUTH");
		if (E)
			myArena.moveNormal(mc, "EAST");
		if (W)
			myArena.moveNormal(mc, "WEST");
	}

	/**
	 * adds labels in the game
	 * 
	 * @param text   the text to be shown
	 * @param colour the colour to be shown(Black or Red)
	 */
	private void getLabels(String text, String colour) {
		vbList.getChildren().clear(); // clears the vBox
		w = new Label(text);
		if (colour == "BLACK") { // set colour
			w.setTextFill(Color.BLACK);
		} else if (colour == "RED") {
			w.setTextFill(Color.RED);
		}
		vbList.getChildren().addAll(Objects); // adds everything to vBox
		vbList.getChildren().add(w);
		ft = new FadeTransition(Duration.millis(4000), w); // fade the labels
		ft.setFromValue(0.99); // strength
		ft.setToValue(0); // strength
		ft.setCycleCount(1);
		ft.play();

	}

	/**
	 * shows the current score to the user
	 */
	private void updatePoints() {
		vbList.getChildren().clear(); // clears the vBox
		l = new Label("Your score is: " + myArena.getScore());
		l.setTextFill(Color.BLACK);
		vbList.getChildren().addAll(Objects);
		vbList.getChildren().addAll(l, w); // adds everything to vBox
	}

	/**
	 * create warnings to inform the user in different cases
	 */
	private void showWarnings() {
		switch (warn) {
		case 1:
			if (myArena.maxNormalDrone() >= 1) {
				getLabels("You can only insert one Normal Drone ", "RED"); // create the label and add it to vBox
			}
			warn = 0;
			break;
		case 2:
			if (myArena.maxTargetDrone() >= 2) {
				getLabels("You can only insert up to 2 Target Drones ", "RED");
			}
			warn = 0;
			break;
		case 3:
			if (myArena.maxMonsterDrone() >= 8) {
				getLabels("You can only insert up to 8 Monster Drones ", "RED");
			} else if (myArena.maxMonsterDrone() >= 4) {
				getLabels("Be careful you the game is getting harder rapidly ", "RED");
			}
			warn = 0;
			break;
		case 4:
			if (myArena.maxNormalDrone() == 0) {
				getLabels("You need to add a Normal Drone to start the game ", "RED");
				timer.stop();
			}
			warn = 0;
			break;
		case 5:
			getLabels("You lost, your score is: " + myArena.getScore(), "BLACK");
			JOptionPane.showMessageDialog(null, "You lost, your score is: " + myArena.getScore());
			myArena.restartScore();
			warn = 0;
			break;
		default:
			break;
		}
	}

	/**
	 * creates the message to be shown from a menu item
	 * 
	 * @param TStr title
	 * @param CStr descreption
	 */
	private void showMessage(String TStr, String CStr) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setHeight(CanvasY);
		alert.setWidth(CanvasX);
		alert.setTitle(TStr);
		alert.setHeaderText(null);
		alert.setContentText(CStr);
		alert.showAndWait();
	}

	/**
	 * calls show message for a specific menu item
	 */
	private void showAbout() {
		showMessage("About",
				"The Hungry Drone is a challenging game for everyone,"
						+ " and is produced by Giorgos Philippou. I hope you like it and have fun, at the "
						+ "first place it may be difficult but as you play you will improve your score."
						+ "Check the Help section for further instructions.\n\t\t\t GOOD LUCK!!! ");
	}

	/**
	 * calls show message for a specific menu item
	 */
	private void showHelp() {
		showMessage("Help",
				"To start the game you have to add a Normal Drone, "
						+ "you can move this drone using 'W', 'A', 'S' and 'D'.\n"
						+ "You can add up to 2 Target Drones and up to 8 Monster Drones. "
						+ "The more your Monster Drones the higher the point ratio you get,"
						+ " you can view this details in Ratio section.\n"
						+ "You can see the Drones' positions on the left-side list and under that"
						+ "your score will be shown, any warnings will also appear there.");
	}

	/**
	 * calls show message for a specific menu item
	 */
	private void showRatio() {
		showMessage("Ratio",
				"Speed:\n\t Your Drone:\t\t 2\n\t Monster Drone:\t 4\n\t"
						+ "Target Drone:\t\t 1\n\nPoints for x Monster Drones:\n\t 0-3:\t 0p\n\t 4:\t 1p"
						+ "\n\t 5:\t 1.25\n\t 6:\t 1.63p\n\t 7:\t 1.92p\n\t 8:\t 2.65p");
	}

	/**
	 * calls show message for a specific menu item
	 */
	private void showTips() {
		showMessage("Tips", "You may take consideraion of some tips.\n\t 1. Try to avoid the monsters"
				+ " first and then go for\n\t\t your food!\n\t 2. Start with lower number of monsters.\n\t 3. Try staying near the center of the Arena\n\t "
				+ " 4. If the specific level looks too challenging clear\n\t\t the arena and add the drones again!");
	}

	/**
	 * creates the menuBar, the menu and its items set the menu items on action
	 * 
	 * @return menuBar
	 */
	private MenuBar setMenu() {
		MenuBar menuBar = new MenuBar(); // create menu

		Menu mHelp = new Menu("Help"); // have entry for help

		MenuItem about = new MenuItem("About"); // create item
		about.setOnAction(e -> { // when user clicks
			showAbout(); // show message
		});

		MenuItem help = new MenuItem("Help");
		help.setOnAction(e -> {
			showHelp();
		});

		MenuItem ratio = new MenuItem("Ratio");
		ratio.setOnAction(e -> {
			showRatio();
		});

		MenuItem tips = new MenuItem("Tips");
		tips.setOnAction(e -> {
			showTips();
		});

		mHelp.getItems().addAll(about, help, ratio, tips); // add the items to the menu

		Menu mFile = new Menu("File");
		MenuItem mExit = new MenuItem("Exit");
		mExit.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent t) {
				System.exit(0); // quit program
			}
		});
		mFile.getItems().addAll(mExit);

		menuBar.getMenus().addAll(mFile, mHelp);

		return menuBar; // return the menu to be added
	}

	/**
	 * starts the stage taking everything together starts the timer
	 */
	public void start(Stage stagePrimary) throws Exception {
		stagePrimary.setTitle("The Hungry Drone");

		Group root = new Group();
		Canvas canvas = new Canvas(750, 500); // setting up the canvas
		root.getChildren().add(canvas);
		mc = new MyCanvas(canvas.getGraphicsContext2D(), CanvasX, CanvasY); // creating the canvas using the x and y and
																			// adding the drone arena
		myArena = new DroneArena(750, 500);
		mc.fillCanvas(CanvasX, CanvasY);

		timer = new AnimationTimer() { // timer for the Animation
			public void handle(long now) { // while is at start stage
				myArena.checkDrones();
				myArena.moveAllDrones(mc);
				fillList(Objects);
				updatePoints();
				getKeyboard();
				if (myArena.remt != null) { // if TargetDrone will be removed
					myArena.addTargetDrone(mc, Objects); // add a new one
					myArena.remt = null;
				}
				if (warn == 5) {
					showWarnings();
					timer.stop();
				}
			}
		};

		Objects = new ListView<>(); // create the ListView to show drones' information
		vbList.getChildren().addAll(Objects); // set up and add everything to vBox
		vbList.setAlignment(Pos.CENTER);
		vbList.setPadding(new Insets(0, 0, 0, 50));

		Button AddDrone_btn = new Button("Add Your Drone"); // button for user interaction
		AddDrone_btn.setOnAction(e -> { // when user press the button
			myArena.addDrone(mc, Objects);
			showWarnings();
		});
		AddDrone_btn.setMinSize(125, 50); // size for button
		AddDrone_btn.setMaxSize(125, 50);

		Button AddDeathDrone_btn = new Button("Add Monster Drone");
		AddDeathDrone_btn.setOnAction(e -> {
			myArena.addMonsterDrone(mc, Objects);
			warn = 3;
			showWarnings();
		});
		AddDeathDrone_btn.setMinSize(125, 50);
		AddDeathDrone_btn.setMaxSize(125, 50);

		Button AddSonicDrone_btn = new Button("Add Target Drone");
		AddSonicDrone_btn.setOnAction(e -> {
			myArena.addTargetDrone(mc, Objects);
			showWarnings();
		});
		AddSonicDrone_btn.setMinSize(125, 50);
		AddSonicDrone_btn.setMaxSize(125, 50);

		Button Start_btn = new Button("Start");
		Start_btn.setOnAction(e -> {
			timer.start(); // starts the timer
			warn = 4;
			showWarnings();
			N = false; // reset buttons to false
			E = false;
			S = false;
			W = false;
		});
		Start_btn.setMinSize(125, 50);
		Start_btn.setMaxSize(125, 50);

		Button Pause_btn = new Button("Pause");
		Pause_btn.setOnAction(e -> timer.stop());
		Pause_btn.setMinSize(125, 50);
		Pause_btn.setMaxSize(125, 50);

		Button Clear_btn = new Button("Clear Arena");
		Clear_btn.setOnAction(e -> {
			timer.stop();
			myArena.allDrones.clear(); // remove all drones
			myArena.restartScore(); // reset the score
			mc.updateCanvas(myArena); // redraw the empty arena
			N = false;
			E = false;
			S = false;
			W = false;
		});
		Clear_btn.setMinSize(125, 50);
		Clear_btn.setMaxSize(125, 50);

		HBox hbButtons = new HBox(20);
		hbButtons.setAlignment(Pos.CENTER);
		hbButtons.setPadding(new Insets(0, 0, 50, 0));
		hbButtons.getChildren().addAll(AddDrone_btn, AddDeathDrone_btn, AddSonicDrone_btn, Start_btn, Pause_btn,
				Clear_btn);

		BorderPane borderPane = new BorderPane(); // create borderpane to store all features
		borderPane.setTop(setMenu());
		borderPane.setCenter(root);
		borderPane.setBottom(hbButtons);
		borderPane.setLeft(vbList); // setting the formating

		scene = new Scene(borderPane, 1200, 700); // create the scene

		scene.setOnKeyPressed(new EventHandler<KeyEvent>() { // see if user press a key
			@Override
			public void handle(KeyEvent event) {
				switch (event.getCode()) {
				case W:
					N = true;
					break;
				case S:
					S = true;
					break;
				case A:
					W = true;
					break;
				case D:
					E = true;
					break;
				default:
					break;
				}
			}
		});

		scene.setOnKeyReleased(new EventHandler<KeyEvent>() { // see if user release a key
			@Override
			public void handle(KeyEvent event) {
				switch (event.getCode()) {
				case W:
					N = false;
					break;
				case S:
					S = false;
					break;
				case A:
					W = false;
					break;
				case D:
					E = false;
					break;
				default:
					break;
				}
			}
		});
		stagePrimary.setScene(scene); // set the scene to be shown
		stagePrimary.show(); // show the scene
		JOptionPane.showMessageDialog(null, "Please read the help section before you start the game"); // message for
																										// the user
																										// before the
																										// start of the
																										// game

	}

	public static void main(String[] args) {
		Application.launch(args); // launch the game
	}

}
