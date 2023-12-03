package main.java;

import java.awt.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, FontFormatException {
        SudokuBoard board = new SudokuBoard();
        board.checkSquare(0, 4, board.boxList, 0);
    }
}