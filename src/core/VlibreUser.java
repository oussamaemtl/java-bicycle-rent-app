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
 * Users with VLIBRE card
 * @author Oussama
 *
 */
public class VlibreUser extends User implements Pricing, Return_Rent, Statistic, Observable{

	/**
	 * Current time credit balance the user has
	 */
	private int time_credit_balance;
	/**
	 * The total time earned by this user
	 */
	private long Total_time_earned = 0;
	private boolean changedtime_balance = false;
	
	/**
	 * @param myVelib velib network from this user
	 */
	public VlibreUser(MyVelib myVelib) {
		super(myVelib);
		this.time_credit_balance = 0;
	}



	public int getTime_credit_balance() {
		return time_credit_balance;
	}

	public void setTime_credit_balance(int time_credit_balance) {
		this.time_credit_balance = time_credit_balance;
	}
	
	
	
	public long getTotal_time_earned() {
		return Total_time_earned;
	}

	public void setTotal_time_earned(long total_time_earned) {
		Total_time_earned = total_time_earned;
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
	 *
	 */
	public int price(int minutes) throws BikeTypeInvalid{
		int reste = minutes - (minutes/60)*60;
		if(this.getBike().getType().equalsIgnoreCase("MECHANIC")) {
			if(minutes<60) {return 0;}
			else {
			if(reste<this.getTime_credit_balance()) {
				int q = (minutes/60) -1;
				int newbalance = this.getTime_credit_balance() - reste;
				this.setTime_credit_balance(newbalance); //UPDATE
				System.out.println("Cost of Ride : " + q);
				return q;
				}
			else {
				int newminutes = minutes - this.getTime_credit_balance();
				this.setTime_credit_balance(0);//UPDATE
				
				int supp = 0;
				if(newminutes%60 > 0) {supp = 1;}
				int q= (newminutes/60) + supp -1;
				System.out.println("Cost of Ride : "+q +"\n");
				return (newminutes/60) + supp -1; 
				}
			}	
		}
		
		
		else if(this.getBike().getType().equalsIgnoreCase("ELECTRIC")) {
			if(minutes<60) {return 1;}
			else {
			if(reste<this.getTime_credit_balance()) {
				int q = (minutes/60) -1;
				int newbalance = this.getTime_credit_balance() - reste;
				this.setTime_credit_balance(newbalance);
				return 1+q;
				}
			else {
				int newminutes = minutes - this.getTime_credit_balance();
				this.setTime_credit_balance(0);
				int supp = 0;
				if(newminutes%60 > 0) {supp = 1;}
				return 2*(newminutes/60) + 2*supp -1 ; 
				}
			}	
		}
		else {
			throw new BikeTypeInvalid(this);
		}
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	

	
	
	
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	
	
	
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
			station.setNumOfRent(station.getNumOfRent() + 1);
			station.updateNumUtility();
			
			if(bikeType.equalsIgnoreCase("ELECTRIC")) {
				station.setChangeElectric(true);}
			else {
				station.setChangeMechanic(true);
			}
			
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
						e.printMsg();
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
	
	//////////////////////////////////////////////////////////////////////////////////
	
	

	/**
	 *return of a bike at a given time
	 */
	public void  retour(int time) throws TravelTimeException{
		
		if(time > this.getLast_time_rent()) {
		
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
			
			
			if(end_station.isStandard() == false) {
				//UPDATE IF PLUS STATION
				this.setTime_credit_balance(this.getTime_credit_balance() + 5);
				this.setTotal_time_earned(this.getTotal_time_earned() + 5);
				this.setChangedtime_balance(true);
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
			
			station.setNumOfReturn(station.getNumOfReturn() + 1);
			station.updateNumUtility();
			
			
			ArrayList<Integer> l = station.getList_time_return();
			l.add(time);
			station.setList_time_return(l);
			
			this.notifyObservers();
		}
		else {
			throw new TravelTimeException(this);
		}

		
	}

	
	/**
	 * return of a bike at a given time and at given station
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
		System.out.println("Blance User : "+ this.getID());
		System.out.println("Card type : VLIBRE");
		System.out.println("Number of rides : "+ this.getNumberOfRides());
		System.out.println("Total time spent (min) " + this.getTimeOnBike());
		System.out.println("Total amount of Charges : " +this.getBalance());
		System.out.println("Total time earned : " + this.getTotal_time_earned()+"\n");
	}
	
	/**
	 *Print the report of a user
	 */
	public void report() {
		System.out.println("------------------------------------------------------");
		System.out.println("User ID : "+ this.getID());
		System.out.println("Card type : VLIBRE");
		System.out.println("Number of rides : "+ this.getNumberOfRides());
		System.out.println("Total time spent (min) " + this.getTimeOnBike());
		System.out.println("Total amount of Charges : " +this.getBalance());
		System.out.println("Total time earned : " + this.getTotal_time_earned());
		System.out.println("Last Location : " + "(" +this.getGPS()[0] +","+this.getGPS()[1] + ")");
		System.out.print("Credit-time Balance :" + this.getTime_credit_balance() +"\n");
		
	}

	
	public void ratio(int te, int ts) {}


	public boolean isChangedtime_balance() {
		return changedtime_balance;
	}



	public void setChangedtime_balance(boolean changedtime_balance) {
		this.changedtime_balance = changedtime_balance;
	}


}
