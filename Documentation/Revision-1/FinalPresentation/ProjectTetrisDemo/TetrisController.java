/**
@mainpage PROJECT TETRIS.
*/
/**
* @file GameEngine.java
* @title GameEngine
* @author Anthony Chang, Daniel Agostinho, Divya Sridhar
* @brief This class is for the controller. 
* @details This class represents the engine used for the game. It is the controller which takes input
* from the user. 	
* @todo Complete code.
*/


import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JOptionPane;

public class TetrisController {
	// MVC component fields
	private Painter painter;
	private Well well;
	Piece currentTetra;
	Piece nextTetra;

	// Set of coordinates for Tetrominoes
	private final Point[][][] pieceCoords = {
			{ { new Point(0, 1), new Point(1, 1), new Point(2, 1), new Point(3, 1) },
					{ new Point(1, 0), new Point(1, 1), new Point(1, 2), new Point(1, 3) },
					{ new Point(0, 1), new Point(1, 1), new Point(2, 1), new Point(3, 1) },
					{ new Point(1, 0), new Point(1, 1), new Point(1, 2), new Point(1, 3) } },
			{ { new Point(0, 1), new Point(1, 1), new Point(2, 1), new Point(2, 0) },
					{ new Point(1, 0), new Point(1, 1), new Point(1, 2), new Point(2, 2) },
					{ new Point(0, 1), new Point(1, 1), new Point(2, 1), new Point(0, 2) },
					{ new Point(1, 0), new Point(1, 1), new Point(1, 2), new Point(0, 0) } },
			{ { new Point(0, 1), new Point(1, 1), new Point(2, 1), new Point(2, 2) },
					{ new Point(1, 0), new Point(1, 1), new Point(1, 2), new Point(0, 2) },
					{ new Point(0, 1), new Point(1, 1), new Point(2, 1), new Point(0, 0) },
					{ new Point(1, 0), new Point(1, 1), new Point(1, 2), new Point(2, 0) } },
			{ { new Point(0, 0), new Point(0, 1), new Point(1, 0), new Point(1, 1) },
					{ new Point(0, 0), new Point(0, 1), new Point(1, 0), new Point(1, 1) },
					{ new Point(0, 0), new Point(0, 1), new Point(1, 0), new Point(1, 1) },
					{ new Point(0, 0), new Point(0, 1), new Point(1, 0), new Point(1, 1) } },
			{ { new Point(1, 0), new Point(2, 0), new Point(0, 1), new Point(1, 1) },
					{ new Point(0, 0), new Point(0, 1), new Point(1, 1), new Point(1, 2) },
					{ new Point(1, 0), new Point(2, 0), new Point(0, 1), new Point(1, 1) },
					{ new Point(0, 0), new Point(0, 1), new Point(1, 1), new Point(1, 2) } },
			{ { new Point(1, 0), new Point(0, 1), new Point(1, 1), new Point(2, 1) },
					{ new Point(1, 0), new Point(0, 1), new Point(1, 1), new Point(1, 2) },
					{ new Point(0, 1), new Point(1, 1), new Point(2, 1), new Point(1, 2) },
					{ new Point(1, 0), new Point(1, 1), new Point(2, 1), new Point(1, 2) } },
			{ { new Point(0, 0), new Point(1, 0), new Point(1, 1), new Point(2, 1) },
					{ new Point(1, 0), new Point(0, 1), new Point(1, 1), new Point(0, 2) },
					{ new Point(0, 0), new Point(1, 0), new Point(1, 1), new Point(2, 1) },
					{ new Point(1, 0), new Point(0, 1), new Point(1, 1), new Point(0, 2) } } };

	private final int originX = 4;
	private final int originY = -1;
	private int score;
	private int counter;
	private int level;
	private int speed;
	private final Color[] pieceColors = { Color.RED, Color.YELLOW, Color.PINK, Color.BLUE, Color.CYAN, Color.GREEN,
			Color.ORANGE };
	Random r = new Random();
	int pieceID = r.nextInt(7);
	int nextPiece;
	private static boolean isPaused = false;
	private static boolean helpPressed = false;

	public TetrisController() {
		painter = new Painter();
		painter.addKListener(new KListener());
		newGame();
	}

