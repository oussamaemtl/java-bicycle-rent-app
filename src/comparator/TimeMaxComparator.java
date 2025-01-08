package comparator;

import java.util.Comparator;

import core.Station;

/**
 * Compare stations regarding the last time they have been used
 * @author Oussama
 *
 */
public class TimeMaxComparator implements Comparator<Station>{
	
	public int compare(Station a, Station b) {
		int ta = a.returnMaxUseTime();
		int tb = b.returnMaxUseTime();
		return ta-tb;
	}

}
