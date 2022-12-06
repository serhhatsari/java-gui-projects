package PegGame;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * PegSolitaire is a class to represent PegSolitaire Game
 * 
 * @author Serhat Sarı  
 * @since  28-01-2022
 * 
 */

/**
 * PegSolitaire is a class to represent PegSolitaire Game
 */
public class PegSolitaire implements PegSolitaireInterface, Cloneable {

    /**
     * Default constructer of PegSolitaire
     */
    public PegSolitaire() {
        chooseBoard();
    }

    /**
     * Clones the PegSolitaire object and returns the clone
     * 
     * @return The clone of the PegSolitaire object.
     */
    @Override
    public PegSolitaire clone() throws CloneNotSupportedException {

        PegSolitaire cloneObj = null;
        try {
            cloneObj = (PegSolitaire) super.clone();

            for (int i = 0; i < moves.size(); i++) {
                cloneObj.moves.add(moves.get(i));
            }

        } catch (CloneNotSupportedException e) {
            System.out.println(e.toString());
        }

        return cloneObj;
    }

    /**
     * This function is used to print the game information
     * 
     * @return The string representation of the game.
     */
    @Override
    public String toString() {

        StringBuffer gameInfo = new StringBuffer();
        gameInfo.append(boardType + "\n");
        gameInfo.append(rowNumber + "\n");
        gameInfo.append(colNumber + "\n");

        for (int i = 0; i < rowNumber; i++) {
            for (int k = 0; k < colNumber; k++) {
                gameInfo.append(board[i][k]);
            }
        }
        gameInfo.append("\n");

        for (int i = 0; i < moves.size(); i++) {
            gameInfo.append(moves.get(i));
        }

        gameInfo.append("\n");

        return gameInfo.toString();
    }

    /**
     * Save the current game to the file
     * 
     * @param filename Name of the file
     */
    @Override
    public void saveGame(String filename) {

        PrintWriter writeFile;

        try {
            writeFile = new PrintWriter(filename);

            writeFile.println(this.toString());

            writeFile.close();

        } catch (FileNotFoundException e) {

            System.out.println(e.toString());

        }
    }

    /**
     * Load a game from a file
     * 
     * @param filename Name of the file
     */
    @Override
    public void loadGame(String filename) {

        int lineNum = 0, i, k, j;

        try {
            File fileObj = new File(filename);

            Scanner readFile = new Scanner(fileObj);

            while (readFile.hasNextLine()) {

                String line = readFile.nextLine();

                if (lineNum == 0) {

                    boardType = Integer.parseInt(line);
                }

                else if (lineNum == 1) {

                    rowNumber = Integer.parseInt(line);
                }

                else if (lineNum == 2) {

                    colNumber = Integer.parseInt(line);
                }

                else if (lineNum == 3) {

                    board = new int[rowNumber][colNumber];

                    j = 0;
                    for (i = 0; i < rowNumber; i++) {
                        for (k = 0; k < colNumber; k++) {
                            board[i][k] = Character.getNumericValue(line.charAt(j));
                            j++;
                        }
                    }

                }

                else if (lineNum == 4) {
                    moves.clear();

                    StringBuffer takeMove = new StringBuffer();
                    k = 0;

                    for (i = 0; i < line.length(); i++) {

                        k++;
                        takeMove.append(line.charAt(i));

                        if (k == 3) {
                            moves.add(takeMove.toString());
                            takeMove.setLength(0);
                            k = 0;
                        }
                    }

                }
                lineNum++;
            }

            pegBoard.setLayout(new GridLayout(rowNumber, colNumber));
            paintScreen();

            if (endGame()) {
                paintScreen();
                JFrame fr = new JFrame();
                JOptionPane.showMessageDialog(fr, "CONGRATULATIONS! GAME HAS FINISHED. YOUR SCORE: " + numberOfPegs());
                fr.dispose();
            }

            readFile.close();

        } catch (FileNotFoundException e) {
            System.out.println(e.toString());

        }

    }

