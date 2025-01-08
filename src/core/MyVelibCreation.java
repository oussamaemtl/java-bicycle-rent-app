package core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Set;

import exception.BikeTypeInvalid;
import exception.FullStationException;
import exception.MyVelibNetworkUnexistent;
import exception.NotOnBikeException;
import exception.PolicyException;
import exception.StationDoesNotExist;
import exception.StationOfflineException;
import exception.TooManyBikesException;
import exception.TravelTimeException;
import exception.UserDoesNotExist;
import exception.WrongCartTypeException;

/**
 * has a data base of velib network an performs operation on them
 * @author Oussama
 *
 */
public class MyVelibCreation {
	
	//VELIB NETWORK DATA BASE
	//NAME ASSOCIATED WITH MYVELIB OBJECT
	
	/**
	 * Data base linking the name of the velib network to itself
	 */
	public HashMap<String, MyVelib> velibDataBase = new HashMap<String, MyVelib>(); //EACH MYVELIB NETWORK HAS A DIFFERENT NAME
	
	//USER DATA BASE
	//NAME ASSOCIATED WITH ID
	
	/**
	 * Data base linking the name of a user to itself
	 */
	public HashMap<String, Integer> userDataBase = new HashMap<String, Integer>();
	
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	/**
	 * Create a uniform velib network (all station are equally filled)
	 * @param nstations number of stations
	 * @param nslots capacity of a station
	 * @param cote length of the square
	 * @param nbikes number of bikes in total
	 * @return MyVelib network
	 * @throws TooManyBikesException Exception if too many bikes entered
	 */
	public MyVelib createUniformMyVelibNetwork(int nstations, int nslots, int cote, int nbikes) throws TooManyBikesException{
		
		if(nbikes > nslots*nstations) {
			throw new TooManyBikesException(this);
		}
		
		else {
		MyVelib myVelib = new MyVelib();
		
		int rangeMin = 0;
		int rangeMax = cote;
		int numOfStations = nstations;
		int capacity = nslots;
		
		double nbBikesPerStation1 = 0.7*capacity;
		
		int nbBikesPerStation = (int) nbBikesPerStation1;
		
		double initialElec1 = 0.3*nbBikesPerStation;
		
		int initialElec = (int)initialElec1;
		int initialMech = nbBikesPerStation - initialElec;
		
		double plusStations1 = 0.2*numOfStations;
		
		int plusStations =(int) plusStations1;
		
		Random r1 = new Random();
		
		//SPACE STATION DISTRIBUTION
		for(int i=0; i<numOfStations; i++) {
			
			double absc1 = rangeMin + (rangeMax - rangeMin) * r1.nextDouble();
			double ord1 = rangeMin + (rangeMax - rangeMin) * r1.nextDouble();
			double [] GPS_station = {absc1,ord1};
			
			Station station;
			
			if(i<plusStations) {
				station = new Station(capacity, GPS_station,false, myVelib);}
			else {
				station = new Station(capacity, GPS_station, myVelib);
			}
			//BIKE EQUAL DISTRIBUTION
			ArrayList<Bycicle> electricBike= new ArrayList<Bycicle>();
			ArrayList<Bycicle> mechanicBike= new ArrayList<Bycicle>();
			
			for(int j = 0;j<initialElec;j++) {
				electricBike.add(new Bycicle("ELECTRIC", station));
			}
			for(int j = 0;j<initialMech;j++) {
				mechanicBike.add(new Bycicle("MECHANIC", station));
			}
			station.setElectricBike(electricBike);
			station.setMechanicBike(mechanicBike);
			station.update_bikes();
			station.setInitial_nbikes(station.getElectricBike().size() + station.getMechanicBike().size());
			myVelib.addStation(station);}
		
		return myVelib;}
	}
	
	
	/**
	 * Create a uniform velib network (Station and bikes are uniformly distributed)
	 * @param nstations number of stations
	 * @param nslots capacity of a station
	 * @param cote length of the square
	 * @param nbikes number of bikes in total
	 * @return MyVelib network
	 * @throws TooManyBikesException Exception if too many bikes entered
	 */
	public MyVelib createRandomMyVelibNetwork(int nstations, int nslots, int cote, int nbikes) throws TooManyBikesException{
		
		if(nbikes > nslots*nstations) {
			throw new TooManyBikesException(this);
		}
		else {
		MyVelib myVelib = new MyVelib();
		
		int rangeMin = 0;
		int rangeMax = cote;
		int numOfStations = nstations;
		int capacity = nslots;
		int nbBikes = nbikes;
		
		double initialElec1 = 0.3*nbBikes;
		int initialElec = (int) initialElec1;
		
		int initialMech = nbBikes - initialElec;	
		
		double plusStations1 = 0.2*numOfStations;
		int plusStations =(int) plusStations1;
		
		//STATION DISTRIBUTION
		for(int i=0; i<numOfStations; i++) {
			
			Random r1 = new Random();
			double absc1 = rangeMin + (rangeMax - rangeMin) * r1.nextDouble();
			double ord1 = rangeMin + (rangeMax - rangeMin) * r1.nextDouble();
			double [] GPS_station = {absc1,ord1};
			
			
			if(i<plusStations) {
				myVelib.addStation(new Station(capacity, GPS_station,false, myVelib));}
			else {
				myVelib.addStation(new Station(capacity, GPS_station, myVelib));}
		}
		
		
		//Random bike distribution
		
		for(int j = 0;j<initialElec;j++) {
			Station station = myVelib.RandomStation();
			station.addBike(new Bycicle("ELECTRIC",station));
		}
		for(int j = 0;j<initialMech;j++) {
			Station station = myVelib.RandomStation();
			station.addBike(new Bycicle("MECHANIC",station));
			}
		
		for(Station station : myVelib.getList_stations()) {
			station.setInitial_nbikes(station.getElectricBike().size() + station.getMechanicBike().size());
		}
		
		return myVelib;}
	}
	
