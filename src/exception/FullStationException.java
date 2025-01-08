package exception;

import core.Station;
	

/**
 * Exception if the station is full
 * @author Oussama
 *
 */
public class FullStationException extends Exception{
	
	private Station s;
	
	public FullStationException(Station s) {
		this.s = s;
	}
	
	public void printMsg() {
		System.out.println("This Station is Full");
	}

}
