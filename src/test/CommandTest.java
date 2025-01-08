package test;


import org.junit.jupiter.api.Test;

import core.MyVelibCreation;
import exception.MyVelibNetworkUnexistent;
import exception.WrongCartTypeException;
import userInterface.Command;

class CommandTest {
	
	//All these function have already been tested
	//So we just need to know if there is
	//an error calling them
	
	@Test
	void testSetup() {
		MyVelibCreation e = new MyVelibCreation();
		String name = "default";
		String[] args = {"setup", name};
		Command.setup(args, e);
		
	}

	@Test
	void testRent() {
		MyVelibCreation e = new MyVelibCreation();
		String name = "default";
		
			try {
				String[] args1 = {"setup", name};
				Command.setup(args1, e);
				e.addUser("h","NONE", name);
				String[] args = {"rent","1","1","electric",name, "1" };
				Command.rent(args, e);
			} catch (MyVelibNetworkUnexistent e1) {
				// TODO Auto-generated catch block
				e1.printMsg();
			} catch (WrongCartTypeException e1) {
				// TODO Auto-generated catch block
				e1.printMsg();
			}
			
		
			}

	@Test
	void testAddUser() {
		MyVelibCreation e = new MyVelibCreation();
		String name = "default";
		String[] args1 = {"setup", name};
		Command.setup(args1, e);
		String[] args = {"addUser", "ouss","NONE",name };
		Command.addUser(args, e);
		}

	@Test
	void testRidesplan() {
		MyVelibCreation e = new MyVelibCreation();
		String name = "default";
		String[] args1 = {"setup", name};
		Command.setup(args1, e);
		String[] args = {"ride", "h", "0","0","1","1","mechanic",name};
		Command.ridesplan(args, e);}

	@Test
	void testRetour() {
		MyVelibCreation e = new MyVelibCreation();
		String name = "default";
		String[] args1 = {"setup", name};
		Command.setup(args1, e);
		String[] args = {"return","1", "1","64", name};
		Command.retour(args, e);}
	
	@Test
	void testOffline() {
		MyVelibCreation e = new MyVelibCreation();
		String name = "default";
		String[] args1 = {"setup", name};
		Command.setup(args1, e);
		String[] args = {"offline", name,"1"};
		Command.online(args, e);}

	@Test
	void testOnline() {
		MyVelibCreation e = new MyVelibCreation();
		String name = "default";
		String[] args1 = {"setup", name};
		Command.setup(args1, e);
		String[] args = {"online", name, "1"};
		Command.online(args, e);}



	@Test
	void testDisplayStation() {
		MyVelibCreation e = new MyVelibCreation();
		String name = "default";
		String[] args1 = {"setup", name};
		Command.setup(args1, e);
		String[] args = {"displayStation",name,"1"};
		Command.displayStation(args, e);}

	@Test
	void testDisplayUser() {
		MyVelibCreation e = new MyVelibCreation();
		String name = "default";
		String[] args1 = {"setup", name};
		Command.setup(args1, e);
		String[] args = {"displayUser",name,"1"};
		Command.displayUser(args, e);}

	@Test
	void testDisplayVelibNet() {
		MyVelibCreation e = new MyVelibCreation();
		String name = "default";
		String[] args1 = {"setup", name};
		Command.setup(args1, e);
		String[] args = {"displayVelibNet",name};
		Command.displayVelibNet(args, e);}

	@Test
	void testDisplayVDataBase() {
		MyVelibCreation e = new MyVelibCreation();
		
		Command.displayVDataBase(e);}

	@Test
	void testDisplayUDataBase() {
		MyVelibCreation e = new MyVelibCreation();

		Command.displayUDataBase(e);}

	@Test
	void testDisplay() {
		MyVelibCreation e = new MyVelibCreation();

		Command.display(e);}

	@Test
	void testRatio() {
		MyVelibCreation e = new MyVelibCreation();
		String name = "default";
		String[] args1 = {"setup", name};
		Command.setup(args1, e);
		String[] args = {"ratio",name, "1","0","87"};
		Command.ratio(args, e);}

	@Test
	void testCurrentState() {
		MyVelibCreation e = new MyVelibCreation();
		String name = "default";
		String[] args1 = {"setup", name};
		Command.setup(args1, e);
		String[] args = {"currentState",name};
		Command.currentState(args, e);}

	@Test
	void testCurrenStateStation() {
		MyVelibCreation e = new MyVelibCreation();
		String name = "default";
		String[] args1 = {"setup", name};
		Command.setup(args1, e);
		String[] args = {"currenStateStation","1",name};
		Command.currenStateStation(args, e);}

	@Test
	void testCurrentStateUser() {
		MyVelibCreation e = new MyVelibCreation();
		String name = "default";
		String[] args1 = {"setup", name};
		Command.setup(args1, e);
		String[] args = {"currentStateUser","1",name};
		Command.currentStateUser(args, e);}

	@Test
	void testBalanceStation() {
		MyVelibCreation e = new MyVelibCreation();
		String name = "default";
		String[] args1 = {"setup", name};
		Command.setup(args1, e);
		String[] args = {"balanceStation","1",name};
		Command.balanceStation(args, e);}

	@Test
	void testBalanceUser() {
		MyVelibCreation e = new MyVelibCreation();
		String name = "default";
		String[] args1 = {"setup", name};
		Command.setup(args1, e);
		String[] args = {"balanceUser","1",name};
		Command.balanceUser(args, e);}

	@Test
	void testSortStation() {
		MyVelibCreation e = new MyVelibCreation();
		String name = "default";
		String[] args1 = {"setup", name};
		Command.setup(args1, e);
		String[] args = {"sortStation",name,"OCCUPIED"};
		Command.sortStation(args, e);}

}
