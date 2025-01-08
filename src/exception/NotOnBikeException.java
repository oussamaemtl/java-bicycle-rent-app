package exception;

import core.User;

/**
 * Exception if user want to return a bike but is not currently renting a bike
 * @author Oussama
 *
 */
public class NotOnBikeException extends Exception{
	
	private User user;
	
	public NotOnBikeException(User user) {
		this.user = user;
	}
	
	public void printMsg() {
		System.out.println("This User has to be on a bike to return a bike");
	}

}
