package report;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;

import customers.Customer;
import customers.CustomersDatabase;
import orders.Order;
import users.User;
import users.UsersDatabase;

public class Report {
	private Order order;
	private String reportMess;
	private Customer customer;
	private User engineer;
	
	public Report(Order order, String reportMess){
		this.order = order;
		this.reportMess = reportMess;
		
		CustomersDatabase customersDB = new CustomersDatabase();
		UsersDatabase usersDB = new UsersDatabase();
		
		customer = new Customer();
		engineer = new User();
		
		engineer = usersDB.getUserByID(order.getEngineerID());
		customer = customersDB.getCustomerByID(order.getCustomerID());
		
		generateReport();
	}
	
	private void generateReport(){
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM/yyyy HH:mm");
		try {
			FileWriter fileWriter = new FileWriter("reports/"+order.getOrderID()+".txt");
			fileWriter.write("\n***** REPORT *****");
			fileWriter.write("\n");
			fileWriter.write("\nOrder ID: "+order.getOrderID());
			fileWriter.write("\nOrder Type: "+order.getOrderType());
			fileWriter.write("\nOrder Date & Time: "+sdf.format(order.getDate()));
			fileWriter.write("\nCustomer: "+customer.getFirstName()+" "+customer.getLastName()+" ("+customer.getCustomerID()+")");
			fileWriter.write("\nEngineer: "+engineer.getFirstName()+" "+engineer.getLastName()+" ("+engineer.getUserID()+")");
			fileWriter.write("\n");
			fileWriter.write("\nReport Message: "+reportMess);
			fileWriter.write("\n");
			fileWriter.write("\n");
			fileWriter.write("\n");
			fileWriter.write("\n");
			fileWriter.write("\n__________________________");
			fileWriter.write("\nSignature of the engineer");
			fileWriter.write("\n");
			fileWriter.write("\n");
			fileWriter.close();
		
		} catch (IOException e) {
				e.printStackTrace();
		}
	}
}
