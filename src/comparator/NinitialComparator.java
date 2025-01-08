package comparator;

import java.util.Comparator;

import core.Station;

/**
 * Comparator between Stations regarding their initial number of bikes
 * @author Oussama
 *
 */
public class NinitialComparator implements Comparator<Station>{
	
	
	public int compare(Station a, Station b) {
		int na = a.getElectricBike().size() + a.getMechanicBike().size();
		int nb = b.getElectricBike().size() + b.getMechanicBike().size();
		return na - nb;
	}

}
