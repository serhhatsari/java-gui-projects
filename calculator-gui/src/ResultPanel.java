package src;
import java.awt.*;
import javax.swing.JPanel;
/**
 * Result Panel shows the output
 * 
 * @since  27-07-2022
 * 
 */

/**
 * It draws the result of the calculation on the screen
 */
class ResultPanel extends JPanel {

  private String result = "";
  
  private static int STARTING_HEIGHT = 60;
  private static int INCREMENT_HEIGHT = 76;
  private static int INCREMENT_WIDTH = 40;
  /**
 * This function takes a string as an argument and sets the result variable to that string.
 * 
 * @param newResult The new result to be set.
 */
  public void setNum(String newResult) {
    result = newResult;
  }


/**
 * For each character in the result string, draw the appropriate character
 * 
 * @param screenGraphic The graphics object that is passed to the paintComponent method.
 */
  @Override
  protected void paintComponent(Graphics screenGraphic) {
    super.paintComponent(screenGraphic);

    int startingWidth = 20;
    
    for (int i = 0; i < result.length(); i++) {
      switch (result.charAt(i)) {
        case '0':
          drawZero(screenGraphic, startingWidth);
          startingWidth += 60;
          break;
        case '1':
          drawOne(screenGraphic, startingWidth);
          startingWidth += 20;
          break;
        case '2':
          drawTwo(screenGraphic, startingWidth);
          startingWidth += 60;
          break;
        case '3':
          drawThree(screenGraphic, startingWidth);
          startingWidth += 60;
          break;
        case '4':
          drawFour(screenGraphic, startingWidth);
          startingWidth += 60;
          break;
        case '5':
          drawFive(screenGraphic, startingWidth);
          startingWidth += 60;
          break;
        case '6':
          drawSix(screenGraphic, startingWidth);
          startingWidth += 60;
          break;
        case '7':
          drawSeven(screenGraphic, startingWidth);
          startingWidth += 60;
          break;
        case '8':
          drawEight(screenGraphic, startingWidth);
          startingWidth += 60;
          break;
        case '9':
          drawNine(screenGraphic, startingWidth);
          startingWidth += 60;
          break;
        case '.':
          drawDot(screenGraphic, startingWidth);
          startingWidth += 25;
          break;
        case '-':
        drawHyphen(screenGraphic, startingWidth);
          startingWidth += 40;
          break;
        default:
          break;
      }
    }
  }

/**
 * Draws a zero
 * 
 * @param screenGraphic Graphics object
 * @param startingWidth the starting width of the digit
 */
  private void drawZero(Graphics screenGraphic, int startingWidth) {
    screenGraphic.drawLine(startingWidth, STARTING_HEIGHT, startingWidth + INCREMENT_WIDTH, STARTING_HEIGHT);
    screenGraphic.drawLine(startingWidth, STARTING_HEIGHT + INCREMENT_HEIGHT, startingWidth + INCREMENT_WIDTH, STARTING_HEIGHT + INCREMENT_HEIGHT);

    screenGraphic.drawLine(startingWidth, STARTING_HEIGHT, startingWidth, STARTING_HEIGHT + INCREMENT_HEIGHT);
    screenGraphic.drawLine(startingWidth + INCREMENT_WIDTH, STARTING_HEIGHT, startingWidth + INCREMENT_WIDTH, STARTING_HEIGHT + INCREMENT_HEIGHT);
  }

/**
 * Draws a one
 * 
 * @param screenGraphic Graphics object
 * @param startingWidth the starting width of the digit
 */
  private void drawOne(Graphics screenGraphic, int startingWidth) {
    screenGraphic.drawLine(startingWidth, STARTING_HEIGHT, startingWidth, STARTING_HEIGHT + INCREMENT_HEIGHT);
  }

  /**
 * Draws a two
 * 
 * @param screenGraphic Graphics object
 * @param startingWidth the starting width of the digit
 */
  private void drawTwo(Graphics screenGraphic, int startingWidth) {
    screenGraphic.drawLine(startingWidth, STARTING_HEIGHT, startingWidth + INCREMENT_WIDTH, STARTING_HEIGHT);
    screenGraphic.drawLine(startingWidth + INCREMENT_WIDTH, STARTING_HEIGHT, startingWidth + INCREMENT_WIDTH, STARTING_HEIGHT + INCREMENT_HEIGHT / 2);

    screenGraphic.drawLine(startingWidth, STARTING_HEIGHT + INCREMENT_HEIGHT / 2, startingWidth + INCREMENT_WIDTH, STARTING_HEIGHT + INCREMENT_HEIGHT / 2);
    screenGraphic.drawLine(startingWidth, STARTING_HEIGHT + INCREMENT_HEIGHT / 2, startingWidth, STARTING_HEIGHT + INCREMENT_HEIGHT);

    screenGraphic.drawLine(startingWidth, STARTING_HEIGHT + INCREMENT_HEIGHT, startingWidth + INCREMENT_WIDTH, STARTING_HEIGHT + INCREMENT_HEIGHT);
  }

