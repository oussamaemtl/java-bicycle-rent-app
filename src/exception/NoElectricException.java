package exception;

import core.Station;



/**
 * Exeption if no eclectric bikes are available at the anted station
 * @author Oussama
 *
 */
public class NoElectricException extends Exception{

	private Station s;
	
	public NoElectricException(Station s) {
		this.s = s;
	}
	
	public void printMsg() {
		System.out.println("There are no ELECTRIC Bycycle in this Station");
	}
	
}
