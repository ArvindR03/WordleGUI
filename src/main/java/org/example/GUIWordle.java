package org.example;

import javax.swing.*;

import org.example.components.Word;

import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class GUIWordle {

    private JFrame frame;
    private JPanel wordpanel = new JPanel();
    private JPanel menupanel = new JPanel();
    private JPanel innermenupanel = new JPanel();
    private Word word = new Word();
    private JButton resetButton;
    private JLabel statisticsLabel;
    private JTextField inputField;

    private Wordle engine = new Wordle();

    public GUIWordle() {

        this.frame = new JFrame();

        frame.setLayout(new BorderLayout(10, 5));
        frame.setTitle("Wordle");
        frame.setSize(300, 200);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        wordpanel.setOpaque(true);
        wordpanel.setBackground(Color.WHITE);

        for (int i = 0; i < word.getWord().size(); i++) {
            JLabel letter = word.getWord().get(i).getLabel();
            wordpanel.add(letter);
        }

        frame.add(wordpanel);
        renderMenu();

        // this.renderWord("hello"); just to chekc it works
        resetWord();

        frame.setVisible(true);

    }

    public void renderMenu() {
        // onetime call

        resetButton = new JButton("Reset");
        statisticsLabel = new JLabel("Number of wins: " + Wordle.returnGuesses() + " \nNumber of tries: " + Wordle.returnTries(), SwingConstants.CENTER);
        inputField = new JTextField();
        inputField.setSize(100, 10);
        inputField.setFont(new Font("Arial", Font.PLAIN, 14));

        ActionListener checkAction = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (inputField.getText().length() == 5) {
                    renderWord(inputField.getText());
                    statisticsLabel.setText("Number of wins: " + Wordle.returnGuesses() + " \nNumber of tries: " + (Wordle.returnTries() - 1));
                    inputField.setText("");
                }
            }
            
        };


        inputField.addActionListener(checkAction);

        ActionListener resetAction = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                resetWord();
                inputField.setText("");
                engine.setIndex();
            }
            
        };

        resetButton.addActionListener(resetAction);

        menupanel.add(resetButton);
        menupanel.add(statisticsLabel);
        menupanel.add(inputField);
        GridLayout grid = new GridLayout(0, 1);
        menupanel.setLayout(grid);

        frame.add(menupanel, BorderLayout.SOUTH);

    }

    public void renderWord(String input) {

        List<Integer> result = engine.getGuess(input);
        
        for (int i = 0; i < word.getWord().size(); i++) {
            char c = input.charAt(i);
            int res = result.get(i);
            word.getWord().get(i).setLetter(c, res);
        }
    }

    public void resetWord() {
        renderWord("_____");
    }
    
    public static void main(String[] args) {
        new GUIWordle();
    }

}
