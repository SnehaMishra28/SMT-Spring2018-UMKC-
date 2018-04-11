package edu.umkc.sneha.interfaces;

 import edu.umkc.sneha.tetrisarchstudio.TileType;

public interface ITetris {
	
	public void startGame();
	public boolean isPaused();
	public boolean isGameOver();
	public boolean isNewGame();
	public TileType getPieceType();
	public int getPieceCol();
	public int getPieceRow();
	public int getPieceRotation();

}
