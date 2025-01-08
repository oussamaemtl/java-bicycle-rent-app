package test;

import core.Bycicle;
import core.MyVelib;
import core.Station;
import core.VlibreUser;
import exception.BikeTypeInvalid;

public class TestRidingPlanning {

	public static void main(String[] args) {
		
		MyVelib myVelib = new MyVelib();
		
		double [] gps1 = {0,0};
		double [] gps2 = {5,5};
		double [] gps3 = {0,10};
		double [] gps4 = {10,0};
		double [] gps5 = {10,10};
		
		int capacity = 10;
		
		VlibreUser pat = new VlibreUser(myVelib);
		
		Station station1 = new Station(capacity, gps1, myVelib);
		Station station2 = new Station(capacity, gps2, myVelib);
		Station station3 = new Station(capacity, gps3, myVelib);
		Station station4 = new Station(capacity, gps4, myVelib);
		Station station5 = new Station(capacity, gps5, myVelib);
		
		myVelib.addStation(station1);
		myVelib.addStation(station2);
		myVelib.addStation(station3);
		myVelib.addStation(station4);
		myVelib.addStation(station5);
		
		
		for(int i=0;i<10;i++) {
			if(i<3) {
			station1.addBike(new Bycicle("ELECTRIC",station1));
			station2.addBike(new Bycicle("ELECTRIC",station2));}
			else {
				station1.addBike(new Bycicle("MECHANIC",station1));
				station2.addBike(new Bycicle("MECHANIC",station2));
			}
			
		}
		
		myVelib.report();
		
		double [] start = {0,10};
		double [] end = {0,0};
		String bikeType = "MECHANIC";
		
		try {
			pat.ridesPlanning(start, end, bikeType);
		} catch (BikeTypeInvalid e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
