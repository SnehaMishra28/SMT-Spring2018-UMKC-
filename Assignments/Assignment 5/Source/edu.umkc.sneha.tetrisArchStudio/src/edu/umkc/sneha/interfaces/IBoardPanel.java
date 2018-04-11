package edu.umkc.sneha.interfaces;

import edu.umkc.sneha.tetris.TetrisImp;
import edu.umkc.sneha.tetrisarchstudio.TileType;


public interface IBoardPanel {

	public void setBoardPanel(TetrisImp tetrisImp);
	public boolean isValidAndEmpty(TileType type, int x, int y, int rotation);
	public void addPiece(TileType type, int x, int y, int rotation);
	public int checkLines();
	public void clear();
	public void repaint();
	public int getColCount();
	public int getRowCount();
	
}
