package ProjectTetris.Group11.Model;


import java.awt.Color;
import java.awt.Point;

/* Piece class
 * Author: Anthony Chang, Daniel Agostinho, Divya Sridhar
 *
 * This class is part of the model. It represents the tetromino pieces in the game and controls their behaviour.
 */
public class Piece {

/** 
* a private variable for the width
*/
	private Point[][] pieceCoords;
/** 
* a private variable for the width
*/
	private int pieceX;
/** 
* a private variable for the width
*/
	private int pieceY;
/** 
* a private variable for the width
*/
	private int orientation;
/** 
* a private variable for the width
*/
	private Color pieceColor;
	
/** 
* @brief Constructor for Piece
* @details Constructor creates and sets the coordinates and color of the pieces.
* @param pieceCoords (x,y) coordinates of the shape of the piece.
* @param pieceX X coordinates.
* @param pieceY Y coordinates.
* @param pieceColor Color.
*/
	public Piece(Point[][] pieceCoords, int pieceX, int pieceY, Color pieceColor){
		this.pieceCoords = pieceCoords;
		this.pieceX = pieceX;
		this.pieceY = pieceY;
		this.pieceColor = pieceColor;
		orientation = 0;
	}
	
/**
* @brief This function drops the piece down 1 line.
*/
	public void pieceDrop(){
		pieceY += 1;
	}
	
/**
* @brief This function moves the piece left over 1 line.
*/
	public void moveLeft(){
		pieceX -= 1;
	}

/**
* @brief This function moves the piece right over 1 line.
*/
	public void moveRight(){
		pieceX += 1;
	}

/**
* @brief This function rotates the piece CCW.
* @param newO new orientation of the piece.
*/
	public void rotateLeft(int newO){
		orientation = newO;
	}
	
/**
* @brief This function rotates the piece CW.
* @param newO new orientation of the piece.
*/
	public void rotateRight(int newO){
		orientation = newO;
	}
	
	

	//Getters and Setters
/**
* @brief This function returns the coordinates of a piece at the given position.
* @param o Position of piece.
* @return pieceCoords
*/
	public Point[] getPieceCoords(int o) {
		return pieceCoords[o];
	}

/**
* @brief This function sets the coordinates of a piece.
* @param pieceCoords coordinates of the new position.
*/
	public void setPieceCoords(Point[][] pieceCoords) {
		this.pieceCoords = pieceCoords;
	}
	
/**
* @brief This function returns the X coordinate.
* @return pieceX
*/
	public int getPieceX() {
		return pieceX;
	}
	
/**
* @brief This function sets the X coordinate.
* @param pieceX new X coordinate.
*/
	public void setPieceX(int pieceX) {
		this.pieceX = pieceX;
	}
	
/**
* @brief This function returns the Y coordinate.
* @return pieceY
*/
	public int getPieceY() {
		return pieceY;
	}
	
/**
* @brief This function sets the Y coordinate.
* @param pieceY new Y coordinate.
*/
	public void setPieceY(int pieceY) {
		this.pieceY = pieceY;
	}
	
/**
* @brief This function returns the orientation of the piece.
* @return orientation
*/
	public int getOrientation() {
		return orientation;
	}
	
/**
* @brief This function sets the orientation of a piece.
* @param orientation New orientation for the piece.
*/
	public void setOrientation(int orientation) {
		this.orientation = orientation;
	}

/**
* @brief This function returns the color of the piece.
* @return pieceColor
*/
	public Color getColor() {
		return pieceColor;
	}
	
	
}
