package exception;

import core.Station;


/**
 * Exception if there is no mechanic bikes at the wanted station
 * @author Oussama
 *
 */
public class NoMechanicException extends Exception{
	
	private Station s;
	
	public NoMechanicException(Station s ) {
		this.s = s;
	}
	
	public void printMsg() {
		System.out.println("There are no MECHANIC Bycicle in this station");
	}

}
