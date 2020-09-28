# GameofDice
The "Game of Dice" is a multiplayer game where N players roll a 6 faced dice in a round-robin fashion. Each time a player rolls the dice their points increase by the number (1 to 6) achieved by the roll.

The main file is [Main.java](https://github.com/bipin2806/GameofDiceAssignment/blob/master/GameofDice/src/com/greatlearning/Main.java).

Setup
-----

* Clone repository.

        https://github.com/bipin2806/GameofDiceAssignment.git

* In Eclipse, Run > Run Configurations...
* Enter <noOfPlayers> <noOfPoints> in 'Program Arguments' tab(Enter numbers of players and number of points to accumulate) and click Run
* Follow the instructions provided in console
e.g: Press key r followed by enter to roll dice.

Run using runnable JAR
-----

* In Eclipse, Export > Runnable JAR file
* Browse Export Destination, and select Main - GameofDice under Launch Configuration, Finish
* Open terminal in the export destination directory and execute

        >java -jar <JarFileName.jar> <noOfPlayers> <noOfPoints>
        Enter numbers of players and number of points to accumulate
* Follow the instructions provided in terminal
e.g: Press key r followed by enter to roll dice.
