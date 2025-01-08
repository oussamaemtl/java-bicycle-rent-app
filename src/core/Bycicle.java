package core;



/**
 * Bycicle class
 * There are only getter and setter methods
 * @author Oussama
 *
 */
public class Bycicle {

	/**
	 * Static attribute for giving a unique ID to an instance
	 */
	private static long counter;
	/**
	 * ID of the bike
	 */
	private long ID;
	/**
	 * ID of the user on bike
	 * If no user, it is equal to -1
	 */
	private long IDuser = -1;
	/**
	 * Type of bike (electric or mechanic)
	 */
	private String type;
	/**
	 * boolean to know if the bike is rent by someone
	 */
	private boolean freeStatus;
	/**
	 * boolean to know the possible station of where the bike is located if not rent
	 */
	private Station station; // if not free station is the futur station
	
	/**
	 * boolean to know if the state of the station has changed
	 */
	private boolean changed = false;
	
	/**
	 * @param type type of bike
	 * @param station Station where you want to put the bike
	 */
	public Bycicle(String type, Station station) {
		Bycicle.counter ++;
		this.ID = Bycicle.counter;
		this.type = type;
		this.freeStatus =true;
		this.station = station;
		this.changed =false;
		
	}
	
	/**
	 * @param type type of bike
	 * @param station Station where you want to put the bike
	 * @param b boolean to know the free status of the bike
	 * @param IDuser to know the ID of the bike user
	 */
	public Bycicle(String type, Station station, boolean b, long IDuser) {
		Bycicle.counter ++;
		this.ID = Bycicle.counter;
		this.type = type;
		this.freeStatus =b;
		this.station = station;
		this.IDuser = IDuser;
		
	}
	
	public Bycicle() {}
	
	
	public void update() {
		
	}

	public long getID() {
		return ID;
	}

	public void setID(long iD) {
		ID = iD;
	}

	public long getIDuser() {
		return IDuser;
	}

	public void setIDuser(long iDuser) {
		IDuser = iDuser;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isFreeStatus() {
		return freeStatus;
	}

	public void setFreeStatus(boolean freeStatus) {
		this.freeStatus = freeStatus;
	}

	public Station getStation() {
		return station;
	}

	public void setStation(Station station) {
		this.station = station;
	}

	public boolean isChanged() {
		return changed;
	}

	public void setChanged(boolean changed) {
		this.changed = changed;
	}
}