    /**
     * This function will count the number of pegs in the board
     * 
     * @return The number of pegs in the board.
     */
    @Override
    public int numberOfPegs() {

        int numOfPegs = 0;
        // this loop will count the number of pegs in the board
        for (int i = 0; i < rowNumber; i++) {

            for (int k = 0; k < colNumber; k++) {

                if (board[i][k] == 1) {
                    numOfPegs++;

                }
            }
        }
        return numOfPegs;
    }

    /**
     * Number of pegs taken in the game
     * 
     * @return Number of Pegs Taken
     */
    @Override
    public int numberOfPegsTaken() {

        return numberOfEmpty() - 1;
    }

    /**
     * Number of Empty cells in the game
     * 
     * @return Number of Empty Cells
     */
    @Override
    public int numberOfEmpty() {

        int numOfEmpty = 0;
        for (int i = 0; i < rowNumber; i++) {

            for (int k = 0; k < colNumber; k++) {

                if (board[i][k] == 2) {
                    numOfEmpty++;

                }
            }
        }
        return numOfEmpty;
    }

    /**
     * Fill the grid layout with cells
     * 
     */
    @Override
    public void fillBoard() {
        for (int i = 0; i < rowNumber; i++) {
            for (int k = 0; k < colNumber; k++) {

                JButton btn = new JButton();
                btn.setToolTipText(Integer.toString(i) + Integer.toString(k));

                btn.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        currentCell = btn.getToolTipText();
                        popupmenu.show(pegBoard, btn.getX() + 20, btn.getY() + 40);

                    }
                });

