package userInterface;


import core.*;

/**
 * Execute User instruction
 * @author Oussama
 *
 */
public class CommandLine {
	
	
	/**
	 * @param cmd Command of the user
	 * @param arg input entered by the user
	 * @param e MyVelibCreation instance
	 */
	public static void commandline(String cmd, String [] arg, MyVelibCreation e) {
		if(cmd.equalsIgnoreCase("help")) {
			Command.help();
		}
		else if(cmd.equalsIgnoreCase("ride")) {
			Command.ridesplan(arg, e);
		}
		else if(cmd.equalsIgnoreCase("setup")) {
			Command.setup(arg, e);
		}
		else if(cmd.equalsIgnoreCase("rent")) {
			Command.rent(arg, e);
		}
		else if(cmd.equalsIgnoreCase("return")) {
			Command.retour(arg, e);
		}
		else if(cmd.equalsIgnoreCase("displayStation")) {
			Command.displayStation(arg, e);
		}
		else if(cmd.equalsIgnoreCase("displayUser")) {
			Command.displayUser(arg, e);
		}
		else if(cmd.equalsIgnoreCase("display")) {
			Command.display(e);
		}
		else if(cmd.equalsIgnoreCase("addUser")) {
			Command.addUser(arg, e);
		}
		else if(cmd.equalsIgnoreCase("displayVelibNet")) {
			Command.displayVelibNet(arg, e);
		}
		else if(cmd.equalsIgnoreCase("displayVDataBase")) {
			Command.displayVDataBase(e);
		}
		else if(cmd.equalsIgnoreCase("displayUDataBase")) {
			Command.displayUDataBase(e);
		}
		else if(cmd.equalsIgnoreCase("online")) {
			Command.online(arg, e);
		}
		else if(cmd.equalsIgnoreCase("offline")) {
			Command.offline(arg, e);
		}
		else if(cmd.equalsIgnoreCase("ratio")) {
			Command.ratio(arg, e);
		}
		else if(cmd.equalsIgnoreCase("currentState")) {
			Command.currentState(arg, e);
		}
		else if(cmd.equalsIgnoreCase("currenStateStation")) {
			Command.currenStateStation(arg, e);
		}
		else if(cmd.equalsIgnoreCase("currentStateUser")) {
			Command.currentStateUser(arg, e);
		}
		else if(cmd.equalsIgnoreCase("balanceStation")) {
			Command.balanceStation(arg, e);
		}
		else if(cmd.equalsIgnoreCase("balanceUser")) {
			Command.balanceUser(arg, e);
		}
		else if(cmd.equalsIgnoreCase("sortStation")) {
			Command.sortStation(arg, e);
		}
		else {
			System.out.println("Unknown Command. Tap <help> to show available command.");
			}
	}

}
