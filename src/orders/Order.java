package orders;

import java.util.Date;

public class Order {
	private int orderID;
	private int customerID;
	private int engineerID;
	private Date date;
	private String orderType;
	private boolean orderStatus;
	
	public Order (){
		
	}
	
	public Order (int orderID, int customerID, int engineerID, Date date, String orderType, boolean orderStatus){
		this.setOrderID(orderID);
		this.setCustomerID(customerID);
		this.setEngineerID(engineerID);
		this.setDate(date);
		this.setOrderType(orderType);
		this.setOrderStatus(orderStatus);
	}

	public int getOrderID() {
		return orderID;
	}

	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}

	public int getCustomerID() {
		return customerID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}

	public int getEngineerID() {
		return engineerID;
	}

	public void setEngineerID(int engineerID) {
		this.engineerID = engineerID;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public boolean isOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(boolean orderStatus) {
		this.orderStatus = orderStatus;
	}
	
	
}