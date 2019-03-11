package ProjectTetris.Group11.View;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

import java.awt.event.KeyListener;
import javax.swing.JFrame;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ProjectTetris.Group11.Model.Piece;
import ProjectTetris.Group11.Model.Well;


/* Painter class
 * Author: Anthony Chang, Daniel Agostinho, Divya Sridhar
 *
 * This class is part of the view. It creates and is responsible for the GUI.
 */
public class Painter extends JFrame {
	
	// CONTROLLER UPDATES THIS DATA
/** 
* a private variable for the level.
*/
	private int level;
/** 
* a private variable for the score.
*/
	private int score;
/** 
* a private variable for the well.
*/
	private Well well;
/** 
* a private variable for the current piece.
*/
	private Piece currentTetra;
/** 
* a private variable for the next piece.
*/
	private Piece nextTetra;
/** 
* a JTextField object for a new game. 
*/
	JTextField newGame;
/** 
* a private final variable.
*/
	private final int UNIT = 29;
/** 
* a public variable for a new game input.
*/
	public int NGInput;
/** 
* a private JPanel object.
*/
	private JPanel panel = new JPanel();

/** 
* @brief Constructor for Painter
* @details Constructor adds the different elements of the GUI to the JFrame.
*/
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
/**
* @brief This function sets the level of the game.
* @param level Level of the game.
*/
	public void setLevel(int level){
		this.level = level;
	}
	
/**
* @brief This function sets the score of the game.
* @param score Score of the current game.
*/
	public void setScore(int score) {
		this.score = score;
	}

/**
* @brief This function sets the well to the game.
* @param well The Well object to set.
*/
	public void setWell(Well well) {
		this.well = well;
	}

/**
* @brief This function sets the current piece.
* @param currentTetra The current piece in the game.
*/
	public void setCurrentTetra(Piece currentTetra) {
		this.currentTetra = currentTetra;
	}

/**
* @brief This function sets the next piece to be used in the game.
* @param nextTetra The next piece.
*/
	public void setNextTetra(Piece nextTetra) {
		this.nextTetra = nextTetra;
	}

	// PAINT METHODS
/**
* @brief This function draws the score box in the game.
* @param g The Graphics object to draw to.
*/
	private void drawScore(Graphics g) {
		g.setColor(Color.YELLOW);
		g.drawString("Score: " + score, 2, 30 * 23 + 50);
		g.drawString("Level: " + level, 200, 30 * 23 + 50);

	}

/**
* @brief This function draws the well.
* @param g The Graphics object to draw to.
*/
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

/**
* @brief This function draws the piece to the game.
* @param g The Graphics object to draw to.
*/
	private void drawPiece(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(currentTetra.getColor());
		for (Point p : currentTetra.getPieceCoords(currentTetra.getOrientation())) {
			g2.fillRect((p.x + currentTetra.getPieceX()) * 30, (p.y + currentTetra.getPieceY()) * 30 + 30, UNIT, UNIT);
		}

	}

/**
* @brief This function draws the indicator box that shows the next piece.
* @param g The Graphics object to draw to.
*/
	private void drawNextTetra(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(nextTetra.getColor());
		for (Point p : nextTetra.getPieceCoords(nextTetra.getOrientation())) {
			g2.fillRect((p.x + 29) * 10, (p.y + 1) * 10 + 18, 9, 9);
		}
		g.setColor(Color.WHITE);
		g.drawString("Next Piece: ", 215, 45);

	}

/**
* @brief This function draws the total GUI.
* @param g The Graphics object to draw to.
*/
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

/**
* @brief This function pauses the game.
*/
	public void pauseMessage() {
		JOptionPane.showMessageDialog(this, "Paused. Press OK to continue");

	}

/**
* @brief This function displays the help dialog which explains how the game works.
*/	
	public void helpMessage() {
		JOptionPane.showMessageDialog(this, "Tetris is a tile-matching puzzle video game.\nHow to play:\nRight Arrow Key- Move Right\nLeft Arrow Key- Move Left\nUp Arrow Key- Rotate Clockwise\nDown Arrow Key- Rotate Counter-Clockwise\nP- Pause\nSpacebar - Speed Up" );

	}
	
/**
* @brief This function shows the end game dialog. It displays the score and gives the user the option to restart.
*/	
	public void gameEnd() {		
		NGInput = JOptionPane.showOptionDialog(null, "Score: " + score + "\nNew Game? ", "Game Over!", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);

	}
	
/**
* @brief This function adds a KeyListener.
* @param k KeyListener to add.
*/	
	public void addKListener(KeyListener k) {
		this.addKeyListener(k);
	}



}
