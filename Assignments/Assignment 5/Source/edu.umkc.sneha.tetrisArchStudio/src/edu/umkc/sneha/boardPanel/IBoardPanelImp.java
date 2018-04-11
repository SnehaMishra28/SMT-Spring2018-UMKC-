package edu.umkc.sneha.boardPanel;


import edu.umkc.sneha.boardPanel.BoardPanelArch;

import edu.umkc.sneha.tetris.TetrisImp;

import edu.umkc.sneha.tetrisarchstudio.TileType;

public interface IBoardPanelImp 
{

	/*
	  Getter and Setter of architecture reference
	*/
    public void setArch (BoardPanelArch arch);
	public BoardPanelArch getArch();
	
	/*
  	  Myx Lifecycle Methods: these methods are called automatically by the framework
  	  as the bricks are created, attached, detached, and destroyed respectively.
	*/	
	public void init();	
	public void begin();
	public void end();
	public void destroy();

	/*
  	  Implementation primitives required by the architecture
	*/
       
  
    //To be imported: TetrisImp,TileType
    public void setBoardPanel (TetrisImp tetrisImp)  ;        
    public boolean isValidAndEmpty (TileType type,int x,int y,int rotation)  ;        
    public void addPiece (TileType type,int x,int y,int rotation)  ;        
    public int checkLines ()  ;        
    public void clear ()  ;        
    public void repaint ()  ;        
    public int getColCount ()  ;        
    public int getRowCount ()  ;        
}