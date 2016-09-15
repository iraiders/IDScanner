package com.ryanb3.IDScanner.test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class Test {

	// Files = IDS/Names (new line = new ID)
	// Files = CSV Days

	ArrayList<String> ids;
	ArrayList<String> coordinatingNames;
	private Scanner scan;
	private Scanner peopleScanner;
	private PrintWriter peoplePrinter;

	public static void main(String[] args) {
		new Test();
	}

	public Test() {
		try {
			ids = new ArrayList<String>();
			coordinatingNames = new ArrayList<String>();
			scan = new Scanner(System.in);
			String currentDate = "";
			File todaysFile = null;
			File peopleData = new File(System.getProperty("user.dir") + "/people.txt");
			if (!peopleData.exists()) {
				peopleData.createNewFile();
			}
			peopleScanner = new Scanner(peopleData);
			while(peopleScanner.hasNext()) {
				String nextLine = peopleScanner.nextLine();
				ids.add(nextLine.split(",")[0]);
				coordinatingNames.add(nextLine.split(",")[1]);
			}
			while (true) {
				peopleData = new File(System.getProperty("user.dir") + "/people.txt");
				FileWriter peopleWriter = new FileWriter(peopleData, true);
				peoplePrinter = new PrintWriter(peopleWriter);
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				Date date = new Date();
				String today = dateFormat.format(date);
				if(!currentDate.equals(today)) {
					todaysFile = new File(System.getProperty("user.dir") + "/" + today + ".txt");
				}
				if (!todaysFile.exists()) {
					todaysFile.createNewFile();
				}
				FileWriter write = new FileWriter(todaysFile, true);
				PrintWriter print = new PrintWriter(write);
				String current = scan.nextLine();
				if (!ids.contains(current)) {
					ids.add(current);	
					coordinatingNames.add(JOptionPane.showInputDialog("What is your name?"));
					peoplePrinter.println(current + "," + coordinatingNames.get(coordinatingNames.size() - 1));
				}
				print.println (current);
				print.flush();
				print.close();
				peopleWriter.flush();
				peopleWriter.close();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
