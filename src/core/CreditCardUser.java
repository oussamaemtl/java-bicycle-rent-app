package core;

import java.util.ArrayList;

import exception.AlreadyOnBikeException;
import exception.BikeTypeInvalid;
import exception.FullStationException;
import exception.NoElectricException;
import exception.NoMechanicException;
import exception.NotOnBikeException;
import exception.StationOfflineException;
import exception.TravelTimeException; 

/**
 * Users with no cards
 * @author Oussama
 *
 */
public class CreditCardUser extends User implements Pricing, Return_Rent, Statistic, Observable{
	
	
	/**
	 * @param myVelib velib network from this user
	 */
	public CreditCardUser(MyVelib myVelib) {
		super(myVelib);
	}
	
	
	public void registerObserver(Observer observer) {
		ArrayList<Observer> l = this.getObservers();
		l.add(observer);
		this.setObservers(l);
	}
	
	public void removeObserver(Observer observer) {
		ArrayList<Observer> l = this.getObservers();
		l.remove(observer);
		this.setObservers(l);
	}
	
	
	public void notifyObservers() {
		if(this.isChanged()) {
			for(Observer ob : this.getObservers()) {
				ob.update(this);
				
			}
			this.setChanged(false);
		}
	}




	
	
	/**
	 * Get the price of the ride
	 */
	public int price(int minutes) throws BikeTypeInvalid{
		if(this.getBike().getType().equalsIgnoreCase("MECHANIC")) {
			int supp = 0;
			if(minutes%60 > 0) {supp = 1;}
			int q = (minutes/60) + supp;
			System.out.println("Cost of Ride : "+q +"\n");
			return (minutes/60) + supp;
		}
		else if(this.getBike().getType().equalsIgnoreCase("ELECTRIC")) {
			int supp = 0;
			if(minutes%60 > 0) {supp = 1;}
			int q = 2*(minutes/60) + 2*supp;
			System.out.println("Cost of Ride : "+q +"\n");
			return 2*(minutes/60) + 2*supp;
		}
		else {
			throw new BikeTypeInvalid(this);
		}
		
	}
	


	
	
	/**
	 *
	 */
	public void rent(String bikeType) throws AlreadyOnBikeException{
		if(this.onBike) {
			throw new AlreadyOnBikeException(this);
		}
		else {
			
			//FIRSTLY WE UPDATE THE RIDE PLANNING
			//this.notifyObservers();
			///
			
			Station start_station = this.getStart();
			Bycicle bike = start_station.getRandomBike(bikeType);
			

			//USER UPDATE
			
			//
			this.onBike = true;
			
			this.setGPS(start_station.getGPS());
			
			this.setNumberOfRides(this.getNumberOfRides()+1);
			this.setChanged(true);		
						
			//BIKE UPDATE :
			this.setBike(bike);
			bike.setFreeStatus(false);
			bike.setIDuser(this.getID());
			bike.setChanged(true);
			
			//STATION UPDATE
			Station station = start_station;
			station.hasbeenused = true;
			station.setChangedNbRent(true);
			station.removeBike(bike);
			if(bikeType.equalsIgnoreCase("ELECTRIC")) {
				station.setChangeElectric(true);}
			else {
				station.setChangeMechanic(true);
			}
			station.setNumOfRent(station.getNumOfRent()+1);
			station.updateNumUtility();
			
			//notify
			
			this.notifyObservers();
		}
		
	}
	
	/**
	 * rent of a bicycle for a user that has already planned a ride
	 * @param bikeType mechanic or electric
	 * @param time (time of rent)
	 * @throws TravelTimeException (exception if time of rent inferior than the last time of return)
	 */
	public void rent(String bikeType, int time) throws TravelTimeException{
		if(time > this.getLast_time_return()) {
			
			
			try {
				this.rent(bikeType);
			} catch (AlreadyOnBikeException e) {
				// TODO Auto-generated catch block
				e.printMsg();
			}
			
			//UPDATE USER
			
			this.setLast_time_rent(time);
			
			//UPDATE STATION
			Station station = this.getStart();
			ArrayList<Integer> l = station.getList_time_rent();
			l.add(time);
			station.setList_time_rent(l);
		}
		else {
			throw new TravelTimeException(this);
		}
	}
	
	/**
	 * rent of a bicycle for a user that has not necessarily planned a ride
	 * @param bikeType mechanic or electric
	 * @param station Station where the user want to rent his bike
	 * @throws StationOfflineException Exception if the wanted station is offline
	 * @throws NoElectricException Exception if the user wanted an electric bike but there is no electric bikes at the station
	 * @throws NoMechanicException Exception if the user wanted an mechanic bike but there is no electric bikes at the station
	 * @throws BikeTypeInvalid Exception if the type of wanted bike is not valid
	 */
	public void rent(String bikeType, Station station) throws StationOfflineException, NoElectricException, NoMechanicException, BikeTypeInvalid{
		
		if(station.isOnline()) {
			if(bikeType.equalsIgnoreCase("ELECTRIC")) {
				if(station.isThereNoElectric() == false) {
			
					
					try {
						this.setPlannedRide(false);
						this.setStart(station);
						this.setGPS(station.getGPS());
						this.rent(bikeType);
					} catch (AlreadyOnBikeException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						}
				}
				else {
					throw new NoElectricException(station);
				}
			}
			else if(bikeType.equalsIgnoreCase("MECHANIC")) {
				
				if(station.isThereNoMechanic() == false) {
					
					
					try {
						this.setPlannedRide(false);
						this.setStart(station);
						this.setGPS(station.getGPS());
						this.rent(bikeType);
					} catch (AlreadyOnBikeException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						}
				}
				else {
					throw new NoMechanicException(station);
				}
				
			}
			else {
				throw new BikeTypeInvalid(this);
			}
		}
		else {
			throw new StationOfflineException(station);
		}
	}
	
