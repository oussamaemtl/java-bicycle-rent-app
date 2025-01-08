package exception;

import core.MyVelibCreation;


/**
 * Exception if the velib network wanted does not exist
 * @author Oussama
 *
 */
public class MyVelibNetworkUnexistent extends Exception{
	
	private MyVelibCreation e;
	
	public MyVelibNetworkUnexistent(MyVelibCreation e) {
		this.e =e;
		
		
	}
	
	
	public void printMsg() {
		System.out.println("The velib network you tried to reach does not exist");
	}
}
