package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import core.MyVelib;
import core.MyVelibCreation;
import core.*;
import exception.MyVelibNetworkUnexistent;
import exception.StationDoesNotExist;
import exception.UserDoesNotExist;
import exception.WrongCartTypeException;

class MyVelibCreationTest {

	@Test
	void testAddUser() {
		MyVelibCreation e = new MyVelibCreation();
		e.setup("velib");
		
		boolean b;
		try {
			e.addUser("thomas", "NONE", "velib");
			b = e.userDataBase.containsKey("thomas");
			assertTrue(b);
		} catch (MyVelibNetworkUnexistent e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (WrongCartTypeException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}

	@Test
	void testRidesPlanning() {
		MyVelibCreation e = new MyVelibCreation();
		e.setup("velib");
		String userName = "thomas";
		String bikeType = "mechanic";
		String velibnetworkName = "velib";
		double [] start = {0,0};
		double [] end = {4,4};
		
		try {
			e.addUser(userName, "NONE", "velib");
			e.ridesPlanning(userName, start, end, bikeType, velibnetworkName);
			int Id = e.userDataBase.get(userName);
			MyVelib v = e.velibDataBase.get(velibnetworkName);
			User user = v.returnUser(Id);
			assertTrue(user.getStart() != null && user.getDest() != null);
			
		} catch (MyVelibNetworkUnexistent | WrongCartTypeException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (UserDoesNotExist e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}


	@Test
	void testReturntBikeIntIntIntString() {
		MyVelibCreation e = new MyVelibCreation();
		e.setup("velib");
		String userName = "thomas";
		String bikeType = "mechanic";
		String velibnetworkName = "velib";
		MyVelib v = e.velibDataBase.get(velibnetworkName);
		Station station = v.returnStation(30);
		try {
			e.addUser(userName, "NONE", velibnetworkName);
			e.rentBike(2, 30, bikeType, velibnetworkName, 1);
			e.returntBike(2, 30, 78, velibnetworkName);
			assertTrue(station.hasbeenused && station.getNumUtiliy() == 2);
		} catch (MyVelibNetworkUnexistent | WrongCartTypeException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (UserDoesNotExist e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (StationDoesNotExist e1) {
			// TODO Auto-generated catch block
			System.out.println("testReturntBikeIntIntIntString");
			e1.printStackTrace();
		}
	}

	@Test
	void testOffline() {
		MyVelibCreation e = new MyVelibCreation();
		e.setup("velib");
		//e.displayVelib();
		String velibnetworkName = "velib";
		MyVelib v = e.velibDataBase.get(velibnetworkName);
		Station station = v.returnStation(1);
		try {
			e.offline(velibnetworkName, 1);
			assertTrue(station.isOnline() == false);
		} catch (MyVelibNetworkUnexistent | StationDoesNotExist e1) {
			// TODO Auto-generated catch block
			System.out.println("testOffline");
			e1.printStackTrace();
		}
	}


}
