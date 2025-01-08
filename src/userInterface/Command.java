package userInterface;

import core.*;
import exception.MyVelibNetworkUnexistent;
import exception.StationDoesNotExist;
import exception.UserDoesNotExist;
import exception.WrongCartTypeException;

/***
 * take all the methods of MyVelibCreation
 * and adapt them for user interaction
 * @author Oussama
 *
 */
public class Command {
	
	/**
	 * Dislplay the available commands
	 */
	public static void help() {
		System.out.println("setup <velibnetworkName>");
		System.out.println("-----------------------------------------");
		
		System.out.println("setup <name> <nstations> <nslots> <s> <nbikes>" );
		
		System.out.println("-----------------------------------------");
		
		System.out.println("addUser <userName,cardType, velibnetworkName> ");
		
		System.out.println("-----------------------------------------");
		
		System.out.println("offline <velibnetworkName, stationID> : to put oine the station stationID\r\n" + 
				"of the myVelib network velibnetworkName");
		
		System.out.println("-----------------------------------------");
		
		System.out.println("online <velibnetworkName, stationID> : to put online the station stationID of\r\n" + 
				"the myVelib network velibnetworkName");
		
		System.out.println("-----------------------------------------");
		
		System.out.println("rentBike <userID, stationID, bikeType, velibNetwork, time> : to let the user userID renting a bike from station\r\n" + 
				"stationID (if no bikes are available should behave accordingly)");
		
		System.out.println("-----------------------------------------");
		
		System.out.println("rentBike <userID, bikeType, velibNetwork, time> : to let the user userID renting a bike from station\r\n" + 
				"stationID (if no bikes are available should behave accordingly)");
		
		System.out.println("-----------------------------------------");
		
		System.out.println("rentBike <userName, bikeType, velibNetwork, time> : to let the user userID renting a bike from station");
		System.out.println("                                                    stationID (if no bikes are available should behave accordingly)");
		
		System.out.println("-----------------------------------------");
		
		System.out.println("returnBike <userID, stationID, time, velibNetwork> ");
		
		System.out.println("-----------------------------------------");
		
		System.out.println("returnBike <userName, time, velibNetwork> ");
		
		System.out.println("-----------------------------------------");
		
		System.out.println("returnBike <userID,  time, velibNetwork> ");
		
		System.out.println("-----------------------------------------");
		
		System.out.println("displayStation<velibnetworkName, stationID> : to display the statistics of station stationID ");
		System.out.println("                                               of a myVelib network velibnetwork.");
		
		System.out.println("-----------------------------------------");
		
		System.out.println("displayUser<velibnetworkName, userID> : to display the statistics "
				+ "of user userID of a myVelib network velibnetwork.");
		
		System.out.println("-----------------------------------------");
		
		System.out.println("sortStation<velibnetworkName, sortpolicy> : to display the stations in increas-\r\n" + 
				"ing order w.r.t. to the sorting policy of user sortpolicy.");
		
		System.out.println("-----------------------------------------");
		
		System.out.println("display <velibnetworkName>: to display the entire status (stations, parking bays,\r\n" + 
				"users) of an a myVelib network velibnetworkName.");
		
		System.out.println("-----------------------------------------");
		
		System.out.println("exit : to exit the application");
	}
	
	
	/**
	 * @param args list of string arguments
	 * @param e MyVelibCreation instance
	 */
	public static void setup(String[] args, MyVelibCreation e) {
		
		if(args.length == 2) {
		String velibnetworkName = args[1];
		e.setup(velibnetworkName);}
		else if(args.length == 6){

			
			String velibnetworkName = args[1];
			int nstations = -1;
			int nslots=-1;
			int s=-1;
			int nbikes=-1;
			
			try {
				nstations = Integer.parseInt(args[2]);
				nslots = Integer.parseInt(args[3]);
				s = Integer.parseInt(args[4]);
				nbikes = Integer.parseInt(args[5]);
				e.setup(velibnetworkName, nstations, nslots, s, nbikes);
			}catch(NumberFormatException d)
			{
				System.out.println("nstation,nslots,s,nbikes must be integer \n");
				System.out.println("setup <name> <nstations> <nslots> <s> <nbikes>\n");
			}			
		}
		else {
			System.out.println("Invalid arguments \n");
			System.out.println("setup <name> <nstations> <nslots> <s> <nbikes>\n");
		}
		
	}
	
