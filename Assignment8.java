//---------------------------------------------------------------------------------------------------
//Name: Sean Casaus
//File Name: Assignment8.java
//Specification: Allows users to purchase and print tickets for an event at Gammage Theater
//For: CSE 110
//Time Spent: 2 weeks
//---------------------------------------------------------------------------------------------------
import java.io.*;
import java.text.DecimalFormat;
import java.util.*;

public class Assignment8 {
	public static void main(String args[]) {
		int menuChoice = 0;
		boolean setter = true; //used to reset main menu
		TicketManager myManager = new TicketManager();
		Scanner scan = new Scanner(System.in);
		DecimalFormat fmt = new DecimalFormat("0.00");
		
		while (setter == true) {
			//Displays menu--------------------------------------------------------------------------
			setter = false;
			System.out.println("\tASU Gammage Theater\n"
					+ "1.) View Available Seats\n"
					+ "2.) Request Tickets\n"
					+ "3.) Display Theater Sales report\n"
					+ "4.) Exit The Program");
			try {
				menuChoice = scan.nextInt();
			}
			
			catch (Exception e) {
				System.out.println("Enter an Integer Dum-Dum");
				setter = true;
			}
				
		
			switch (menuChoice) {
				//Displays Seating-------------------------------------------------------------------
				case 1: 
					myManager.displaySeating();
					System.out.println("\tLegend:\n'*' = Unavailable Seats\n'#' = Available Seats");
					System.out.println(" ");
					setter = true;
				break;//-----------------------------------------------------------------------------
				
				//Requests Tickets-------------------------------------------------------------------
				case 2:
					int seatsDesired;
					int rowNumber;
					int startingSeat;
					String quit;
					
					//Tests desired number of seats--------------------------------------------------	
					System.out.println("Number of seats desired(1-30): ");
					seatsDesired = scan.nextInt();
					while (seatsDesired > 30 || seatsDesired <= 0) {
						System.out.println("Not within 1-30. Enter a new number.");
						seatsDesired = scan.nextInt();
					}
					
					//Tests Desired Row--------------------------------------------------------------
					System.out.println("Desired Row (1-15): ");
					rowNumber = scan.nextInt();
					while (rowNumber > 15 || rowNumber <= 0) {
						System.out.println("Not within 1-15. Enter a new number.");
						rowNumber = scan.nextInt();
					}
		
					//Tests Desired Starting Seating Number------------------------------------------
					System.out.println("Desired starting seat number in the row (1-30): ");
					startingSeat = scan.nextInt();
					while (startingSeat > (31 - seatsDesired) || startingSeat < 0) {
						System.out.println("Not within limits of row. Enter a new number.");
						startingSeat = scan.nextInt();
					}
					
					//If tickets are available then user is displayed the price, and prompted to purchase the tickets. 
					if (myManager.requestTickets(seatsDesired, rowNumber, startingSeat) == true) {
						System.out.println("The tickets requested are available for purchase.");
						
						System.out.println("The total purchase price is: " + seatsDesired + "X $" + fmt.format(myManager.getPrice(rowNumber))
								+ " = $" + fmt.format(seatsDesired * myManager.getPrice(rowNumber)));
						
						System.out.println("Do you wish to purchase these tickets? Input Y for Yes, or any other key to return to main menu ");
						scan.nextLine();
						char yn = scan.nextLine().charAt(0);
						
						//If user inputs Y, or y, then the user is prompted to input an amount paid.
							//Tickets are printed using printTickets() method. A receipt is printed.
						if (yn == 'Y' || yn == 'y') {
							System.out.printf("Num Seats: %s\n", seatsDesired);
							System.out.printf("The price of the requested tickets is $%s\n",fmt.format(seatsDesired * myManager.getPrice(rowNumber)));
							System.out.println("Please input the amount paid: ($)");
							double amountPaid = scan.nextDouble();
							System.out.println(" ");
							
							if (amountPaid >= (seatsDesired * myManager.getPrice(rowNumber))) {
								myManager.purchaseTickets(seatsDesired, rowNumber, startingSeat);
								myManager.printTickets();
								System.out.println(" ");
								System.out.printf("Tickets Purchased: %s\n", seatsDesired);
								System.out.printf("Payment: $%s\n", fmt.format(amountPaid));
								System.out.printf("Total Ticket Prices: $%s\n", fmt.format(seatsDesired * myManager.getPrice(rowNumber)));
								System.out.printf("Change Due: $%s\n", fmt.format(amountPaid - seatsDesired * myManager.getPrice(rowNumber)));
								System.out.println(" ");
							}
							
							else {
								System.out.println("Declined. Redirecting to main menu. ");
							}
							setter = true;
						}
						
						//If user inputs anything but Y or y then program returns to main menu.  
						else {
							System.out.println("Tickets not purchased. Returning to main menu.");
							System.out.println(" ");
							setter = true;
						}
					}
					
					
					
				break;//-----------------------------------------------------------------------------
				
				//Displays Sales Report from method displaySalesReport()----------------------------- 
				case 3:
					
					myManager.displaySalesReport();
					System.out.println(" ");
					setter = true;
				
				break;//-----------------------------------------------------------------------------
			
				//Exits the Program------------------------------------------------------------------
				case 4:
					System.out.println("Thank you for choosing Gammage Theater at Arizona State University!");
					myManager.closeFiles();
					setter = false;
				break;//-----------------------------------------------------------------------------
			}
		}
	}
}		
