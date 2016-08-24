package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import statistics.Statistics;
import users.User;
import users.UsersDatabase;

public class StatisticsPage extends Page{
	public StatisticsPage(){
		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				refreshTable();
			}
			
		});
		buttonsPanel.add(btnRefresh);
	}
	
	protected void refreshTable() {
		setTable();
		table.setModel(tModel);
	}

	protected void setTable() {
		tableTitles = new Object[6];
		tableTitles[0] = "Engineer ID";
		tableTitles[1] = "Engineer's First Name";
		tableTitles[2] = "Engineer's Last Name";
		tableTitles[3] = "Number of Inspections";
		tableTitles[4] = "Number of Installations";
		tableTitles[5] = "Worked Hours";
		
		UsersDatabase usersDB = new UsersDatabase();
		List<User> users = new ArrayList<User>();
		users = usersDB.getUsersByCategory("Engineer");
		
		tModel = new DefaultTableModel();
		tModel.setColumnIdentifiers(tableTitles);
		
		Object[] data = new Object[6];
		
		if(users != null){
			for(int i=0;i<users.size(); i++){
				Statistics statistics = new Statistics(users.get(i));
				
				data[0] = statistics.getEngineer().getUserID();
				data[1] = statistics.getEngineer().getFirstName();
				data[2] = statistics.getEngineer().getLastName();
				data[3] = statistics.getInspectionNo();
				data[4] = statistics.getInstallationNo();
				data[5] = statistics.getHours();
				tModel.addRow(data);
			} 
		}
	}
		
}
