package customers;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.Database;

public class CustomersDatabase extends Database{
	List<Customer> customers;
	Customer customer;
	
	public void deleteCustomer(Customer customer){
		openConnection();
		this.customer = customer;
		statementString = "DELETE FROM Customers WHERE customerid='"+this.customer.getCustomerID()
						+"' AND first_name='"+this.customer.getFirstName()+"' AND last_name='"+this.customer.getLastName()+"'"; 
		
		try {
			
			statement = connection.createStatement();
			statement.executeUpdate(statementString);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		closeConnection();
	}
	
	public void updateCustomer(Customer customer){
		openConnection();
		this.customer = customer;
		statementString = "UPDATE Customers SET first_name='"+this.customer.getFirstName()+"', last_name='"+this.customer.getLastName()+"', email='"+this.customer.getEmail()
						+"', contact_no='"+this.customer.getContactNo()+"', address='"+this.customer.getAddress()+"', city='"+this.customer.getCity()
						+"', state='"+this.customer.getState()+"' WHERE customerid='"+this.customer.getCustomerID()+"'";
		try {
			
			statement = connection.createStatement();
			statement.executeUpdate(statementString);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		closeConnection();
	}
	
	public Customer getCustomerByLastName(String lastName){
		openConnection();
		statementString = "SELECT * FROM Customers WHERE last_name='"+lastName+"'";
		customer = null;
		
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(statementString);
			
			while (resultSet.next()){
				customer = new Customer();
				customer.setCustomerID(resultSet.getInt("customer_id"));
				customer.setFirstName(resultSet.getString("first_name"));
				customer.setLastName(resultSet.getString("last_name"));
				customer.setEmail(resultSet.getString("email"));
				customer.setContactNo(resultSet.getString("contact_no"));
				customer.setAddress(resultSet.getString("address"));
				customer.setCity(resultSet.getString("city"));
				customer.setState(resultSet.getString("state"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		closeConnection();
		return customer;
	}
	
	public Customer getCustomerByFirstName(String firstName){
		openConnection();
		statementString = "SELECT * FROM Customers WHERE first_name='"+firstName+"'";
		customer = null;
		
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(statementString);
			
			while (resultSet.next()){
				customer = new Customer();
				customer.setCustomerID(resultSet.getInt("customerid"));
				customer.setFirstName(resultSet.getString("first_name"));
				customer.setLastName(resultSet.getString("last_name"));
				customer.setEmail(resultSet.getString("email"));
				customer.setContactNo(resultSet.getString("contact_no"));
				customer.setAddress(resultSet.getString("address"));
				customer.setCity(resultSet.getString("city"));
				customer.setState(resultSet.getString("state"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		closeConnection();
		return customer;
	}
	
	public Customer getCustomerByID(int customerID){
		openConnection();
		statementString = "SELECT * FROM Customers WHERE customerid='"+customerID+"'";
		customer = null;
		
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(statementString);
			
			while (resultSet.next()){
				customer = new Customer();
				customer.setCustomerID(resultSet.getInt("customerid"));
				customer.setFirstName(resultSet.getString("first_name"));
				customer.setLastName(resultSet.getString("last_name"));
				customer.setEmail(resultSet.getString("email"));
				customer.setContactNo(resultSet.getString("contact_no"));
				customer.setAddress(resultSet.getString("address"));
				customer.setCity(resultSet.getString("city"));
				customer.setState(resultSet.getString("state"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		closeConnection();
		return customer;
	}
	
	public List<Customer> getAllCustomers(){
		openConnection();
		statementString = "SELECT * FROM Customers";
		customers = null;
		customer = null;
		
		try {
			
			statement = connection.createStatement();
			resultSet = statement.executeQuery(statementString);
			customers = new ArrayList<Customer>();
			
			while (resultSet.next()){
				customer = new Customer();
				customer.setCustomerID(resultSet.getInt("customerid"));
				customer.setFirstName(resultSet.getString("first_name"));
				customer.setLastName(resultSet.getString("last_name"));
				customer.setEmail(resultSet.getString("email"));
				customer.setContactNo(resultSet.getString("contact_no"));
				customer.setAddress(resultSet.getString("address"));
				customer.setCity(resultSet.getString("city"));
				customer.setState(resultSet.getString("state"));
				
				customers.add(customer);
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		closeConnection();
		return customers;
	}
	
	public void addCustomer(Customer customer) {
		openConnection();
		this.customer = customer;
		statementString = "INSERT INTO Customers (first_name, last_name, email, contact_no, address, city, state) VALUES ('" 
							+this.customer.getFirstName()+"', '"+this.customer.getLastName()+"', '"+this.customer.getEmail()+"','"
							+this.customer.getContactNo()+"', '"+this.customer.getAddress()+"', '"+this.customer.getCity()+"', '"
							+this.customer.getState()+"')";
		try {
			
			statement = connection.createStatement();
			statement.executeUpdate(statementString);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		closeConnection();
	}
}
