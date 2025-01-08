package test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import core.CreditCardUser;
import core.MyVelib;
import core.MyVelibCreation;
import core.Station;
import exception.BikeTypeInvalid;
import exception.TooManyBikesException;
import exception.TravelTimeException;

class CreditCardUserTest {

	@Test
	void testPrice() {
		MyVelibCreation e =  new MyVelibCreation();
		try {
			MyVelib myVelib = e.createUniformMyVelibNetwork(10, 10, 5, 75);
			CreditCardUser ouss = new CreditCardUser(myVelib);
			Station station = myVelib.returnStation(1);
			ouss.rent("mechanic", station, 1);
			assertTrue(ouss.price(322) ==  6);
		} catch (TooManyBikesException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (BikeTypeInvalid e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (TravelTimeException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

}
