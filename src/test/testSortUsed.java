package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

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

class testSortUsed {

	@Test
	void testSort_used_station() {
		
		MyVelibCreation e = new MyVelibCreation();
		MyVelib myVelib;
		try {
			myVelib = e.createUniformMyVelibNetwork(10, 10, 4, 75);
			myVelib.reportStation();
			VlibreUser thomas = new VlibreUser(myVelib);
			myVelib.addUser(thomas);
			Station station1 = myVelib.returnStation(1);
			Station station2 = myVelib.returnStation(2);
			Station station3 = myVelib.returnStation(3);
			Station station4 = myVelib.returnStation(4);
			Station station5 = myVelib.returnStation(5);
			Station station6 = myVelib.returnStation(6);
			Station station7 = myVelib.returnStation(7);
			Station station8 = myVelib.returnStation(8);
			Station station9 = myVelib.returnStation(9);
			Station station10 = myVelib.returnStation(10);
			
			thomas.rent("MECHANIC", myVelib.returnStation(1), 50);
			thomas.retour(55, myVelib.returnStation(1));
			
			thomas.rent("MECHANIC", myVelib.returnStation(1), 70);			
			thomas.retour(90, myVelib.returnStation(1));
			
			thomas.rent("MECHANIC", myVelib.returnStation(1), 110);			
			thomas.retour(120, myVelib.returnStation(1));
			
			thomas.rent("MECHANIC", myVelib.returnStation(1), 220);			
			thomas.retour(250, myVelib.returnStation(1));
			
			thomas.rent("MECHANIC", myVelib.returnStation(1), 330);			
			thomas.retour(340, myVelib.returnStation(1));
			
			
			thomas.rent("MECHANIC", station2, 350);
			thomas.retour(450, station2);
			
			thomas.rent("MECHANIC", station2, 550);
			thomas.retour(650, station2);
			
			thomas.rent("MECHANIC", station2, 750);
			thomas.retour(850, station2);
			
			thomas.rent("MECHANIC", station2, 950);
			thomas.retour(1050, station2);
			
			
			thomas.rent("MECHANIC", station3, 1100);
			thomas.retour(1200, station3);
			
			thomas.rent("MECHANIC", station3, 1300);
			thomas.retour(1400, station3);
			
			thomas.rent("MECHANIC", station3, 1500);
			thomas.retour(1600, station3);
			
			
			
			thomas.rent("MECHANIC", station4, 1700);
			thomas.retour(1800, station4);
			
			thomas.rent("MECHANIC", station4, 1900);
			thomas.retour(2000, station4);
			
			thomas.rent("MECHANIC", station5, 2100);
			thomas.retour(2200, station5);
			
			
			thomas.rent("MECHANIC", station5, 2300);
			
			
			
			ArrayList<Station> l = myVelib.sort_used_station();
			ArrayList<Station> l2 = new ArrayList<Station>();
			l2.add(station6);
			l2.add(station7);
			l2.add(station8);
			l2.add(station9);
			l2.add(station10);
			l2.add(station5);
			l2.add(station4);
			l2.add(station3);
			l2.add(station2);
			l2.add(station1);
			boolean res = true;
			for(int i =0; i<10; i++) {
				if(l.get(i)!=l2.get(i)) {
					res = false;
				}
			}
			assertTrue(res);
			
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
