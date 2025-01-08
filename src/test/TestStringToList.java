package test;

import userInterface.InputToList;

public class TestStringToList {
	
	
	public static void main(String[] args) {
		String s = "setup vfhf 10 45 13 10 45";
		String [] arg = InputToList.StringToList(s);
		for(int i = 0 ; i< arg.length; i++) {
			System.out.println(arg[i]);
		}
		
		
	}
	
	

}
