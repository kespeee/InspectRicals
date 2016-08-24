package users;

public class User {
	private int userID;
	private String username;
	private String password;
	private String category;
	private	String firstName;
	private String lastName;
	
	public User (){
		
	}
	
	public User (int userID, String username, String password, String category, String firstName, String lastName){
		this.userID = userID;
		this.username = username;
		this.password = password;
		this.category = category;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public void setUserID(int userID){
		this.userID = userID;
	}
	
	public void setUsername(String username){
		this.username = username;
	}
	
	public void setPassword(String password){
		this.password = password;
	}
	
	public void setCategory(String category){
		this.category = category;
	}
	
	public void setFirstName(String firstName){
		this.firstName = firstName;
	}
	
	public void setLastName(String lastName){
		this.lastName = lastName;
	}
	
	public int getUserID(){
		return userID;
	}
	
	public String getUsername(){
		return username;
	}
	
	public String getPassword(){
		return password;
	}
	
	public String getCategory(){
		return category;
	}
	
	public String getFirstName(){
		return firstName;
	}
	
	public String getLastName(){
		return lastName;
	}
	
	
}