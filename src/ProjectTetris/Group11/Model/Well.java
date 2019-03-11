package ProjectTetris.Group11.Model;


import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;

/* Well class
 * Author: Anthony Chang, Daniel Agostinho, Divya Sridhar
 *
 * This class is part of the model. It represents the well in the game and is responsible for fixing pieces to the well.
 */
public class Well {

/** 
* a private variable that lists the coordinate points that create the well
*/
	private ArrayList<Point> wellCoords = new ArrayList<Point>(Arrays.asList(new Point(0, 22), new Point(1, 22),
			new Point(2, 22), new Point(3, 22), new Point(4, 22), new Point(5, 22), new Point(6, 22), new Point(7, 22),
			new Point(8, 22), new Point(9, 22), new Point(10, 22), new Point(11, 22), new Point(0, 0), new Point(11, 0),
			new Point(0, 1), new Point(0, 2), new Point(0, 3), new Point(0, 4), new Point(0, 5), new Point(0, 6),
			new Point(0, 7), new Point(0, 8), new Point(0, 9), new Point(0, 10), new Point(0, 11), new Point(0, 12),
			new Point(0, 13), new Point(0, 14), new Point(0, 15), new Point(0, 16), new Point(0, 17), new Point(0, 18),
			new Point(0, 19), new Point(0, 20), new Point(0, 21), new Point(0, 22), new Point(11, 1), new Point(11, 2),
			new Point(11, 3), new Point(11, 4), new Point(11, 5), new Point(11, 6), new Point(11, 7), new Point(11, 8),
			new Point(11, 9), new Point(11, 10), new Point(11, 11), new Point(11, 12), new Point(11, 13),
			new Point(11, 14), new Point(11, 15), new Point(11, 16), new Point(11, 17), new Point(11, 18),
			new Point(11, 19), new Point(11, 20), new Point(11, 21), new Point(11, 22)));
/** 
* a private variable for the border color
*/
	private final Color borderColor = Color.GRAY;
/** 
* a private variable for the well color
*/
	private final Color wellColor = new Color(192, 192, 192);

/** 
* @brief Constructor for the Well
* @details Constructor creates the well object.
*/
	public Well() {

	}

/**
* @brief This function adds a piece to the well.
* @param pieceCoords orientation coordinates of piece.
* @param pieceX x coordinate of piece.
* @param pieceY y coordinate of piece.
*/
	public void addToWell(Point[] pieceCoords, int pieceX, int pieceY) {
		for (Point p : pieceCoords) {
			wellCoords.add(new Point(p.x + pieceX, p.y + pieceY));
		}
	}

/**
* @brief This function returns the coordinates for the well.
* @return wellCoords
*/
	public ArrayList<Point> getWellCoords() {
		return wellCoords;

	}

/**
* @brief This function sets the coordinates for the well.
* @param wellCoords new coordinates for the well.
*/
	public void setWellCoords(ArrayList<Point> wellCoords) {
		this.wellCoords = wellCoords;
	}

/**
* @brief This function returns the color of the border.
* @return borderColor
*/
	public Color getBorderColor() {
		return borderColor;
	}

/**
* @brief This function returns the color of the well.
* @return wellColor
*/
	public Color getWellColor() {
		return wellColor;
	}

}