	/**
	 * setup a basic velib network and add it to the data base
	 * @param velibnetworkName name of the velib network
	 */
	public void setup(String velibnetworkName) {
		
		try {
			MyVelib myVelib = this.createRandomMyVelibNetwork(10, 10, 4, 75);
			this.velibDataBase.put(velibnetworkName, myVelib);
		} catch (TooManyBikesException e) {
			// TODO Auto-generated catch block
			e.printMsg();
		}
		
			
	}
	
	
	
	
	/**
	 * print the velib network data base
	 */
	public void displayVDatabase() {
		System.out.println(this.velibDataBase);
	}
	
	/**
	 * print the user Data Base
	 */
	public void displayUDatabase() {
		System.out.println(this.userDataBase);
	}
	
	/**
	 * setup a velib network with entered characteristics and add it to the data base
	 * @param Name name of the velib network
	 * @param nstations number of stations
	 * @param nslots capacity of a station
	 * @param s length of the square
	 * @param nbikes number of bikes in total
	 */
	public void setup(String Name, int nstations, int nslots, int s, int nbikes) {
		try {
			MyVelib myVelib = this.createRandomMyVelibNetwork(nstations, nslots, s, nbikes);
			this.velibDataBase.put(Name, myVelib);
		} catch (TooManyBikesException e) {
			// TODO Auto-generated catch block
			e.printMsg();
		}
		
		
	}
	