                if (board[i][k] == 0) {
                    btn.setBackground(Color.GRAY);
                    btn.setEnabled(false);
                    pegBoard.add(btn);
                } else if (board[i][k] == 1) {
                    btn.setBackground(Color.GREEN);
                    pegBoard.add(btn);
                } else if (board[i][k] == 2) {
                    btn.setBackground(Color.WHITE);
                    pegBoard.add(btn);
                }
            }
        }
    }

    /**
     * Check move if it is valid or not
     * 
     * @param row       Row index of the move
     * @param col       Column index of the move
     * @param direction Direction of the move
     * @return True or False
     */
    @Override
    public Boolean checkMove(int row, int col, char direction) {

        if (board[row][col] == 1) {
            if (direction == 'u') {
                if (row - 2 >= 0) {
                    if (board[row - 1][col] == 1) {
                        if (board[row - 2][col] == 2) {

                            return true;
                        }
                    }
                }

            } else if (direction == 'd') {
                if (row + 2 < board.length) {
                    if (board[row + 1][col] == 1) {
                        if (board[row + 2][col] == 2) {

                            return true;
                        }
                    }
                }

            } else if (direction == 'r') {
                if (col + 2 < board[0].length) {
                    if (board[row][col + 1] == 1) {
                        if (board[row][col + 2] == 2) {

                            return true;
                        }
                    }
                }

            } else if (direction == 'l') {
                if (col - 2 >= 0) {
                    if (board[row][col - 1] == 1) {
                        if (board[row][col - 2] == 2) {

                            return true;
                        }
                    }
                }

            }
        }
        return false;
    }

    /**
     * Check if there is any move left
     * 
     * @return True or False
     */
    @Override
    public Boolean endGame() {

        // Checking if there is any valid move left
        for (int i = 0; i < rowNumber; i++) {
            for (int k = 0; k < colNumber; k++) {
                if (checkMove(i, k, 'u')) {
                    return false;
                } else if (checkMove(i, k, 'd')) {
                    return false;
                } else if (checkMove(i, k, 'r')) {
                    return false;
                } else if (checkMove(i, k, 'l')) {
                    return false;
                }
            }
        }

        return true;

    }

    /**
     * Reset the current board
     * 
     */
    @Override
    public void resetGame() {

        moves.clear();

        pegBoard.removeAll();
        initializeBoard(boardType);
        fillBoard();
        pegBoard.revalidate();
        pegBoard.repaint();

        numOfPegs.removeAll();
        numOfPegs.setText("SCORE (Number of Pegs):" + numberOfPegs());
        numOfPegs.revalidate();
        numOfPegs.repaint();

        numOfEmpty.removeAll();
        numOfEmpty.setText("Number of Empty Cells: " + numberOfEmpty());
        numOfEmpty.revalidate();
        numOfEmpty.repaint();

        numOfPegsTaken.removeAll();
        numOfPegsTaken.setText("Number of Pegs Taken: " + numberOfPegsTaken());
        numOfPegsTaken.revalidate();
        numOfPegsTaken.repaint();
    }

    /**
     * Go back to one step before
     */
    @Override
    public void undoMove() {

        if (moves.size() != 0) {

            String lastMove = moves.get(moves.size() - 1);

            if (lastMove.charAt(2) == 'u') {

                board[Character.getNumericValue(lastMove.charAt(0))][Character.getNumericValue(lastMove.charAt(1))] = 1;
                board[Character.getNumericValue(lastMove.charAt(0)) - 1][Character
                        .getNumericValue(lastMove.charAt(1))] = 1;
                board[Character.getNumericValue(lastMove.charAt(0)) - 2][Character
                        .getNumericValue(lastMove.charAt(1))] = 2;

            }

            else if (lastMove.charAt(2) == 'd') {

                board[Character.getNumericValue(lastMove.charAt(0))][Character.getNumericValue(lastMove.charAt(1))] = 1;
                board[Character.getNumericValue(lastMove.charAt(0)) + 1][Character
                        .getNumericValue(lastMove.charAt(1))] = 1;
                board[Character.getNumericValue(lastMove.charAt(0)) + 2][Character
                        .getNumericValue(lastMove.charAt(1))] = 2;

            } else if (lastMove.charAt(2) == 'r') {

                board[Character.getNumericValue(lastMove.charAt(0))][Character.getNumericValue(lastMove.charAt(1))] = 1;
                board[Character.getNumericValue(lastMove.charAt(0))][Character
                        .getNumericValue(lastMove.charAt(1)) + 1] = 1;
                board[Character.getNumericValue(lastMove.charAt(0))][Character
                        .getNumericValue(lastMove.charAt(1)) + 2] = 2;

            } else if (lastMove.charAt(2) == 'l') {

                board[Character.getNumericValue(lastMove.charAt(0))][Character.getNumericValue(lastMove.charAt(1))] = 1;
                board[Character.getNumericValue(lastMove.charAt(0))][Character
                        .getNumericValue(lastMove.charAt(1)) - 1] = 1;
                board[Character.getNumericValue(lastMove.charAt(0))][Character
                        .getNumericValue(lastMove.charAt(1)) - 2] = 2;

            }
            moves.remove(moves.size() - 1);

            paintScreen();

        }
    }

    /**
     * Making a move
     * 
     * @param row       Row index of the move
     * @param col       Column index of the move
     * @param direction Direction of the move
     */
    @Override
    public void playMove(int row, int col, char direction) {

        // Checking if the direction of the move is up, down, left, or right.
        if (direction == 'u') {
            board[row][col] = 2;
            board[row - 1][col] = 2;
            board[row - 2][col] = 1;
        }

        else if (direction == 'd') {
            board[row][col] = 2;
            board[row + 1][col] = 2;
            board[row + 2][col] = 1;
        } else if (direction == 'r') {
            board[row][col] = 2;
            board[row][col + 1] = 2;
            board[row][col + 2] = 1;
        } else if (direction == 'l') {

            board[row][col] = 2;
            board[row][col - 1] = 2;
            board[row][col - 2] = 1;
        }

        String newMove = Integer.toString(row) + Integer.toString(col) + direction;
        moves.add(newMove);

        // Checking if the game is over. If it is, it will display a message box with
        // the score.
        if (endGame()) {
            paintScreen();
            JFrame fr = new JFrame();
            JOptionPane.showMessageDialog(fr, "CONGRATULATIONS! GAME HAS FINISHED. YOUR SCORE: " + numberOfPegs());
            fr.dispose();
        }

    }

    /**
     * Paint game to the screen
     */
    @Override
    public void paintScreen() {

        pegBoard.removeAll();
        fillBoard();
        pegBoard.revalidate();
        pegBoard.repaint();

        numOfPegs.removeAll();
        numOfPegs.setText("SCORE (Number of Pegs):" + numberOfPegs());
        numOfPegs.revalidate();
        numOfPegs.repaint();

        numOfEmpty.removeAll();
        numOfEmpty.setText("Number of Empty Cells: " + numberOfEmpty());
        numOfEmpty.revalidate();
        numOfEmpty.repaint();

        numOfPegsTaken.removeAll();
        numOfPegsTaken.setText("Number of Pegs Taken: " + numberOfPegsTaken());
        numOfPegsTaken.revalidate();
        numOfPegsTaken.repaint();
    }

    /**
     * Computer will make a one move
     */
    @Override
    public void playAuto() {

        // check if the game has ended or not
        if (endGame()) {
            return;
        }

        Random randomNum = new Random();

        String directions = "udrl";

        int randomRow = randomNum.nextInt(rowNumber);
        int randomColumn = randomNum.nextInt(colNumber);
        int randomIndex = randomNum.nextInt(4);

        // The above code is generating a random number for the row and column and then
        // checking if the move is
        // valid. If the move is not valid, then it will generate a new random number
        // and check again.
        while (!checkMove(randomRow, randomColumn, directions.charAt(randomIndex))) {
            randomRow = randomNum.nextInt(rowNumber);
            randomColumn = randomNum.nextInt(colNumber);
            randomIndex = randomNum.nextInt(4);
        }

        playMove(randomRow, randomColumn, directions.charAt(randomIndex));
        paintScreen();

    }

    /**
     * Computer plays the game until it ends
     */
    @Override
    public void playAutoAll() {
        while (!endGame()) {
            playAuto();
        }
    }

    /**
     * This function initializes the board with the given board number
     * 
     * @param boardNumber the number of the board you want to use.
     */
    @Override
    public void initializeBoard(int boardNumber) {
        if (boardNumber == 1) {

            int[][] board_1 = {
                    { 0, 0, 1, 1, 1, 0, 0 },
                    { 0, 1, 1, 1, 1, 1, 0 },
                    { 1, 1, 1, 1, 1, 1, 1 },
                    { 1, 1, 1, 2, 1, 1, 1 },
                    { 1, 1, 1, 1, 1, 1, 1 },
                    { 0, 1, 1, 1, 1, 1, 0 },
                    { 0, 0, 1, 1, 1, 0, 0 },
            };
            board = board_1;
        } else if (boardNumber == 2) {

            int[][] board_2 = {
                    { 0, 0, 0, 1, 1, 1, 0, 0, 0 },
                    { 0, 0, 0, 1, 1, 1, 0, 0, 0 },
                    { 0, 0, 0, 1, 1, 1, 0, 0, 0 },
                    { 1, 1, 1, 1, 1, 1, 1, 1, 1 },
                    { 1, 1, 1, 1, 2, 1, 1, 1, 1 },
                    { 1, 1, 1, 1, 1, 1, 1, 1, 1 },
                    { 0, 0, 0, 1, 1, 1, 0, 0, 0 },
                    { 0, 0, 0, 1, 1, 1, 0, 0, 0 },
                    { 0, 0, 0, 1, 1, 1, 0, 0, 0 },

            };
            board = board_2;

        } else if (boardNumber == 3) {

            int[][] board_3 = {
                    { 0, 0, 1, 1, 1, 0, 0, 0 },
                    { 0, 0, 1, 1, 1, 0, 0, 0 },
                    { 0, 0, 1, 1, 1, 0, 0, 0 },
                    { 1, 1, 1, 1, 1, 1, 1, 1 },
                    { 1, 1, 1, 2, 1, 1, 1, 1 },
                    { 1, 1, 1, 1, 1, 1, 1, 1 },
                    { 0, 0, 1, 1, 1, 0, 0, 0 },
                    { 0, 0, 1, 1, 1, 0, 0, 0 },

            };
            board = board_3;

        } else if (boardNumber == 4) {

            int[][] board_4 = {
                    { 0, 0, 1, 1, 1, 0, 0 },
                    { 0, 0, 1, 1, 1, 0, 0 },
                    { 1, 1, 1, 1, 1, 1, 1 },
                    { 1, 1, 1, 2, 1, 1, 1 },
                    { 1, 1, 1, 1, 1, 1, 1 },
                    { 0, 0, 1, 1, 1, 0, 0 },
                    { 0, 0, 1, 1, 1, 0, 0 },
            };
            board = board_4;
        } else if (boardNumber == 5) {

            int[][] board_5 = {
                    { 0, 0, 0, 0, 1, 0, 0, 0, 0 },
                    { 0, 0, 0, 1, 1, 1, 0, 0, 0 },
                    { 0, 0, 1, 1, 1, 1, 1, 0, 0 },
                    { 0, 1, 1, 1, 1, 1, 1, 1, 0 },
                    { 1, 1, 1, 1, 2, 1, 1, 1, 1 },
                    { 0, 1, 1, 1, 1, 1, 1, 1, 0 },
                    { 0, 0, 1, 1, 1, 1, 1, 0, 0 },
                    { 0, 0, 0, 1, 1, 1, 0, 0, 0 },
                    { 0, 0, 0, 0, 1, 0, 0, 0, 0 },

            };
            board = board_5;

        }

    }

    /**
     * Chose a board from board types
     */
    @Override
    public void chooseBoard() {
        JFrame fm = new JFrame("CHOOSE BOARD TYPE");

        // adding a button the frame
        JLabel boards = new JLabel();
        boards.setIcon(new ImageIcon("PegGame/boards.jpg"));
        boards.setBounds(20, 20, 600, 200);

        ButtonGroup boardGroup = new ButtonGroup();

        // adding a button the frame
        JLabel board1 = new JLabel("1)");
        board1.setText("1)");
        board1.setBounds(75, 250, 20, 20);

        // adding a button the frame
        JRadioButton firstBoard = new JRadioButton("1", true);
        firstBoard.setBounds(88, 250, 20, 20);

        // adding a button the frame
        JLabel board2 = new JLabel("2)");
        board2.setText("2)");
        board2.setBounds(180, 250, 20, 20);

        // adding a button the frame
        JRadioButton secondBoard = new JRadioButton("2");
        secondBoard.setBounds(192, 250, 20, 20);

        // adding a button the frame
        JLabel board3 = new JLabel("3)");
        board3.setText("3)");
        board3.setBounds(285, 250, 20, 20);

        // adding a button the frame
        JRadioButton thirdBoard = new JRadioButton("3");
        thirdBoard.setBounds(297, 250, 20, 20);

        // adding a button the frame
        JLabel board4 = new JLabel("4)");
        board4.setText("4)");
        board4.setBounds(390, 250, 20, 20);

        // adding a button the frame
        JRadioButton fourthBoard = new JRadioButton("4");
        fourthBoard.setBounds(402, 250, 20, 20);

        // adding a button the frame
        JLabel board5 = new JLabel("5)");
        board5.setText("5)");
        board5.setBounds(495, 250, 20, 20);

        // adding a button the frame
        JRadioButton fifthBoard = new JRadioButton("5");
        fifthBoard.setBounds(510, 250, 20, 20);

        boardGroup.add(firstBoard);
        boardGroup.add(secondBoard);
        boardGroup.add(thirdBoard);
        boardGroup.add(fourthBoard);
        boardGroup.add(fifthBoard);

        // adding a button the frame
        JButton enterBoardType = new JButton("START THE GAME!");
        enterBoardType.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                if (firstBoard.isSelected()) {
                    // if user selected the first board
                    boardType = 1;
                    rowNumber = 7;
                    colNumber = 7;
                    initializeBoard(boardType);

                    startGame();
                } else if (secondBoard.isSelected()) {
                    // if user selected the second board
                    boardType = 2;
                    rowNumber = 9;
                    colNumber = 9;
                    initializeBoard(boardType);

                    startGame();

                } else if (thirdBoard.isSelected()) {
                    // if user selected the third board
                    boardType = 3;
                    rowNumber = 8;
                    colNumber = 8;
                    initializeBoard(boardType);

                    startGame();

                } else if (fourthBoard.isSelected()) {
                    // if user selected the fourth board
                    boardType = 4;
                    rowNumber = 7;
                    colNumber = 7;
                    initializeBoard(boardType);

                    startGame();

                } else if (fifthBoard.isSelected()) {
                    // if user selected the fifth board
                    boardType = 5;
                    rowNumber = 9;
                    colNumber = 9;

                    initializeBoard(boardType);
                    startGame();

                }
                fm.setVisible(false);
                fm.dispose();

            }

        });

        // set a bound to the frame
        enterBoardType.setBounds(220, 350, 200, 50);

        fm.add(board1);
        fm.add(firstBoard);

        fm.add(board2);
        fm.add(secondBoard);

        fm.add(board3);
        fm.add(thirdBoard);

        fm.add(board4);
        fm.add(fourthBoard);

        fm.add(board5);
        fm.add(fifthBoard);

        fm.add(enterBoardType);

        fm.add(boards);

        fm.setSize(700, 500);
        fm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fm.setLayout(null);
        fm.setVisible(true);
    }

    /**
     * Display the game to the screen
     */
    @Override
    public void startGame() {

        pegBoard.setBounds(20, 20, 600, 600);
        pegBoard.setLayout(new GridLayout(rowNumber, colNumber));

        numOfPegs.setText("SCORE (Number of Pegs):" + numberOfPegs());
        numOfPegs.setBounds(650, 50, 200, 20);

        numOfEmpty.setText("Number of Empty Cells: " + numberOfEmpty());
        numOfEmpty.setBounds(650, 70, 200, 20);

        numOfPegsTaken.setText("Number of Pegs Taken: " + numberOfPegsTaken());
        numOfPegsTaken.setBounds(650, 90, 200, 20);

        undoButton.setBounds(650, 150, 100, 30);
        undoButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                undoMove();
            }
        });

        resetButton.setBounds(650, 200, 100, 30);
        resetButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                resetGame();
            }
        });

        saveButton.setBounds(650, 250, 100, 30);
        saveButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                JFrame frm = new JFrame("FILE NAME");
                JLabel headText = new JLabel();

                headText.setText("Enter the file name:");
                headText.setBounds(30, 30, 300, 30);

                JTextField getFileName = new JTextField();
                getFileName.setBounds(30, 80, 200, 30);

                JButton confirmButton = new JButton();
                JButton cancelButton = new JButton();

                confirmButton.setText("CONFIRM");
                confirmButton.setBounds(30, 130, 100, 30);
                confirmButton.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String fileName = " ";

                        fileName = getFileName.getText();

                        try {

                            PrintWriter writeFile = new PrintWriter(fileName);

                            JFrame fr = new JFrame();
                            JOptionPane.showMessageDialog(fr, "Game has been saved correctly!");

                            frm.setVisible(false);
                            frm.dispose();

                            saveGame(fileName);

                        } catch (Exception f) {

                            JFrame fr = new JFrame();
                            JOptionPane.showMessageDialog(fr, "Invalid File Name!");
                            fr.dispose();
                        }

                    }

                });

                cancelButton.setText("CANCEL");
                cancelButton.setBounds(150, 130, 100, 30);
                cancelButton.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {

                        frm.setVisible(false);
                        frm.dispose();
                    }

                });

                frm.add(headText);
                frm.add(getFileName);
                frm.add(confirmButton);
                frm.add(cancelButton);

                frm.setSize(300, 300);
                frm.setLayout(null);
                frm.setVisible(true);

            }
        });

        loadButton.setBounds(650, 300, 100, 30);
        loadButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                JFrame frm = new JFrame("FILE NAME");
                JLabel headText = new JLabel();

                headText.setText("Enter the file name:");
                headText.setBounds(30, 30, 300, 30);

                JTextField getFileName = new JTextField();
                getFileName.setBounds(30, 80, 200, 30);

                JButton confirmButton = new JButton();
                JButton cancelButton = new JButton();

                confirmButton.setText("CONFIRM");
                confirmButton.setBounds(30, 130, 100, 30);
                confirmButton.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String fileName = " ";

                        fileName = getFileName.getText();

                        try {

                            File fileObj = new File(fileName);

                            Scanner readFile = new Scanner(fileObj);

                            JFrame fr = new JFrame();
                            JOptionPane.showMessageDialog(fr, "Game has been loaded!");

                            frm.setVisible(false);
                            frm.dispose();

                            loadGame(fileName);

                        } catch (Exception f) {

                            JFrame fr = new JFrame();
                            JOptionPane.showMessageDialog(fr, "File doesnt exist!");
                            fr.dispose();
                        }

                    }

                });

                cancelButton.setText("CANCEL");
                cancelButton.setBounds(150, 130, 100, 30);
                cancelButton.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {

                        frm.setVisible(false);
                        frm.dispose();
                    }

                });

                frm.add(headText);
                frm.add(getFileName);
                frm.add(confirmButton);
                frm.add(cancelButton);

                frm.setSize(300, 300);
                frm.setLayout(null);
                frm.setVisible(true);

            }
        });

        playAutoButton.setBounds(650, 350, 200, 30);
        playAutoButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                playAuto();
            }
        });

        playAutoAllButton.setBounds(650, 400, 200, 30);
        playAutoAllButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                playAutoAll();
            }
        });

        popupmenu.add(upSide);
        popupmenu.add(downSide);
        popupmenu.add(rightSide);
        popupmenu.add(leftSide);

        upSide.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int index = Integer.parseInt(currentCell);
                int rowNum = index / 10;
                int colNum = index % 10;

                if (checkMove(rowNum, colNum, 'u')) {

                    playMove(rowNum, colNum, 'u');

                    paintScreen();
                }
            }
        });

        downSide.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int index = Integer.parseInt(currentCell);
                int rowNum = index / 10;
                int colNum = index % 10;

                if (checkMove(rowNum, colNum, 'd')) {

                    playMove(rowNum, colNum, 'd');
                    paintScreen();
                }
            }
        });

        rightSide.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int index = Integer.parseInt(currentCell);
                int rowNum = index / 10;
                int colNum = index % 10;

                if (checkMove(rowNum, colNum, 'r')) {

                    playMove(rowNum, colNum, 'r');

                    paintScreen();
                }
            }
        });

        leftSide.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int index = Integer.parseInt(currentCell);
                int rowNum = index / 10;
                int colNum = index % 10;

                if (checkMove(rowNum, colNum, 'l')) {

                    playMove(rowNum, colNum, 'l');

                    paintScreen();
                }
            }
        });

        fillBoard();

        frame.add(numOfPegs);
        frame.add(numOfEmpty);
        frame.add(numOfPegsTaken);
        frame.add(undoButton);
        frame.add(resetButton);
        frame.add(saveButton);
        frame.add(loadButton);
        frame.add(playAutoButton);
        frame.add(playAutoAllButton);
        frame.add(pegBoard);

        frame.setSize(900, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    private int[][] board;
    private int boardType;
    private int rowNumber;
    private int colNumber;
    private ArrayList<String> moves = new ArrayList<String>();

    private String currentCell = "";
    private JFrame frame = new JFrame("PEG SOLITAIRE!");
    private JPanel pegBoard = new JPanel();
    private JPopupMenu popupmenu = new JPopupMenu("SIDE");

    private JMenuItem upSide = new JMenuItem("UP");
    private JMenuItem downSide = new JMenuItem("DOWN");
    private JMenuItem leftSide = new JMenuItem("LEFT");
    private JMenuItem rightSide = new JMenuItem("RIGHT");

    private JLabel numOfPegs = new JLabel();
    private JLabel numOfEmpty = new JLabel();
    private JLabel numOfPegsTaken = new JLabel();
    private JButton resetButton = new JButton("RESET");
    private JButton undoButton = new JButton("UNDO");
    private JButton saveButton = new JButton("SAVE");
    private JButton loadButton = new JButton("LOAD");
    private JButton playAutoButton = new JButton("PLAY AUTO");
    private JButton playAutoAllButton = new JButton("PLAY AUTO ALL");

}
