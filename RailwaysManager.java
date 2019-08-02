
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;


public class RailwaysManager {
	
	Scanner scanner = new Scanner(System.in);
	static List<Passenger> passengers = new ArrayList<>();
	int AC_ClassSeats=80,SecondClassSeats=180,SeaterSeats=300,ACSeat=1,SLSeat=1,sitterSeat=1;
	String seatNo;
	int pNo=1;

	public void routeSelection(){

		Set<String> routes = new HashSet<>(); 
		routes.add("Chennai");
		routes.add("Thanjavur");
		routes.add("Thiruvaiyaru");
		routes.add("Kumbakonam");
		routes.add("Ariyalur");

		System.out.println("Trains are available in these routes.");
		System.out.println("--------------------------------------");

		System.out.println("Select 1 for Chennai <---> Thanjavur");
		System.out.println("Select 2 for Chennai <---> Kumbakonam");
		System.out.println("Select 3 for Chennai <---> Thiruvaiyaru");
		System.out.println("Select 4 for Chennai <---> Ariyalur");

		Map<Integer,String> route = new HashMap<>();
		route.put(1,"Welcome to Thanjai Express");
		route.put(2,"Welcome to Vaigai Express");
		route.put(3,"Welcome to Thirunelveli Express");
		route.put(4,"Welcome to Pallavan Express");

		System.out.println("Please Select the route");
		int selectRoute = scanner.nextInt();
		for(Map.Entry<Integer,String> map:route.entrySet()){
			if(map.getKey() == selectRoute){
				System.out.println(map.getValue());
			}
		}

	}

	public void doBook() throws SQLException {

		System.out.println("Enter the No.of.Passengers");
		int noOfPassenger = scanner.nextInt();

		String passengerName,classOfTravel,berth;
		int age;
		int price=0;

		for(int i=0;i<noOfPassenger;i++){
			System.out.println("Enter the passenger Name: ");
			passengerName = scanner.next();
			System.out.println("Enter the Age of Passenger");
			age = scanner.nextInt();
			System.out.println("Class of Travel");
			classOfTravel = scanner.next();

			if(classOfTravel.equalsIgnoreCase("AC_Class")){
				AC_ClassSeats--;
				seatNo = "AC"+Integer.toString(ACSeat++);
				if(ACSeat%3 == 1){
					berth="Lower";
				}else if(ACSeat%3 == 2){
					berth="Middle";
				}else{
					berth="Upper";
				}				
				price+=1000;
			}
			else if(classOfTravel.equalsIgnoreCase("SecondClass")){
				SecondClassSeats--;
				seatNo = "SL"+Integer.toString(SLSeat++);
				if(SLSeat%3 == 1){
					berth="Lower";
				}else if(SLSeat%3 == 2){
					berth="Middle";
				}else{
					berth="Upper";
				}
				price+=500;
			}
			else{
				SeaterSeats--;
				seatNo = "Sitter"+Integer.toString(sitterSeat++);
				if(sitterSeat%3 == 1){
					berth="Lower";
				}else if(sitterSeat%3 == 2){
					berth="Middle";
				}else{
					berth="Upper";
				}
				price+=130;
			}
			pNo++;
			Passenger passenger = new Passenger(pNo,age,passengerName,classOfTravel,berth,seatNo);
			RailwayDb.addPassenger(passenger);
			passengers.add(passenger);
			System.out.println("Passenger Added Successfully");
			
		}
		doPayment(price);
	}
	
	public static void getPassenger(ResultSet resultSet) throws SQLException{
		
		int passengerNo = resultSet.getInt(1);
		String passengerName = resultSet.getString(2);
		int passengerAge = resultSet.getInt(3);
		String classOfTravel = resultSet.getString(4);
		String berth = resultSet.getString(5);
		String seatNo = resultSet.getString(6);
		
		Passenger passenger = new Passenger(passengerNo, passengerAge, passengerName, classOfTravel, berth, seatNo);
		passengers.add(passenger);	
	}

	public void doPayment(int totalPrice){
		System.out.println("------Payment Form-------");
		System.out.println("Total Fare: "+totalPrice);
		System.out.println("Enter the Card Number");
		int cardNumber = scanner.nextInt();
		System.out.println("Enter the PIN Number");
		int pinNumber = scanner.nextInt();
		if(cardNumber == 123 && pinNumber == 789){
			System.out.println("Payment Successful");	
		}else{
			System.out.println("Invalid Card Details");
		}
	}

	public void displayUnBookedTickets(){
		System.out.println("Available Seats in AC_Class: "+AC_ClassSeats);
		System.out.println("Available Seats in Second Class: "+SecondClassSeats);
		System.out.println("Available Seats in SeaterSeats: "+SeaterSeats);
	}

	public void displayResvChart(){
		System.out.print("Passenger Name");
		System.out.print("Passenger Seat_No ");
		System.out.println();
		for(Passenger passenger : passengers){
			System.out.print(passenger.getPassengerName()+"      "+passenger.getSeatNo());
			System.out.println();
		}
	}

	public Passenger cancelTicket(String passengerName) throws SQLException{
		Passenger findPassenger=null;
		RailwayDb.cancelTicket(passengerName);
		for(Passenger passenger : passengers){
			if(passenger.getPassengerName().equalsIgnoreCase(passengerName)){
				findPassenger = passenger;
				System.out.println("Passenger Removed Successfully");
			}
		}
		return findPassenger;
	}

	public void searchPassenger(String passengerName) throws SQLException{
		RailwayDb.getSearchPassenger(passengerName);
		for(Passenger passenger : passengers){
			if(passenger.getPassengerName().equalsIgnoreCase(passengerName)){
				passenger.display();
			}
		}
	}
}
