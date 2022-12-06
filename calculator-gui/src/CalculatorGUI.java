package src;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;
/**
 * CalculatorGUI is the main class of the program
 * 
 * @since  27-07-2022
 * 
 */


/**
 * It's a GUI class that implements a Simple Calculator
 */
public class CalculatorGUI extends JFrame implements ActionListener {

  // Main Frame of the App
  private JFrame mainFrame = new JFrame(Constants.PROGRAM_NAME);

  // Result Panel shows the output of the operation
  ResultPanel resultPanel = new ResultPanel();

  // Checks if user wants to do a new operation
  boolean isNewOperation = false;

  // Store operator and operands
  String leftOperand = "";
  String operator = "";
  String rightOperand = "";

  // Default constructer of the Calculator class
  public CalculatorGUI() {
    try {
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }

    // initialize the screen
    initialize();
  }

  /**
   * If the user pressed a number, add it to the screen. If the user pressed a dot, add it to the screen.
   * If the user pressed clear, clear the screen. If the user pressed equals, calculate the result.
   *
   * @param event The event that triggered the action.
   */
  public void actionPerformed(ActionEvent event) {
    // get input from the user
    String userInput = event.getActionCommand();

    if (isNumber(userInput) || isDot(userInput)) {
      pressNumber(userInput);
    } else if (isClear(userInput)) {
      clearScreen();
    } else if (isEquals(userInput)) {
      pressEquals();
    } else {
      pressOperator(userInput);
    }
  }

  /**
   * If the first character of the userInput string is a dot, return true, otherwise return false.
   *
   * @param userInput The user's input.
   * @return The method is returning a boolean value.
   */
  private boolean isDot(String userInput) {
    return userInput.charAt(0) == Constants.DOT_CHARACTER;
  }

  /**
   * If the first character of the user input is 'C', then return true.
   *
   * @param userInput The user's input.
   * @return The method is returning a boolean value.
   */
  private boolean isClear(String userInput) {
    return userInput.charAt(0) == Constants.CLEAR_SIGN;
  }

  /**
   * If the first character of the user input is an equals sign, return true.
   *
   * @param userInput The user's input.
   * @return The method is returning a boolean value.
   */
  private boolean isEquals(String userInput) {
    return userInput.charAt(0) == Constants.EQUAL_SIGN;
  }

  /**
   * If the first character of the string is a digit, return true, otherwise return false.
   *
   * @param userInput The user's input.
   * @return The method is returning a boolean value.
   */
  private boolean isNumber(String userInput) {
    return (userInput.charAt(0) >= '0' && userInput.charAt(0) <= '9');
  }

  /**
   * This function returns true if the userInput is an empty string, and false otherwise.
   *
   * @param userInput The user's input.
   * @return The method isEmptyString is returning a boolean value.
   */
  private boolean isEmptyString(String userInput) {
    return userInput.equals("");
  }

  /**
   * It clears the screen by setting the leftOperand, operator, and rightOperand to the empty string, and
   * then it sets the resultPanel's number to the empty string and repaints the resultPanel
   */
  private void clearScreen() {
    leftOperand = operator = rightOperand = "";
    resultPanel.setNum("");
    resultPanel.repaint();
  }

  /**
   * If the user is starting a new operation, clear the left operand, operator, right operand, and result
   * panel. Otherwise, if the user is entering a number, append the number to the left operand if there
   * is no operator, or append the number to the right operand if there is an operator
   *
   * @param userInput The user input from the button pressed.
   */
  private void pressNumber(String userInput) {
    if (isNewOperation) {
      leftOperand = operator = rightOperand = "";
      resultPanel.setNum("");
      resultPanel.repaint();
      isNewOperation = false;
    }

    if (!isEmptyString(operator)) {
      rightOperand = rightOperand + userInput;
    } else {
      leftOperand = leftOperand + userInput;
    }

    if (!isEmptyString(operator)) {
      resultPanel.setNum(rightOperand);
      resultPanel.repaint();
    } else {
      resultPanel.setNum(leftOperand);
      resultPanel.repaint();
    }
  }