	/**
	 * Create and add user to the data base
	 * @param userName name of the user
	 * @param cardType type of card
	 * @param velibnetworkName name of the velib network
	 * @throws MyVelibNetworkUnexistent Exception if the name of the velib network does not exist
	 * @throws WrongCartTypeException Exception if the card type is wrong
	 */
	public void addUser(String userName, String cardType, String velibnetworkName) throws MyVelibNetworkUnexistent, WrongCartTypeException{
		if(this.velibDataBase.containsKey(velibnetworkName)) {
			MyVelib myVelib = this.velibDataBase.get(velibnetworkName);
			if(cardType.equalsIgnoreCase("VLIBRE")) {
				VlibreUser user = new VlibreUser(myVelib);
				myVelib.addUser(user);
				this.userDataBase.put(userName, user.getID());
			}
			else if(cardType.equalsIgnoreCase("VMAX")) {
				VmaxUser user = new VmaxUser(myVelib);
				myVelib.addUser(user);
				this.userDataBase.put(userName, user.getID());
			}
			else if(cardType.equalsIgnoreCase("NONE")) {
				CreditCardUser user = new CreditCardUser(myVelib);
				myVelib.addUser(user);
				this.userDataBase.put(userName, user.getID());
			}
			else {
				throw new  WrongCartTypeException(myVelib);
			}
		}
		else {
			throw new MyVelibNetworkUnexistent(this);
		}
		
	}
	
	
	
