/**
 * 
 */
package edu.umkc.sneha.myextension;

import edu.umkc.sneha.myplugin.IGreeter;

/**
 * @author snehamishra
 *
 */
public class GreeterSpanish implements IGreeter {

	/**
	 * 
	 */
	public GreeterSpanish() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see edu.umkc.sneha.myplugin.IGreeter#sayHello()
	 */
	@Override
	public String sayHello() {
		// TODO Auto-generated method stub
		return "Spanish - Hola!!";
	}

}
