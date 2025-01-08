package exception;

import core.Station;

/**
 * Exception if the wanted station is currently offline
 * @author Oussama
 *
 */
public class StationOfflineException extends Exception{
	
	private Station s;
	
	public StationOfflineException(Station s) {
		this.s = s;
	}
	
	public void printMsg() {
		System.out.println("The station you put is currently OFFLINE");
	}

}
