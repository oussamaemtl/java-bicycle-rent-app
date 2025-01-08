package userInterface;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * transform the input in a string array by deleting the spaces
 * @author Oussama
 *
 */
public class InputToList {
	
	
	/**
	 * @param string the input string
	 * @return array of string
	 */
	public static String [] StringToList(String string) {
		ArrayList<String> l = new ArrayList<String>(Arrays.asList(string.split(" ")));
		int n = l.size();
		
		String [] args = new String[n];
		for(int i = 0 ; i<n; i++) {
			args[i]=l.get(i);
		}
		return args;
	}
	
}
