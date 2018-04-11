package edu.umkc.sneha.tetris;


import edu.uci.isr.myx.fw.AbstractMyxSimpleBrick;
import edu.uci.isr.myx.fw.IMyxName;
import edu.uci.isr.myx.fw.MyxUtils;

import edu.umkc.sneha.interfaces.IBoardPanel;
import edu.umkc.sneha.interfaces.IClock;
import edu.umkc.sneha.interfaces.ISidePanel;
import edu.umkc.sneha.interfaces.ITetris;

import edu.umkc.sneha.tetrisarchstudio.TileType;

public class TetrisArch extends AbstractMyxSimpleBrick implements ITetris
{
    public static final IMyxName msg_IClock = MyxUtils.createName("edu.umkc.sneha.interfaces.IClock");
    public static final IMyxName msg_IBoardPanel = MyxUtils.createName("edu.umkc.sneha.interfaces.IBoardPanel");
    public static final IMyxName msg_ITetris = MyxUtils.createName("edu.umkc.sneha.interfaces.ITetris");
    public static final IMyxName msg_ISidePanel = MyxUtils.createName("edu.umkc.sneha.interfaces.ISidePanel");

    public IClock OUT_IClock;
    public IBoardPanel OUT_IBoardPanel;
    public ISidePanel OUT_ISidePanel;

	private ITetrisImp _imp;

    public TetrisArch (){
		_imp = getImplementation();
		if (_imp != null){
			_imp.setArch(this);
		} else {
			System.exit(1);
		}
	}
    
    protected ITetrisImp getImplementation(){
        try{
			return new TetrisImp();    
        } catch (Exception e){
            System.err.println(e.getMessage());
            return null;
        }
    }

    public void init(){
        _imp.init();
    }
    
    public void begin(){
        OUT_IClock = (IClock) MyxUtils.getFirstRequiredServiceObject(this,msg_IClock);
        if (OUT_IClock == null){
 			System.err.println("Error: Interface edu.umkc.sneha.interfaces.IClock returned null");
			return;       
        }
        OUT_IBoardPanel = (IBoardPanel) MyxUtils.getFirstRequiredServiceObject(this,msg_IBoardPanel);
        if (OUT_IBoardPanel == null){
 			System.err.println("Error: Interface edu.umkc.sneha.interfaces.IBoardPanel returned null");
			return;       
        }
        OUT_ISidePanel = (ISidePanel) MyxUtils.getFirstRequiredServiceObject(this,msg_ISidePanel);
        if (OUT_ISidePanel == null){
 			System.err.println("Error: Interface edu.umkc.sneha.interfaces.ISidePanel returned null");
			return;       
        }
        _imp.begin();
    }
    
    public void end(){
        _imp.end();
    }
    
    public void destroy(){
        _imp.destroy();
    }
    
	public Object getServiceObject(IMyxName arg0) {
		if (arg0.equals(msg_ITetris)){
			return this;
		}        
		return null;
	}
  
    //To be imported: TileType
    public void startGame ()   {
		_imp.startGame();
    }    
    public boolean isPaused ()   {
		return _imp.isPaused();
    }    
    public boolean isGameOver ()   {
		return _imp.isGameOver();
    }    
    public boolean isNewGame ()   {
		return _imp.isNewGame();
    }    
    public TileType getPieceType ()   {
		return _imp.getPieceType();
    }    
    public int getPieceCol ()   {
		return _imp.getPieceCol();
    }    
    public int getPieceRow ()   {
		return _imp.getPieceRow();
    }    
    public int getPieceRotation ()   {
		return _imp.getPieceRotation();
    }    
}