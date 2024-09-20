
/***
 * This program implements the classic "2048" game using Java's Swing framework for graphical display and keyboard input. It allows the player to move tiles in four directions, combine like tiles, and aim to achieve the 2048 tile to win.
 * Name : Tanishq Dosaya
 * Date : 17 Sept 2024
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class GameOf2048 extends JPanel {

    private int SIZE;
    public static final char MOVE_LEFT = 'A';
    public static final char MOVE_RIGHT = 'D';
    public static final char MOVE_UP = 'W';
    public static final char MOVE_DOWN = 'S';

    private int[][] board;
    private Random random;
    private int score;

    // Constructor initializes the game board with the specified size and sets up
    // key listeners for player input.
    public GameOf2048(int size) {
        SIZE = size;
        board = new int[SIZE][SIZE];
        random = new Random();
        score = 0;

        setFocusable(true);
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(600, 650));
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                Constant constant = new Constant();
                new Thread(() -> {
                    int keyCode = e.getKeyCode();
                    char move = Character.toUpperCase(e.getKeyChar());

                    if (keyCode == KeyEvent.VK_LEFT || keyCode == KeyEvent.VK_A) {
                        processMove(MOVE_LEFT);
                    } else if (keyCode == KeyEvent.VK_RIGHT || keyCode == KeyEvent.VK_D) {
                        processMove(MOVE_RIGHT);
                    } else if (keyCode == KeyEvent.VK_UP || keyCode == KeyEvent.VK_W) {
                        processMove(MOVE_UP);
                    } else if (keyCode == KeyEvent.VK_DOWN || keyCode == KeyEvent.VK_S) {
                        processMove(MOVE_DOWN);
                    } else {
                        return;
                    }

                    SwingUtilities.invokeLater(() -> {
                        repaint();
                    });

                    if (!isGameOver()) {
                        int r = random.nextInt(100);
                        if (r % 2 == 0) {
                            addRandomDigit(2);
                        } else {
                            addRandomDigit(4);
                        }
                    }

                    if (gameWon()) {
                        SwingUtilities.invokeLater(() -> {
                            JOptionPane.showMessageDialog(null, constant.Won);
                            askToPlayAgain(constant.WonPlayAgain);
                        });
                    } else if (isGameOver()) {
                        SwingUtilities.invokeLater(() -> {
                            JOptionPane.showMessageDialog(null, constant.Lost);
                            askToPlayAgain(constant.LostPlayAgain);
                        });
                    }

                    SwingUtilities.invokeLater(() -> {
                        repaint();
                    });
                }).start();
            }

        });
        addRandomDigit(2);
        addRandomDigit(4);
    }

    // Checks if the given key code or character corresponds to a valid move (up,
    // down, left, or right).
    private boolean isValidMoveKey(int keyCode, char move) {
        return move == MOVE_LEFT || move == MOVE_RIGHT || move == MOVE_UP || move == MOVE_DOWN ||
                keyCode == KeyEvent.VK_LEFT || keyCode == KeyEvent.VK_RIGHT || keyCode == KeyEvent.VK_UP
                || keyCode == KeyEvent.VK_DOWN;
    }

    @Override
    // Handles the rendering of the game board and score on the panel.
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawScore(g);
        drawBoard(g);
    }

    // Draws the current score on the screen.
    private void drawScore(Graphics g) {
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 24));
        g.drawString("Score: " + score, 20, 40);
    }

    // Draws the game board, including each tile, on the screen.
    private void drawBoard(Graphics g) {
        int cellSize = 600 / SIZE;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                int value = board[i][j];
                drawCell(g, i, j, value, cellSize);
            }
        }
    }

    // Draws an individual cell with a specific value at a given row and column.
    private void drawCell(Graphics g, int row, int col, int value, int cellSize) {
        int x = col * cellSize;
        int y = row * cellSize + 50;

        g.setColor(getTileColor(value));
        g.fillRect(x, y, cellSize, cellSize);

        g.setColor(Color.BLACK);
        g.drawRect(x, y, cellSize, cellSize);

        if (value != 0) {
            g.setColor(Color.BLACK);
            g.setFont(new Font("Arial", Font.BOLD, cellSize / 3));
            String text = String.valueOf(value);
            FontMetrics metrics = g.getFontMetrics();
            int textX = x + (cellSize - metrics.stringWidth(text)) / 2;
            int textY = y + (cellSize - metrics.getHeight()) / 2 + metrics.getAscent();
            g.drawString(text, textX, textY);
        }
    }

    // Returns the color corresponding to the value of a tile for visual
    // representation.
    private Color getTileColor(int value) {
        switch (value) {
            case 2:
                return new Color(0xeee4da);
            case 4:
                return new Color(0xede0c8);
            case 8:
                return new Color(0xf2b179);
            case 16:
                return new Color(0xf59563);
            case 32:
                return new Color(0xf67c5f);
            case 64:
                return new Color(0xf65e3b);
            case 128:
                return new Color(0xedcf72);
            case 256:
                return new Color(0xedcc61);
            case 512:
                return new Color(0xedc850);
            case 1024:
                return new Color(0xedc53f);
            case 2048:
                return new Color(0xedc22e);
            default:
                return new Color(0xcdc1b4);
        }
    }

    // Adds a random digit (2 or 4) to an empty spot on the game board.
    public void addRandomDigit(int digit) {
        int i = random.nextInt(SIZE);
        int j = random.nextInt(SIZE);

        while (board[i][j] != 0) {
            i = random.nextInt(SIZE);
            j = random.nextInt(SIZE);
        }

        board[i][j] = digit;
    }

    // Searches for a specific value (like 2048) on the game board.
    public boolean searchOnBoard(int x) {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] == x) {
                    return true;
                }
            }
        }
        return false;
    }

    // Checks if the player has won by finding the 2048 tile on the board.
    public boolean gameWon() {
        return searchOnBoard(2048);
    }

    // Determines if the player can make any valid move by checking for empty tiles
    // or adjacent equal tiles.
    public boolean userCanMakeAMove() {
        if (searchOnBoard(0)) {
            return true;
        }

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (j < SIZE - 1 && board[i][j] == board[i][j + 1]) {
                    return true;
                }
                if (i < SIZE - 1 && board[i][j] == board[i + 1][j]) {
                    return true;
                }
            }
        }

        return false;
    }

    // Checks if the game is over by seeing if no valid moves are possible.
    public boolean isGameOver() {
        return !userCanMakeAMove();
    }

    // Processes a move (left, right, up, or down) by updating the board
    // accordingly.
    public void processMove(char move) {
        switch (move) {
            case MOVE_LEFT: {
                for (int i = 0; i < SIZE; i++) {
                    int newRow[] = processLeftMove(board[i]);
                    for (int j = 0; j < SIZE; j++) {
                        board[i][j] = newRow[j];
                    }
                }
            }
                break;

            case MOVE_RIGHT: {
                for (int i = 0; i < SIZE; i++) {
                    int newRow[] = processRightMove(board[i]);
                    for (int j = 0; j < SIZE; j++) {
                        board[i][j] = newRow[j];
                    }
                }
            }
                break;

            case MOVE_UP: {
                for (int j = 0; j < SIZE; j++) {
                    int row[] = new int[SIZE];
                    for (int i = 0; i < SIZE; i++) {
                        row[i] = board[i][j];
                    }
                    int newRow[] = processLeftMove(row);
                    for (int i = 0; i < SIZE; i++) {
                        board[i][j] = newRow[i];
                    }
                }
            }
                break;

            case MOVE_DOWN: {
                for (int j = 0; j < SIZE; j++) {
                    int row[] = new int[SIZE];
                    for (int i = 0; i < SIZE; i++) {
                        row[i] = board[i][j];
                    }
                    int newRow[] = processRightMove(row);
                    for (int i = 0; i < SIZE; i++) {
                        board[i][j] = newRow[i];
                    }
                }
            }
                break;
        }
    }

    // Moves tiles to the left, combining like tiles and updating the score.
    public int[] processLeftMove(int row[]) {
        int newRow[] = new int[SIZE];
        int j = 0;

        for (int i = 0; i < SIZE; i++) {
            if (row[i] != 0) {
                newRow[j++] = row[i];
            }
        }

        for (int i = 0; i < SIZE - 1; i++) {
            if (newRow[i] != 0 && newRow[i] == newRow[i + 1]) {
                newRow[i] *= 2;
                score += newRow[i];
                for (int k = i + 1; k < SIZE - 1; k++) {
                    newRow[k] = newRow[k + 1];
                }
                newRow[SIZE - 1] = 0;
            }
        }
        return newRow;
    }

    // Moves tiles to the right by first reversing the row, processing a left move,
    // then reversing again.
    public int[] processRightMove(int row[]) {
        int newRow[] = reverseArray(row);
        newRow = processLeftMove(newRow);
        return reverseArray(newRow);
    }

    // Reverses the order of elements in the given array.
    public int[] reverseArray(int arr[]) {
        int[] reverseArr = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            reverseArr[i] = arr[arr.length - 1 - i];
        }
        return reverseArr;
    }

    // Prompts the player to play again after the game ends.
    public void askToPlayAgain(String message) {
        Constant constant = new Constant();
        int option = JOptionPane.showConfirmDialog(this, message, constant.GameOver, JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            resetGame();
        } else {
            System.exit(0);
        }
    }

    // Resets the game board and score, starting a new game.
    private void resetGame() {
        score = 0;
        board = new int[SIZE][SIZE];
        addRandomDigit(2);
        addRandomDigit(4);
        repaint();
    }

    public static void main(String[] args) {
        boolean validInput = false;
        int size = 0;
        Constant constant = new Constant();
        while (!validInput) {
            try {
                String sizeInput = JOptionPane.showInputDialog(null, constant.PositiveBoard,
                        "2048 Game", JOptionPane.QUESTION_MESSAGE);

                if (sizeInput == null) {
                    System.exit(0);
                }
                size = Integer.parseInt(sizeInput);

                if (size <= 0) {
                    throw new NumberFormatException(constant.PositiveSize);
                }
                validInput = true;

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, constant.Invalid, constant.Error,
                        JOptionPane.ERROR_MESSAGE);
            }
        }

        JFrame frame = new JFrame(constant.Title);
        GameOf2048 gamePanel = new GameOf2048(size);
        frame.add(gamePanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
