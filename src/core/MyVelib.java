package core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;
import java.util.Set;

import comparator.NinitialComparator;
import comparator.OccupationComparator;
import comparator.TimeMaxComparator;
import comparator.UsedComparator;
import exception.PolicyException;
import exception.WrongCartTypeException;

/**
 * velib network
 * @author Oussama
 *
 */
public class MyVelib implements Observer{
	
	//STATIONS DATA BASE
	/**
	 * hash map linking a station ID and itself
	 */
	private HashMap<Integer, Station> stations = new HashMap<Integer, Station>();
	/**
	 * List of station in this velib network
	 */
	private ArrayList<Station> list_stations = new ArrayList<Station>();
	
	
	//USER DATA BASE
	/**
	 * Hash map linking a user ID and itself
	 */
	private HashMap<Integer, User> user = new HashMap<Integer, User>();
	/**
	 * list of user
	 */
	private ArrayList<User> list_user = new ArrayList<User>();
	
	//VLIBRE USER DATA BASE
	/**
	 * hash map linking a Vlibre user ID and itself
	 */
	private HashMap<Integer, VlibreUser> vlibreUser = new HashMap<Integer, VlibreUser>();
	/**
	 * list of vlibre users
	 */
	private ArrayList<VlibreUser> list_vlibreUser = new ArrayList<VlibreUser>();
	
	
	//VMAX USER DATA BASE
	/**
	 * hash map linking a vmax user ID and itself
	 */
	private HashMap<Integer, VmaxUser> vmaxUser = new HashMap<Integer, VmaxUser>();
	/**
	 * list of vmax users
	 */
	private ArrayList<VmaxUser> list_vmaxUser = new ArrayList<VmaxUser>();
	
	//CREDIT USER DATA BASE
	/**
	 * hash map linking a user ID with no cards and itself
	 */
	private HashMap<Integer, CreditCardUser> creditUser = new HashMap<Integer, CreditCardUser>();
	/**
	 * list of users with no cards
	 */
	private ArrayList<CreditCardUser> list_cerditUser = new ArrayList<CreditCardUser>();
	
	////////////////////////////////////////////////////////////
	
	/**
	 * print the current state of a user
	 * @param ID Id of user
	 */
	public void currentStateUser(int ID) {
		User user = this.getUser().get(ID);
		user.currentState();
		
	}
	
	/**
	 * print the current state of a station
	 * @param ID station id
	 */
	public void currentStateStation(int ID) {
		Station station = this.getStations().get(ID);
		station.currentState();
	}
	
	/**
	 * print the report of the station
	 * @param ID station id
	 */
	public void reportStation(int ID) {
		Station station = this.getStations().get(ID);
		station.report();
	}
	
	/**
	 * print report of the user
	 * @param ID User ID
	 */
	public void reportUser(int ID) {
		
		if(this.getVlibreUser().containsKey(ID)) {
			VlibreUser vlibreUser = this.getVlibreUser().get(ID);
			vlibreUser.report();
		}
		else if(this.getVmaxUser().containsKey(ID)) {
			VmaxUser vmaxUser = this.getVmaxUser().get(ID);
			vmaxUser.report();
		}
		else if(this.getCreditUser().containsKey(ID)){
			CreditCardUser creditUser = this.getCreditUser().get(ID);
			creditUser.report();
		}
		else {
			System.out.println("User couldn't be found");
		}
	}
	
	
	/**
	 * print balance of user
	 * @param ID User ID
	 */
	public void balanceUser(int ID) {
		if(this.getVlibreUser().containsKey(ID)) {
			VlibreUser vlibreUser = this.getVlibreUser().get(ID);
			vlibreUser.balance();
		}
		else if(this.getVmaxUser().containsKey(ID)) {
			VmaxUser vmaxUser = this.getVmaxUser().get(ID);
			vmaxUser.balance();
		}
		else if(this.getCreditUser().containsKey(ID)){
			CreditCardUser creditUser = this.getCreditUser().get(ID);
			creditUser.balance();
		}
		else {
			System.out.println("User couldn't be found");
		}
	}
	
