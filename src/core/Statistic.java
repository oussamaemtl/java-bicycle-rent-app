package core;

/**
 * Statistic for user and stations
 * @author Oussama
 *
 */
public interface Statistic {
 
	public void balance();
	
	public void report();
	
	public void ratio(int te, int ts);
}
