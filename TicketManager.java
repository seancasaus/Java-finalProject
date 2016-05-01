import java.io.*;
import java.text.DecimalFormat;
import java.util.*;

public class TicketManager {
	private final int NUMROWS = 15;
	private final int NUMCOLUMNS = 30;
	private char arraySeats [][];
	private double arrayPrice[];
	private int seatsSold;
	private double totalRevenue;
	private Scanner scan1;
	private Scanner scan2;
	private int storeSeatA; //used to store seats for printTicket method
	private int storeSeatB; //used to store seats for printTicket method
	private int storeSeatC; //used to store seats for printTicket method
	DecimalFormat fmt = new DecimalFormat("0.00");
	
	
	//Constructor initializes arraySeats and arrayPrice by accessing the openFile() and readFile() classes
		//Constructor initializes the instance variables at 0. 
	public TicketManager() {
		seatsSold = 0;
		totalRevenue = 0.00;
		arraySeats = new char [NUMROWS][NUMCOLUMNS];
		arrayPrice = new double [NUMROWS];
		openFiles();
		readFiles();
	}
	
	//Opens seatAvailability and seatPrices .txt files located in Assignmnet8 folder.
		//Catches error if the files could not be read 
	private void openFiles() {
		try {
			scan1 = new Scanner (new File("seatAvailability.txt"));
			scan2 = new Scanner (new File ("seatPrices.txt"));
		}
		catch (Exception e) {
			System.out.println("Could not read file.");
		} 
	}

	//Uses scan1 and scan2 instance variables to read through each text file, and then fills each array.
	private void readFiles() {
		int rowCounter = 0;
		int priceCounter = 0;
		while (scan1.hasNext()) {	
			String row = scan1.nextLine();
			for (int a = 0; a < 30; a ++) {
				arraySeats[rowCounter][a] = row.charAt(a);
			}
			rowCounter++;
		}
		
		while (scan2.hasNext()) {
				double price = scan2.nextDouble();
				arrayPrice[priceCounter] = price;
				priceCounter++;
		}
	}
	
	//Closes files
	public void closeFiles() {
		scan1.close();
		scan2.close();
	}
	
	//Requests availability of tickets based off of user input for number of seats customer wishes to purchase (int a), the row of seat 1 (int b),
		//the column of seat 1 (int c). Seats availability is tested left to right from seat 1 to int a.	
	public boolean requestTickets(int a, int b, int c) {	
		storeSeatA = a;
		storeSeatB = b;
		storeSeatC = c;
		boolean flag = true;
			for (int aa = 0; aa < a; aa++) {
				if (arraySeats[b][c-1] == '#') {
					c++;
				}
				
				else {
					flag = false;
				}
			}
		
		if (flag == true) {
			return true;
		}
		
		else {
			return false;
		}
	}
	
	//Changes row and column in arraySeats[][] from # to * for every seat purchased.
		//Increments total number of seats sold represented by int seatsSold
		//Adds the price of each ticket to the total revenue represented by double totalRevenue
	public void purchaseTickets(int a, int b, int c) {
		for (int i = 0; i < a; i++) {
			arraySeats[b-1][c-1] = '*';
			seatsSold++;
			totalRevenue += arrayPrice[b];
			c++;
		}
	}
	
	//Returns double totalRevenue
	public double getRevenue() {
		return totalRevenue; 
	}
	
	//Returns int seatsSold
	public int getSeatsSold() {
		return seatsSold;
	}
	
	//Returns the price of a row located in arrayPrice[]
	public double getPrice(int a) {
		return arrayPrice[a];
	}
	
	//Returns the character # or * to determine seat availability
	public char getSeat (int a, int b) {
		return arraySeats[a][b];
		
	}
	
	//Prints out a ticket for every seat purchased that includes the seat location from char arraySeats[][], and the price from double arrayPrice[].
 	public void printTickets() {
		for (int a = storeSeatB -1; a < storeSeatB + storeSeatA -1; a++) {
				System.out.println ("***********************************************\n"
						+ "*\tGammage Theater\t\t*\n"
						+ "*\tRow: " + storeSeatB + " Seat: " + (a+1) +  "\t\t*\n"
						+ "*\tPrice: $ " + fmt.format(arrayPrice[storeSeatB-1]) + "\t\t*\n"
						+ "***********************************************");
				storeSeatC++;	
		}
	}
	
	// Displays the current seating availability in the theater. 
 	public void displaySeating() {
		System.out.println("\t\tSeats");
 		System.out.println("       123456789012345678901234567890");
 		for (int a = 0; a < arraySeats.length; a++) {
 			System.out.print("Row " + (a+1) + ": ");
			for (int b = 0; b < arraySeats[a].length; b++) {
				System.out.print(arraySeats[a][b]);
			}
		System.out.println(" ");
		}
	}
 	
 	//Displays a sales report that displays the seats sold, seats available, and the total revenue.
 	public void displaySalesReport() {
 		int seatsAvailable = 450 - seatsSold;
 		System.out.println("Gammage Sales Report"
 				+ "\n___________________________\n"
 				+ "Seats Sold: " + seatsSold + "\n"
 				+ "Seats Available: " + seatsAvailable + "\n"
 				+ "Total Revenue to Date: $ " + fmt.format(totalRevenue));
 	}
		
}
