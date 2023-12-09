package main.java;

import org.json.simple.parser.ParseException;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class Main {
    public static void main(String[] args) throws
            IOException, FontFormatException, URISyntaxException, InterruptedException, ParseException {
        SudokuBoard board = new SudokuBoard();
        board.setupBoard();
    }
}