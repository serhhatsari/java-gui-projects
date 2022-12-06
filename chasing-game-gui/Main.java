import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JPanel;
import src.Kovalamaca;
import java.util.Timer;
import java.util.TimerTask;

public class Main {

  Kovalamaca kovala = new Kovalamaca();

  public Main() {
  }

  public class Monster extends Thread {

    private int x_coordinate;
    private int y_coordinate;
    private JPanel panel = new JPanel();

    public Monster(int x_coor, int y_coor) {
      super.run();

      TimerTask task = new TimerTask() {
        @Override
        public void run() {
          if (isOver()) {
            System.exit(0);
          }
        }
      };

      Timer timer = new Timer();
      timer.schedule(task, 0l, 1l);

      x_coordinate = x_coor;
      y_coordinate = y_coor;
      panel.setBounds(x_coordinate, y_coordinate, 20, 20);
      panel.setBackground(Color.BLUE);
      kovala.addComponentToScreen(panel);
    }

    @Override
    public void run() {

      while (true) {
        try {
          Thread.sleep(1000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        if (isOver()) {

          System.exit(0);
        }
        if (Math.abs(x_coordinate - kovala.getXCoordinate()) > Math.abs(y_coordinate - kovala.getYCoordinate())) {
          if (x_coordinate - kovala.getXCoordinate() > 0) {
            moveLeft();
          } else {
            moveRight();
          }
        } else {
          if (y_coordinate - kovala.getYCoordinate() > 0) {
            moveUp();
          } else {
            moveDown();
          }
        }
        if (isOver()) {

          System.exit(0);
        }
      }
    }

    private void moveLeft() {
      x_coordinate -= 10;
      panel.setBounds(x_coordinate, y_coordinate, 20, 20);
      kovala.repaintScreen();
    }

    private void moveRight() {
      x_coordinate += 10;
      panel.setBounds(x_coordinate, y_coordinate, 20, 20);
      kovala.repaintScreen();
    }

    private void moveUp() {
      y_coordinate -= 10;
      panel.setBounds(x_coordinate, y_coordinate, 20, 20);
      kovala.repaintScreen();
    }

    private void moveDown() {
      y_coordinate += 10;
      panel.setBounds(x_coordinate, y_coordinate, 20, 20);
      kovala.repaintScreen();
    }

    private boolean isOver() {

      return x_coordinate < kovala.getXCoordinate() + 20 && x_coordinate + 20 > kovala.getXCoordinate()
          && y_coordinate < kovala.getYCoordinate() + 20 && y_coordinate + 20 > kovala.getYCoordinate();
    }

  }

  public static void main(String[] args) {
    int number_of_monsters = Integer.parseInt(args[0]);
    System.out.println(number_of_monsters);
    Main m = new Main();

    Main.Monster[] monsters = new Main.Monster[number_of_monsters];

    Random r = new Random();

    for (int i = 0; i < number_of_monsters; i++) {
      monsters[i] = m.new Monster(Math.abs(r.nextInt() % 500), Math.abs(r.nextInt() % 500));
    }

    for (int i = 0; i < number_of_monsters; i++)
      monsters[i].start();

    try {
      for (int i = 0; i < number_of_monsters; i++)
        monsters[i].join();
    } catch (InterruptedException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
}
