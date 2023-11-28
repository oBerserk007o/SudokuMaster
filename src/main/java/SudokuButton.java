package main.java;

import javax.swing.*;

public class SudokuButton {
    int x;
    int y;
    JButton button;
    Boolean[] possibleNumbers;
    int currentNumber;

    public SudokuButton(int x, int y, int currentNumber) {
        this.button = new JButton();
        this.x = x;
        this.y = y;
        this.currentNumber = currentNumber;
    }

    public void setPossibleNumbers(Boolean[] possibleNumbers) {
        this.possibleNumbers = possibleNumbers;
    }

    public Boolean[] getPossibleNumbers() {
        return possibleNumbers;
    }
}