	/**
	 * @param args list of string arguments
	 * @param e MyVelibCreation instance
	 */
	public static void rent(String [] args, MyVelibCreation e) {
		if(args.length == 5) {
			
			int userID;
			String bikeType = args[2];
			String velibnetworkName = args[3];
			String userName;
			int time;
			
			try {
				userID = Integer.parseInt(args[1]);
				time = Integer.parseInt(args[4]);
				try {
					e.rentBike(userID, bikeType, velibnetworkName, time);
				} catch (MyVelibNetworkUnexistent e1) {
					// TODO Auto-generated catch block
					e1.printMsg();
				} catch (UserDoesNotExist e1) {
					// TODO Auto-generated catch block
					e1.printMsg();
				}
				
				
			}catch(NumberFormatException e1) {
				try {
					userName = args[1];
					time = Integer.parseInt(args[4]);
					try {
						e.rentBike(userName, bikeType, velibnetworkName, time);
					} catch (UserDoesNotExist e2) {
						// TODO Auto-generated catch block
						e2.printMsg();
					}
					
				}catch(NumberFormatException e2) {
					System.out.println("Invalid arguments ");
					System.out.println("rent userID bikeType velibnetworkName time");
					System.out.println("rent userName bikeType velibnetworkName time");
					System.out.println("rent userID stationID bikeType velibnetworkName time");
				}
			}
			
		}
		else if(args.length == 6) {
			
			int userID;
			int stationID;
			String bikeType = args[3];
			String velibnetworkName = args[4];
			int time;
			
			try {
				userID = Integer.parseInt(args[1]);
				stationID = Integer.parseInt(args[2]);
				time = Integer.parseInt(args[5]);
				try {
					e.rentBike(userID, stationID, bikeType, velibnetworkName, time);
				} catch (MyVelibNetworkUnexistent e1) {
					// TODO Auto-generated catch block
					e1.printMsg();
				} catch (UserDoesNotExist e1) {
					// TODO Auto-generated catch block
					e1.printMsg();
				} catch (StationDoesNotExist e1) {
					// TODO Auto-generated catch block
					e1.printMsg();
				}
				
				
			}catch(NumberFormatException e2) {
				System.out.println("Invalid arguments ");
				System.out.println("rent userID bikeType velibnetworkName time");
				System.out.println("rent userName bikeType velibnetworkName time");
				System.out.println("rent userID stationID bikeType velibnetworkName time");
			}
			
			
			
		}
		else {
			System.out.println("Invalid arguments ");
			System.out.println("rent userID bikeType velibnetworkName time");
			System.out.println("rent userName bikeType velibnetworkName time");
			System.out.println("rent userID stationID bikeType velibnetworkName time");
		}
		
	}
	
	/**
	 * @param args list of string arguments
	 * @param e MyVelibCreation instance
	 */
	public static void addUser(String [] args, MyVelibCreation e) {
		
		if(args.length == 4) {
		String userName = args[1];
		String cardType = args[2];
		String velibnetworkName = args[3];
		
		try {
			e.addUser(userName, cardType, velibnetworkName);
		} catch ( WrongCartTypeException e1) {
			// TODO Auto-generated catch block
			e1.printMsg();
		}
		catch(MyVelibNetworkUnexistent e2) {
			e2.printMsg();
			}
		}	
		else {
			System.out.println("Invalid arguments");
			System.out.println("addUser <userName,cardType, velibnetworkName>");
		
		}
		
	}
	
	
	/**
	 * @param args list of string arguments
	 * @param e MyVelibCreation instance
	 */
	public static void ridesplan(String [] args, MyVelibCreation e) {
		if(args.length == 8) {
			
			
			double gpsstart1;
			double gpsstart2 = 0;
			double gpsend1 = 0;
			double gpsend2 = 0;
			String bikeType = args[6];
			String velibnetworkName = args[7];
			String userName = args[1];
			
			try {
				gpsstart1 = Double.parseDouble(args[2]);
				gpsstart2 = Double.parseDouble(args[3]);
				gpsend1 = Double.parseDouble(args[4]);
				gpsend2 = Double.parseDouble(args[5]);
				
				double[] start = {gpsstart1,gpsstart2};
				double[] end = {gpsend1,gpsend2};
				
				
				try {
					e.ridesPlanning(userName, start, end, bikeType, velibnetworkName);
				} catch (MyVelibNetworkUnexistent e1) {
					// TODO Auto-generated catch block
					e1.printMsg();
				} catch (UserDoesNotExist e1) {
					// TODO Auto-generated catch block
					e1.printMsg();
				}
				
				
			}
			catch(NumberFormatException e1) {
				System.out.println("Invalid arguments");
			}
			
			
		}
		else {
			System.out.println("Invalid arguments");
			System.out.println("ride username gpsstart1 gpsstart2 gpsend1 gpsend2 bikeType velibnetworkName");
		}
	}
	
	
	
