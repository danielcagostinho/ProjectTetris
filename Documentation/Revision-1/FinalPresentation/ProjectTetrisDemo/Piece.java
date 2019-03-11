

import java.awt.Color;
import java.awt.Point;

public class Piece {

	private Point[][] pieceCoords;
	private int pieceX;
	private int pieceY;
	private int orientation;
	private Color pieceColor;
	
	public Piece(Point[][] pieceCoords, int pieceX, int pieceY, Color pieceColor){
		this.pieceCoords = pieceCoords;
		this.pieceX = pieceX;
		this.pieceY = pieceY;
		this.pieceColor = pieceColor;
		orientation = 0;
	}
	
	
	public void pieceDrop(){
		pieceY += 1;
	}
	
	public void moveLeft(){
		pieceX -= 1;
	}
	
	public void moveRight(){
		pieceX += 1;
	}
	
	public void rotateLeft(int newO){
		orientation = newO;
	}
	public void rotateRight(int newO){
		orientation = newO;
	}
	
	
	
	
	
	
	
	//Getters and Setters
	public Point[] getPieceCoords(int o) {
		return pieceCoords[o];
	}
	public void setPieceCoords(Point[][] pieceCoords) {
		this.pieceCoords = pieceCoords;
	}
	public int getPieceX() {
		return pieceX;
	}
	public void setPieceX(int pieceX) {
		this.pieceX = pieceX;
	}
	public int getPieceY() {
		return pieceY;
	}
	public void setPieceY(int pieceY) {
		this.pieceY = pieceY;
	}
	public int getOrientation() {
		return orientation;
	}
	public void setOrientation(int orientation) {
		this.orientation = orientation;
	}


	public Color getColor() {
		return pieceColor;
	}
	
	
}
