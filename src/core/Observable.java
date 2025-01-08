package core;

/**
 * Observale
 * @author Oussama
 *
 */
public interface Observable {
	public void registerObserver(Observer observer);
	
	public void removeObserver(Observer observer);
	
	public void notifyObservers();

}
