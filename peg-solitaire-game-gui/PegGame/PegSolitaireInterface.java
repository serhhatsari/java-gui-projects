package PegGame;

/**
 * PegSolitaire is a class to represent PegSolitaire Game
 * 
 * @author Serhat Sarı  
 * @since  28-01-2022
 * 
 */

/**
 * Peg Solitaire Game Interface
 */
public interface PegSolitaireInterface {
    /**
     * Chose a board from board types
     */
    public void chooseBoard();

    /**
     * Display the game to the screen
     */
    public void startGame();

    /**
     * Save the current game to the file
     * 
     * @param filename Name of the file
     */
    public void saveGame(String filename);

    /**
     * Load a game from a file
     * 
     * @param filename Name of the file
     */
    public void loadGame(String filename);

    /**
     * Number of pegs in the game
     * 
     * @return Number of Pegs
     */
    public int numberOfPegs();

    /**
     * Number of pegs taken in the game
     * 
     * @return Number of Pegs Taken
     */
    public int numberOfPegsTaken();

    /**
     * Number of Empty cells in the game
     * 
     * @return Number of Empty Cells
     */
    public int numberOfEmpty();

    /**
     * Fill the grid layout with cells
     * 
     */
    public void fillBoard();

    /**
     * Check move if it is valid or not
     * 
     * @param row       Row index of the move
     * @param col       Column index of the move
     * @param direction Direction of the move
     * @return True or False
     */
    public Boolean checkMove(int row, int col, char direction);

    /**
     * Check if there is any move left
     * 
     * @return True or False
     */
    public Boolean endGame();

    /**
     * Reset the current board
     * 
     */
    public void resetGame();

    /**
     * Go back to one step before
     */
    public void undoMove();

    /**
     * Making a move
     * 
     * @param row       Row index of the move
     * @param col       Column index of the move
     * @param direction Direction of the move
     */
    public void playMove(int row, int col, char direction);

    /**
     * Paint game to the screen
     */
    public void paintScreen();

    /**
     * Computer will make a one move
     */
    public void playAuto();

    /**
     * Computer plays the game until it ends
     */
    public void playAutoAll();

    /**
     * Initialize the board
     */
    public void initializeBoard(int boardNumber);

}