	/**
	 * plan a ride for a user
	 * @param userName name of the user
	 * @param start start localization
	 * @param end end localization
	 * @param bikeType electric or mechanic
	 * @param velibnetworkName name of the velib network
	 * @throws MyVelibNetworkUnexistent Exception if the name of the velib network does not exist
	 * @throws UserDoesNotExist Exception if the user does not exist
	 */
	public void ridesPlanning(String userName, double[] start, double [] end, String bikeType, String velibnetworkName) 
			throws MyVelibNetworkUnexistent, UserDoesNotExist {
		
		if(this.velibDataBase.containsKey(velibnetworkName)) {
			

			
			MyVelib myVelib = this.velibDataBase.get(velibnetworkName);
			
			
			if(this.userDataBase.containsKey(userName)) {
				
				
				
				int id = this.userDataBase.get(userName);
				if(myVelib.getUser().containsKey(id)) {
					if(myVelib.getVlibreUser().containsKey(id)) {
						VlibreUser user = myVelib.getVlibreUser().get(id);
						try {
							user.ridesPlanning(start, end, bikeType);
						} catch (BikeTypeInvalid e) {
							// TODO Auto-generated catch block
							e.printMsg();
						}
					}
					else if(myVelib.getVmaxUser().containsKey(id)) {
						VmaxUser user= myVelib.getVmaxUser().get(id);
						try {
							user.ridesPlanning(start, end, bikeType);
						} catch (BikeTypeInvalid e) {
							// TODO Auto-generated catch block
							e.printMsg();
						}
						
					}
					else if(myVelib.getCreditUser().containsKey(id)) {
						CreditCardUser user = myVelib.getCreditUser().get(id);
						try {
							user.ridesPlanning(start, end, bikeType);
						} catch (BikeTypeInvalid e) {
							// TODO Auto-generated catch block
							e.printMsg();
						}
					}
				}
				else {
					System.out.println("This user is not part of this velib network");
				}
		
			}
			else {
				throw new UserDoesNotExist(this);
			}
			
		}
		else {
			throw new MyVelibNetworkUnexistent(this);
		}
		
		
	}
	
	
	/**
	 * rent bike for a user with a given ID if user has planned ride
	 * @param userID user ID
	 * @param bikeType electric or mechanic
	 * @param velibnetworkName name of the velib network
	 * @param time time of rent
	 * @throws MyVelibNetworkUnexistent Exception if the name of the velib network does not exist
	 * @throws UserDoesNotExist Exception if the user does not exist
	 */
	public  void rentBike(int userID, String bikeType , String velibnetworkName, int time) 
			throws MyVelibNetworkUnexistent, UserDoesNotExist{
		
		if(this.velibDataBase.containsKey(velibnetworkName)) {
			MyVelib myVelib = this.velibDataBase.get(velibnetworkName);
			if(myVelib.getUser().containsKey(userID)) {
				if(myVelib.getVlibreUser().containsKey(userID)) {
					VlibreUser user = myVelib.getVlibreUser().get(userID);
					
						try {
							user.rent(bikeType, time);
						} catch (TravelTimeException e) {
							// TODO Auto-generated catch block
							e.printMsg();
						}
					
				}
				else if(myVelib.getVmaxUser().containsKey(userID)) {
					VmaxUser user = myVelib.getVmaxUser().get(userID);
					try {
						user.rent(bikeType, time);
					} catch (TravelTimeException e) {
						// TODO Auto-generated catch block
						e.printMsg();
					}
					
				}
				else if(myVelib.getCreditUser().containsKey(userID)) {
					CreditCardUser user = myVelib.getCreditUser().get(userID);
					try {
						user.rent(bikeType, time);
					} catch (TravelTimeException e) {
						// TODO Auto-generated catch block
						e.printMsg();
					}
					
				}
			}
			else {
				throw new UserDoesNotExist(this);
			}
			
		}
		else {
			throw new MyVelibNetworkUnexistent(this);
		}
	}
	
	
	
	
	/**
	 * rent bike for a user with a given name if user has planned ride
	 * @param userName name of user
	 * @param bikeType electric or mechanic
	 * @param velibnetworkName name of the velib network
	 * @param time time of rent
	 * @throws UserDoesNotExist Exception if user does not exist
	 */
	public  void rentBike(String userName, String bikeType , String velibnetworkName, int time) throws UserDoesNotExist{
		if(this.userDataBase.containsKey(userName)) {
			int userID = this.userDataBase.get(userName);
			try {
				this.rentBike(userID, bikeType, velibnetworkName, time);
			} catch (MyVelibNetworkUnexistent e) {
				// TODO Auto-generated catch block
				e.printMsg();
			} catch (UserDoesNotExist e) {
				// TODO Auto-generated catch block
				e.printMsg();
			}
		}
		else {
			throw new UserDoesNotExist(this);
		}
	}
	
	
	

		
	
	
	/**
	 * rent bike for a user with a given ID at a specific station
	 * @param userID user ID
	 * @param stationID Station ID
	 * @param bikeType electric or mechanoc
	 * @param velibnetworkName name of the velib network
	 * @param time time of rent
	 * @throws MyVelibNetworkUnexistent Exception if the name of the velib network does not exist
	 * @throws UserDoesNotExist Exception if the user does not exist
	 * @throws StationDoesNotExist Exception if the station does not exist
	 */
	public  void rentBike(int userID, int stationID, String bikeType , String velibnetworkName, int time) 
			throws MyVelibNetworkUnexistent, UserDoesNotExist, StationDoesNotExist {
		
		
		if(this.velibDataBase.containsKey(velibnetworkName)) {
			MyVelib myVelib = this.velibDataBase.get(velibnetworkName);
			if(myVelib.getStations().containsKey(stationID)) {
				
				Station station = myVelib.getStations().get(stationID);
				
				if(myVelib.getUser().containsKey(userID)) {
					
					
					if(myVelib.getVlibreUser().containsKey(userID)) {
						VlibreUser user = myVelib.getVlibreUser().get(userID);
						
							try {
								user.rent(bikeType, station, time);
							} catch (TravelTimeException e) {
								// TODO Auto-generated catch block
								e.printMsg();
							}
						
					}
					else if(myVelib.getVmaxUser().containsKey(userID)) {
						VmaxUser user= myVelib.getVmaxUser().get(userID);
						try {
							user.rent(bikeType, station, time);
						} catch (TravelTimeException e) {
							// TODO Auto-generated catch block
							e.printMsg();
						}
						
					}
					else if(myVelib.getCreditUser().containsKey(userID)) {
						CreditCardUser user = myVelib.getCreditUser().get(userID);
						try {
							user.rent(bikeType, station, time);
						} catch (TravelTimeException e) {
							// TODO Auto-generated catch block
							e.printMsg();
						}
						
						}
					}	
				else {
					throw new UserDoesNotExist(this);
				}
				
			}
			else {
				throw new StationDoesNotExist(this);
			}
			}
		else {
			throw new MyVelibNetworkUnexistent(this);
		}
		
	}
	
	
	/**
	 * return bike for user with a given name and if he has planned a ride
	 * @param userName user name
	 * @param time time of return
	 * @param velibnetworkName name of the velib network
	 * @throws UserDoesNotExist Exception if the user does not exist
	 */
	public  void returntBike(String userName, int time, String velibnetworkName) throws UserDoesNotExist{
		if(this.userDataBase.containsKey(userName))  {
			int userID = this.userDataBase.get(userName);
			try {
				this.returntBike(userID, time, velibnetworkName);
			} catch (MyVelibNetworkUnexistent e) {
				// TODO Auto-generated catch block
				e.printMsg();
			} catch (UserDoesNotExist e) {
				// TODO Auto-generated catch block
				e.printMsg();
			}
		}
		else {
			throw new UserDoesNotExist(this);
		}
	}
	
	
	
