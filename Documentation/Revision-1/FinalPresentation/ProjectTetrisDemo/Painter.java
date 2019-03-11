

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class Painter extends JFrame {
	// CONTROLLER UPDATES THIS DATA
	private int level;
	private int score;
	private Well well;
	private Piece currentTetra;
	private Piece nextTetra;
	JTextField newGame;

	private final int UNIT = 29;
	public int NGInput;
	private JPanel panel = new JPanel();

	public Painter() {
		this.setTitle("PROJECT TETRIS");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(12 * 30 + 10, 30 * 23 + 70);
		this.setVisible(true);
		this.setFocusable(true);
		panel.setPreferredSize(this.getSize());
		this.add(panel);
	}

	// GETTERS FOR DATA PASSED INTO THE VIEW
	public void setLevel(int level){
		this.level = level;
	}
	public void setScore(int score) {
		this.score = score;
	}

	public void setWell(Well well) {
		this.well = well;
	}

	public void setCurrentTetra(Piece currentTetra) {
		this.currentTetra = currentTetra;
	}

	public void setNextTetra(Piece nextTetra) {
		this.nextTetra = nextTetra;
	}

	// PAINT METHODS

	private void drawScore(Graphics g) {
		g.setColor(Color.YELLOW);
		g.drawString("Score: " + score, 2, 30 * 23 + 50);
		g.drawString("Level: " + level, 200, 30 * 23 + 50);

	}

	private void drawWell(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		for (Point p : well.getWellCoords()) {
			{
				if (p.x == 0 || p.y == 22 || p.x == 11) {
					g2.setColor(well.getBorderColor());
					g2.fillRect(p.x * (UNIT + 1), p.y * (UNIT + 1) + 30, UNIT, UNIT);
				} else {
					g2.setColor(well.getWellColor());
					g2.fillRect(p.x * (UNIT + 1), p.y * (UNIT + 1) + 30, UNIT, UNIT);

				}

			}
		}
	}

	private void drawPiece(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(currentTetra.getColor());
		for (Point p : currentTetra.getPieceCoords(currentTetra.getOrientation())) {
			g2.fillRect((p.x + currentTetra.getPieceX()) * 30, (p.y + currentTetra.getPieceY()) * 30 + 30, UNIT, UNIT);
		}

	}

	private void drawNextTetra(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(nextTetra.getColor());
		for (Point p : nextTetra.getPieceCoords(nextTetra.getOrientation())) {
			g2.fillRect((p.x + 29) * 10, (p.y + 1) * 10 + 18, 9, 9);
		}
		g.setColor(Color.WHITE);
		g.drawString("Next Piece: ", 215, 45);

	}

	public void paint(Graphics g) {
		panel.paint(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(new Color(32, 32, 32));
		g2.fillRect(0, 0, 12 * 30 + 10 + 30, 30 * 23 + 70);
		
		drawScore(g);
		drawWell(g);
		drawPiece(g);
		drawNextTetra(g);
		g2.setColor(Color.WHITE);
		g2.setFont(new Font("Arial", Font.PLAIN, 20)); 
		
		g2.drawString("PROJECT TETRIS", 100, 80);
		g2.setFont(new Font("Arial", Font.PLAIN, 14));
		g2.drawString("H = Help   P = Pause", 120, 100);

	}

	public void pauseMessage() {
		JOptionPane.showMessageDialog(this, "Paused. Press OK to continue");

	}

	public void helpMessage() {
		JOptionPane.showMessageDialog(this, "Tetris is a tile-matching puzzle video game.\nHow to play:\nRight Arrow Key- Move Right\nLeft Arrow Key- Move Left\nUp Arrow Key- Rotate Clockwise\nDown Arrow Key- Rotate Counter-Clockwise\nP- Pause\n Spacebar - Speed Up" );

	}
	public void gameEnd() {		
		NGInput = JOptionPane.showOptionDialog(null, "Score: " + score + "\nNew Game? ", "Game Over!", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);

	}
	
	public void addKListener(KeyListener k) {
		this.addKeyListener(k);
	}



}