	public void newGame() {
		well = new Well();
		score = 0;
		counter = 0;
		level = 1;
		newTetraGen();
		while (!endGame()) {
			try {
				if (isPaused) {
					pause();
					painter.pauseMessage();
				} else if (helpPressed) {
					help();
					painter.helpMessage();
				}
				sendToPainter();
				setLevel();

				Thread.sleep(speed);
				pieceDrop();

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void setLevel() {
		level = counter / 10 + 1;
		if (level >= 9) {
			speed = 200;
		} else {
			speed = 1000 - 100 * (level - 1);
		}

	}
	
	public void pause() {
		isPaused = !isPaused;
	}

	public void help() {
		helpPressed = !helpPressed;
	}

	public void newTetraGen() {
		counter += 1;
		pieceID = nextPiece;
		nextPiece = r.nextInt(7);
		currentTetra = new Piece(pieceCoords[pieceID], originX, originY, pieceColors[pieceID]);
		nextTetra = new Piece(pieceCoords[nextPiece], originX, originY, pieceColors[nextPiece]);
	}

	public boolean movePossible(Piece piece, Well well, int x, int y, int o) {
		for (Point p : piece.getPieceCoords(o)) {
			for (Point w : well.getWellCoords()) {
				if ((p.x + x == w.x) && ((p.y + y) == w.y)) {
					return false;
				}
			}
		}
		return true;
	}

	public void pieceDrop() {

		if (movePossible(currentTetra, well, currentTetra.getPieceX(), currentTetra.getPieceY() + 1,
				currentTetra.getOrientation())) {
			currentTetra.pieceDrop();
		} else {
			addToWell();
		}
		sendToPainter();
		painter.repaint();

	}

	public void moveLeft() {
		if (movePossible(currentTetra, well, currentTetra.getPieceX() - 1, currentTetra.getPieceY(),
				currentTetra.getOrientation())) {
			currentTetra.moveLeft();
		}
		sendToPainter();
		painter.repaint();
	}

	public void moveRight() {
		if (movePossible(currentTetra, well, currentTetra.getPieceX() + 1, currentTetra.getPieceY(),
				currentTetra.getOrientation())) {
			currentTetra.moveRight();
		}
		sendToPainter();
		painter.repaint();
	}

	public void rotateLeft() {
		int newO = (currentTetra.getOrientation() + 3) % 4;
		if (movePossible(currentTetra, well, currentTetra.getPieceX(), currentTetra.getPieceY(), newO)) {
			currentTetra.rotateLeft(newO);
		}
		sendToPainter();
		painter.repaint();
	}

	public void rotateRight() {
		int newO = (currentTetra.getOrientation() + 1) % 4;
		if (movePossible(currentTetra, well, currentTetra.getPieceX(), currentTetra.getPieceY(), newO)) {
			currentTetra.rotateRight(newO);
		}
		sendToPainter();
		painter.repaint();
	}

	private boolean endGame() {
		for (Point w : well.getWellCoords()) {
			if (w.x != 0 && w.x != 11) {
				if (w.y == 3) {
					painter.gameEnd(); 
					if (painter.NGInput == 0) {
					        newGame();
					      }
					return true;
				}
			}
		}
		return false;
	}

	public void sendToPainter() {
		painter.setLevel(level);
		painter.setScore(score);
		painter.setWell(well);
		painter.setCurrentTetra(currentTetra);
		painter.setNextTetra(nextTetra);

	}

	private void addToWell() {
		for (Point p : currentTetra.getPieceCoords(currentTetra.getOrientation())) {
			well.getWellCoords().add(new Point(p.x + currentTetra.getPieceX(), p.y + currentTetra.getPieceY()));
		}
		clearCheck();
		newTetraGen();

	}

	private void clearCheck() {
		boolean incompleteRow;
		int scoreMultiplier = 0;

		for (int y = 21; y > 0; y--) {
			int counter = 0;
			for (int x = 1; x < 11; x++) {
				incompleteRow = true;
				Point p = new Point(x, y);
				if (well.getWellCoords().contains(p)) {
					counter += 1;
					if (counter == 10) {
						incompleteRow = false;
					}
				} else {
					break;
				}
				if (incompleteRow == false) {
					removeRow(y);
					y += 1;
					scoreMultiplier += 1;
				}
			}
		}

		switch (scoreMultiplier) {
		case 0:
			break;
		case 1:
			score += 40;
			break;
		case 2:
			score += 100;
			break;
		case 3:
			score += 300;
			break;
		case 4:
			score += 1200;
			break;
		}
	}

	private void removeRow(int row) {
		ArrayList<Point> newWell = new ArrayList<Point>();
		for (Point w : well.getWellCoords()) {
			if (w.x == 0 || w.x == 11) {
				newWell.add(w);
			} else if (w.y > row) {
				newWell.add(w);
			} else if (w.y < row) {
				Point p = new Point(w.x, w.y + 1);
				newWell.add(p);
			}
		}
		well.setWellCoords(newWell);
		sendToPainter();
		painter.repaint();
	}

	class KListener implements KeyListener {

		public void keyTyped(KeyEvent e) {
		}

		public void keyPressed(KeyEvent e) {
			switch (e.getKeyCode()) {
			case KeyEvent.VK_UP:
				rotateLeft();
				break;
			case KeyEvent.VK_DOWN:
				rotateRight();
				break;
			case KeyEvent.VK_LEFT:
				moveLeft();
				break;
			case KeyEvent.VK_RIGHT:
				moveRight();
				break;
			case KeyEvent.VK_SPACE:
				pieceDrop();
				score += 1;
				break;
			case KeyEvent.VK_P:
				pause();
				break;
			case KeyEvent.VK_H:
				help();
				break;
			}
		}

		public void keyReleased(KeyEvent e) {
		}

	}

}
