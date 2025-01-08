package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import core.*;
import exception.BikeTypeInvalid;
import exception.FullStationException;
import exception.NotOnBikeException;
import exception.StationOfflineException;
import exception.TooManyBikesException;
import exception.TravelTimeException;
class VlibreUserTest {

	@Test
	void testPrice() {
		MyVelibCreation e =  new MyVelibCreation();
		try {
			MyVelib myVelib = e.createUniformMyVelibNetwork(10, 10, 5, 75);
			VlibreUser ouss = new VlibreUser(myVelib);
			Station station = myVelib.returnStation(1);
			ouss.rent("electric", station, 1);
			assertTrue(ouss.price(322) ==  11);
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

	@Test
	void testRentStringInt() {
		MyVelibCreation e =  new MyVelibCreation();
		try {
			MyVelib myVelib = e.createUniformMyVelibNetwork(10, 10, 5, 75);
			VlibreUser ouss = new VlibreUser(myVelib);
			double [] start = {0,0};
			double [] end = {4,4};
			ouss.ridesPlanning(start, end, "electric");
			ouss.rent("electric", 1);
			Station station = ouss.getStart();
			assertTrue(station.hasbeenused);
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


	@Test
	void testRetourInt() {
		MyVelibCreation e =  new MyVelibCreation();
		try {
			MyVelib myVelib = e.createUniformMyVelibNetwork(10, 10, 5, 75);
			VlibreUser ouss = new VlibreUser(myVelib);
			double [] start = {0,0};
			double [] end = {4,4};
			ouss.ridesPlanning(start, end, "electric");
			ouss.rent("electric", 1);
			Station station = ouss.getDest();
			ouss.retour(322);
			
			assertTrue(station.getNumUtiliy() == 1 && ouss.getTotalAmountCharges() == 11);
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
