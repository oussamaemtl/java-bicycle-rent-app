package core;


/**
 * Observer
 * @author Oussama
 *
 */
public interface Observer {
	
	//USER UPDATE
	
	public void update(VlibreUser user);
	
	public void update(VmaxUser user);
	
	
	public void update(CreditCardUser user);
	
	public void update(Bycicle bike);
	
	public void update(Station station);

}
