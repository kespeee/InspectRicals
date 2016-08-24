package customers;

public class Customer {
	private int customerID;
	private String firstName;
	private String lastName;
	private String email;
	private String contactNo;
	private String address;
	private String city;
	private String state;
	
	public Customer(){
		
	}
	
	public Customer(int customerID, String firstName, String lastName, String email, String contactNo, String address, String city, String state){
		this.setCustomerID(customerID);
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setEmail(email);
		this.setContactNo(contactNo);
		this.setAddress(address);
		this.setCity(city);
		this.setState(state);
	}

	public int getCustomerID() {
		return customerID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
}
