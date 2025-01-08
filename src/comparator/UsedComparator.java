package comparator;

import java.util.Comparator;

import core.Station;

/**
 * Comare stations regarding the number of times they have been used
 * @author Oussama
 *
 */
public class UsedComparator implements Comparator<Station>{
	
	@Override
	public int compare(Station a, Station b) {
		int res = (int) (a.getNumUtiliy() - b.getNumUtiliy());
		return res;
	}

}
