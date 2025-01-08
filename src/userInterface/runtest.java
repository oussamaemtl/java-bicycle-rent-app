package userInterface;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * For reading and transform the test Scenario files
 * @author Oussama
 *
 */
public class runtest {

	
	
	/**
	 * @param filename name of the file
	 * @return List of all the command of the file (each line of the file is a command )
	 */
	public static ArrayList<String> fileToStringList(String filename) {
		
		FileReader file = null;
		BufferedReader reader = null;
				
		ArrayList<String> res = new ArrayList<String>();
		
		try {
			file = new FileReader(filename);
			reader = new BufferedReader(file);
			
			String line ="";
			
			try {
				while ((line=reader.readLine()) != null) {
					res.add(line);
				}
			} catch (IOException e) {
				System.out.println("IOException");
			}
			
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		}
		
		return res;
	}
	
	
	
}
