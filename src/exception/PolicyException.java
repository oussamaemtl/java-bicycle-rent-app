package exception;

import core.MyVelib;

/**
 * Exception if the arguments for policy are invalid
 * @author Oussama
 *
 */
public class PolicyException extends Exception{
	
	private MyVelib v;

	public PolicyException(MyVelib v) {
		this.v= v;
	}
	
	public void printMsg() {
		System.out.println("Invalid Policy Arguments. Choose between USED and OCCUPIED");
	}
	
}
