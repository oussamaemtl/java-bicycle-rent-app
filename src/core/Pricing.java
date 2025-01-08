package core;

import exception.BikeTypeInvalid;

/**
 * Price of user ride
 * @author Oussama
 *
 */
public interface Pricing {
	
	public int price(int minutes) throws BikeTypeInvalid;
}
