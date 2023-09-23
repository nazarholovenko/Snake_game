import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class SnakeGameMain extends JPanel implements ActionListener {
    public static JFrame jFrame;
    public static final int SCALE = 32;
    public static final int WIDTH = 20;
    public static final int HEIGHT = 20;
    public static int speed = 10;
    public Snake snake = new Snake(5, 6, 5, 5);
    Apple apple = new Apple(Math.abs((int) (Math.random() * SnakeGameMain.WIDTH - 1)), Math.abs((int) (Math.random() * SnakeGameMain.HEIGHT - 1)));
    Timer timer = new Timer(1000 / speed, this);

    public SnakeGameMain() {
        timer.start();
        addKeyListener(new KeyBoard());
        setFocusable(true);

    }

    public void paint(Graphics graphics) {
        graphics.setColor(Color.black);
        graphics.fillRect(0, 0, WIDTH * SCALE, HEIGHT * SCALE);

        for (int x = 0; x < WIDTH * SCALE; x += SCALE) {
            graphics.setColor(Color.black);
            graphics.drawLine(x, 0, x, HEIGHT * SCALE);

        }
        for (int y = 0; y < HEIGHT * SCALE; y += SCALE) {
            graphics.setColor(Color.black);
            graphics.drawLine(0, y, WIDTH * SCALE, y);
        }
        graphics.setColor(Color.red);
        graphics.fillOval(apple.positionX * SCALE + 4, apple.positionY * SCALE + 4, SCALE - 8, SCALE - 8);

        for (int l = 1; l < snake.length; l++) {
            graphics.setColor(Color.green);
            graphics.fillRect(snake.snakeX[l] * SCALE + 3, snake.snakeY[l] * SCALE + 3, SCALE - 6, SCALE - 6);
            graphics.setColor(Color.white);
            graphics.fillRect(snake.snakeX[0] * SCALE + 3, snake.snakeY[0] * SCALE + 3, SCALE - 6, SCALE - 6);
        }

    }

    public static void main(String[] args) {
        jFrame = new JFrame("Snake!");
        jFrame.setSize(WIDTH * SCALE + 10, HEIGHT * SCALE + 8);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setResizable(false);
        jFrame.setLocationRelativeTo(null);
        jFrame.add(new SnakeGameMain());
        jFrame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        snake.move();
        if ((snake.snakeX[0] == apple.positionX) && (snake.snakeY[0] == apple.positionY)) {
            apple.setRandomPosition();
            snake.length++;
        }
        for (int l = 1; l < snake.length; l++) {
            if ((snake.snakeX[l] == apple.positionX) && (snake.snakeY[l] == apple.positionY)) {

            }
            if ((snake.snakeX[0] == snake.snakeX[l]) && (snake.snakeY[0] == snake.snakeY[l])) {
                timer.stop();
                JOptionPane.showMessageDialog(null, "Game over!");
                jFrame.setVisible(false);
                snake.length = 2;
                snake.direction = 0;
                apple.setRandomPosition();
                jFrame.setVisible(true);
                timer.start();

            }
        }
        repaint();
    }
    public class KeyBoard extends KeyAdapter {
        public void keyPressed (KeyEvent keyEvent) {
            int key = keyEvent.getKeyCode();
            if ((key == KeyEvent.VK_UP) && (snake.direction != 2)) snake.direction = 0;
            if ((key == KeyEvent.VK_DOWN) && (snake.direction != 0)) snake.direction = 2;
            if ((key == KeyEvent.VK_RIGHT) && (snake.direction != 3)) snake.direction = 1;
            if ((key == KeyEvent.VK_LEFT) && (snake.direction != 1)) snake.direction = 3;
        }

    }
}