	/**
	 * return bike for user with a given ID and if he has planned a ride
	 * @param userID user ID
	 * @param time time of return
	 * @param velibnetworkName name of the velib network
	 * @throws MyVelibNetworkUnexistent Exception if the name of the velib network does not exist
	 * @throws UserDoesNotExist Exception if the user does not exist
	 */
	public  void returntBike(Integer userID, int time, String velibnetworkName) 
			throws MyVelibNetworkUnexistent, UserDoesNotExist{
		if(this.velibDataBase.containsKey(velibnetworkName)) {
			MyVelib myVelib = this.velibDataBase.get(velibnetworkName);
			if(myVelib.getUser().containsKey(userID)) {
				
				if(myVelib.getVlibreUser().containsKey(userID)) {
					VlibreUser user = myVelib.getVlibreUser().get(userID);
					try {
						user.retour(time);
					} catch (TravelTimeException e) {
						// TODO Auto-generated catch block
						e.printMsg();
					}
				}
				else if(myVelib.getVmaxUser().containsKey(userID)) {
					VmaxUser user = myVelib.getVmaxUser().get(userID);
					try {
						user.retour(time);
					} catch (TravelTimeException e) {
						// TODO Auto-generated catch block
						e.printMsg();
					}
					
				}
				else if(myVelib.getCreditUser().containsKey(userID)) {
					CreditCardUser user = myVelib.getCreditUser().get(userID);
					try {
						user.retour(time);
					} catch (TravelTimeException e) {
						// TODO Auto-generated catch block
						e.printMsg();
					}
					
				}
				
				
			}
			else {
				throw new UserDoesNotExist(this);
			}
		}
		else {
			throw new MyVelibNetworkUnexistent(this);
		}
	}
	
	
	
	
	
	
	/**
	 * return bike for user with a given ID at a specific station
	 * @param userID user ID
	 * @param stationID station ID
	 * @param minutes time of return
	 * @param velibnetworkName velib network name
	 * @throws MyVelibNetworkUnexistent Exception if the name of the velib network does not exist
	 * @throws UserDoesNotExist Exception if the user does not exist
	 * @throws StationDoesNotExist Exception if the station does not exist
	 */
	public  void returntBike(int userID, int stationID, int minutes, String velibnetworkName) 
			throws MyVelibNetworkUnexistent, UserDoesNotExist, StationDoesNotExist{
		
		if(this.velibDataBase.containsKey(velibnetworkName)) {
			MyVelib myVelib = this.velibDataBase.get(velibnetworkName);
			if(myVelib.getStations().containsKey(stationID)) {
				
				Station station = myVelib.getStations().get(stationID);
				
				if(myVelib.getUser().containsKey(userID)) {
					
					
					if(myVelib.getVlibreUser().containsKey(userID)) {
						VlibreUser user = myVelib.getVlibreUser().get(userID);
						try {
							user.retour(minutes, station);
						} catch (FullStationException e) {
							// TODO Auto-generated catch block
							e.printMsg();
						} catch (StationOfflineException e) {
							// TODO Auto-generated catch block
							e.printMsg();
						} catch (NotOnBikeException e) {
							// TODO Auto-generated catch block
							e.printMsg();
						}
					}
					else if(myVelib.getVmaxUser().containsKey(userID)) {
						VmaxUser user= myVelib.getVmaxUser().get(userID);
						try {
							user.retour(minutes, station);
						} catch (FullStationException e) {
							// TODO Auto-generated catch block
							e.printMsg();
						} catch (StationOfflineException e) {
							// TODO Auto-generated catch block
							e.printMsg();
						} catch (NotOnBikeException e) {
							// TODO Auto-generated catch block
							e.printMsg();
						}
						
					}
					else if(myVelib.getCreditUser().containsKey(userID)) {
						CreditCardUser user = myVelib.getCreditUser().get(userID);
						try {
							user.retour(minutes, station);
						} catch (FullStationException e) {
							// TODO Auto-generated catch block
							e.printMsg();
						} catch (StationOfflineException e) {
							// TODO Auto-generated catch block
							e.printMsg();
						} catch (NotOnBikeException e) {
							// TODO Auto-generated catch block
							e.printMsg();
						}}
					}	
				else {
					throw new UserDoesNotExist(this);
				}
				
			}
			else {
				throw new StationDoesNotExist(this);
			}
			}
		else {
			throw new MyVelibNetworkUnexistent(this);
		}
		
		
		
	}
	
	
	/**
	 * Set a given station online
	 * @param velibnetworkName velib network name
	 * @param stationID station ID
	 * @throws MyVelibNetworkUnexistent Exception if the name of the velib network does not exist
	 * @throws StationDoesNotExist Exception if the station does not exist
	 */
	public void online(String velibnetworkName, int stationID) throws MyVelibNetworkUnexistent, StationDoesNotExist{
		
		if(this.velibDataBase.containsKey(velibnetworkName)) {
			MyVelib myVelib = this.velibDataBase.get(velibnetworkName);
			if(myVelib.getStations().containsKey(stationID)) {
				Station station = myVelib.returnStation(stationID);
				station.setOnline(true);
				System.out.println("Station " + station.getID() + " is online");
			}
			else {
				throw new StationDoesNotExist(this);
			}
		}
		else {
			throw new MyVelibNetworkUnexistent(this);
		}
		
		
	}
	
