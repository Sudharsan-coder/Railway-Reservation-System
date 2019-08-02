import java.sql.SQLException;
import java.util.Scanner;

public class Railways {
	public static void main(String[] args) throws SQLException{
		RailwayDb.getPassengers();
		RailwaysManager rm = new RailwaysManager();
	//	RailwaysManager.doHeading();
		Scanner in = new Scanner(System.in);
		
		while(true){
			System.out.println("1.Route Selection");
			System.out.println("2.Book Ticket");
			System.out.println("3.Cancel Ticket");
			System.out.println("4.Search Passenger");
			System.out.println("5.Reservation Chart");
			System.out.println("6.Display UnBooked Tickets");
			System.out.println("7.Exit");
			System.out.println();
			System.out.println("Please enter your choice");
			int choice = in.nextInt();
			switch(choice){
			case 1 : 
					rm.routeSelection();
					break;
			case 2 : 
					rm.doBook();
					break;
			case 3 : 
					System.out.println("Enter the Name of Passenger to cancel ticket");
					Passenger passenger = rm.cancelTicket(in.next());
					rm.passengers.remove(passenger);
					break;
			case 4 :
					System.out.println("Enter the Name of Passenger you want to Search");
					rm.searchPassenger(in.next());
					break;
			case 5 :
					rm.displayResvChart();
					break;
			case 6 :
					rm.displayUnBookedTickets();
					break;
			default :
					System.exit(0);
					break;
			}
			System.out.println("----------------------------");
		}
	}
}