	/**
	 * print current State of the velib network
	 */
	public void currentState() {
		Set<Integer> userkey = this.getUser().keySet();
		Set<Integer> stationkey = this.getStations().keySet();
		for(int IDuser:userkey) {
			currentStateUser(IDuser);}
		for(int IDstation:stationkey) {
			currentStateStation(IDstation);
		}
	}
	
	/**
	 * @param ID Station ID
	 * @return Station with given ID
	 */
	public Station returnStation(int ID) {
		return this.getStations().get(ID);
	}
	
	
	/**
	 * @param ID USER ID
	 * @return return User with given ID
	 */
	public User returnUser(int ID) {
		return this.getUser().get(ID);
	}
	
	
	/**
	 * print all the stations locations
	 */
	public void mapStation() {
		for(Station station : this.getList_stations()) {
			System.out.println(station.getGPS()[0]+"," + station.getGPS()[1]);
			System.out.println("---------------");
		}
	}
	
	public void reportStation() {
		Set<Integer> stationkey = this.getStations().keySet();
		for(int IDstation:stationkey) {
			reportStation(IDstation);
		}
	}
	
	/**
	 * print the report of all the user of the velib network
	 */
	public void reportUser() {
		Set<Integer> userkey = this.getUser().keySet();
		for(Integer IDuser:userkey) {
			reportUser(IDuser);}
	}
	
	
	/**
	 * print the report of all the stations and all the users
	 */
	public void report() {
		reportStation();
		reportUser();
	}
	
	/**
	 * Add Station to the data base
	 * @param station Station
	 */
	public void addStation(Station station) {
		ArrayList<Station> l = this.getList_stations();
		l.add(station);
		this.setList_stations(l);
		
		HashMap<Integer, Station> dico = this.getStations();
		dico.put(station.getID(), station);
		this.setStations(dico);
	}
	
	
	/**
	 * Create a station and add it to the Data Base
	 * @param capacity capacity of the station
	 * @param GPS Localization of the station
	 * @param b Standard or plus station
	 * @param electricBike list of electric bicycles
	 * @param mechanicBike list of mechanic bicycles
	 */
	public void addStation(int capacity, double [] GPS, boolean b, ArrayList<Bycicle> electricBike, 
			ArrayList<Bycicle> mechanicBike ) {
		
		Station station = new Station(capacity,GPS, b,electricBike, mechanicBike, this );
		this.addStation(station);
		}
	
	
	/**
	 * Create a station and add it to the Data Base
	 * @param capacity capacity of the station
	 * @param GPS Localization of the station
	 * @param b Standard or plus station
	 */
	public void addStation(int capacity, double [] GPS, boolean b ) {
		
		Station station = new Station(capacity,GPS, b, this);
		this.addStation(station);		
	}
	
	/**
	 * Create a station and add it to the Data Base
	 * @param capacity capacity of the station
	 * @param GPS Localization of the station
	 */
	public void addStation(int capacity, double [] GPS) {
			
			Station station = new Station(capacity,GPS, this);
			this.addStation(station);		
		}
	
	
	
	
	/**
	 * Add a basic user
	 * @param user user of class User
	 */
	public void addUserglobal(User user) {
		
		HashMap<Integer, User> dico = this.getUser();
		ArrayList<User> l = this.getList_user();
		
		dico.put(user.getID(), user);
		l.add(user);
		this.setUser(dico);
		this.setList_user(l);
		
	}
	
	
	
	/**
	 * Add vlibre user
	 * @param user Vlibre user
	 */
	public void addUser(VlibreUser user) {
		
		HashMap<Integer, VlibreUser> dico = this.getVlibreUser();
		ArrayList<VlibreUser> l = this.getList_vlibreUser();
		
		dico.put(user.getID(), user);
		l.add(user);
		this.setVlibreUser(dico);
		this.setList_vlibreUser(l);
		
		this.addUserglobal(user);
		
	}
	
	/**
	 * Add vmax user
	 * @param user Vmax User
	 */
	public void addUser(VmaxUser user) {
		
		HashMap<Integer, VmaxUser> dico = this.getVmaxUser();
		ArrayList<VmaxUser> l = this.getList_vmaxUser();
		
		dico.put(user.getID(), user);
		l.add(user);
		this.setVmaxUser(dico);
		this.setList_vmaxUser(l);
		this.addUserglobal(user);
	}