	/**
	 * Set a station offline
	 * @param velibnetworkName velib network name
	 * @param stationID station ID
	 * @throws MyVelibNetworkUnexistent Exception if the name of the velib network does not exist
	 * @throws StationDoesNotExist Exception if the station does not exist
	 */
	public void offline(String velibnetworkName, int stationID) throws MyVelibNetworkUnexistent, StationDoesNotExist {
		
		if(this.velibDataBase.containsKey(velibnetworkName)) {
			MyVelib myVelib = this.velibDataBase.get(velibnetworkName);
			if(myVelib.getStations().containsKey(stationID)) {
				Station station = myVelib.returnStation(stationID);
				station.setOnline(false);
				System.out.println("Station " + station.getID() + " is offline");
			}
			else {
				throw new StationDoesNotExist(this);
			}
		}
		else {
			throw new MyVelibNetworkUnexistent(this);
		}
		
	}
	
	
	
	////STATISTIC
	
	/**
	 * print the ratio occupation of a given station between two moments
	 * @param velibnetworkName velib network name
	 * @param stationID station ID
	 * @param te time te
	 * @param ts time ts
	 * @throws MyVelibNetworkUnexistent Exception if the name of the velib network does not exist
	 * @throws StationDoesNotExist Exception if the station does not exist
	 */
	public void ratio(String velibnetworkName, int stationID, int te, int ts) throws MyVelibNetworkUnexistent, StationDoesNotExist{
		if(this.velibDataBase.containsKey(velibnetworkName)) {
			MyVelib myVelib = this.velibDataBase.get(velibnetworkName);
			if(myVelib.getStations().containsKey(stationID)) {
				Station station = myVelib.returnStation(stationID);
				station.ratio(te, ts);
			}
			else {
				throw new StationDoesNotExist(this);
			}
		}
		else {
			throw new MyVelibNetworkUnexistent(this);
		}
	}
	
