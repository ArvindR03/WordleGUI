package org.example;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Wordle {

    private List<String> words = new ArrayList<String>();
    private static int guesses = 0; // correct guesses
    private static int tries = 0;
    private int index = 0;

    public Wordle() {
        initialiseWords();
        setIndex();
    }

    public boolean checkIfWord(String word) {
        if (words.contains(word)) {
            return true;
        } else {
            return false;
        }
    }

    public void initialiseWords() {
        // TODO: implement file reading from the text file in resources
        /*words.add(new String("hello"));
        words.add(new String("cakes"));
        words.add(new String("spike"));
        words.add(new String("water"));
        words.add(new String("bikes"));
        words.add(new String("bears"));
        words.add(new String("truck"));
        words.add(new String("paper"));*/
        String filename = "src/main/resources/words.txt";
        BufferedReader br = null;
        String line = "";
        try {
            br = new BufferedReader(new FileReader(filename));
            while( (line = br.readLine()) != null){
                // System.out.println(line);
                if (line.length() == 5) {
                    words.add(line);
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Oops! Please check for the presence of file in the path specified.");
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("Oops! Unable to read the file.");
            e.printStackTrace();
        }
    }

    public int getIndex() {
        return index;
    }

    public void setIndex() {
        Random rd = new Random();
        index = rd.nextInt(words.size() - 1); // words.size() - 1
    }

    public List<String> getWords() {
        return words;
    }

    public String getWord() {
        return words.get(index);
    }

    public static class Guess {
        private static List<Integer> result = new ArrayList<Integer>();

        public static List<Integer> returnGuess(String word, String guess) {
            tries += 1;
            guessCheck(word, guess);
            if (checkCorrect(result)) {
                guesses += 1;
            }
            return result;
        }

        public static boolean checkCorrect(List<Integer> res) {
            for (int i = 0; i < res.size(); i++) {
                if (result.get(i) != 1) {
                    return false;
                } else {
                    continue;
                }
            }
            return true;
        }

        public static void guessCheck(String word, String guess) {

            result = new ArrayList<Integer>();

            for (int j = 0; j < guess.length(); j++) {
                if (guess.charAt(j) == word.charAt(j)) {
                    result.add(1);
                } else if (word.indexOf(guess.charAt(j)) > -1) {
                    result.add(0);
                } else {
                    result.add(-1);
                }
            }
        }
    }

    
    public List<Integer> getGuess(String guess) {
        List<Integer> result = Guess.returnGuess(getWord(), guess);
        return result;
    }

    public static int returnGuesses() {
        return guesses;
    }

    public static int returnTries() {
        return tries;
    }

}