	/**
	 * add user with no cards
	 * @param user CreditCard user
	 */
	public void addUser(CreditCardUser user) {
	
	HashMap<Integer, CreditCardUser> dico = this.getCreditUser();
	ArrayList<CreditCardUser> l = this.getList_cerditUser();
	
	dico.put(user.getID(), user);
	l.add(user);
	this.setCreditUser(dico);
	this.setList_cerditUser(l);
	this.addUserglobal(user);
	}
	
	
	/**
	 * Create a user and add it to the data base
	 * @param cardType NONE, VLIBRE or VMAX
	 * @throws WrongCartTypeException Exception if the card type is wrong
	 */
	public void addUser(String cardType) throws WrongCartTypeException{
		if(cardType.equalsIgnoreCase("VLIBRE")) {
			VlibreUser user = new VlibreUser(this);
			
			HashMap<Integer, VlibreUser> dico = this.getVlibreUser();
			ArrayList<VlibreUser> l = this.getList_vlibreUser();
			
			dico.put(user.getID(), user);
			l.add(user);
			this.setVlibreUser(dico);
			this.setList_vlibreUser(l);
			
			this.addUserglobal(user);
			
		}
		else if(cardType.equalsIgnoreCase("VMAX")) {
			VmaxUser user = new VmaxUser(this);
			
			HashMap<Integer, VmaxUser> dico = this.getVmaxUser();
			ArrayList<VmaxUser> l = this.getList_vmaxUser();
			
			dico.put(user.getID(), user);
			l.add(user);
			this.setVmaxUser(dico);
			this.setList_vmaxUser(l);
			this.addUser(user);
			
		}
		else if(cardType.equalsIgnoreCase("NONE")) {
			CreditCardUser user = new CreditCardUser(this);
			
			HashMap<Integer, CreditCardUser> dico = this.getCreditUser();
			ArrayList<CreditCardUser> l = this.getList_cerditUser();
			
			dico.put(user.getID(), user);
			l.add(user);
			this.setCreditUser(dico);
			this.setList_cerditUser(l);
			this.addUser(user);
		}
		else {
			throw new WrongCartTypeException(this);
		}
	}
	
	
	/**
	 * @return random station from the data base
	 */
	public Station RandomStation() {
		ArrayList<Station> list_of_station = this.getList_stations();
		Random rand = new Random(); 
        Station station =  list_of_station.get(rand.nextInt(list_of_station.size()));
        while(station.isFull()) {
        	station =  list_of_station.get(rand.nextInt(list_of_station.size()));
        }
        return station;
	}
	
	
	
	//////////////SORTING OF STATION////////////////////////
	
	
	/**
	 * @return the maximal time when a station had been used from all the station in the data base
	 */
	public int returnMaxTimeUse() {
		ArrayList<Station> list_station= this.getList_stations();
		TimeMaxComparator timeComparator = new TimeMaxComparator();
		Collections.sort(list_station, timeComparator);
		Station station = list_station.get(list_station.size()-1);
		return station.returnMaxUseTime();
	}
	
	
	/**
	 * @return list of sorted station regarding their use
	 */
	public ArrayList<Station> sort_used_station(){
		ArrayList<Station> list_station= this.getList_stations();
		UsedComparator useComparator = new UsedComparator();
		Collections.sort(list_station, useComparator);
		return list_station;
	}
	
