package core;

import java.util.ArrayList; 
import java.util.Random;

/**
 * Station
 * @author Oussama
 *
 */
public class Station implements Statistic{
	
	/**
	 * counter for unique station ID
	 */
	private static int counter;
	/**
	 * statio ID
	 */
	private int ID;
	/**
	 * List of available electric bikes
	 */
	private ArrayList<Bycicle> electricBike= new ArrayList<Bycicle>(); //ensembles des vélos disponibles
	/**
	 * List of available mechanic bikes
	 */
	private ArrayList<Bycicle> mechanicBike= new ArrayList<Bycicle>();
	/**
	 * List of available bikes
	 */
	private ArrayList<Bycicle> bikes = new ArrayList<Bycicle>();
	/**
	 * Capacity of the station
	 */
	private int capacity;
	/**
	 * Number of electric bikes
	 */
	private int nbElec;
	/**
	 * Number of Mechanic bikes
	 */
	private int nbMech;
	/**
	 * true if standard station
	 */
	private boolean standard;
	/**
	 * localization of the station
	 */
	private double [] GPS;
	/**
	 * number of rent in this station
	 */
	private long numOfRent = 0;
	/**
	 * number of return in this station
	 */
	private long numOfReturn = 0;
	/**
	 * number of use of this station
	 */
	private long numUtiliy = 0;
	/**
	 * velib network of the station
	 */
	private MyVelib myVelib;
	/**
	 * true if station is online
	 */
	private boolean online = true;
	/**
	 * true if station hasbeen used
	 */
	public boolean hasbeenused = false;
	
	//ATTRIBUTES FOR STATISTIC
	
	/**
	 * list of time peole rent bikes at this station
	 */
	private ArrayList<Integer> list_time_rent= new ArrayList<Integer>();
	/**
	 * List of time people returned bikes at this station
	 */
	private ArrayList<Integer> list_time_return= new ArrayList<Integer>();
	/**
	 * number of initial bikes at this station during a velib network creation
	 */
	private int initial_nbikes = -1;
	
	//ATTRIBUT OF CHANGE FOR OBSERVE PATTERN
	
	/**
	 * true if the station state has changed
	 */
	private boolean changed = false;
	
	private boolean changedNbRent = false;
	private boolean changedNbReturn = false;
	private boolean changeElectric = false;
	private boolean changeMechanic= false;
	
	
	//3 constructors
	
	
	public Station(int capacity, double [] GPS, boolean b, ArrayList<Bycicle> electricBike, 
			ArrayList<Bycicle> mechanicBike, MyVelib myVelib) {
		Station.counter ++;
		this.ID = Station.counter;
		this.capacity = capacity;
		this.electricBike = electricBike;
		this.mechanicBike = mechanicBike;
		this.nbElec = this.electricBike.size();
		this.nbMech = this.mechanicBike.size();
		this.GPS = GPS;
		this.standard = b;
		this.myVelib = myVelib;
				
	}
	
	public Station(int capacity, double [] GPS, boolean b, MyVelib myVelib) {
		Station.counter ++;
		this.ID = Station.counter;
		this.capacity = capacity;
		this.GPS = GPS;
		this.standard = b;
		this.myVelib = myVelib;
		this.nbElec = this.electricBike.size();
		this.nbMech = this.mechanicBike.size();
		}
	
		
	public Station(int capacity, double [] GPS, MyVelib myVelib) {
		Station.counter ++;
		this.ID = Station.counter;
		this.capacity = capacity;
		this.GPS = GPS;
		this.standard = true;
		this.myVelib = myVelib;
		this.nbElec = this.electricBike.size();
		this.nbMech = this.mechanicBike.size();
		}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public ArrayList<Bycicle> getElectricBike() {
		return electricBike;
	}

	public void setElectricBike(ArrayList<Bycicle> electricBike) {
		this.electricBike = electricBike;
	}

	public ArrayList<Bycicle> getMechanicBike() {
		return mechanicBike;
	}

