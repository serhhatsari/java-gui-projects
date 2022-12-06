package src;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Kovalamaca implements KeyListener {

  private JFrame mainFrame = new JFrame("KOVALAMACA");
  private JPanel greenSquare = new JPanel();
  private int x_coordinate = 240;
  private int y_coordinate = 240;

  public int getXCoordinate() {
    return x_coordinate;
  }

  public int getYCoordinate() {
    return y_coordinate;
  }

  public Kovalamaca() {
    try {
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }
    greenSquare.setBounds(x_coordinate, y_coordinate, 20, 20);
    greenSquare.setBackground(Color.GREEN);

    mainFrame.add(greenSquare);
    setScreenSettings();
  }

  public void addComponentToScreen(Component c) {
    mainFrame.add(c);
  }

  protected void paintComponent(Graphics screenGraphic) {}

  @Override
  public void keyPressed(KeyEvent event) {
    char userInput = event.getKeyChar();
    move(userInput);
  }

  @Override
  public void keyReleased(KeyEvent event) {}

  @Override
  public void keyTyped(KeyEvent event) {}

  private void move(char userInput) {

    switch (userInput) {
      case 'a':
        moveLeft();
        break;
      case 's':
        moveDown();
        break;
      case 'd':
        moveRight();
        break;
      case 'w':
        moveUp();
        break;
      default:
    }
    mainFrame.repaint();
  }

  public void repaintScreen(){
    mainFrame.repaint();
  }

  private void moveLeft() {
    x_coordinate -= 10;
    if(!checkWall()){
      x_coordinate += 10;
      return;
    }
    greenSquare.setBounds(x_coordinate, y_coordinate, 20, 20);
  }

  private void moveRight() {
    x_coordinate += 10;
    if(!checkWall()){
      x_coordinate -= 10;
      return;
    }
    greenSquare.setBounds(x_coordinate, y_coordinate, 20, 20);
  }

  private void moveUp() {
    y_coordinate -= 10;
    if(!checkWall()){
      y_coordinate += 10;
      return;
    }
    greenSquare.setBounds(x_coordinate, y_coordinate, 20, 20);
  }

  private void moveDown() {
    y_coordinate += 10;
    if(!checkWall()){
      y_coordinate -= 10;
      return;
    }
    greenSquare.setBounds(x_coordinate, y_coordinate, 20, 20);
  }

  private boolean checkWall() {
    if(x_coordinate < 0){
      return false;
    }else if(x_coordinate > 480){
      return false;
    }
    else if(y_coordinate < 0){
      return false;
    }
    else if(y_coordinate > 440){ 
      return false;
    }
    return true;
  }


  private void setScreenSettings() {
    mainFrame.addKeyListener(this);
    mainFrame.getContentPane().setBackground(Color.GRAY);
    mainFrame.setSize(500, 500);
    mainFrame.setLayout(null);
    mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    mainFrame.setResizable(false);
    mainFrame.setVisible(true);
  }
}
