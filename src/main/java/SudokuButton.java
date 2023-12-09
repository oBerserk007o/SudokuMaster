package main.java;

import javax.swing.*;

public class SudokuButton {
    int x;
    int y;
    JButton button;
    int[] possibleNumbers;
    int currentNumber;

    public SudokuButton(int x, int y, int currentNumber) {
        this.button = new JButton();
        this.x = x;
        this.y = y;
        this.currentNumber = currentNumber;
    }

    public void setCurrentNumber(int currentNumber) {
        this.currentNumber = currentNumber;
    }

    public void setPossibleNumbers(int[] possibleNumbers) {
        this.possibleNumbers = possibleNumbers;
    }

    public int[] getPossibleNumbers() {
        return possibleNumbers;
    }

    public void updateText() {
        this.button.setText(String.valueOf(currentNumber));
    }
}
