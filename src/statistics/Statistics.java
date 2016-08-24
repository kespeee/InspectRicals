package statistics;

import java.util.ArrayList;
import java.util.List;

import orders.Order;
import orders.OrdersDatabase;
import users.User;

public class Statistics {
	private User engineer;
	private int inspectionNo;
	private int installationNo;
	private int hours;
	
	public Statistics(User engineer){
		this.engineer = engineer;
		inspectionNo = calculateInspections();
		installationNo = calculateInstallations();
		hours = calculateHours();
	}
	
	private int calculateInspections(){
		List<Order> orders = new ArrayList <Order>();
		OrdersDatabase ordersDB = new OrdersDatabase();
		orders = ordersDB.getOrdersByEngineer(engineer.getUserID());
		
		int inspections = 0;
		
		for (int i=0;i<orders.size();i++) {
			if (orders.get(i).getOrderType().equals("Inspection")) {
				inspections++;
			}
		}
		
		return inspections;
	}
	
	private int calculateInstallations(){
		List<Order> orders = new ArrayList <Order>();
		OrdersDatabase ordersDB = new OrdersDatabase();
		orders = ordersDB.getOrdersByEngineer(engineer.getUserID());
		
		int installations = 0;
		
		for (int i=0;i<orders.size();i++){
			if (orders.get(i).getOrderType().equals("Installation")){
				installations++;
			}
		}
		
		return installations;
	}
	
	private int calculateHours(){
		return inspectionNo + installationNo;
	}
	
	public int getInspectionNo(){
		return inspectionNo;
	}
	
	public int getInstallationNo(){
		return installationNo;
	}

	public User getEngineer() {
		return engineer;
	}

	public int getHours() {
		return hours;
	}
	
}