	/**
	 * print the station report of all the stations in a given velib network
	 * @param velibnetworkName velib network name
	 * @param stationID station id
	 * @throws MyVelibNetworkUnexistent Exception if the name of the velib network does not exist
	 * @throws StationDoesNotExist Exception if the station does not exist
	 */
	public  void displayStation(String velibnetworkName, int stationID) 
			throws MyVelibNetworkUnexistent,  StationDoesNotExist{
		
		
		if(this.velibDataBase.containsKey(velibnetworkName)) {
			MyVelib myVelib = this.velibDataBase.get(velibnetworkName);
			if(myVelib.getStations().containsKey(stationID)) {
				
				Station station = myVelib.getStations().get(stationID);
				
				station.report();
				
			}
			else {
				throw new StationDoesNotExist(this);
			}
			}
		else {
			throw new MyVelibNetworkUnexistent(this);
		}
		
	}
	
	
	/**
	 * print the general report of a given velib network
	 * @param velibnetworkName velib network name
	 * @throws MyVelibNetworkUnexistent Exception if the name of the velib network does not exist
	 */
	public void displayVelibNet(String velibnetworkName) throws MyVelibNetworkUnexistent {
		if(this.velibDataBase.containsKey(velibnetworkName)) {
			MyVelib myVelib = this.velibDataBase.get(velibnetworkName);
			myVelib.report();
			
		}
		else {
			throw new MyVelibNetworkUnexistent(this);
		}
	}
	
	
	
	/**
	 * display the rapport of all the users of a given velib network
	 * @param velibnetworkName velib network name
	 * @param userID user ID
	 * @throws MyVelibNetworkUnexistent Exception if the name of the velib network does not exist
	 * @throws UserDoesNotExist Exception if user does not exist in this network
	 */
	public  void displayUser(String velibnetworkName,int userID) throws MyVelibNetworkUnexistent, UserDoesNotExist {
		if(this.velibDataBase.containsKey(velibnetworkName)) {
			MyVelib myVelib = this.velibDataBase.get(velibnetworkName);
			if(myVelib.getUser().containsKey(userID)) {
				myVelib.reportUser(userID);
			}
			else {
				throw new UserDoesNotExist(this);
			}
		}
		else {
			throw new MyVelibNetworkUnexistent(this);
		}
		
	}
	
	
	
	
	
	
	
	/**
	 * display the general rapport of all the velib network
	 */
	public  void displayVelib() {
		
		Set<String> keys= this.velibDataBase.keySet();
		for(String name : keys) {
			System.out.println("----------"+name+"----------");
			MyVelib velib = this.velibDataBase.get(name);
			velib.report();}
		
		for(String name : keys) {
			System.out.println("----------"+name+"----------");
			MyVelib velib = this.velibDataBase.get(name);
			Set<Integer> userkeys = velib.getUser().keySet();
			for(int userid : userkeys) {
				System.out.println("----------"+userid+"----------");
				velib.reportUser(userid);
				
			}
		}
	}
	
	
	/**
	 * print the current state of a given velib network
	 * @param velibnetworkName velib network name
	 * @throws MyVelibNetworkUnexistent Exception if velib network does not exist
	 */
	public void currentState(String velibnetworkName)throws MyVelibNetworkUnexistent{
		if(this.velibDataBase.containsKey(velibnetworkName)) {
			MyVelib myVelib = this.velibDataBase.get(velibnetworkName);
			myVelib.currentState();
			
		}
		else {
			throw new MyVelibNetworkUnexistent(this);
		}
	}
	
