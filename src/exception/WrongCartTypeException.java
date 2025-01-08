package exception;

import core.MyVelib;


/**
 * Exception if the type of card choosen by the user is invalid
 * @author Oussama
 *
 */
public class WrongCartTypeException extends Exception{
	private MyVelib myVelib;
	
	public WrongCartTypeException(MyVelib myVelib) {
		this.myVelib = myVelib;
	}
	
	public void printMsg() {
		System.out.println("This type of card does not exist. Choose between VLIBRE, VMAX or NONE");
	}

}