  /**
 * Draws a three
 * 
 * @param screenGraphic Graphics object
 * @param startingWidth the starting width of the digit
 */
  private void drawThree(Graphics screenGraphic, int startingWidth) {
    screenGraphic.drawLine(startingWidth, STARTING_HEIGHT, startingWidth + INCREMENT_WIDTH, STARTING_HEIGHT);
    screenGraphic.drawLine(startingWidth + INCREMENT_WIDTH, STARTING_HEIGHT, startingWidth + INCREMENT_WIDTH, STARTING_HEIGHT + INCREMENT_HEIGHT);

    screenGraphic.drawLine(startingWidth, STARTING_HEIGHT + INCREMENT_HEIGHT / 2, startingWidth + INCREMENT_WIDTH, STARTING_HEIGHT + INCREMENT_HEIGHT / 2);

    screenGraphic.drawLine(startingWidth, STARTING_HEIGHT + INCREMENT_HEIGHT, startingWidth + INCREMENT_WIDTH, STARTING_HEIGHT + INCREMENT_HEIGHT);
  }

  /**
 * Draws a four
 * 
 * @param screenGraphic Graphics object
 * @param startingWidth the starting width of the digit
 */
  private void drawFour(Graphics screenGraphic, int startingWidth) {
    screenGraphic.drawLine(startingWidth + INCREMENT_WIDTH, STARTING_HEIGHT, startingWidth + INCREMENT_WIDTH, STARTING_HEIGHT + INCREMENT_HEIGHT);
    screenGraphic.drawLine(startingWidth, STARTING_HEIGHT + INCREMENT_HEIGHT / 2, startingWidth + INCREMENT_WIDTH, STARTING_HEIGHT + INCREMENT_HEIGHT / 2);
    screenGraphic.drawLine(startingWidth, STARTING_HEIGHT, startingWidth, STARTING_HEIGHT + INCREMENT_HEIGHT / 2);
  }

  /**
 * Draws a five
 * 
 * @param screenGraphic Graphics object
 * @param startingWidth the starting width of the digit
 */
  private void drawFive(Graphics screenGraphic, int startingWidth) {
    screenGraphic.drawLine(startingWidth, STARTING_HEIGHT, startingWidth + INCREMENT_WIDTH, STARTING_HEIGHT);
    screenGraphic.drawLine(startingWidth + INCREMENT_WIDTH, STARTING_HEIGHT + INCREMENT_HEIGHT / 2, startingWidth + INCREMENT_WIDTH, STARTING_HEIGHT + INCREMENT_HEIGHT);
    screenGraphic.drawLine(startingWidth, STARTING_HEIGHT + INCREMENT_HEIGHT / 2, startingWidth + INCREMENT_WIDTH, STARTING_HEIGHT + INCREMENT_HEIGHT / 2);
    screenGraphic.drawLine(startingWidth, STARTING_HEIGHT, startingWidth, STARTING_HEIGHT + INCREMENT_HEIGHT / 2);
    screenGraphic.drawLine(startingWidth, STARTING_HEIGHT + INCREMENT_HEIGHT, startingWidth + INCREMENT_WIDTH, STARTING_HEIGHT + INCREMENT_HEIGHT);
  }

  /**
 * Draws a six
 * 
 * @param screenGraphic Graphics object
 * @param startingWidth the starting width of the digit
 */
  private void drawSix(Graphics screenGraphic, int startingWidth) {
    screenGraphic.drawLine(startingWidth, STARTING_HEIGHT, startingWidth + INCREMENT_WIDTH, STARTING_HEIGHT);
    screenGraphic.drawLine(startingWidth, STARTING_HEIGHT, startingWidth, STARTING_HEIGHT + INCREMENT_HEIGHT);
    screenGraphic.drawLine(startingWidth, STARTING_HEIGHT + INCREMENT_HEIGHT, startingWidth + INCREMENT_WIDTH, STARTING_HEIGHT + INCREMENT_HEIGHT);
    screenGraphic.drawLine(startingWidth, STARTING_HEIGHT + INCREMENT_HEIGHT / 2, startingWidth + INCREMENT_WIDTH, STARTING_HEIGHT + INCREMENT_HEIGHT / 2);
    screenGraphic.drawLine(startingWidth + INCREMENT_WIDTH, STARTING_HEIGHT + INCREMENT_HEIGHT / 2, startingWidth + INCREMENT_WIDTH, STARTING_HEIGHT + INCREMENT_HEIGHT);
  }

