package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Wordle {

    private List<String> words = new ArrayList<String>();
    private int guesses = 0;
    private int index = 0;

    public Wordle() {
        initialiseWords();

    }

    public void initialiseWords() {
        // TODO: implement file reading from the text file in resources
        words.add(new String("hello"));
        words.add(new String("cakes"));
        words.add(new String("spike"));
        words.add(new String("water"));
        words.add(new String("bikes"));
        words.add(new String("bears"));
        words.add(new String("truck"));
        words.add(new String("paper"));
    }

    public int getIndex() {
        return index;
    }

    public void setIndex() {
        Random rd = new Random();
        index = rd.nextInt(0); // words.size() - 1
    }

    public List<String> getWords() {
        return words;
    }

    public static class Guess {
        private static List<Integer> result = new ArrayList<Integer>();

        public static List<Integer> returnGuess(List<String> words, String guess, int index) {
            guessCheck(words, guess, index);
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

        public static void guessCheck(List<String> words, String guess, int index) {
            String word = words.get(index);

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
        this.guesses += 1;
        return Guess.returnGuess(this.words, guess, this.index);
    }

    public int returnGuesses() {
        return this.guesses;
    }

}
