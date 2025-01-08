package exception;

import core.User;



/**
 * Exception if the bike type chosen is not ELECTRIC or MECHANIC
 * @author Oussama
 *
 */
public class BikeTypeInvalid extends Exception{
	
	private User user;
	
	public BikeTypeInvalid(User user) {
		this.user = user;
	}
	
	public void printMsg() {
		System.out.println("Bike type invalid \n"+
							"you must tap ELECTRIC or MECHANIC");
	}
}
