package main.java;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class NumberPad implements ActionListener {
    // Initializing stuff
    SudokuBoard board;
    JFrame frame;
    JButton[] numbers = new JButton[9];
    JButton numberButton;
    Font comfortaa = Font.createFont(Font.TRUETYPE_FONT,
            new File("C:\\Users\\Dorno\\Desktop\\Coding projects\\Java\\MyFirstProject\\src\\resources\\Comfortaa-VariableFont_wght.ttf"));

    public NumberPad(SudokuBoard board) throws IOException, FontFormatException {
        this.board = board;

        // Setting up the frame
        frame = new JFrame("Number Pad");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(new Color(0, 0, 0));
        frame.setCursor(new Cursor(Cursor.HAND_CURSOR));
        frame.setLayout(new GridLayout(3, 3, 5, 5));
        frame.setSize(250, 272);
        frame.setResizable(false);

        // Creating and adding buttons
        for (int i = 0; i < 9; i++) {
            numberButton = new JButton(String.valueOf(i+1));
            numberButton.setBackground(new Color(200, 200, 200));
            numberButton.setFocusPainted(false);
            numberButton.addActionListener(this);
            numberButton.setBorder(null);
            numberButton.setSize(75, 75);
            numberButton.setFont(comfortaa.deriveFont(Font.BOLD, 25f));
            numbers[i] = numberButton;
            frame.add(numberButton);
        }

        frame.setVisible(true);
    }

    // Button action
    @Override
    public void actionPerformed(ActionEvent event) {
        if (board.selectedButton != null && board.selectedButton.currentNumber == 0) {
            for (int i = 0; i < 9; i++) {
                if (event.getSource() == numbers[i]) {
                    board.selectedButton.setCurrentNumber(i+1);
                    break;
                }
            }
        }
    }
}
