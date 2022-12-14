# Wordle in Java

## Overview

- Created an implementation of Wordle in Java
- Used OOP to create two runnable files `CLIWordle.java` and `GUIWordle.java` that separately implement command line and graphical interfaces using the game engine `Wordle.java`

## GUI Mechanics

- Only takes input if it is a valid five letter word, and the word hasn't been guessed yet
- Returns graphical implementation of result - the letter appears:
  - grey if it is not in the word
  - yellow if it is in the word, but in the wrong position
  - green if it is in the right position
- Uses words from the words.txt file to choose a random word
- Stores simple version of history so far that is reset on the reset button

![wordle gui](src/main/resources/gui_ss.png)
