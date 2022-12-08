package org.example;

import javax.swing.*;
import javax.swing.border.Border;

import org.example.components.Word;

import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class GUIWordle {

    private JFrame frame;
    private JPanel wordpanel = new JPanel();
    private JPanel menupanel = new JPanel();
    private JTextArea historyLabel;
    private String historyLabelText = "Words: ";
    private Word word = new Word();
    private JButton resetButton;
    private JLabel statisticsLabel;
    private JTextField inputField;

    private Wordle engine = new Wordle();

    public GUIWordle() {

        //TODO: implement a way to view word history

        this.frame = new JFrame();

        frame.setLayout(new BorderLayout(10, 5));
        frame.setTitle("Wordle");
        frame.setSize(300, 300);
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

    public void generateHistory() {
        historyLabel = new JTextArea();
        historyLabel.setText(historyLabelText);
        historyLabel.setEditable(false);
        historyLabel.setLineWrap(true);
    }

    public void addHistory() {
        historyLabel.setText(historyLabelText);
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
                    String inp = inputField.getText();
                    renderWord(inp);
                    statisticsLabel.setText("Number of wins: " + Wordle.returnGuesses() + " \nNumber of tries: " + (Wordle.returnTries() - 1));
                    inputField.setText("");
                    historyLabelText += inp;
                    historyLabelText += ", ";
                    addHistory();
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
                historyLabelText = "Words: ";
                historyLabel.setText(historyLabelText);
            }
            
        };

        resetButton.addActionListener(resetAction);

        generateHistory();

        menupanel.add(historyLabel);
        menupanel.add(resetButton);
        menupanel.add(statisticsLabel);
        menupanel.add(inputField);
        GridLayout grid = new GridLayout(0, 1);
        menupanel.setLayout(grid);
        menupanel.setBackground(Color.LIGHT_GRAY);
        Border border = BorderFactory.createLineBorder(Color.BLACK);
        menupanel.setBorder(border);

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
