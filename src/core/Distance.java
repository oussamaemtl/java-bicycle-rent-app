package core;

/**
 * Euclidian distance
 * @author Oussama
 *
 */
public class Distance {
	
	public int compare(double [] a, double [] b) {
		int res = (int)(double) Math.sqrt(((a[0]-b[0])*(a[0]-b[0])) + ((a[1]-b[1])*(a[1]-b[1])));
		return res;
	}
}
