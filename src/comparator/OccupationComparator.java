package comparator;


import java.util.Comparator;

import core.Station;

/**
 * Compartor of stations regarding their occupation rate
 * @author Oussama
 *
 */
public class OccupationComparator implements Comparator<Station>{
	
	private int tmax;

	
	
	
	
	public int compare(Station a, Station b ) {
		double ra = a.returnRatio(0, this.tmax);
		double rb = b.returnRatio(0, this.tmax);
		double res = ra - rb;
		if(res<0){
			return -1;
		}
		else if(res ==0){
			return 0;
		}
		else {
			return 1;
		}
	}





	public int getTmax() {
		return tmax;
	}





	public void setTmax(int tmax) {
		this.tmax = tmax;
	}
	
	
	
}
