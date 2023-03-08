package more;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class SnakeGame extends JPanel {
	private int x;
	private int y;
	private int dx;
	private int dy;
	private int gridSize;
	private int foodX;
	private int foodY;
	private boolean gameOver;
	private LinkedList<Point> snake;
	private static boolean Pause = false;
	private int Points;

	public SnakeGame() {
		x = 0;
		y = 0;
		dx = 1;
		dy = 0;
		gridSize = 20;
		foodX = (int) (Math.random() * gridSize);
		foodY = (int) (Math.random() * gridSize);
		gameOver = false;
		snake = new LinkedList<Point>();
		snake.add(new Point(x, y));
		setFocusTraversalKeysEnabled(false);
		Points = 0;

		setPreferredSize(new Dimension(gridSize * 20, gridSize * 20));
		setFocusable(true);
		addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_UP) {
					dx = 0;
					dy = -1;
				} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					dx = 0;
					dy = 1;
				} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					dx = -1;
					dy = 0;
				} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					dx = 1;
					dy = 0;
				} else if (e.getKeyCode() == KeyEvent.VK_W) {
					dx = 0;
					dy = -1;
				} else if (e.getKeyCode() == KeyEvent.VK_S) {
					dx = 0;
					dy = 1;
				} else if (e.getKeyCode() == KeyEvent.VK_A) {
					dx = -1;
					dy = 0;
				} else if (e.getKeyCode() == KeyEvent.VK_D) {
					dx = 1;
					dy = 0;
				} else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					System.exit(0);
					System.out.println("Game left");
				} else if (e.getKeyCode() == KeyEvent.VK_TAB) {
					if (Pause == false) {
						Pause = true;
						System.out.println("Game paused");
					} else if (Pause == true) {
						Pause = false;
						System.out.println("Game unpaused");
					}
				}
			}
		});
	}

	public void paint(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth(), getHeight());
		g.setColor(Color.GREEN);
		for (Point p : snake) {	
			g.fillRect(p.x * 20, p.y * 20, 20, 20);
		}
		g.setColor(Color.YELLOW);
		g.fillRect(foodX * 20, foodY * 20, 20, 20);
	    g.setColor(Color.WHITE);
	    g.drawString("Points: " + Points, 10, 20);
	}

	public void update() {
		if (gameOver) {
			return;
		}
		x += dx;
		y += dy;
		if (x < 0 || x > gridSize - 1 || y < 0 || y > gridSize - 1) {
			gameOver = true;
			JOptionPane.showMessageDialog(this, "Game Over, you earned " + Points + " points!");
			System.exit(0);
		}
		if (x == foodX && y == foodY) {
			foodX = (int) (Math.random() * gridSize);
			foodY = (int) (Math.random() * gridSize);
			Points++;
		} else {
			snake.removeLast();
		}
		snake.addFirst(new Point(x, y));
		repaint();
	}


	public static void main(String[] args) {
		JFrame frame = new JFrame("Snake Game");
		SnakeGame game = new SnakeGame();
		frame.add(game);
		frame.pack();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Timer timer = new Timer(100, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Pause == false) {
					game.update();
				}
			}
		});
		timer.start();
	}
}    
