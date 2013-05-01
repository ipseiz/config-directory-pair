// SyncTableDirectoryView.java
package com.fip.dir_table;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import com.fip.common.TextTranslation;

/**
 * This class represents the main Java Swing frame 
 *
 * @author Fabien Ipseiz
 */
public class SyncTableDirectoryView extends JFrame implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	
	private final JButton addButton ;
	private final JButton clearButton ;
	private final JTable viewTable ;
	
	public SyncTableDirectoryView()  {
		
		// Get translation object and set default locale:
		TextTranslation t = TextTranslation.getInstance();
				
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setTitle("JFrame title");
		contentPane.setName(getTitle());
		setContentPane(contentPane);

		// Initialize JTable:
		TableDirectoryModel dirTableModel = new TableDirectoryModel();
		//viewTable = new JTable(dirTableModel);
		viewTable = dirTableModel.getJTable();
		getContentPane().add(new JScrollPane(viewTable));
		viewTable.setName("table");
		
		//viewList.setCellRenderer(new ImageListCellRenderer());

		// Create buttons panel:
		JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		addButton = new JButton(t.get("button.add"));
		addButton.setEnabled(true);
		addButton.setName("add");
		addButton.addActionListener(this);
		
		buttonsPanel.add(addButton);
		clearButton=new JButton(t.get("button.clear"));
		clearButton.setEnabled(true);
		clearButton.setName("clear");
		clearButton.addActionListener(this);
		buttonsPanel.add(clearButton);
		contentPane.add(buttonsPanel, BorderLayout.SOUTH);
		
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
		String cmd = event.getActionCommand();
		
		switch (cmd) {
		case "Add" : System.out.println("call DirectoryConfigMain");
					 break;

		case "Clear" :System.out.println("clear the table");
		 			  break;
		}
	}
		
}
