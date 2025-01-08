package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import core.MyVelib;
import core.MyVelibCreation;
import core.Station;
import core.VlibreUser;
import exception.FullStationException;
import exception.NotOnBikeException;
import exception.StationOfflineException;
import exception.TooManyBikesException;
import exception.TravelTimeException;

class StationTest {

	@Test
	void testReturnRatio() {
		MyVelibCreation e = new MyVelibCreation();
		MyVelib myVelib;
		try {
			myVelib = e.createUniformMyVelibNetwork(10, 10, 4, 75);
			VlibreUser thomas = new VlibreUser(myVelib);
			myVelib.addUser(thomas);
			Station station1 = myVelib.returnStation(1);
			thomas.rent("MECHANIC", myVelib.returnStation(1), 50);
			thomas.retour(55, myVelib.returnStation(10));
			
			thomas.rent("MECHANIC", myVelib.returnStation(1), 70);
			
			thomas.retour(90, myVelib.returnStation(10));
			
			thomas.rent("MECHANIC", myVelib.returnStation(1), 110);
			
			thomas.retour(120, myVelib.returnStation(1));
			
			thomas.rent("MECHANIC", myVelib.returnStation(1), 220);
			
			thomas.retour(250, myVelib.returnStation(1));
			
			thomas.rent("MECHANIC", myVelib.returnStation(1), 330);
			
			thomas.retour(340, myVelib.returnStation(10));
			
			double r = station1.returnRatio(0, 330);
			
			assertTrue(0.31 <= r && r <=0.33);
		} catch (TooManyBikesException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (TravelTimeException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (FullStationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (StationOfflineException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (NotOnBikeException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}