	/**
	 * print the current state of a station station in a velib network
	 * @param stationID station ID
	 * @param velibnetworkName velib network name
	 * @throws MyVelibNetworkUnexistent Exception if velib network does not exist
	 * @throws StationDoesNotExist Exception if station does not exist in this network
	 */
	public void currenStateStation(int stationID, String velibnetworkName)throws MyVelibNetworkUnexistent, StationDoesNotExist{
		if(this.velibDataBase.containsKey(velibnetworkName)) {
			MyVelib myVelib = this.velibDataBase.get(velibnetworkName);
			if(myVelib.getStations().containsKey(stationID)) {
				Station station = myVelib.returnStation(stationID);
				station.currentState();
			}
			else {
				throw new StationDoesNotExist(this);
			}
		}
		else {
			throw new MyVelibNetworkUnexistent(this);
		}
	}
	
	/**
	 * print the current state of a user in a given network
	 * @param userID user ID
	 * @param velibnetworkName velib network name
	 * @throws MyVelibNetworkUnexistent Exception if velib network does not exist
	 * @throws UserDoesNotExist Exception if user does not exist
	 */
	public void currentStateUser(int userID, String velibnetworkName) throws MyVelibNetworkUnexistent, UserDoesNotExist{
		if(this.velibDataBase.containsKey(velibnetworkName)) {
			MyVelib myVelib = this.velibDataBase.get(velibnetworkName);
			if(myVelib.getUser().containsKey(userID)) {
				myVelib.currentStateUser(userID);
			}
			else {
				throw new UserDoesNotExist(this);
			}
		}
		else {
			throw new MyVelibNetworkUnexistent(this);
		}
	}
	
	/**
	 * print balance of a station in a given network
	 * @param stationID station ID
	 * @param velibnetworkName velib network name
	 * @throws MyVelibNetworkUnexistent Exception if velib network does not exist
	 * @throws StationDoesNotExist Exception if station does not exist
	 */
	public void balanceStation(int stationID, String velibnetworkName) throws MyVelibNetworkUnexistent, StationDoesNotExist{
		if(this.velibDataBase.containsKey(velibnetworkName)) {
			MyVelib myVelib = this.velibDataBase.get(velibnetworkName);
			if(myVelib.getStations().containsKey(stationID)) {
				Station station = myVelib.returnStation(stationID);
				station.balance();
			}
			else {
				throw new StationDoesNotExist(this);
			}
		}
		else {
			throw new MyVelibNetworkUnexistent(this);
		}
	}
	
	/**
	 * print balance of a user in a given network
	 * @param userID user ID
	 * @param velibnetworkName velib network name
	 * @throws MyVelibNetworkUnexistent Exception if velib network does not exist
	 * @throws UserDoesNotExist Exception if user does not exist
	 */
	public void balanceUser(int userID, String velibnetworkName) throws MyVelibNetworkUnexistent, UserDoesNotExist {
		if(this.velibDataBase.containsKey(velibnetworkName)) {
			MyVelib myVelib = this.velibDataBase.get(velibnetworkName);
			if(myVelib.getUser().containsKey(userID)) {
				myVelib.balanceUser(userID);
			}
			else {
				throw new UserDoesNotExist(this);
			}
		}
		else {
			throw new MyVelibNetworkUnexistent(this);
		}
	}
	
	
	/**
	 * print the station sorted regarding a given policy
	 * @param velibnetworkName velib network name
	 * @param sortpolicy USED or OCCUPIED
	 * @throws MyVelibNetworkUnexistent Exception if velib network does not exist
	 */
	public  void sortStation(String velibnetworkName, String sortpolicy) throws MyVelibNetworkUnexistent{
		if(this.velibDataBase.containsKey(velibnetworkName)) {
			MyVelib myVelib = this.velibDataBase.get(velibnetworkName);
			try {
				myVelib.sort(sortpolicy);
			} catch (PolicyException e) {
				// TODO Auto-generated catch block
				e.printMsg();
			}
			
		}
		else {
			throw new MyVelibNetworkUnexistent(this);
		}
	}
	
}