	/**
	 * @param args list of string arguments
	 * @param e MyVelibCreation instance
	 */
	public static void retour(String [] args, MyVelibCreation e) {
		if(args.length == 5) {
			int userID;
			int stationID;
			int time;
			String velibnetworkName = args[4];
			
			try {
				userID = Integer.parseInt(args[1]);
				stationID = Integer.parseInt(args[2]);
				time = Integer.parseInt(args[3]);
				try {
					
				e.returntBike(userID, stationID, time, velibnetworkName);
				
			} catch (MyVelibNetworkUnexistent e1) {
				// TODO Auto-generated catch block
				e1.printMsg();
			} catch (UserDoesNotExist e1) {
				// TODO Auto-generated catch block
				e1.printMsg();
			} catch (StationDoesNotExist e1) {
				// TODO Auto-generated catch block
				e1.printMsg();
			}
			}
			catch(NumberFormatException e1) {
				System.out.println("Invalid arguments");
				System.out.println("return userID stationID time velibnetworkName");
				System.out.println("return userID time velibnetworkName");
				System.out.println("return userName time velibnetworkName");
			}
		}
		else if(args.length == 4) {
			int userID;
			String userName;
			int time;
			String velibnetworkName = args[3];
			
			
			try {
				userID = Integer.parseInt(args[1]);
				time = Integer.parseInt(args[2]);
				
				try {
					e.returntBike(userID, time, velibnetworkName);
				} catch (MyVelibNetworkUnexistent e1) {
					// TODO Auto-generated catch block
					e1.printMsg();
				} catch (UserDoesNotExist e1) {
					// TODO Auto-generated catch block
					e1.printMsg();
				}
				
			}
			catch(NumberFormatException e1) {
				
				try {
				
					userName = args[1];
					
					
					
					time = Integer.parseInt(args[2]);
					
					try {
						e.returntBike(userName, time, velibnetworkName);
					} catch (UserDoesNotExist e2) {
						// TODO Auto-generated catch block
						e2.printMsg();
					}
					
				}catch(NumberFormatException e2) {
					System.out.println("Invalid arguments");
					System.out.println("return userID stationID time velibnetworkName");
					System.out.println("return userID time velibnetworkName");
					System.out.println("return userName time velibnetworkName");
				}
			}
			
		}
		else {
			System.out.println("Invalid arguments");
			System.out.println("return userID stationID time velibnetworkName");
			System.out.println("return userID time velibnetworkName");
			System.out.println("return userName time velibnetworkName");
		}
		
	}
	
	/**
	 * @param args list of string arguments
	 * @param e MyVelibCreation instance
	 */
	public static void online(String [] args, MyVelibCreation e) {
		if(args.length == 3) {
			
			String velibNetworkName = args[1];
			int stationID;
			
			try {
				
				stationID = Integer.parseInt(args[2]);
				
				try {
					e.online(velibNetworkName, stationID);
				} catch (MyVelibNetworkUnexistent e1) {
					// TODO Auto-generated catch block
					e1.printMsg();
				} catch (StationDoesNotExist e1) {
					// TODO Auto-generated catch block
					e1.printMsg();
				}
				
				
			}catch(NumberFormatException e1) {
				System.out.println("Invalid arguments");
				System.out.println("online velibNetworkName stationID");
			}
			
		}
		else {
			System.out.println("Invalid arguments");
			System.out.println("online velibNetworkName stationID");
		}
	}
	
	/**
	 * @param args list of string arguments
	 * @param e MyVelibCreation instance
	 */
	public static void offline(String [] args, MyVelibCreation e) {
		if(args.length == 3) {
			
			String velibNetworkName = args[1];
			int stationID;
			
			try {
				
				stationID = Integer.parseInt(args[2]);
				
				try {
					e.offline(velibNetworkName, stationID);
				} catch (MyVelibNetworkUnexistent e1) {
					// TODO Auto-generated catch block
					e1.printMsg();
				} catch (StationDoesNotExist e1) {
					// TODO Auto-generated catch block
					e1.printMsg();
				}
				
				
			}catch(NumberFormatException e1) {
				System.out.println("Invalid arguments");
				System.out.println("offline velibNetworkName stationID");
			}
			
		}
		else {
			System.out.println("Invalid arguments");
			System.out.println("offline velibNetworkName stationID");
		}
	}
	
	
	
	
	////////////Statistic////////////////////////////
	
