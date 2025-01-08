package core;

import exception.AlreadyOnBikeException;
import exception.TravelTimeException;

/**
 * Return and rent of bikes
 * @author Oussama
 *
 */
public interface Return_Rent {
	
	public void retour(int minutes) throws TravelTimeException;
	
	public void rent(String bikeType) throws AlreadyOnBikeException;
	
	
}
