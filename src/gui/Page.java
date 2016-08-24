package gui;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;

public abstract class Page extends JPanel{
	protected JScrollPane scrollPane;
	protected JTable table;
	protected JPanel buttonsPanel;
	protected DefaultTableModel tModel;
	protected Object[] tableTitles;
	
	public Page(){
		this.setPreferredSize(new Dimension (830,600));
		setLayout(new BorderLayout());
		
		setTable();
		
		scrollPane = new JScrollPane();
		
		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setModel(tModel);
		
		scrollPane.getViewport().add(table);
		add(scrollPane, BorderLayout.CENTER);
		
		buttonsPanel = new JPanel();
		add(buttonsPanel, BorderLayout.NORTH);
		

	}
	
	protected abstract void setTable();
	
}