	/**
	 * @return list of sorted station regarding their average occupation rate
	 */
	public ArrayList<Station> sort_occupied_station(){
		ArrayList<Station> list_station= this.getList_stations();
		
		int tmax = this.returnMaxTimeUse();
		if(tmax>0) {
			OccupationComparator occupationComparator = new OccupationComparator();
			occupationComparator.setTmax(tmax);
			Collections.sort(list_station, occupationComparator);
		}
		else {
			NinitialComparator ncomp = new NinitialComparator();
			Collections.sort(list_station, ncomp);
		}
		
		
		return list_station;
	}
	
	
	/**
	 * sort the list of station according to the policy and print it
	 * @param Policy USED or OCCUPIED
	 * @throws PolicyException Exception if the policy is incorrect
	 */
	public void sort(String Policy) throws PolicyException{
		if(Policy.equalsIgnoreCase("USED")) {
			ArrayList<Station> l = this.sort_used_station();
			this.setList_stations(l);
			System.out.println("------------------------------------------------------");
			System.out.println("Stations sorted by number of time they have been used");
			for(Station s : l) {
				s.report();
			}
		}
		else if(Policy.equalsIgnoreCase("OCCUPIED")){
			ArrayList<Station> l = this.sort_occupied_station();
			this.setList_stations(l);
			System.out.println("------------------------------------------------------");
			System.out.println("Stations sorted by thier occupation rate");
			for(Station s : l) {
				s.report();
			}
		}
		else {
			throw new PolicyException(this);
		}
	}
	
	
	
	////////////////////////////////////////////////UPDATE ///////////////////////////////////////////////////
	
	
	public void update(VlibreUser user) {
		if(user.onBike) {
			if(user.isPlannedRide()) {
				System.out.println("User "+ user.getID() + " is going to Station "+ user.getDest().getID() );
				}
			System.out.println("User "+ user.getID() + " rent Bike " + user.getBike().getID() + " (" + user.getBike().getType() 
					+ ") on Station " + user.getBike().getStation().getID());
			System.out.println("User "+ user.getID() + " is on Bike" );
		}
		else if(user.isChangedDest()) {
			System.out.println("User "+ user.getID() + " is going to "+ user.getDest().getID() );	
			user.setChangedDest(false);
		}
		else if(user.isChangedAmountCharge()) {
			System.out.println("User "+ user.getID() + " returned bike " + user.getBike().getID() + " (" + user.getBike().getType() + ")"
					+ " on Station " + user.getBike().getStation().getID());
			
			System.out.println("User "+ user.getID() + " has now a total amount of charges of : " + user.getTotalAmountCharges() );
			user.setChangedAmountCharge(false);
			if(user.isChangedtime_balance()) {
				System.out.println("User "+ user.getID() +
					" earned time credit. Available Credit-time : " + user.getTime_credit_balance() );
				user.setChangedtime_balance(false);
				}
		}
		else {
			System.out.println("State of user "+ user.getID() + " has changed" );
		}
			
	}
	
	public void update(VmaxUser user) {
		if(user.onBike) {
			if(user.isPlannedRide()) {
				System.out.println("User "+ user.getID() + " is going to Station "+ user.getDest().getID() );
				}
			System.out.println("User "+ user.getID() + " rent Bike " + user.getBike().getID() + " (" + user.getBike().getType() 
					+ ") on Station " + user.getBike().getStation().getID());
			System.out.println("User "+ user.getID() + " is on Bike" );
		}
		else if(user.isChangedDest()) {
			System.out.println("User "+ user.getID() + " is going to "+"(" + user.getDest().getGPS()[0] +","+user.getDest().getGPS()[1]+")" );
			user.setChangedDest(false);
		}
		else if(user.isChangedAmountCharge()) {
			System.out.println("User "+ user.getID() + " returned bike " + user.getBike().getID() + " (" + user.getBike().getType() + ")"
					+ " on Station " + user.getBike().getStation().getID());
			
			System.out.println("User "+ user.getID() + " has now a total amount of charges of : " + user.getTotalAmountCharges() );
			user.setChangedAmountCharge(false);
		}
		else {
			System.out.println("State of user "+ user.getID() + " has changed" );
		}
	}
	
	
	public void update(CreditCardUser user) {
		if(user.onBike) {
			if(user.isPlannedRide()) {
				System.out.println("User "+ user.getID() + " is going to Station "+ user.getDest().getID() );
				}
			System.out.println("User "+ user.getID() + " rent Bike " + user.getBike().getID() + " (" + user.getBike().getType() 
					+ ") on Station " + user.getBike().getStation().getID());
			System.out.println("User "+ user.getID() + " is on Bike" );
		}
		else if(user.isChangedDest()) {
			System.out.println("User "+ user.getID() + " is going to "+"(" + user.getDest().getGPS()[0] +","+user.getDest().getGPS()[1]+")" );
			user.setChangedDest(false);
		}
		else if(user.isChangedAmountCharge()) {
			System.out.println("User "+ user.getID() + " returned bike " + user.getBike().getID() + " (" + user.getBike().getType() + ")"
					+ " on Station " + user.getBike().getStation().getID());
			
			System.out.println("User "+ user.getID() + " has now a total amount of charges of : " + user.getTotalAmountCharges() );
			user.setChangedAmountCharge(false);
		}
		else {
			System.out.println("State of user "+ user.getID() + " has changed" );
		}
	}
	
	
	
