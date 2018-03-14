package com.sneha.tetrisgame.plugin.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;

import com.sneha.tetrisgame.plugin.BoardPanel;
import com.sneha.tetrisgame.plugin.Clock;
import com.sneha.tetrisgame.plugin.Tetris;

/**
 * Our sample handler extends AbstractHandler, an IHandler base class.
 * @see org.eclipse.core.commands.IHandler
 * @see org.eclipse.core.commands.AbstractHandler
 */
public class SampleHandler extends AbstractHandler {

	public SampleHandler() {
	}

	/**
	 * the command has been executed, so extract extract the needed information
	 * from the application context.
	 */
	public Object execute(ExecutionEvent event) throws ExecutionException {		
		startGame();
		startBoard();
		startClock();

		return null;
	}

	
  private String startGame() {
		
		IConfigurationElement[] config = Platform.getExtensionRegistry()
				.getConfigurationElementsFor("tetris.ext");
		try {
			for (IConfigurationElement e : config) {
				System.out.println("Evaluating extension");
				final Object o = e.createExecutableExtension("Class");
				if (o instanceof Tetris) {
					((Tetris) o).startGame();
				}
			}
			return null;
		} catch (CoreException ex) {
			System.out.println(ex.getMessage());
			return null;
		}
  }
  
  private String startBoard() {
		
		IConfigurationElement[] config = Platform.getExtensionRegistry()
				.getConfigurationElementsFor("tetris.ext");
		try {
			for (IConfigurationElement e : config) {
				System.out.println("Evaluating extension");
				final Object o = e.createExecutableExtension("BoardPanel");
				if (o instanceof BoardPanel) {
					((BoardPanel) o).checkLines();
				}
			}
			return null;
		} catch (CoreException ex) {
			System.out.println(ex.getMessage());
			return null;
		}
}
  
  private String startClock() {
		
		IConfigurationElement[] config = Platform.getExtensionRegistry()
				.getConfigurationElementsFor("tetris.ext");
		try {
			for (IConfigurationElement e : config) {
				System.out.println("Evaluating extension");
				final Object o = e.createExecutableExtension("Clock");
				if (o instanceof Clock) {
					((Clock) o).reset();
				}
			}
			return null;
		} catch (CoreException ex) {
			System.out.println(ex.getMessage());
			return null;
		}
}
}