  /**
 * Draws a seven
 * 
 * @param screenGraphic Graphics object
 * @param startingWidth the starting width of the digit
 */
  private void drawSeven(Graphics screenGraphic, int startingWidth) {
    screenGraphic.drawLine(startingWidth, STARTING_HEIGHT, startingWidth + INCREMENT_WIDTH, STARTING_HEIGHT);
    screenGraphic.drawLine(startingWidth + INCREMENT_WIDTH, STARTING_HEIGHT, startingWidth + INCREMENT_WIDTH, STARTING_HEIGHT + INCREMENT_HEIGHT);
  }

  /**
 * Draws a eight
 * 
 * @param screenGraphic Graphics object
 * @param startingWidth the starting width of the digit
 */
  private void drawEight(Graphics screenGraphic, int startingWidth) {
    screenGraphic.drawLine(startingWidth, STARTING_HEIGHT, startingWidth + INCREMENT_WIDTH, STARTING_HEIGHT);
    screenGraphic.drawLine(startingWidth, STARTING_HEIGHT + INCREMENT_HEIGHT / 2, startingWidth + INCREMENT_WIDTH, STARTING_HEIGHT + INCREMENT_HEIGHT / 2);
    screenGraphic.drawLine(startingWidth, STARTING_HEIGHT + INCREMENT_HEIGHT, startingWidth + INCREMENT_WIDTH, STARTING_HEIGHT + INCREMENT_HEIGHT);
    screenGraphic.drawLine(startingWidth, STARTING_HEIGHT, startingWidth, STARTING_HEIGHT + INCREMENT_HEIGHT);
    screenGraphic.drawLine(startingWidth + INCREMENT_WIDTH, STARTING_HEIGHT, startingWidth + INCREMENT_WIDTH, STARTING_HEIGHT + INCREMENT_HEIGHT);
  }

  /**
 * Draws a nine
 * 
 * @param screenGraphic Graphics object
 * @param startingWidth the starting width of the digit
 */
  private void drawNine(Graphics screenGraphic, int startingWidth) {
    screenGraphic.drawLine(startingWidth, STARTING_HEIGHT, startingWidth + INCREMENT_WIDTH, STARTING_HEIGHT);
    screenGraphic.drawLine(startingWidth, STARTING_HEIGHT + INCREMENT_HEIGHT / 2, startingWidth + INCREMENT_WIDTH, STARTING_HEIGHT + INCREMENT_HEIGHT / 2);
    screenGraphic.drawLine(startingWidth, STARTING_HEIGHT + INCREMENT_HEIGHT, startingWidth + INCREMENT_WIDTH, STARTING_HEIGHT + INCREMENT_HEIGHT);
    screenGraphic.drawLine(startingWidth, STARTING_HEIGHT, startingWidth, STARTING_HEIGHT + INCREMENT_HEIGHT / 2);
    screenGraphic.drawLine(startingWidth + INCREMENT_WIDTH, STARTING_HEIGHT, startingWidth + INCREMENT_WIDTH, STARTING_HEIGHT + INCREMENT_HEIGHT);
  }

  /**
 * Draws a dot
 * 
 * @param screenGraphic Graphics object
 * @param startingWidth the starting width of the digit
 */
  private void drawDot(Graphics screenGraphic, int startingWidth) {
    screenGraphic.drawOval(startingWidth, 130, 5, 5);
  }

  /**
 * Draws a hyphen
 * 
 * @param screenGraphic Graphics object
 * @param startingWidth the starting width of the digit
 */
  private void drawHyphen(Graphics screenGraphic, int startingWidth){
    screenGraphic.drawLine(startingWidth, STARTING_HEIGHT + INCREMENT_HEIGHT / 2, startingWidth + 30, STARTING_HEIGHT + INCREMENT_HEIGHT / 2);

  }
}
