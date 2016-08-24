package orders;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import customers.CustomersDatabase;
import database.Database;
import users.UsersDatabase;

public class OrdersDatabase extends Database{
	private CustomersDatabase customersDatabase;
	private UsersDatabase usersDatabase;
	private List<Order> orders;
	private Order order;
	private Timestamp timestamp;
	
	public void deleteOrder(Order order){
		openConnection();
		this.order = order;
		statementString = "DELETE FROM Orders WHERE orderid='"+this.order.getOrderID()+"'";
		
		try {
			
			statement = connection.createStatement();
			statement.executeUpdate(statementString);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		closeConnection();
	}
	
	public void updateOrder(Order order){
		openConnection();
		this.order = order;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateTime = sdf.format(this.order.getDate());
		int orderStatus = 0;
		if (order.isOrderStatus()){
			orderStatus = 1;
		}
		
		statementString = "UPDATE Orders SET customerid='"+this.order.getCustomerID()+"', engineerid='"+this.order.getEngineerID()
						+ "', date='"+dateTime+"', order_type='"+this.order.getOrderType()+"',"+" order_status='"+orderStatus
						+"' WHERE orderid='"+this.order.getOrderID()+"'";
		try {
			
			statement = connection.createStatement();
			statement.executeUpdate(statementString);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		closeConnection();
	}
	
	public List<Order> getOrdersByEngineer(int engineerID){
		openConnection();
		statementString = "SELECT * FROM Orders WHERE engineerid='"+engineerID+"'";
		order = null;
		orders = null;
		
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(statementString);
			orders = new ArrayList<Order>();
			
			while (resultSet.next()){
				order = new Order();
				order.setOrderID(resultSet.getInt("orderid"));
				order.setCustomerID(resultSet.getInt("customerid"));
				order.setEngineerID(resultSet.getInt("engineerid"));
				timestamp = resultSet.getTimestamp("date");
				order.setDate(new Date(timestamp.getTime()));
				order.setOrderType(resultSet.getString("order_type"));
				int orderStatus = resultSet.getInt("order_status");
				if (orderStatus == 0){
					order.setOrderStatus(false);
				} else {
					order.setOrderStatus(true);
				}
				
				orders.add(order);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		closeConnection();
		return orders;
	}
	
	public Order getOrderByID(int orderID){
		openConnection();
		statementString = "SELECT * FROM Orders WHERE orderid='"+orderID+"'";
		order = null;
		
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(statementString);
			
			while (resultSet.next()){
				order = new Order();
				order.setOrderID(resultSet.getInt("orderid"));
				order.setCustomerID(resultSet.getInt("customerid"));
				order.setEngineerID(resultSet.getInt("engineerid"));
				timestamp = resultSet.getTimestamp("date");
				order.setDate(new Date(timestamp.getTime()));
				order.setOrderType(resultSet.getString("order_type"));
				int orderStatus = resultSet.getInt("order_status");
				if (orderStatus == 0){
					order.setOrderStatus(false);
				} else {
					order.setOrderStatus(true);
				}
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		closeConnection();
		return order;
	}
	
	public List<Order> getAllOrders(){
		openConnection();
		statementString = "SELECT * FROM Orders";
		order = null;
		orders = null;
		
		try {
			customersDatabase = new CustomersDatabase();
			usersDatabase = new UsersDatabase();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(statementString);
			orders = new ArrayList<Order>();
			
			while (resultSet.next()){
				order = new Order();
				order.setOrderID(resultSet.getInt("orderid"));
				order.setCustomerID(resultSet.getInt("customerid"));
				order.setEngineerID(resultSet.getInt("engineerid"));
				timestamp = resultSet.getTimestamp("date");
				order.setDate(new Date(timestamp.getTime()));
				order.setOrderType(resultSet.getString("order_type"));
				int orderStatus = resultSet.getInt("order_status");
				if (orderStatus == 0){
					order.setOrderStatus(false);
				} else {
					order.setOrderStatus(true);
				}
				
				orders.add(order);
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		closeConnection();
		return orders;
	}
	
	public void addOrder(Order order) {
		openConnection();
		this.order = order;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateTime = sdf.format(this.order.getDate());
		int orderStatus = 0;
		if(order.isOrderStatus()){
			orderStatus = 1;
		}
		statementString = "INSERT INTO Orders (customerid, engineerid, date, order_type, order_status) "
						+ "VALUES ('"+this.order.getCustomerID()+"', '"+this.order.getEngineerID()+"', '"+dateTime+"', "
								+ "'"+this.order.getOrderType()+"', '"+orderStatus+"')";
		try {
			
			statement = connection.createStatement();
			statement.executeUpdate(statementString);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		closeConnection();
	}
}
