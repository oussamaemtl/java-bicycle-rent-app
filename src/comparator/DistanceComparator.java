package comparator;

import java.util.Comparator;

import core.Distance;
import core.Station;

/**
 * Comparator between Stations regarding their euclidian distance
 * between a given station
 * @author Oussama
 */
public class DistanceComparator implements Comparator<Station>{
	
	private double [] position;
	public Distance distance = new Distance();
	
	public double[] getPosition() {
		return position;
	}


	public void setPosition(double[] position) {
		this.position = position;
	}
	
	@Override
	public int compare(Station a, Station b) {
		return distance.compare(this.getPosition(), a.getGPS()) - distance.compare(this.getPosition(), b.getGPS());
	}












	
}
