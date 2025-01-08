package test;

import java.util.ArrayList;

import userInterface.runtest;

public class TestrunTest {
	
	public static void main(String[] args) {
		
		
		String filename = "TestScenario1.txt";
		ArrayList<String> res = runtest.fileToStringList(filename);
		System.out.println(res);
	}
}
