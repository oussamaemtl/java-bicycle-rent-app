package core;

import java.util.ArrayList; 
import java.util.Collections;

import comparator.DistanceComparator;
import exception.BikeTypeInvalid;

/**
 * User
 * @author Oussama
 *
 */
public class User {
	
	/**
	 * Static counter for unique ID
	 */
	private static int counter;
	/**
	 * ID of user
	 */
	private int ID;
	/**
	 * Position of user
	 */
	private double [] GPS = new double[2];
	/**
	 * balance
	 */
	private double balance;
	/**
	 * total number of rids
	 */
	private int numberOfRides;
	/**
	 * starting station if planned ride
	 */
	private Station start= null;
	/**
	 * destination station of planned ride
	 */
	private Station dest = null;
	/**
	 * Bicycle if user is renting a bike
	 */
	private Bycicle bike = null;
	/**
	 * total time spent on bike
	 */
	private int timeOnBike = 0;
	/**
	 * total charges from the beginning
	 */
	private long totalAmountCharges = 0;
	/**
	 * velib network attached to the user
	 */
	private MyVelib myVelib;
	/**
	 * boolean to know if user has planned ride
	 */
	private boolean plannedRide = false;
	/**
	 * last time user had rent a bike
	 */
	private int last_time_rent = -1;
	/**
	 * last time user had returned a bike
	 */
	private int last_time_return = -1;
	
	//ATTRIBUT OF CHANGE FOR OBSERVE PATTERN
	private ArrayList<Observer> observers = new ArrayList<Observer>();
	
	
	public boolean onBike = false;
	private boolean changedDest = false;
	private boolean changedAmountCharge = false;
	/**
	 * boolean to know if the user state has changed
	 */
	private boolean changed = false;

	
	
	/**
	 * @param myVelib the velib network
	 */
	public User(MyVelib myVelib) {
		User.counter +=1;
		this.ID = counter;
		this.balance = 0;
		this.numberOfRides = 0;
		this.myVelib = myVelib;
		observers.add(myVelib);
	}

	
	
	

	public int getID() {
		return ID;
	}


	public void setID(int iD) {
		ID = iD;
	}




	public double getBalance() {
		return balance;
	}


	public void setBalance(double balance) {
		this.balance = balance;
	}


	public int getNumberOfRides() {
		return numberOfRides;
	}


	public void setNumberOfRides(int numberOfRides) {
		this.numberOfRides = numberOfRides;
	}
	
	
	public Bycicle getBike() {
		return bike;
	}


	public void setBike(Bycicle bike) {
		this.bike = bike;
	}
	
	public double[] getGPS() {
		return GPS;
	}


	public void setGPS(double[] gPS) {
		GPS = gPS;
	}


	public Station getStart() {
		return start;
	}


	public void setStart(Station start) {
		this.start = start;
	}


	public Station getDest() {
		return dest;
	}


	public void setDest(Station dest) {
		this.dest = dest;
	}
	
	public int getTimeOnBike() {
		return timeOnBike;
	}


	public void setTimeOnBike(int timeOnBike) {
		this.timeOnBike = timeOnBike;
	}


	public long getTotalAmountCharges() {
		return totalAmountCharges;
	}


	public void setTotalAmountCharges(long totalAmountCharges) {
		this.totalAmountCharges = totalAmountCharges;
	}	
	
	
	
	
	
	/**
	 * print the current state of the user
	 */
	public void currentState() {
		System.out.println("------------------------------------------------------");
		if(this.onBike) {
			System.out.println("User "+"("+this.getID()+")"+" is on a bike \n");
		}
		else {
			System.out.println("User "+"("+this.getID()+")"+" is not on a bike \n");
		}
	}

	
	
	/**
	 * add a ride to the total number of rides
	 */
	public void addRide() {
		this.setNumberOfRides(this.getNumberOfRides() +1);
	}
	
	
	
	
	
	
	/**
	 * Plan a ride for the user
	 * @param start start localization
	 * @param end end localization
	 * @param bikeType mechanic or electric
	 * @throws BikeTypeInvalid Exception if bike type is wrong
	 */
	public void ridesPlanning(double [] start, double [] end, String bikeType) throws BikeTypeInvalid{
		
		if(bikeType.equalsIgnoreCase("ELECTRIC") || bikeType.equalsIgnoreCase("MECHANIC")) {
			
			this.setPlannedRide(true);	
					
			ArrayList<Station> listOfStations= this.getMyVelib().getList_stations();
			
			
			DistanceComparator distanceComparator = new DistanceComparator();
			distanceComparator.setPosition(start);
			Collections.sort(listOfStations, distanceComparator);
			ArrayList<Station> near_to_start_stations = new ArrayList<Station>(listOfStations);
		
					
			distanceComparator.setPosition(end);
			Collections.sort(listOfStations, distanceComparator);
			ArrayList<Station> near_to_end_stations = new ArrayList<Station>(listOfStations);
				
			
			int arraySize = listOfStations.size();
			
			
			Station start_station = null;
			Station end_station = null;
			
			for(int i =0; i<arraySize;i++) {
				end_station =  near_to_end_stations.get(i);
				if(end_station.isFull() || end_station.isOnline()==false) {continue;}
				else {
					break;}
			}
			
			
			
			for(int i =0; i<arraySize;i++) {
				start_station = near_to_start_stations.get(i);
				if(start_station.isEmpty() || start_station.isOnline() == false) {
					continue;
				}
				else {
					if(bikeType.equalsIgnoreCase("ELECTRIC")){
						if(start_station.isThereNoElectric()) {
							
							continue;
						}
						else {
							break;
						}
					}
					else {
						if(start_station.isThereNoMechanic()) {
							continue;
						}
						else {
							break;
						}
					}
				}
			}
			
			
			
			
			//USER UPDATE	
			this.setGPS(start);
			this.setStart(start_station); 
			this.setDest(end_station);
			
			
			//this.setChanged(true);
			//this.setChangedDest(true);
			}
		
		else {
			throw new BikeTypeInvalid(this);
		}
		
	}

	
	
	
			
	
	
	public MyVelib getMyVelib() {
		return myVelib;
	}


	public void setMyVelib(MyVelib myVelib) {
		this.myVelib = myVelib;
	}


	public boolean isChangedDest() {
		return changedDest;
	}


	public void setChangedDest(boolean changedDest) {
		this.changedDest = changedDest;
	}


	public boolean isChangedAmountCharge() {
		return changedAmountCharge;
	}


	public void setChangedAmountCharge(boolean changedAmountCharge) {
		this.changedAmountCharge = changedAmountCharge;
	}


	public boolean isChanged() {
		return changed;
	}


	public void setChanged(boolean changed) {
		this.changed = changed;
	}


	public ArrayList<Observer> getObservers() {
		return observers;
	}


	public void setObservers(ArrayList<Observer> observers) {
		this.observers = observers;
	}


	public boolean isPlannedRide() {
		return plannedRide;
	}


	public void setPlannedRide(boolean plannedRide) {
		this.plannedRide = plannedRide;
	}


	public int getLast_time_rent() {
		return last_time_rent;
	}


	public void setLast_time_rent(int last_time_rent) {
		this.last_time_rent = last_time_rent;
	}


	public int getLast_time_return() {
		return last_time_return;
	}


	public void setLast_time_return(int last_time_return) {
		this.last_time_return = last_time_return;
	}


	
	
	
	
	
	
	
	
}
