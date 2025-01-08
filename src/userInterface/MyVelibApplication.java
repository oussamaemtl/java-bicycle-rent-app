package userInterface;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

import org.ini4j.Ini;
import org.ini4j.InvalidFileFormatException;

import core.MyVelibCreation;
	


/**
 * Final application
 * @author Oussama
 *
 */
public class MyVelibApplication {
	
	public MyVelibCreation e;
	public static Ini aread;
	
	/**
	 * 2 modes : Interactive one and an other one to run tests
	 * @param args runtest testScenario.txt or I
	 */
	public static void main(String[] args) {
		
		
		if(args.length > 0) {
			aread = new Ini();
			
			if(args[0].equals("I")) {
				
				
				
				try {
					aread.load(new FileReader("./eval/my_velib.ini"));
					
					String velibName = aread.get("default","name", String.class );
					int nstations = aread.get("default","nstations", int.class);
					int nslot = aread.get("default", "nslots", int.class);
					int cote = aread.get("default", "s", int.class);
					int nbikes = aread.get("default", "nbike", int.class);
					
					
					MyVelibCreation e = new MyVelibCreation();
					
					e.setup(velibName, nstations, nslot, cote, nbikes);
					
					
					
					int j=0;
					boolean res = true;
					
					while(res) {
						if(j==0) {
							System.out.println("Initialization completed");
							System.out.println("Welcome to MyVelib Application \n");
							System.out.println("tap <help> to show the command lines \n");
							j++;
						}
						else {
							Scanner scanner = new Scanner(System.in);
							System.out.println(">>>");
							String s = scanner.nextLine();
							String [] arg = InputToList.StringToList(s);
							String cmd = arg[0];

							if(cmd.equalsIgnoreCase("exit")) {
								res = false;}
							else {
								CommandLine.commandline(cmd, arg, e);
							}
						}
					}
					
				} catch (InvalidFileFormatException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
		
			}	
			
			
			else if(args[0].equalsIgnoreCase("runtest")) {
				String fileName = "./eval/" + args[1];
				char nb = args[1].charAt(12);
				try {
					PrintStream myconsole = new PrintStream(new File("./eval/output" + nb+".txt"));
					System.setOut(myconsole);
					
				
					try {
						aread.load(new FileReader("./eval/my_velib.ini"));
						String velibName = aread.get("default","name", String.class );
						int nstations = aread.get("default","nstations", int.class);
						int nslot = aread.get("default", "nslots", int.class);
						int cote = aread.get("default", "s", int.class);
						int nbikes = aread.get("default", "nbike", int.class);
						
						
						MyVelibCreation e = new MyVelibCreation();
						
						e.setup(velibName, nstations, nslot, cote, nbikes);
						
						//INITIALISATION
						
						
						//EXECUTION OF THE COMMAND WRITTEN IN TEST SCENARIO
						ArrayList<String> instructions = runtest.fileToStringList(fileName);
						for(String instr : instructions) {
							String [] arg = InputToList.StringToList(instr);
							String cmd = arg[0];
							CommandLine.commandline(cmd, arg, e);
						}
					} catch (IOException e1) {
						
						// TODO Auto-generated catch block
						e1.printStackTrace();}
				
				}
				catch(FileNotFoundException fx) {
					System.out.println(fx);				}
				
				
				
			}
		}
		else {
			System.out.println("Unknown Command. Tap runtest <fileName> to run test.");
			System.out.println("Tap <I> to go to interactive mode.");
		
		}
	}
	
	
}
