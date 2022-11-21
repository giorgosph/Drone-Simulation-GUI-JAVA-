# Drone Simulation GUI

### About
Drone Simulation GUI is a small game developed in eclipse using Java and JavaFX for the GUI. The project is the implementation of the console version "Drone Simulation" which can be found in my projects. Please refer to **"demo.mp4"** to observe the results and to **"DroneSimulationGUI.pdf"** for detailed information about the methodology and the development of the project.

### Instructions
The user can control the "normal drone" using 'W', 'A', 'S', and 'D' to move around the arena. The user can set the difficulty on his own by adding more enemies to the arena (max. 8) and his goal is to collect as many burgers as possible while avoiding interaction with the enemies.

There are several warnings to avoid bugs, such as there must be at least one "normal drone" to start the game, and others. The difficulty is proportional to the points that a burger gives to the user. For example, 0-3 monsters result in 0 points per burger, 4 monsters result in 1 point per burger, ... , and 8 monsters result in 2.65 points per burger.

The state of the arena is continuously shown on the left window stating the position of each object along with the direction in that is currently moving. In addition, there is a "help" section that provides information to the user, like ratios, instructions, and tips.
