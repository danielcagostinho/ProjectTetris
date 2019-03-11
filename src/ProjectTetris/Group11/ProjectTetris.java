/**
* @mainpage Main Page
* This is Team Tetra's (Group 11) final project. It is a redevelopment of the classic game Tetris.
* @file ProjectTetris.java
* @title ProjectTetris
* @author Anthony Chang, Daniel Agostinho, Divya Sridhar
* @date 2016/12/07
* @brief This class runs the game. 
* @details This class contains the main method used to start and run the game.
*/

package ProjectTetris.Group11;

import ProjectTetris.Group11.Controller.TetrisController;

/* ProjectTetris class
 * Author: Anthony Chang, Daniel Agostinho, Divya Sridhar
 *
 * This class is used to run the game. It creates a new TetrisController in the main method.
 */
public class ProjectTetris {

/**
* @brief Main method. Runs the game by creating the TetrisController.
*/
	public static void main(String[] args) {
		new TetrisController();
	}
}

