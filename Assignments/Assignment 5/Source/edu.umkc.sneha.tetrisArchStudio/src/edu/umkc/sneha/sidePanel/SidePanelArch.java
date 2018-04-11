package edu.umkc.sneha.sidePanel;


import edu.uci.isr.myx.fw.AbstractMyxSimpleBrick;
import edu.uci.isr.myx.fw.IMyxName;
import edu.uci.isr.myx.fw.MyxUtils;

import edu.umkc.sneha.interfaces.IBoardPanel;
import edu.umkc.sneha.interfaces.ISidePanel;

import edu.umkc.sneha.tetris.TetrisImp;

public class SidePanelArch extends AbstractMyxSimpleBrick implements ISidePanel
{
    public static final IMyxName msg_ISidePanel = MyxUtils.createName("edu.umkc.sneha.interfaces.ISidePanel");
    public static final IMyxName msg_IBoardPanel = MyxUtils.createName("edu.umkc.sneha.interfaces.IBoardPanel");

    public IBoardPanel OUT_IBoardPanel;

	private ISidePanelImp _imp;

    public SidePanelArch (){
		_imp = getImplementation();
		if (_imp != null){
			_imp.setArch(this);
		} else {
			System.exit(1);
		}
	}
    
    protected ISidePanelImp getImplementation(){
        try{
			return new SidePanelImp();    
        } catch (Exception e){
            System.err.println(e.getMessage());
            return null;
        }
    }

    public void init(){
        _imp.init();
    }
    
    public void begin(){
        OUT_IBoardPanel = (IBoardPanel) MyxUtils.getFirstRequiredServiceObject(this,msg_IBoardPanel);
        if (OUT_IBoardPanel == null){
 			System.err.println("Error: Interface edu.umkc.sneha.interfaces.IBoardPanel returned null");
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
		if (arg0.equals(msg_ISidePanel)){
			return this;
		}        
		return null;
	}
  
    //To be imported: TetrisImp
    public void setSidePanel (TetrisImp tetrisImp)   {
		_imp.setSidePanel(tetrisImp);
    }    
    public void repaint ()   {
		_imp.repaint();
    }    
}