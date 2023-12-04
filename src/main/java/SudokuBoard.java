package main.java;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class SudokuBoard implements ActionListener {
    // Swing stuff
    JFrame frame;
    JPanel panel1;
    JPanel[] boxPanels = new JPanel[9];
    JPanel bigPanel;
    JLabel label1;
    SudokuButton box;
    JButton[][] boxList = new JButton[9][9];
    JButton solveButton;
    Font comfortaa = Font.createFont(Font.TRUETYPE_FONT,
            new File("C:\\Users\\Dorno\\Desktop\\Coding projects\\Java\\MyFirstProject\\src\\resources\\Comfortaa-VariableFont_wght.ttf"));


    // Colours
    final Color BLACK = new Color(0, 0, 0);
    final Color LIGHT_GREY = new Color(200, 200, 200);
    final Color GREEN = new Color(77, 163, 87);

    public SudokuBoard() throws IOException, FontFormatException {
        // Setting up the frame
        frame = new JFrame("Sudoku");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(770, 800);
        frame.getContentPane().setBackground(BLACK);
        frame.setIconImage(Toolkit.getDefaultToolkit()
                .getImage("C:\\Users\\Dorno\\Desktop\\Coding projects\\Java\\MyFirstProject\\src\\resources\\sudoku_icon.png"));
        frame.setLayout(new BorderLayout(0, 10));
        frame.setResizable(false);
        frame.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Setting up player score display
        panel1 = new JPanel();
        label1 = new JLabel("*Insert player score here somehow*");
        label1.setFont(comfortaa.deriveFont(Font.BOLD, 15f));
        solveButton = new JButton("Solve");
        solveButton.setBackground(LIGHT_GREY);
        solveButton.setFont(comfortaa.deriveFont(Font.BOLD, 15f));
        solveButton.setFocusable(false);
        panel1.setBorder(null);
        panel1.add(label1);
        panel1.add(solveButton);

        // Setting up big panel
        bigPanel = new JPanel(new GridLayout(3, 3, 10, 10));
        bigPanel.setBackground(BLACK);
        bigPanel.setSize(725, 735);

        // Creating box panels
        for (int i = 0; i < 9; i++) {
            boxPanels[i] = new JPanel(new GridLayout(3, 3, 5, 5));
            boxPanels[i].setBackground(BLACK);
        }

        // Setting up boxes
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                box = new SudokuButton(x, y,  -1);
                box.button.setBackground(LIGHT_GREY);
                box.button.addActionListener(this);
                box.button.setFocusPainted(false);
                box.button.setBorder(null);
                box.button.setText(x + "," + y);
                box.button.setSize(75, 75);
                box.button.setFont(comfortaa.deriveFont(Font.BOLD, 15f));
                boxList[x][y] = box.button;
                boxPanels[y].add(box.button);
            }
            bigPanel.add(boxPanels[y]);
        }
        // Adding everything to the frame
        frame.add(panel1, BorderLayout.NORTH);
        frame.add(bigPanel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    // Function to check row for a certain number
    public void checkRow(int xPos, int yPos, JButton[][] boxList, int num) {
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                if (x % 3 == (xPos % 3) && y % 3 == (yPos % 3)) {
                    boxList[x][y].setBackground(GREEN);
                }
            }
        }
    }

    // Function to check column for a certain number
    public void checkColumn(int xPos, int yPos, JButton[][] boxList, int num) {
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                if ((x / 3) == (xPos / 3) && (y / 3) == (yPos / 3)) {
                    boxList[x][y].setBackground(GREEN);
                }
            }
        }
    }

    // Function to check square for a certain number
    public void checkSquare(int yPos, JButton[][] boxList, int num) {
        for (int x = 0; x < 9; x++) {
            boxList[x][yPos].setBackground(GREEN);
        }
    }

    // Function to colour row
    public void colourRow(int xPos, int yPos, JButton[][] boxList, Color colour) {
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                if (x % 3 == (xPos % 3) && y % 3 == (yPos % 3)) {
                    boxList[x][y].setBackground(colour);
                }
            }
        }
    }

    // Function to color column
    public void colourColumn(int xPos, int yPos, JButton[][] boxList, Color colour) {
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                if ((x / 3) == (xPos / 3) && (y / 3) == (yPos / 3)) {
                    boxList[x][y].setBackground(colour);
                }
            }
        }
    }

    // Function to colour square
    public void colourSquare(int yPos, JButton[][] boxList, Color colour) {
        for (int x = 0; x < 9; x++) {
            boxList[x][yPos].setBackground(colour);
        }
    }

    // Setting up button action
    public int oldX = -1;
    public int oldY = -1;
    @Override
    public void actionPerformed(ActionEvent event) {
        if (oldX != -1 && oldY != -1) {
            colourRow(oldX, oldY, boxList, LIGHT_GREY);
            colourColumn(oldX, oldY, boxList, LIGHT_GREY);
            colourSquare(oldY, boxList, LIGHT_GREY);
        }
        int newX = Integer.parseInt(event.getActionCommand().split(",")[0]);
        int newY = Integer.parseInt(event.getActionCommand().split(",")[1]);
        System.out.println(event.getActionCommand());
        colourRow(newX, newY, boxList, GREEN);
        colourColumn(newX, newY, boxList, GREEN);
        colourSquare(newY, boxList, GREEN);
        oldX = newX;
        oldY = newY;
    }
}