	public void setMechanicBike(ArrayList<Bycicle> mechanicBike) {
		this.mechanicBike = mechanicBike;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public boolean isStandard() {
		return standard;
	}

	public void setStandard(boolean standard) {
		this.standard = standard;
	}

	public double [] getGPS() {
		return GPS;
	}

	public void setGPS(double [] gPS) {
		GPS = gPS;
	}
	

	public ArrayList<Bycicle> getBikes() {
		return bikes;
	}

	public void setBikes(ArrayList<Bycicle> bikes) {
		this.bikes = bikes;
	}
	
	public long getNumOfRent() {
		return numOfRent;
	}

	public void setNumOfRent(long numOfRent) {
		this.numOfRent = numOfRent;
	}

	public long getNumOfReturn() {
		return numOfReturn;
	}

	public void setNumOfReturn(long numOfReturn) {
		this.numOfReturn = numOfReturn;
	}

	public long getNumUtiliy() {
		return numUtiliy;
	}

	public void setNumUtiliy(long numUtiliy) {
		this.numUtiliy = numUtiliy;
	}
	
	/**
	 * print the current state of the station
	 */
	public void currentState() {
		double numOfbike = this.getElectricBike().size() + this.getMechanicBike().size() ;
		double percent = ((numOfbike/this.getCapacity())*100);
		System.out.println("------------------------------------------------------");
		if(this.isOnline()) {
			System.out.println("Station "+this.getID()+" Status : Online");
		}
		else {
			System.out.println("Station "+this.getID()+" Status : Offline");
		}
		System.out.println("Station"+this.getID()+ " is "+ percent + "% full"+"\n");
	}
	


	
	/**
	 * @return true if the station is full
	 */
	public boolean isFull() {
		int numOfbike = this.getElectricBike().size() + this.getMechanicBike().size();
		if(numOfbike < this.getCapacity()) {return false;}
		return true;
				}
	
	/**
	 * @return true if the station is empty
	 */
	public boolean isEmpty() {
		int numOfbike = this.getElectricBike().size() + this.getMechanicBike().size();
		if(numOfbike == 0) {
			return true;
		}
		return false;
	}
	
	/**
	 * @return true if there is no electric bikes
	 */
	public boolean isThereNoElectric() {
		if(this.getElectricBike().size()>0) {return false;}
		return true;
	}
	
	/**
	 * @return true if there is no mechanic bikes
	 */
	public boolean isThereNoMechanic() {
		if(this.getMechanicBike().size()>0) {return false;}
		return true;
	}
	
	/**
	 * update the number of bikes
	 */
	public void update_bikes() {
		ArrayList<Bycicle> res= new ArrayList<Bycicle>();
		for(Bycicle bike : this.getElectricBike()) {
			res.add(bike);
		}
		for(Bycicle bike : this.getMechanicBike()) {
			res.add(bike);
		}
		this.setBikes(res);
		
	}
	
	/**
	 * @param bikeType electric or mechanic
	 * @return a random electric or mechanic bike 
	 */
	public Bycicle getRandomBike(String bikeType) {
		Random rand = new Random();
		
		if(bikeType.equalsIgnoreCase("ELECTRIC") && this.getElectricBike().size() > 0) {
			int idx = rand.nextInt(this.getElectricBike().size());
			return this.getElectricBike().get(idx);
			
			}
		else if(bikeType.equalsIgnoreCase("MECHANIC") && this.getMechanicBike().size() > 0) {
			int idx = rand.nextInt(this.getMechanicBike().size());
			return this.getMechanicBike().get(idx);
		}
		else {
			int idx = rand.nextInt(this.getBikes().size());
			return this.getBikes().get(idx);
		}
		}
	
	/**
	 * @return arandom bike
	 */
	public Bycicle getRandomBike() {
		Random rand = new Random();
		int idx = rand.nextInt(this.getBikes().size());
		return this.getBikes().get(idx);
	}

	/**
	 * update the total number of use of the station
	 */
	public void updateNumUtility() {
		this.setNumUtiliy(this.getNumOfRent() + this.getNumOfReturn());
	}
	
	/**
	 *print the balance of the station
	 */
	public void balance() {
		System.out.println("------------------------------------------------------");
		System.out.println("Blance Station "+ this.getID());
		System.out.println("Rents operations : " + this.getNumOfRent());
		System.out.println("Return operations : " + this.getNumOfReturn());
		System.out.println("Total Operations : " + this.getNumUtiliy());
		System.out.println("Total Average Occupation since last use : " + this.returnTotalAverageOccupation()+"\n");
	}
	
	
	
	
	/**
	 * @param te time te
	 * @param ts time ts
	 * @return the occupation rate between te and ts
	 */
	public double returnRatio(int te, int ts) {
		int N = this.getCapacity();
		int Ni = this.getInitial_nbikes();
		int delta = ts-te;
		ArrayList<Integer> list_rent = this.getList_time_rent();
		ArrayList<Integer> list_return = this.getList_time_return();
		
		
		// CREATE SUBLIST
		
		
		
		ArrayList<Integer> sub_list_rent = new ArrayList<Integer>();
		ArrayList<Integer> sub_list_return = new ArrayList<Integer>();
		
		for(int time : list_rent) {
			if(time>=te && time <=ts) {
				sub_list_rent.add(time);
			}
		}
		for(int time : list_return) {
			if(time>=te && time <=ts) {
				sub_list_return.add(time);
			}
		}
		
		int nbOfrent = 0;
		int nbOfreturn  = 0;
		
		
		if(sub_list_rent.size()>0) {
			int time0rent = sub_list_rent.get(0);
			nbOfrent = list_rent.indexOf(time0rent);
		}
		
		if(sub_list_return.size()>0) {
			int time0return = sub_list_return.get(0);
			nbOfreturn = list_return.indexOf(time0return);
		}
		
		
		
		//CALCUL DE N(te)
		
		int N_te = Ni + (nbOfreturn - nbOfrent);
		
		///
		
		int alpha = 0;
		int beta = 0;
		
		for(int k = 0; k<sub_list_rent.size();k++) {
			alpha+= sub_list_rent.get(k);
		}
		
		for(int k =0; k< sub_list_return.size();k++) {
			beta += sub_list_return.get(k);
		}
		

		double a11bis = N_te - (sub_list_rent.size() + sub_list_return.size());
		double a11 = Double.max(a11bis, 0);
		double a12 = N;
		
		
		double a1 = a11/a12;
		
		double a21 = alpha - sub_list_rent.size()*te;
		double a22 = N*delta;
		
		double a2 = a21/a22;
		
		double a31 = (sub_list_return.size()*ts) - beta;
		
		double a32 = N*delta;
		
		double a3 = a31/a32;
		
		
		double ratio = a1 + a2 + a3;
		
		return ratio;
	}
	
	
	
	
	/**
	 * print the occupation rate between te and ts
	 */
	public void ratio(int te, int ts) {
		if(ts > te) {
			
			double ratio = this.returnRatio(te, ts);
			System.out.println("Average Occupation ratio in Station "+ this.getID() +  " during ["+te +","+ts+"] : " + ratio);
			
			
			
			}
		else {
			System.out.println("ts must be greater than te and both must be integer");
		}
	}
	
	
	
	
	/**
	 * @return the last time the station has been used
	 */
	public int returnMaxUseTime() {
		int t1 = 0;
		int t2 = 0;
		
		if(this.getList_time_rent().size() >0) {
			t1 = this.getList_time_rent().get(this.getList_time_rent().size()-1);
		}
		if(this.getList_time_return().size() >0) {
			t2 = this.getList_time_return().get(this.getList_time_return().size()-1);
			}
		int max = Integer.max(t1, t2);
		return max;				
		
		
	}
	
	
	/**
	 * @return the glabal average occupation rate
	 */
	public double returnTotalAverageOccupation() {
		
		
		int max = this.returnMaxUseTime();
		double res = this.returnRatio(0, max);
		
		return res;
	}
	
	
	
	
	/**
	 * Remove bike from station
	 * @param bike Bike
	 */
	public void removeBike(Bycicle bike) {
		if(bike.getType().equalsIgnoreCase("ELECTRIC")) {
			ArrayList<Bycicle> elecBikes = this.getElectricBike();
			elecBikes.remove(bike);
			this.setElectricBike(elecBikes);
			this.update_bikes();
		}
		else {
			ArrayList<Bycicle> mechanicBike = this.getMechanicBike();
			mechanicBike.remove(bike);
			this.setMechanicBike(mechanicBike);
			this.update_bikes();
		}
	}
	
	/**
	 * add bike to station
	 * @param bike bike
	 */
	public void addBike(Bycicle bike) {
		if(bike.getType().equalsIgnoreCase("ELECTRIC")) {
			ArrayList<Bycicle> elecBikes = this.getElectricBike();
			elecBikes.add(bike);
			this.setElectricBike(elecBikes);
			this.update_bikes();
		}
		else {
			ArrayList<Bycicle> mechanicBike = this.getMechanicBike();
			mechanicBike.add(bike);
			this.setMechanicBike(mechanicBike);
			this.update_bikes();
		}
	}
	
	
	
	
	/**
	 *print report of the station
	 */
	public void report() {
		int numOfbike = this.getElectricBike().size() + this.getMechanicBike().size() ;
		if(this.isStandard()) {
			System.out.println("------------------------------------------------------");
			System.out.println("Station "+ this.getID());
			System.out.println("Type : Standard");
			if(this.isOnline()) {
				System.out.println("Status: Online");
			}
			else {
				System.out.println("Status: Offline");
			}
			System.out.println("Total Capacity : " + this.getCapacity());
			System.out.println("Free Parking slots : "+ (this.getCapacity()- numOfbike));
			System.out.println("Available electric bycicles : " + this.getElectricBike().size() );
			System.out.println("Available Mechanic bycicles : " + this.getMechanicBike().size());
			System.out.println("Rents operations : " + this.getNumOfRent());
			System.out.println("Return operations : " + this.getNumOfReturn());
			System.out.println("Total Operations : " + this.getNumUtiliy());
			if(this.hasbeenused) {
				System.out.println("Total Average Occupation since last use : " + this.returnTotalAverageOccupation());
			}
			else {
				double a = this.getInitial_nbikes();
				double b = this.capacity;
				
				System.out.println("This station has never been used. Current occupation rate : " + a/b);
			}
			System.out.println("Localisation : ("+this.getGPS()[0]+","+this.getGPS()[1]+")"+"\n");
			
			}
		
		else {
			System.out.println("------------------------------------------------------");
			System.out.println("Station "+ this.getID());
			System.out.println("Type : Plus");
			if(this.isOnline()) {
				System.out.println("Status : Online");
			}
			else {
				System.out.println("Status : Offline");
			}
			System.out.println("Total Capacity : " + this.getCapacity());
			System.out.println("Free Parking slots : "+ (this.getCapacity()- numOfbike));
			System.out.println("Available electric bycicles : " + this.getElectricBike().size() );
			System.out.println("Available Mechanic bycicles : " + this.getMechanicBike().size());
			System.out.println("Rents operations : " + this.getNumOfRent());
			System.out.println("Return operations : " + this.getNumOfReturn());
			System.out.println("Total Operations : " + this.getNumUtiliy());
			if(this.hasbeenused) {
				System.out.println("Total Average Occupation since last use : " + this.returnTotalAverageOccupation());
			}
			else {
				double a = this.getInitial_nbikes();
				double b = this.capacity;
				
				System.out.println("This station has never been used. Current occupation rate : " + a/b);
			}
			System.out.println("Localisation : ("+this.getGPS()[0]+","+this.getGPS()[1]+")"+"\n");
			}
		
		
	}

	public MyVelib getMyVelib() {
		return myVelib;
	}

	public void setMyVelib(MyVelib myVelib) {
		this.myVelib = myVelib;
	}

	public boolean isChangedNbRent() {
		return changedNbRent;
	}

	public void setChangedNbRent(boolean changedNbRent) {
		this.changedNbRent = changedNbRent;
	}

	public boolean isChangedNbReturn() {
		return changedNbReturn;
	}

	public void setChangedNbReturn(boolean changedNbReturn) {
		this.changedNbReturn = changedNbReturn;
	}

	public boolean isChangeElectric() {
		return changeElectric;
	}

	public void setChangeElectric(boolean changeElectric) {
		this.changeElectric = changeElectric;
	}

	public boolean isChangeMechanic() {
		return changeMechanic;
	}

	public void setChangeMechanic(boolean changeMechanic) {
		this.changeMechanic = changeMechanic;
	}

	public int getNbElec() {
		return nbElec;
	}

	public void setNbElec(int nbElec) {
		this.nbElec = nbElec;
	}

	public int getNbMech() {
		return nbMech;
	}

	public void setNbMech(int nbMech) {
		this.nbMech = nbMech;
	}

	public boolean isChanged() {
		return changed;
	}

	public void setChanged(boolean changed) {
		this.changed = changed;
	}

	public boolean isOnline() {
		return online;
	}

	public void setOnline(boolean online) {
		this.online = online;
	}

	public ArrayList<Integer> getList_time_rent() {
		return list_time_rent;
	}

	public void setList_time_rent(ArrayList<Integer> list_time_rent) {
		this.list_time_rent = list_time_rent;
	}

	public ArrayList<Integer> getList_time_return() {
		return list_time_return;
	}

	public void setList_time_return(ArrayList<Integer> list_time_return) {
		this.list_time_return = list_time_return;
	}

	public int getInitial_nbikes() {
		return initial_nbikes;
	}

	public void setInitial_nbikes(int initial_nbikes) {
		this.initial_nbikes = initial_nbikes;
	}


	
	
}

