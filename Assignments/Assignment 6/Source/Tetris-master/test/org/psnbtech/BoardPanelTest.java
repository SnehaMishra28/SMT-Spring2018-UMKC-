/**
 * 
 */
package org.psnbtech;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.psnbtech.Tetris;
import org.psnbtech.Clock;
import java.util.Random;

/**
 * @author snehamishra
 *
 */
class BoardPanelTest {
	private Tetris tetris;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		tetris = new Tetris();	
		
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
		tetris=null;
	}

	/**
	 * Test method for {@link org.psnbtech.BoardPanel#isValidAndEmpty(org.psnbtech.TileType, int, int, int)}.
	 */
	@Test
	void testIsValidAndEmpty() {
		tetris.random = new Random();
		tetris.isNewGame = true;
		tetris.gameSpeed = 1.0f;
		tetris.logicTimer = new Clock(tetris.gameSpeed);
		tetris.logicTimer.setPaused(true);
		int cleared = 0;
		while(true) {
			//Get the time that the frame started.
			long start = System.nanoTime();
			
			//Update the logic timer.
			tetris.logicTimer.update();
			
			/*
			 * If a cycle has elapsed on the timer, we can update the game and
			 * move our current piece down.
			 */
			if(tetris.logicTimer.hasElapsedCycle()) {
				boolean isEmpty = tetris.board.isValidAndEmpty(tetris.currentType, tetris.currentCol, tetris.currentRow + 1, tetris.currentRotation);
				//Testing whether isValidAndEmpty method returning correct value or not
				//by asserting its return value
				assertTrue("Reached bottom of board, cannot move further", isEmpty);
				if(isEmpty) {
					//Increment the current row if it's safe to do so.
					tetris.currentRow++;
					
				} else {
					/*
					 * We've either reached the bottom of the board, or landed on another piece, so
					 * we need to add the piece to the board.
					 */
					tetris.board.addPiece(tetris.currentType, tetris.currentCol, tetris.currentRow, tetris.currentRotation);
					
					/*
					 * Check to see if adding the new piece resulted in any cleared lines. If so,
					 * increase the player's score. (Up to 4 lines can be cleared in a single go;
					 * [1 = 100pts, 2 = 200pts, 3 = 400pts, 4 = 800pts]).
					 */
					cleared = tetris.board.checkLines();
					if(cleared > 0) {
						tetris.score += 50 << cleared;
					}
					
					/*
					 * Increase the speed slightly for the next piece and update the game's timer
					 * to reflect the increase.
					 */
					tetris.gameSpeed = tetris.gameSpeed + 0.035f;
					tetris.logicTimer.setCyclesPerSecond(tetris.gameSpeed);
					tetris.logicTimer.reset();
					
					/*
					 * Set the drop cooldown so the next piece doesn't automatically come flying
					 * in from the heavens immediately after this piece hits if we've not reacted
					 * yet. (~0.5 second buffer).
					 */
					tetris.dropCooldown = 25;
					
					/*
					 * Update the difficulty level. This has no effect on the game, and is only
					 * used in the "Level" string in the SidePanel.
					 */
					tetris.level = (int)(tetris.gameSpeed * 1.70f);
					
					/*
					 * Spawn a new piece to control.
					 */
					tetris.spawnPiece();
				
				}	
			}
			if(tetris.dropCooldown > 0) {
				tetris.dropCooldown--;
			}
			
			//Display the window to the user.
			tetris.renderGame();
			
			/*
			 * Sleep to cap the framerate.
			 */
			long delta = (System.nanoTime() - start) / 1000000L;
			if(delta < Tetris.FRAME_TIME) {
				try {
					Thread.sleep(Tetris.FRAME_TIME - delta);
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
			
		}
	}

	/**
	 * Test method for {@link org.psnbtech.BoardPanel#addPiece(org.psnbtech.TileType, int, int, int)}.
	 */
	
	@Test
	void testAddPiece() {
		//fail("Not yet implemented");
		
		tetris.random = new Random();
		tetris.isNewGame = true;
		tetris.gameSpeed = 1.0f;
		tetris.logicTimer = new Clock(tetris.gameSpeed);
		tetris.logicTimer.setPaused(true);
		int cleared = 0;
		boolean canWeAddPiece = true;
		boolean isEmpty = true;
		while(true) {
			//Get the time that the frame started.
			long start = System.nanoTime();
			
			//Update the logic timer.
			tetris.logicTimer.update();
			
			/*
			 * If a cycle has elapsed on the timer, we can update the game and
			 * move our current piece down.
			 */
			if(tetris.logicTimer.hasElapsedCycle()) {
				isEmpty = tetris.board.isValidAndEmpty(tetris.currentType, tetris.currentCol, tetris.currentRow + 1, tetris.currentRotation);
				if(isEmpty) {
					//Increment the current row if it's safe to do so.
					tetris.currentRow++;
					
				} else {
					/*
					 * We've either reached the bottom of the board, or landed on another piece, so
					 * we need to add the piece to the board.
					 */
					canWeAddPiece = tetris.board.addPiece(tetris.currentType, tetris.currentCol, tetris.currentRow, tetris.currentRotation);
					//Checking the correctness of addPiece method by asserting its return value
					assertTrue("We cannot move the piece further", canWeAddPiece);
					
					/*
					 * Check to see if adding the new piece resulted in any cleared lines. If so,
					 * increase the player's score. (Up to 4 lines can be cleared in a single go;
					 * [1 = 100pts, 2 = 200pts, 3 = 400pts, 4 = 800pts]).
					 */
					cleared = tetris.board.checkLines();
					if(cleared > 0) {
						tetris.score += 50 << cleared;
					}
					
					/*
					 * Increase the speed slightly for the next piece and update the game's timer
					 * to reflect the increase.
					 */
					tetris.gameSpeed = tetris.gameSpeed + 0.035f;
					tetris.logicTimer.setCyclesPerSecond(tetris.gameSpeed);
					tetris.logicTimer.reset();
					
					/*
					 * Set the drop cooldown so the next piece doesn't automatically come flying
					 * in from the heavens immediately after this piece hits if we've not reacted
					 * yet. (~0.5 second buffer).
					 */
					tetris.dropCooldown = 25;
					
					/*
					 * Update the difficulty level. This has no effect on the game, and is only
					 * used in the "Level" string in the SidePanel.
					 */
					tetris.level = (int)(tetris.gameSpeed * 1.70f);
					
					/*
					 * Spawn a new piece to control.
					 */
					tetris.spawnPiece();
				
				}	
			}
			if(tetris.dropCooldown > 0) {
				tetris.dropCooldown--;
			}
			
			//Display the window to the user.
			tetris.renderGame();
			
			/*
			 * Sleep to cap the framerate.
			 */
			long delta = (System.nanoTime() - start) / 1000000L;
			if(delta < Tetris.FRAME_TIME) {
				try {
					Thread.sleep(Tetris.FRAME_TIME - delta);
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		
	}

	/**
	 * Test method for {@link org.psnbtech.BoardPanel#checkLines()}.
	 */
	
	@Test
	void testCheckLines() {
		//fail("Not yet implemented");
		
		tetris.random = new Random();
		tetris.isNewGame = true;
		tetris.gameSpeed = 1.0f;
		tetris.logicTimer = new Clock(tetris.gameSpeed);
		tetris.logicTimer.setPaused(true);
		int cleared = 0;
		boolean isEmpty = true;
		while(true) {
			//Get the time that the frame started.
			long start = System.nanoTime();
			
			//Update the logic timer.
			tetris.logicTimer.update();
			
			/*
			 * If a cycle has elapsed on the timer, we can update the game and
			 * move our current piece down.
			 */
			if(tetris.logicTimer.hasElapsedCycle()) {
				isEmpty = tetris.board.isValidAndEmpty(tetris.currentType, tetris.currentCol, tetris.currentRow + 1, tetris.currentRotation);
				if(isEmpty) {
					//Increment the current row if it's safe to do so.
					tetris.currentRow++;
					
				} else {
					/*
					 * We've either reached the bottom of the board, or landed on another piece, so
					 * we need to add the piece to the board.
					 */
					tetris.board.addPiece(tetris.currentType, tetris.currentCol, tetris.currentRow, tetris.currentRotation);
					
					/*
					 * Check to see if adding the new piece resulted in any cleared lines. If so,
					 * increase the player's score. (Up to 4 lines can be cleared in a single go;
					 * [1 = 100pts, 2 = 200pts, 3 = 400pts, 4 = 800pts]).
					 */
					cleared = tetris.board.checkLines();
					//Checking the correctness of updateGame() method by 
					//asserting the number of lines to be cleared
					assertTrue("We cannot clear "+cleared+" rows in a single go", tetris.board.checkLines()<=4);
					if(cleared > 0) {
						tetris.score += 50 << cleared;
					}
					
					/*
					 * Increase the speed slightly for the next piece and update the game's timer
					 * to reflect the increase.
					 */
					tetris.gameSpeed = tetris.gameSpeed + 0.035f;
					tetris.logicTimer.setCyclesPerSecond(tetris.gameSpeed);
					tetris.logicTimer.reset();
					
					/*
					 * Set the drop cooldown so the next piece doesn't automatically come flying
					 * in from the heavens immediately after this piece hits if we've not reacted
					 * yet. (~0.5 second buffer).
					 */
					tetris.dropCooldown = 25;
					
					/*
					 * Update the difficulty level. This has no effect on the game, and is only
					 * used in the "Level" string in the SidePanel.
					 */
					tetris.level = (int)(tetris.gameSpeed * 1.70f);
					
					/*
					 * Spawn a new piece to control.
					 */
					tetris.spawnPiece();
				
				}	
			}
			if(tetris.dropCooldown > 0) {
				tetris.dropCooldown--;
			}
			
			//Display the window to the user.
			tetris.renderGame();
			
			/*
			 * Sleep to cap the framerate.
			 */
			long delta = (System.nanoTime() - start) / 1000000L;
			if(delta < Tetris.FRAME_TIME) {
				try {
					Thread.sleep(Tetris.FRAME_TIME - delta);
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}
