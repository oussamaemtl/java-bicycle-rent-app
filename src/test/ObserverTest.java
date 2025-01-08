package test;

import core.CreditCardUser;
import core.MyVelib;
import core.MyVelibCreation;
import core.VlibreUser;
import core.VmaxUser;
import exception.AlreadyOnBikeException;
import exception.BikeTypeInvalid;
import exception.TooManyBikesException;
import exception.TravelTimeException;

public class ObserverTest {
	
	public static void main(String[] args) {
		
		// We create a basic setup
		
		MyVelibCreation myVelibCreation = new MyVelibCreation();
		
		MyVelib myVelib;
		try {
			myVelib = myVelibCreation.createRandomMyVelibNetwork(80, 20, 10, 10);
			
			
			//WE CREATE 3 USERS
			
			VlibreUser thomas = new VlibreUser(myVelib);
			VmaxUser pat = new VmaxUser(myVelib);
			CreditCardUser lea = new CreditCardUser(myVelib);
			
			myVelib.addUser(thomas);
			myVelib.addUser(pat);
			myVelib.addUser(lea);
	/*		
			//REGISTER OBSERVER
			thomas.registerObserver(myVelib);
			pat.registerObserver(myVelib);
			lea.registerObserver(myVelib);
	*/		
			
			// Plan a ride for each user
			
			double [] start_thomas = {1,1.3};
			double [] end_thomas = {9.8,7};
			String bikeType_thomas = "ELECTRIC";
			int minutes_thomas = 75;
			
			double [] start_pat = {5,5};
			double [] end_pat = {8,2};
			String bikeType_pat = "MECHANIC";
			int minutes_pat = 302;
			
			double [] start_lea = {9,1};
			double [] end_lea = {0.5,8.7};
			String bikeType_lea = "ELECTRIC";
			int minutes_lea = 86;
			
			//THOMAS
			
			try {
				thomas.ridesPlanning(start_thomas, end_thomas, bikeType_thomas);
			} catch (BikeTypeInvalid e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			try {
				thomas.rent(bikeType_thomas);
			} catch (AlreadyOnBikeException e) {
				//TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
			try {
				thomas.retour(minutes_thomas);
			} catch (TravelTimeException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
			//LEA
			
			try {
				lea.ridesPlanning(start_lea, end_lea, bikeType_lea);
			} catch (BikeTypeInvalid e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				lea.rent(bikeType_lea);
			} catch (AlreadyOnBikeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				lea.retour(minutes_lea);
			} catch (TravelTimeException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			//PAT
			
			try {
				pat.ridesPlanning(start_pat, end_pat, bikeType_pat);
			} catch (BikeTypeInvalid e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				pat.rent(bikeType_pat);
			} catch (AlreadyOnBikeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				pat.retour(minutes_pat);
			} catch (TravelTimeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			myVelib.report();
			
		} catch (TooManyBikesException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
		
	}
}
