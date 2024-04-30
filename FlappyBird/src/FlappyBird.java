import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;


public class FlappyBird extends JPanel implements ActionListener, KeyListener {
    private int frameWidth = 360;
    private int frameHeight = 630;
    private Image backGroundImage;
    private Image birdImage;
    private Image lowerPipeImage;
    private Image upperPipeImage;

    // player attributes
    private int startPlayerPosx = frameWidth / 8;
    private int getStartPlayerPosY = frameHeight / 2;
    private int playerWidth = 34;
    private int playerHeight = 24;
    Player player;

    // game logic
    Timer gameLoop;
    Timer pipesCooldown;
    private int gravity = 1;
    private boolean gameOver = false;
    private int score = 0;
    private boolean isPaused = false; // Added for pause functionality

    // pipe attributes
    private int pipeStartposX = frameWidth;
    private int pipeStartposY = 0;
    private int pipeWidth = 64;
    private int pipeHeight = 512;
    private int pipeSpeed = -3; // Initial speed of pipes
    ArrayList<Pipe> pipes;

    // constructor
    public FlappyBird() {
        setPreferredSize(new Dimension(frameWidth, frameHeight));
        setFocusable(true);
        addKeyListener(this);

        backGroundImage = new ImageIcon(getClass().getResource("Assets/background.png")).getImage();
        birdImage = new ImageIcon(getClass().getResource("Assets/bird.png")).getImage();
        lowerPipeImage = new ImageIcon(getClass().getResource("Assets/lowerPipe.png")).getImage();
        upperPipeImage = new ImageIcon(getClass().getResource("Assets/upperPipe.png")).getImage();

        player = new Player(startPlayerPosx, getStartPlayerPosY, playerWidth, playerHeight, birdImage);
        pipes = new ArrayList<>();

        pipesCooldown = new Timer(1500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!gameOver) {
                    placePipes();
                }
            }
        });
        pipesCooldown.start();

        gameLoop = new Timer(1000 / 60, this);
        gameLoop.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        g.drawImage(backGroundImage, 0, 0, frameWidth, frameHeight, null);
        g.drawImage(player.getImage(), player.getPostX(), player.getPostY(), player.getWidth(), player.getHeight(), null);

        for (int i = 0; i < pipes.size(); i++) {
            Pipe pipe = pipes.get(i);
            g.drawImage(pipe.getImage(), pipe.getPostX(), pipe.getPostY(), pipe.getWidth(), pipe.getHeight(), null);
        }

        // Display Score
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Score: " + score, 10, 20);

        if (gameOver) {
            g.setFont(new Font("Arial", Font.BOLD, 30));
            g.drawString("Game Over!", frameWidth / 2 - 80, frameHeight / 2 - 15);
            g.drawString("Score: " + score, frameWidth / 2 - 60, frameHeight / 2 + 15);
        }

        if (isPaused) { // Display "Paused" message if the game is paused
            g.setFont(new Font("Arial", Font.BOLD, 30));
            g.drawString("Paused", frameWidth / 2 - 50, frameHeight / 2);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!gameOver && !isPaused) { // Only proceed if the game is not over and not paused
            move();
            checkCollisions();
            repaint();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (!gameOver && e.getKeyCode() == KeyEvent.VK_SPACE) {
            player.setVelocityY(-10);
        } else if (gameOver && e.getKeyCode() == KeyEvent.VK_SPACE) {
            int choice = JOptionPane.showOptionDialog(null, "Game Over!\nYour Score: " + score + "\nDo you want to restart?", "Game Over", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
            if (choice == JOptionPane.YES_OPTION) {
                restartGame(); // Restart game
            } else {
                System.exit(0); // Exit game
            }
        } else if (e.getKeyCode() == KeyEvent.VK_R) {
            if (gameOver) {
                int choice = JOptionPane.showOptionDialog(null, "Game Over!\nYour Score: " + score + "\nDo you want to restart?", "Game Over", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
                if (choice == JOptionPane.YES_OPTION) {
                    restartGame(); // Restart game
                } else {
                    System.exit(0); // Exit game
                }
            }
        } else if (e.getKeyCode() == KeyEvent.VK_P) { // Pause the game when 'P' is pressed
            if (!gameOver) {
                isPaused = !isPaused; // Toggle pause status
                repaint(); // Repaint to show "Paused" message
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    public void move() {
        player.setVelocityY(player.getVelocityY() + gravity);
        player.setPostY(player.getPostY() + player.getVelocityY());
        player.setPostY(Math.max(player.getPostY(), 0));
        player.setPostY(Math.min(player.getPostY(), frameHeight));

        for (int i = 0; i < pipes.size(); i++) {
            Pipe pipe = pipes.get(i);
            pipe.setPostX(pipe.getPostX() + pipeSpeed); // Pipes move with the same speed
        }

        // Remove pipes that have passed the left edge of the screen
        pipes.removeIf(pipe -> pipe.getPostX() + pipe.getWidth() <= 0);
    }

    public void placePipes() {
        int randomPosY = (int) (pipeStartposY - pipeHeight / 4 - Math.random() * (pipeHeight / 2));
        int openingSpace = frameHeight / 4;

        Pipe upperPipe = new Pipe(pipeStartposX, randomPosY, pipeWidth, pipeHeight, upperPipeImage);
        pipes.add(upperPipe);
        Pipe lowerPipe = new Pipe(pipeStartposX, (randomPosY + openingSpace + pipeHeight), pipeWidth, pipeHeight, lowerPipeImage);
        pipes.add(lowerPipe);

    }

    public void checkCollisions() {
        Rectangle playerRect = new Rectangle(player.getPostX(), player.getPostY(), player.getWidth(), player.getHeight());

        for (int i = 0; i < pipes.size(); i += 2) {
            Pipe upperPipe = pipes.get(i);
            Pipe lowerPipe = pipes.get(i + 1);

            Rectangle upperPipeRect = new Rectangle(upperPipe.getPostX(), upperPipe.getPostY(), upperPipe.getWidth(), upperPipe.getHeight());
            Rectangle lowerPipeRect = new Rectangle(lowerPipe.getPostX(), lowerPipe.getPostY(), lowerPipe.getWidth(), lowerPipe.getHeight());

            if (playerRect.intersects(upperPipeRect) || playerRect.intersects(lowerPipeRect)) {
                gameOver = true;
                break;
            }

            if (!upperPipe.isPassed() && player.getPostX() > upperPipe.getPostX() + upperPipe.getWidth()) {
                upperPipe.setPassed(true);
                lowerPipe.setPassed(true);
                score++;
            }
        }

        if (player.getPostY() >= frameHeight - player.getHeight()) {
            gameOver = true;
        }
    }

    public void restartGame() {
        pipes.clear();
        player.setPostY(getStartPlayerPosY);
        player.setPostX(startPlayerPosx);
        score = 0;
        gameOver = false;
        pipeSpeed = -3; // Reset pipe speed
        isPaused = false; // Reset pause status
    }
}