	/**
	 * @param args list of string arguments
	 * @param e MyVelibCreation instance
	 */
	public static void displayStation(String [] args, MyVelibCreation e) {
		if(args.length == 3) {
			String velibnetworkName = args[1];
			int stationID;
			try {
				stationID = Integer.parseInt(args[2]);
				e.displayStation(velibnetworkName, stationID);
			} catch (MyVelibNetworkUnexistent e1) {
				// TODO Auto-generated catch block
				e1.printMsg();
			} catch (StationDoesNotExist e1) {
				// TODO Auto-generated catch block
				e1.printMsg();
			}
		}
		else {
			System.out.println("Invalid arguments");
			System.out.println("displayStation velibnetworkName stationID");
		}
		
	}
	
	
	/**
	 * @param args list of string arguments
	 * @param e MyVelibCreation instance
	 */
	public static void displayUser(String [] args, MyVelibCreation e) {
		if(args.length == 3) {
			int id;
			String  velibnetworkName = args[1];
			try {
				id = Integer.parseInt(args[2]);
				e.displayUser(velibnetworkName, id);
			} catch (MyVelibNetworkUnexistent e1) {
				// TODO Auto-generated catch block
				e1.printMsg();
			} catch (UserDoesNotExist e1) {
				// TODO Auto-generated catch block
				e1.printMsg();
			}
		}
		else {
			System.out.println("Invalid arguments");
			System.out.println("displayUser velibnetworkName userID");
		}
	}
	
	
	/**
	 * @param args list of string arguments
	 * @param e MyVelibCreation instance
	 */
	public static void displayVelibNet(String [] args, MyVelibCreation e) {
		if(args.length == 2) {
			String velibnetworkName = args[1];
			
			try {
				e.displayVelibNet(velibnetworkName);
			} catch (MyVelibNetworkUnexistent e1) {
				// TODO Auto-generated catch block
				e1.printMsg();
			}
			
		}
		else {
			System.out.println("Invalid arguments");
			System.out.println("displayVelibNet velibnetworkName");
		}
	}
	
	
	/**
	 * @param e MyVelibCreation instance
	 */
	public static void displayVDataBase(MyVelibCreation e) {
		e.displayVDatabase();
	}
	
	
	/**
	 * @param e MyVelibCreation instance
	 */
	public static void displayUDataBase(MyVelibCreation e) {
		e.displayUDatabase();
	}
	
	/**
	 * @param e MyVelibCreation instance
	 */
	public static void display(MyVelibCreation e) {
		e.displayVelib();
	}
	
	
	/**
	 * @param args list of string arguments
	 * @param e MyVelibCreation instance
	 */
	public static void ratio(String [] args, MyVelibCreation e) {
		if(args.length == 5) {
			String velibnetworkName = args[1];
			int stationID;
			int te;
			int ts;
			
			try {
				stationID = Integer.parseInt(args[2]);
				te = Integer.parseInt(args[3]);
				ts = Integer.parseInt(args[4]);
				
				try {
					e.ratio(velibnetworkName, stationID, te, ts);
				} catch (MyVelibNetworkUnexistent e1) {
					// TODO Auto-generated catch block
					e1.printMsg();
				} catch (StationDoesNotExist e1) {
					// TODO Auto-generated catch block
					e1.printMsg();
				}
				
			}catch(NumberFormatException e1) {
				System.out.println("Invalid arguments");
				System.out.println("ratio velibnetworkName stationID te ts");
			}
			
		}
		else {
			System.out.println("Invalid arguments");
			System.out.println("ratio velibnetworkName stationID te ts");
		}
	}

	
	/**
	 * @param args list of string arguments
	 * @param e MyVelibCreation instance
	 */
	public static void currentState(String [] args,MyVelibCreation e) {
		if(args.length == 2) {
			String velibnetworkName = args[1];
			try {
				e.currentState(velibnetworkName);
			} catch (MyVelibNetworkUnexistent e1) {
				// TODO Auto-generated catch block
				e1.printMsg();
			}
			
		}
		else {
			System.out.println("Invalid arguments");
			System.out.println("currentState velibnetworkName");
		}
	}
	