	/**
	 * rent of a bicycle for a user that has not necessarily planned a ride with a time entered
	 * @param bikeType mechanic or electric
	 * @param station Station where the user want to rent his bike
	 * @param time time of rent 
	 * @throws TravelTimeException exception if time of rent inferior than the last time of return
	 */
	public void rent(String bikeType, Station station, int time) throws TravelTimeException{
		if(time > this.getLast_time_return()) {
		
			try {
				this.rent(bikeType, station);
				ArrayList<Integer> l = station.getList_time_rent();
				
				//UPDATE OF last_time_rent
				this.setLast_time_rent(time);
				
				//UPDATE OF List_time_rent
				l.add(time);
				station.setList_time_rent(l);
				
				
			} catch (StationOfflineException e) {
				// TODO Auto-generated catch block
				e.printMsg();
			} catch (NoElectricException e) {
				// TODO Auto-generated catch block
				e.printMsg();
			} catch (NoMechanicException e) {
				// TODO Auto-generated catch block
				e.printMsg();
			} catch (BikeTypeInvalid e) {
				// TODO Auto-generated catch block
				e.printMsg();
			}
		}
		else {
			throw new TravelTimeException(this);
		}
		
	}

	/**
	 * return of bike at a given time
	 */
	public void  retour(int time) throws TravelTimeException{
		
		if(time>this.getLast_time_rent()) {
			Station end_station = this.getDest();
			Bycicle bike = this.getBike();
			
			int minutes = time - this.getLast_time_rent();
			//USER UPDATE
			
			try {
				int Price = this.price(minutes);
				this.setBalance(this.getBalance() + Price);
				this.setTotalAmountCharges(this.getTotalAmountCharges() + Price);
				
			} catch (BikeTypeInvalid e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			this.setGPS(end_station.getGPS());
			this.onBike = false;
			this.setTimeOnBike(this.getTimeOnBike() + minutes);
			this.setChangedAmountCharge(true);
			this.setChanged(true);
			this.setLast_time_rent(-1);
			this.setLast_time_return(time);
			
			//BIKE UPDATE	
			bike.setStation(end_station);
			bike.setFreeStatus(true);
			bike.setChanged(true);
	
			
			//STATION UPDATE
			Station station = end_station;
			station.addBike(bike);
			station.hasbeenused = true;
			if(bike.getType().equalsIgnoreCase("ELECTRIC")) {
				station.setChangeElectric(true);
			}
			else {
				station.setChangeMechanic(true);
			}
			station.setChangedNbReturn(true);
			station.setChanged(true);
			ArrayList<Integer> l = station.getList_time_return();
			l.add(time);
			station.setList_time_return(l);
			this.notifyObservers();
			station.setNumOfReturn(station.getNumOfReturn() +1 );
			station.updateNumUtility();
		}
		else {
			throw new TravelTimeException(this);
		}

		
	}
	
	
	/**
	 * return of a bike at a givent time and at a given station
	 * @param time time of return
	 * @param station station of return
	 * @throws FullStationException Exception if the station is full
	 * @throws StationOfflineException Exception if the station is offline
	 * @throws NotOnBikeException Exception if the user has not rent a bike before returning one
	 */
	public void retour(int time, Station station) throws FullStationException, StationOfflineException, NotOnBikeException{
		
		if(this.onBike) {
			if(station.isOnline()) {
				if(station.isFull() == false) {
					
					this.setDest(station);
					try {
						this.retour(time);
					} catch (TravelTimeException e) {
						// TODO Auto-generated catch block
						e.printMsg();
					}
					
				}
				else {
					throw new FullStationException(station);
				}
				
			}
			else {
				throw new StationOfflineException(station);
			}
		}
		else {
			throw new NotOnBikeException(this);
		}
	}
	
	/**
	 *Print the balance of a user
	 */
	public void balance() {
		System.out.println("------------------------------------------------------");
		System.out.println("Balance User :"+ this.getID());
		System.out.println("Number of rides : "+ this.getNumberOfRides());
		System.out.println("Total time spent (min) " + this.getTimeOnBike());
		System.out.println("Total amount of Charges : " +this.getBalance()+"\n");
	}
	
	/**
	 *Print the report of a user
	 */
	public void report() {
		System.out.println("------------------------------------------------------");
		System.out.println("User ID :"+ this.getID());
		System.out.println("Card type : NONE");
		System.out.println("Number of rides : "+ this.getNumberOfRides());
		System.out.println("Total time spent (min) " + this.getTimeOnBike());
		System.out.println("Total amount of Charges : " +this.getBalance());
		System.out.println("Last Location : " + "(" +this.getGPS()[0] +","+this.getGPS()[1] + ")"+"\n");
		
	}
	public void ratio(int te, int ts) {}
}
