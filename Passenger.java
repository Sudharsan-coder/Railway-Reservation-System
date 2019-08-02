
public class Passenger {
		
	private int passengerNo,age;
	private String passengerName,classOfTravel,berth,seatNo;
	
	public Passenger(int passengerNo, int age, String passengerName,
			String classOfTravel, String berth, String seatNo) {		
		this.passengerNo = passengerNo;
		this.age = age;
		this.passengerName = passengerName;
		this.classOfTravel = classOfTravel;
		this.berth = berth;
		this.seatNo = seatNo;
	}
	
	public int getPassengerNo() {
		return passengerNo;
	}
	public void setPassengerNo(int passengerNo) {
		this.passengerNo = passengerNo;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getPassengerName() {
		return passengerName;
	}
	public void setPassengerName(String passengerName) {
		this.passengerName = passengerName;
	}
	public String getClassOfTravel() {
		return classOfTravel;
	}
	public void setClassOfTravel(String classOfTravel) {
		this.classOfTravel = classOfTravel;
	}
	public String getBerth() {
		return berth;
	}
	public void setBerth(String berth) {
		this.berth = berth;
	}
	public String getSeatNo() {
		return seatNo;
	}
	public void setSeatNo(String seatNo) {
		this.seatNo = seatNo;
	}
	
	public void display(){
		System.out.println("------------Passenger Details------------");
		System.out.println("Passenger Name: "+getPassengerName());
		System.out.println("Passenger No: "+getPassengerNo());
		System.out.println("Age of Passenger: "+getAge());
		System.out.println("Class of Travel: "+getClassOfTravel());
		System.out.println("Berth: "+getBerth());
		System.out.println("Seat No: "+getSeatNo());
	}	
	
}