	/**
	 * @param args list of string arguments
	 * @param e MyVelibCreation instance
	 */
	public static void currenStateStation(String [] args, MyVelibCreation e) {
		if(args.length == 3) {
			int stationID;
			String velibnetworkName = args[2];
			try {
				stationID = Integer.parseInt(args[1]);
				try {
					e.currenStateStation(stationID, velibnetworkName);
				} catch (MyVelibNetworkUnexistent e1) {
					// TODO Auto-generated catch block
					e1.printMsg();
				} catch (StationDoesNotExist e1) {
					// TODO Auto-generated catch block
					e1.printMsg();
				}
			}catch(NumberFormatException e1) {
				System.out.println("Invalid arguments");
				System.out.println("currenStateStation stationID velibnetworkName");
			}
		}
		else {
			System.out.println("Invalid arguments");
			System.out.println("currenStateStation stationID velibnetworkName");
		}
	}
	
	
	/**
	 * @param args list of string arguments
	 * @param e MyVelibCreation instance
	 */
	public static void currentStateUser(String [] args, MyVelibCreation e) {
		if(args.length == 3) {
			int userID;
			String velibnetworkName = args[2];
			try {
				userID = Integer.parseInt(args[1]);
				try {
					e.currentStateUser(userID, velibnetworkName);
				} catch (MyVelibNetworkUnexistent e1) {
					// TODO Auto-generated catch block
					e1.printMsg();
				} catch (UserDoesNotExist e1) {
					// TODO Auto-generated catch block
					e1.printMsg();
				}
				
			}catch(NumberFormatException e1) {
				System.out.println("Invalid arguments");
				System.out.println("currentStateUser userID velibnetworkName");
			}
			
		}
		else {
			System.out.println("Invalid arguments");
			System.out.println("currentStateUser userID velibnetworkName");
		}
	}
	
	
	/**
	 * @param args list of string arguments
	 * @param e MyVelibCreation instance
	 */
	public static void balanceStation(String [] args, MyVelibCreation e) {
		if(args.length == 3) {
			int stationID;
			String velibnetworkName = args[2];
			try {
				stationID = Integer.parseInt(args[1]);
				
				try {
					e.balanceStation(stationID, velibnetworkName);
				} catch (MyVelibNetworkUnexistent e1) {
					// TODO Auto-generated catch block
					e1.printMsg();
				} catch (StationDoesNotExist e1) {
					// TODO Auto-generated catch block
					e1.printMsg();
				}
			}catch(NumberFormatException e1) {
				System.out.println("Invalid arguments");
				System.out.println("balanceStation stationID velibnetworkName");
			}
		}
		else {
			System.out.println("Invalid arguments");
			System.out.println("balanceStation stationID velibnetworkName");
		}
	}
	
	
	/**
	 * @param args list of string arguments
	 * @param e MyVelibCreation instance
	 */
	public static void balanceUser(String [] args, MyVelibCreation e) {
		if(args.length == 3) {
			int userID;
			String velibnetworkName = args[2];
			try {
				userID = Integer.parseInt(args[1]);
				
				try {
					e.balanceUser(userID, velibnetworkName);
				} catch (MyVelibNetworkUnexistent e1) {
					// TODO Auto-generated catch block
					e1.printMsg();
				} catch (UserDoesNotExist e1) {
					// TODO Auto-generated catch block
					e1.printMsg();
				}
			}catch(NumberFormatException e1) {
				System.out.println("Invalid arguments");
				System.out.println("balanceUser userID velibnetworkName");
			}
		}
		else {
			System.out.println("Invalid arguments");
			System.out.println("balanceUser userID velibnetworkName");
		}
	}
	
	
	/**
	 * @param args list of string arguments
	 * @param e MyVelibCreation instance
	 */
	public static void sortStation(String [] args, MyVelibCreation e) {
		if(args.length == 3) {
			String velibnetworkName = args[1];
			String policy = args[2];
			
			try {
				e.sortStation(velibnetworkName, policy);
			} catch (MyVelibNetworkUnexistent e1) {
				// TODO Auto-generated catch block
				e1.printMsg();
			}
		}
		else {
			System.out.println("Invalid arguments");
			System.out.println("sortStation velibnetworkName policy");
		}
	}
}
