# Game Project

## Overview
This is a Java-based game project where players can interact with various game elements such as items, hiding places, and NPCs. The game includes features like walking, interacting, hiding, attacking, and fullscreen mode.

## Features
- **Player Movement**: Move the player using the keyboard.
- **Interactions**: Interact with items and hiding places.
- **Combat**: Attack NPCs.
- **Fullscreen Mode**: Toggle fullscreen mode.
- **Game States**: Manage different game states like playing and menu.

## Controls
- **Walk**: Q, D
- **Interact**: E
- **Hide/Stairs**: R
- **Attack**: P
- **Fullscreen**: F
- **Jump**: M
- **Run**: Shift

## Classes

### Main Class
- **`Main`**: Entry point of the game.

### Game States
-**`GameMenu`**: Manages the menu when the game is launched
- **`Playing`**: Manages the game state when the player is actively playing.

### Entities
- **`Player`**: Represents the player character.
- **`Entity`**: Base class for all entities in the game.
- **`NPCs`**: Base class for all enemies in the game. 
- **`Items`**: Represents items that can be picked up by the player.
- **`HidingPlaces`**: Represents hiding places where the player can hide from enemies.

## How to Run
1. Clone the repository.
2. Open the project in IntelliJ IDEA.
3. Run the `Main` class to start the game.

## Dependencies
- Java Development Kit (JDK)
- IntelliJ IDEA (recommended for development)
- JUnit: Used for unit testing. Make sure to include the appropriate JUnit dependency in your project.
- JaCoCo: Code coverage tool for tracking test coverage.
- GDD : [Game Design Document](https://docs.google.com/document/d/1OUo62EBrk1mNHxTaX_aceChuscdSlQPeAzwYNRJISFk/edit?tab=t.0#heading=h.31g0zqx0061o)

## Running Tests
To run the tests, you can use your IDE's built-in support for JUnit, or run them from the command line:
1. **JUnit**: Right-click on the test class or method in IntelliJ IDEA and select **Run**.
2. **JaCoCo**: To view code coverage, you can run tests with JaCoCo integration (instructions may depend on your build tool).

## Javadoc Documentation
The project includes Javadoc documentation for all classes and methods.

## Contributing
Contributions are welcome! Please fork the repository and submit a pull request.

## Contact
For any questions or issues, please open an issue on GitHub.