  /**
   * If the leftOperand and rightOperand are not empty, then perform the operation and display the result
   *
   */
  private void pressEquals() {
    double result;

    // Checking if the leftOperand or rightOperand is empty. If it is, then do nothing.
    if (leftOperand.equals("") || rightOperand.equals("")) {
      return;
    }

    // Checking the operator and then performing the operation.
    if (operator.equals(Constants.PLUS_SIGN)) {
      result =
        (Double.parseDouble(leftOperand) + Double.parseDouble(rightOperand));
    } else if (operator.equals(Constants.MINUS_SIGN)) {
      result =
        (Double.parseDouble(leftOperand) - Double.parseDouble(rightOperand));
    } else if (operator.equals(Constants.DIVIDE_SIGN)) {
      result =
        (Double.parseDouble(leftOperand) / Double.parseDouble(rightOperand));
    } else {
      result =
        (Double.parseDouble(leftOperand) * Double.parseDouble(rightOperand));
    }

    // setting the output screen
    resultPanel.setNum(Double.toString(result));
    resultPanel.repaint();

    leftOperand = Double.toString(result);

    operator = rightOperand = "";
    isNewOperation = true;
  }

 /**
  * If there was no operand, then set the operator to the user input. Else, evaluate the expression and
  * set the left operand to the result
  * 
  * @param userInput the input from the user
  */
  private void pressOperator(String userInput) {
    // check if there is any operand
    if (!isEmptyString(leftOperand) ) {
      operator = userInput;
    } 
    isNewOperation = false;
  }

  /**
   * It adds the result panel, clear button, and buttons to the screen, and then sets the screen settings
   *
   */
  private void initialize() {
    addResultPanelToScreen();
    addClearButtonToScreen();
    addButtonsToScreen();
    setScreenSettings();
  }

  /**
   * The function adds the result panel to the main frame
   */
  private void addResultPanelToScreen() {
    resultPanel.setBounds(20, 20, 750, 200);
    Border blueBorder = BorderFactory.createLineBorder(Color.blue);
    resultPanel.setBorder(blueBorder);
    mainFrame.add(resultPanel);
  }

  /**
   * It creates a panel, sets the layout of the panel, sets the bounds of the panel, creates 16 buttons,
   * sets the settings of the buttons, and adds the buttons to the panel
   */
  private void addButtonsToScreen() {
    JPanel buttonPanel = new JPanel();
    GridLayout gridLayout = new GridLayout(4, 4);

    gridLayout.setHgap(20);
    gridLayout.setVgap(20);
    buttonPanel.setLayout(gridLayout);
    buttonPanel.setBackground(Color.WHITE);
    buttonPanel.setBounds(20, 400, 750, 550);

    for (int i = 0; i < 16; i++) {
      JButton button = new JButton(Constants.getButtonStrings().get(i));
      setButtonSettings(button);
      buttonPanel.add(button);
    }

    mainFrame.add(buttonPanel);
  }

  /**
   * This function adds a button to the screen that clears the screen when pressed
   */
  private void addClearButtonToScreen() {
    JButton clearButton;
    clearButton = new JButton("C");
    clearButton.setBounds(20, 240, 750, 150);
    setButtonSettings(clearButton);
    mainFrame.add(clearButton);
  }

  /**
   * This function sets the font, background color, and adds an action listener to the button.
   *
   * @param button The button that you want to set the settings for.
   */
  private void setButtonSettings(JButton button) {
    button.setFont(new Font("Arial", Font.PLAIN, 40));
    button.setBackground(Color.BLUE);
    button.setOpaque(true);
    button.setBorderPainted(false);
    button.addActionListener(this);
  }

  /**
   * This function sets the settings of the main frame (screen)
   */
  private void setScreenSettings() {
    mainFrame.getContentPane().setBackground(Color.WHITE);
    mainFrame.setSize(800, 1000);
    mainFrame.setLayout(new BorderLayout());
    mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    mainFrame.setResizable(false);
    mainFrame.setVisible(true);
  }
}
