package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import core.MyVelib;
import core.MyVelibCreation;
import core.Station;
import core.VlibreUser;
import exception.BikeTypeInvalid;
import exception.TooManyBikesException;
import exception.TravelTimeException;

class UserTest {

	@Test
	void testRidesPlanning() {
		MyVelibCreation e =  new MyVelibCreation();
		try {
			MyVelib myVelib = e.createUniformMyVelibNetwork(10, 10, 5, 75);
			VlibreUser ouss = new VlibreUser(myVelib);
			double [] start = {0,0};
			double [] end = {4,4};
			ouss.ridesPlanning(start, end, "electric");
			assertTrue(ouss.getStart()!=null && ouss.getDest()!=null);
		} catch (TooManyBikesException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (BikeTypeInvalid e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}
