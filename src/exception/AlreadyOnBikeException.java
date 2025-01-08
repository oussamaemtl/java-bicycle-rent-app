package exception;


import core.User;


/**
 * Exception if the user is currently renting a Bycicle.
 * @author Oussama
 *
 */
public class AlreadyOnBikeException extends Exception{
	private User user;
	
	public AlreadyOnBikeException(User user) {
		this.user = user;
	}
	
	public void printMsg() {
		System.out.println("User is already on bike");
	}
}
