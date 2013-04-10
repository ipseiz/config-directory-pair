// SyncTableDirectoryView.java
package com.fip.dir_table;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import com.fip.common.DirectoryPair;
import com.fip.common.TextTranslation;

/**
 * Description
 *
 * @author Fabien Ipseiz
 */
public class SyncTableDirectoryView extends JFrame {
	
	private final JButton addButton ;
	private final JButton clearButton ;
	private final JTable viewTable ;
	
	public SyncTableDirectoryView(DirectoryPair dirModel)  {
		
		// Get translation object and set default locale:
		TextTranslation t = TextTranslation.getInstance();
				
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setTitle("JFrame title");
		contentPane.setName(getTitle());
		setContentPane(contentPane);

		// Create table panel:
		viewTable = new JTable();
		viewTable.setName("table");
		//viewList.setCellRenderer(new ImageListCellRenderer());
		getContentPane().add(new JScrollPane(viewTable));

		// Create buttons panel:
		JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		addButton = new JButton(t.get("button.add"));
		addButton.setEnabled(false);
		addButton.setName("add");
		
		buttonsPanel.add(addButton);
		clearButton=new JButton(t.get("button.clear"));
		clearButton.setEnabled(false);
		clearButton.setName("clear");
		buttonsPanel.add(clearButton);
		contentPane.add(buttonsPanel, BorderLayout.SOUTH);
	}
}