	public void update(Bycicle bike) {
		if(bike.isFreeStatus() == false) {
			System.out.println("Bike "+ bike.getID() + " (" + bike.getType() + ")" + " is available in Station " + bike.getStation().getID() );
		}
		
	}
	
	public void update(Station station) {
		if(station.isChangedNbRent()) {
			
			if(station.isChangeElectric()) {
				System.out.println("Station " + station.getID()+ " has rent an Electric Bike");
				station.setChangeElectric(false);
			}
			else {
				System.out.println("Station " + station.getID()+ " has rent an Mechanic Bike");
				station.setChangeMechanic(false);
			}
			station.setChangedNbRent(false);
		}
		else {
			
			if(station.isChangeElectric()) {
				System.out.println("Station " + station.getID()+ " has now one more Electric Bike");
				station.setChangeElectric(false);
			}
			else {
				System.out.println("Station " + station.getID()+ " has now one more Mechanic Bike");
				station.setChangeMechanic(false);}
			station.setChangedNbReturn(false);
			}
			
		}


	
	
				/////////////////////////////////////////GETTER & SETTER/////////////////////////////////////////
	
	
	public HashMap<Integer, Station> getStations() {
		return stations;
	}

	public void setStations(HashMap<Integer, Station> stations) {
		this.stations = stations;
	}

	public ArrayList<Station> getList_stations() {
		return list_stations;
	}

	public void setList_stations(ArrayList<Station> list_stations) {
		this.list_stations = list_stations;
	}

	public HashMap<Integer, User> getUser() {
		return user;
	}

	public void setUser(HashMap<Integer, User> user) {
		this.user = user;
	}

	public ArrayList<User> getList_user() {
		return list_user;
	}

	public void setList_user(ArrayList<User> list_user) {
		this.list_user = list_user;
	}

	public HashMap<Integer, VlibreUser> getVlibreUser() {
		return vlibreUser;
	}

	public void setVlibreUser(HashMap<Integer, VlibreUser> vlibreUser) {
		this.vlibreUser = vlibreUser;
	}

	public ArrayList<VlibreUser> getList_vlibreUser() {
		return list_vlibreUser;
	}

	public void setList_vlibreUser(ArrayList<VlibreUser> list_vlibreUser) {
		this.list_vlibreUser = list_vlibreUser;
	}

	public HashMap<Integer, VmaxUser> getVmaxUser() {
		return vmaxUser;
	}

	public void setVmaxUser(HashMap<Integer, VmaxUser> vmaxUser) {
		this.vmaxUser = vmaxUser;
	}

	public ArrayList<VmaxUser> getList_vmaxUser() {
		return list_vmaxUser;
	}

	public void setList_vmaxUser(ArrayList<VmaxUser> list_vmaxUser) {
		this.list_vmaxUser = list_vmaxUser;
	}

	public HashMap<Integer, CreditCardUser> getCreditUser() {
		return creditUser;
	}

	public void setCreditUser(HashMap<Integer, CreditCardUser> creditUser) {
		this.creditUser = creditUser;
	}

	public ArrayList<CreditCardUser> getList_cerditUser() {
		return list_cerditUser;
	}

	public void setList_cerditUser(ArrayList<CreditCardUser> list_cerditUser) {
		this.list_cerditUser = list_cerditUser;
	}
	
	

	
	///////////////////////////////////////////////////////////////////////////////
	
	
	
	
	
	
	
	
	
}
	
