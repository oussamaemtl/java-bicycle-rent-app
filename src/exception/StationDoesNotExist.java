package exception;

import core.MyVelibCreation;


/**
 * Exception if the station does not exist in the data base
 * @author Oussama
 *
 */
public class StationDoesNotExist extends Exception{

private MyVelibCreation e;
	
	public StationDoesNotExist(MyVelibCreation e) {
		this.e =e;
		
		
	}

	public void printMsg() {
		System.out.println("The Station you tried to reach does not exist");
	}
	
	
}
