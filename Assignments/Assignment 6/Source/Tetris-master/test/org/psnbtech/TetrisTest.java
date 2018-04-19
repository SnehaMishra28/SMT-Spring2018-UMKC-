/**
 * 
 */
package org.psnbtech;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author snehamishra
 *
 */
class TetrisTest {
	
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
		tetris = null;
	}

	/**
	 * Test method for {@link org.psnbtech.Tetris#updateGame()}.
	 */
	@Test
	void testUpdateGame() {
		//fail("Not yet implemented");
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
				cleared = tetris.updateGame();
				//At any time, the no. of rows cleared should be <=4 
				assertTrue("Cleared rows cannot be more than 4 at once", cleared<=4);
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
