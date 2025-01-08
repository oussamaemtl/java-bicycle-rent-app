package exception;

import core.MyVelibCreation;

/**
 * Exception when creating a velib environment and that number
 * of bikes exceed the velib total capacity
 * @author Oussama
 *
 */
public class TooManyBikesException extends Exception{
	
	private MyVelibCreation m;
	
	public TooManyBikesException(MyVelibCreation m) {
		this.m = m;
	}
	
	public void printMsg() {
		System.out.println("You entered too many bycicles");
	}

}
