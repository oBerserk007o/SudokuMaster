package main.java;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;

import org.json.simple.*;
import org.json.simple.parser.*;

public class SudokuBoard implements ActionListener {
    // Initializing stuff
    JFrame frame;
    JPanel panel1;
    JPanel[] boxPanels = new JPanel[9];
    JPanel bigPanel;
    JLabel label1;
    SudokuButton box;
    SudokuButton[][] boxList = new SudokuButton[9][9];
    JButton solveButton;
    SudokuButton selectedButton;
    Font comfortaa = Font.createFont(Font.TRUETYPE_FONT,
            new File("C:\\Users\\Dorno\\Desktop\\Coding projects\\Java\\MyFirstProject\\src\\resources\\Comfortaa-VariableFont_wght.ttf"));

    // Colours
    final Color BLACK = new Color(0, 0, 0);
    final Color LIGHT_GREY = new Color(200, 200, 200);
    final Color LIGHT_BLUE = new Color(147, 160, 199);

    public SudokuBoard() throws IOException, FontFormatException {
        // Setting up the frame
        frame = new JFrame("Sudoku");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(770, 800);
        frame.getContentPane().setBackground(BLACK);
        frame.setIconImage(Toolkit.getDefaultToolkit()
                .getImage("C:\\Users\\Dorno\\Desktop\\Coding projects\\Java\\SudokuMaster\\src\\main\\resources\\sudoku_icon.png"));
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
                box = new SudokuButton(x, y,  0);
                box.button.setBackground(LIGHT_GREY);
                box.button.addActionListener(this);
                box.button.setFocusPainted(false);
                box.button.setBorder(null);
                box.button.setSize(75, 75);
                box.button.setFont(comfortaa.deriveFont(Font.BOLD, 25f));
                boxList[x][y] = box;
                boxPanels[y].add(box.button);
            }
            bigPanel.add(boxPanels[y]);
        }
        // Adding everything to the frame
        frame.add(panel1, BorderLayout.NORTH);
        frame.add(bigPanel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    // Function to write numbers to a row
    public void writeToRow(int row, SudokuButton[][] boxList, int[] numberArray) {
        int i = 0;
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                statement:
                if ((x / 3) == (row % 3) && (y / 3) == (row / 3)) {
                    if (numberArray[i] == 0) {
                        boxList[x][y].button.setText("");
                        i++;
                        break statement;
                    }
                    boxList[x][y].setCurrentNumber(numberArray[i]);
                    i++;
                }
            }
        }
    }

    // Function to check row for a certain number
    public boolean checkRow(int xPos, int yPos, SudokuButton[][] boxList, int num) {
        boolean bool = false;
        loop:
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                if (x % 3 == (xPos % 3) && y % 3 == (yPos % 3)) {
                    bool = boxList[x][y].currentNumber == num;
                    if (bool) break loop;
                }
            }
        }
        return bool;
    }

    // Function to check column for a certain number
    public boolean checkColumn(int xPos, int yPos, SudokuButton[][] boxList, int num) {
        boolean bool = false;
        loop:
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                if ((x / 3) == (xPos / 3) && (y / 3) == (yPos / 3)) {
                    bool = boxList[x][y].currentNumber == num;
                    if (bool) break loop;
                }
            }
        }
        return bool;
    }

    // Function to check square for a certain number
    public boolean checkSquare(int yPos, SudokuButton[][] boxList, int num) {
        boolean bool = false;
        for (int x = 0; x < 9; x++) {
            bool = boxList[x][yPos].currentNumber == num;
            if (bool) break;
        }
        return bool;
    }

    // Function to colour row
    public void colourRow(int xPos, int yPos, SudokuButton[][] boxList, Color colour) {
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                if (x % 3 == (xPos % 3) && y % 3 == (yPos % 3)) {
                    boxList[x][y].button.setBackground(colour);
                }
            }
        }
    }

    // Function to color column
    public void colourColumn(int xPos, int yPos, SudokuButton[][] boxList, Color colour) {
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                if ((x / 3) == (xPos / 3) && (y / 3) == (yPos / 3)) {
                    boxList[x][y].button.setBackground(colour);
                }
            }
        }
    }

    // Function to colour square
    public void colourSquare(int yPos, SudokuButton[][] boxList, Color colour) {
        for (int x = 0; x < 9; x++) {
            boxList[x][yPos].button.setBackground(colour);
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
        JButton button = (JButton) event.getSource();
        int newX = 0;
        int newY = 0;
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                if (boxList[x][y].button == button) {
                    selectedButton = boxList[x][y];
                    newX = x;
                    newY = y;
                    break;
                }
            }
        }
        colourRow(newX, newY, boxList, LIGHT_BLUE);
        colourColumn(newX, newY, boxList, LIGHT_BLUE);
        colourSquare(newY, boxList, LIGHT_BLUE);
        boxList[newX][newY].button.setBackground(new Color(105, 128, 199));
        oldX = newX;
        oldY = newY;
    }

    // Filling the board with a solvable set of numbers
    public void setupBoard() throws URISyntaxException, IOException, InterruptedException, ParseException {
        // Sending GET request to Dosuku API to get a new random board arrangement (thank you to them)
        HttpRequest getRequest = HttpRequest.newBuilder()
                .uri(new URI("https://sudoku-api.vercel.app/api/dosuku"))
                .GET()
                .build();
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse<String> getResponse = httpClient.send(getRequest, HttpResponse.BodyHandlers.ofString());

        // Copying the result to a JSON file
        FileWriter getResponseJSON =
                new FileWriter("C:\\Users\\Dorno\\Desktop\\Coding projects\\Java\\SudokuMaster\\src\\main\\resources\\get_response.json");
        getResponseJSON.flush();
        getResponseJSON.write(getResponse.body());
        getResponseJSON.close();

        // Setting up board values and board solution (an absolute mess (and probably as fast as a lethargic turtle))
        JSONObject grids_value = getJsonObject();
        JSONArray value = (JSONArray) grids_value.get("value");
        Object[] boardValuesJSONArray = value.toArray();
        String[] boardValuesArray = Arrays.toString(boardValuesJSONArray)
                .replace("[", "")
                .replace("]", "")
                .replace(" ", "")
                .split(",");
        int[][] boardValues = new int[9][9];

        JSONArray solution = (JSONArray) grids_value.get("solution");
        Object[] boardSolutionJSONArray = solution.toArray();
        String[] boardSolutionArray = Arrays.toString(boardSolutionJSONArray)
                .replace("[", "")
                .replace("]", "")
                .replace(" ", "")
                .split(",");
        int[][] boardSolution = new int[9][9];

        int k = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                boardValues[i][j] = Integer.parseInt(boardValuesArray[k]);
                boardSolution[i][j] = Integer.parseInt(boardSolutionArray[k]);
                k++;
            }
            writeToRow(i, boxList, boardValues[i]);
        }
    }

    private static JSONObject getJsonObject() throws IOException, ParseException {
        JSONParser jsonParser = new JSONParser();
        FileReader reader =
                new FileReader("C:\\Users\\Dorno\\Desktop\\Coding projects\\Java\\SudokuMaster\\src\\main\\resources\\get_response.json");

        JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);
        JSONObject newBoard = (JSONObject) jsonObject.get("newboard");
        JSONArray grids = (JSONArray) newBoard.get("grids");
        JSONObject grids_value = (JSONObject) grids.get(0);
        return grids_value;
    }
}
