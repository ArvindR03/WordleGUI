package org.example;
import org.example.Wordle;
import org.example.Wordle.Guess;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.Year;
import java.util.List;

public class CLIWordle {

    private static Wordle engine = new Wordle();
    private static boolean win = false;

    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RESET = "\u001B[0m";

    static Exception InvalidInputException;
    
    public static Wordle createGame() {
        return new Wordle();
    }

    public static void main(String[] args) throws IOException {
        // engine = createGame();

        // safe as it exits if necessary in runGame()
        while (true) {
            win = false;
            runGame();
            engine.setIndex();
        }

        
    }

    // TODO: fix the error that the result list doesn't seem to change after first try
    public static void runGame() throws IOException {
        boolean wantsToPlay = !(askPlay());
        if (wantsToPlay == false) {
            System.exit(0);
        }
        while (win == false) {
            String guess = askGuess();
            List<Integer> result = checkGuess(guess);
            printGuess(result, guess);
            if (Guess.checkCorrect(result) == true) {
                System.out.println("Well done, you've guessed the word correctly!");
                win = true;
            }
            else {
                System.out.print("\nTry again...");
                continue;
            }
        }
    }

    public static void quitApp(boolean v) {
        if (v) {
            System.out.println("You attempted " + Integer.toString(engine.returnTries()) + " guess(es), and got " + Integer.toString(engine.returnGuesses()) + " correct.");
            System.exit(0);
        }
    }

    public static boolean askPlay() throws IOException {
        boolean invalid = true;
        BufferedReader b = new BufferedReader(new InputStreamReader(System.in));

        while (invalid) {
            System.out.println("Would you like to play a(nother) round? (y/n)");
            String check = b.readLine();
            if (check != null) {
                if (check.charAt(0) == 'y') {
                    invalid = false;
                } else if (check.charAt(0) == 'n') {
                    quitApp(true);;
                } else {
                    System.out.print("Sorry but this is an invalid input, please enter either y or n.");
                }
            } else {
                System.out.println("Please input something!");
            }

        }

        return invalid;

    }

    public static String askGuess() throws IOException {
        boolean invalid = true;
        String guess = "";
        BufferedReader b = new BufferedReader(new InputStreamReader(System.in));

        while ((guess == "") | (invalid)) {
            System.out.println("\nPlease guess a word (between 5 letters, lowercase):");
            guess = b.readLine();

            // TODO: add check to make sure that it is only characters from alphabet
            // TODO: catch invalid input exceptions
            if (guess == null) {
                System.out.println("Please input something!");
            } else if (guess.length() != 5) {
                System.out.println("Please input a 5 letter word!");
                continue;
            } else {
                guess = guess.toLowerCase();
                invalid = false;
            }
        
        }
        return guess;
    }

    public static List<Integer> checkGuess(String guess) {
        return Guess.returnGuess(engine.getWord(), guess);
    }

    public static void printGuess(List<Integer> result, String guess) {
        for (int i = 0; i < 5; i++) {
            char c = guess.charAt(i);
            int r = result.get(i);
            
            if (r == -1) {
                System.out.print(c);
            } else if (r == 0) {
                System.out.print(ANSI_YELLOW + c + ANSI_RESET);
            } else if (r == 1) {
                System.out.print(ANSI_GREEN + c + ANSI_RESET);
            }
        }
    }



}
