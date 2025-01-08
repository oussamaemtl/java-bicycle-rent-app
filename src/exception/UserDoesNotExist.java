package exception;

import core.MyVelibCreation;

/**
 * Exception if the wanted user does not exist
 * @author Oussama
 *
 */
public class UserDoesNotExist extends Exception{
private MyVelibCreation e;
	
	public UserDoesNotExist(MyVelibCreation e) {
		this.e =e;
		System.out.println("The user you tried to reach does not exist");
		
	}
	
	public void printMsg() {
		System.out.println("The user you tried to reach does not exist");
	}
}
