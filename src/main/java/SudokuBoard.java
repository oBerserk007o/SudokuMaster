package main.java;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class SudokuBoard implements ActionListener {
    JFrame frame;
    JPanel panel1;
    JPanel[] boxPanels = new JPanel[9];
    JPanel bigPanel;
    JLabel label1;
    JButton[][] boxes;
    JButton solveButton;
    Font comfortaa = Font.createFont(Font.TRUETYPE_FONT,
            new File("C:\\Users\\Dorno\\Desktop\\Coding projects\\Java\\SudokuMaster\\src\\resources\\Comfortaa-VariableFont_wght.ttf"));

    public SudokuBoard() throws IOException, FontFormatException {
        // Setting up the frame
        frame = new JFrame("Sudoku");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(770, 800);
        frame.getContentPane().setBackground(new Color(0, 0, 0));
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
        solveButton.setBackground(new Color(200, 200, 200));
        solveButton.setFocusable(false);
        panel1.setBorder(null);
        panel1.add(label1);
        panel1.add(solveButton);

        // Setting up big panel
        bigPanel = new JPanel(new GridLayout(3, 3, 10, 10));
        bigPanel.setBackground(new Color(0, 0, 0));
        bigPanel.setSize(770, 770);

        // Creating box panels
        for (int i = 0; i < 9; i++) {
            boxPanels[i] = new JPanel(new GridLayout(3, 3, 5, 5));
            boxPanels[i].setBackground(new Color(0, 0, 0));
        }

        // Setting up boxes
        boxes = new JButton[9][9];
        for(int y = 0; y < 9; y++) {
            for(int x = 0; x < 9; x++) {
                boxes[x][y] = new JButton();
                boxes[x][y].setBackground(new Color(200, 200, 200));
                boxes[x][y].addActionListener(this);
                boxes[x][y].setFocusable(false);
                boxes[x][y].setBorder(null);
                boxes[x][y].setText(x + ", " + y);
                boxPanels[y].add(boxes[x][y]);
            }
            bigPanel.add(boxPanels[y]);
        }
        // Adding everything to the frame
        frame.add(panel1, BorderLayout.NORTH);
        frame.add(bigPanel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    // Setting up button action
    @Override
    public void actionPerformed(ActionEvent event) {
        System.out.println(event.getActionCommand());
    }
}