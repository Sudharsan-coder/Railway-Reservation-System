import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class RailwayDb {

	private static Connection connection = null;
	private static String url = "jdbc:mysql://localhost:3306/railway";
	private static String username = "sudharsan";
	private static String password = "password";
	private static PreparedStatement pst=null;
	private static String query;
	private static ResultSet resultSet=null;
	private static Statement statement;

	static{

		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(url,username,password);
			statement = connection.createStatement();
			query = "CREATE TABLE IF NOT EXISTS Passenger(passenger_No int,passenger_Name Varchar(30),passenger_Age int,classOfTravel Varchar(20),berth Varchar(20),seat_No Varchar(20))";
			pst = connection.prepareStatement(query);
			pst.executeUpdate();		
		}
		catch (ClassNotFoundException e) {

			e.printStackTrace();
		}
		catch(SQLException e){
			System.out.println(e);
		}
		
	}
	
	public static boolean addPassenger(Passenger passenger)throws SQLException
	{
		query="INSERT INTO Passenger(passenger_No,passenger_Name,passenger_Age,classOfTravel,berth,seat_No)VALUES('"+passenger.getPassengerNo()+"','"+passenger.getPassengerName()+"','"+passenger.getAge()+"','"+passenger.getClassOfTravel()+"','"+passenger.getBerth()+"','"+passenger.getSeatNo()+"')";
		int result = statement.executeUpdate(query);
		return (result>0) ? true : false;
	}
	
	public static void cancelTicket(String passengerName) throws SQLException{
		
		query = "DELETE FROM Passenger WHERE passenger_Name ='"+passengerName+"'";
		int result = statement.executeUpdate(query);
	}
	
	public static void getPassengers() throws SQLException{
		
		query = "SELECT * FROM Passenger";
		resultSet = statement.executeQuery(query);
		
		while(resultSet.next()){
			RailwaysManager.getPassenger(resultSet);
		}
	}
	
	public static void getSearchPassenger(String passengerName) throws SQLException{
		
		query = "SELECT * FROM Passenger where passenger_Name ='"+passengerName+"'";
		resultSet = statement.executeQuery(query);
	}
}
