package exception;

import core.User;

/**
 * Exception if the rent time is inferior than the previous return time
 * or if the return time is inferior than the previous rent time
 * @author Oussama
 *
 */
public class TravelTimeException extends Exception{

	private User user;
	
	public TravelTimeException(User u) {
		this.user = u;
		
	}
	
	public void printMsg(){
		if(this.user.getLast_time_rent() != -1) {
			System.out.println("You cannot choose a time to return lesser than the last time \n"+
					"you rent a bike. (i.e : " + this.user.getLast_time_rent() +")");
			
		}
		else {
		System.out.println("You cannot choose a time to rent lesser than the last time \n"+
					"you returned a bike. (i.e : " + this.user.getLast_time_return() + ")");
		}
	}
}
