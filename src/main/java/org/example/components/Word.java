package org.example.components;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class Word {
    
    private List<Letter> word = new ArrayList<Letter>();

    public Word() {

        for (int i = 0; i < 5; i++) {
            Letter letter = new Letter();
            word.add(letter);
        }

    }

    public List<Letter> getWord() {
        return this.word;
    }




}
