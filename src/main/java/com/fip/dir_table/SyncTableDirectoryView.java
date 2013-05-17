// SyncTableDirectoryView.java
package com.fip.dir_table;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fip.common.DirectoryPair;
import com.fip.common.Element;
import com.fip.common.TextTranslation;

/**
 * This class represents the main Java Swing frame 
 * View and controller in the same class
 * Calls Model as needed.
 * (Handles user interaction with listeners.)
 *
 * @author Fabien Ipseiz
 */
public class SyncTableDirectoryView extends JFrame implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	
	private final JButton addButton ;
	private final JButton clearButton ;
	private final JTable viewTable ;
	private final TableDirectoryModel dirTableModel;
	
	/** SLF4J bound to logback-classic. */
	private static final Logger logger = LoggerFactory.getLogger(SyncTableDirectoryView.class);
	
	public SyncTableDirectoryView(DirectoryPair pair)  {
		
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
		dirTableModel = new TableDirectoryModel();
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
		
		createListElements(pair);
		
	}

	/**
	 * Fill the directory table with element objects
	 */
	public void createListElements(DirectoryPair pair) {
		final String srcDir = pair.getSrc();
		final String tgtDir = pair.getTgt();
		final Path srcFolder = Paths.get(srcDir);
		final Path tgtFolder = Paths.get(tgtDir);

		// source folder is a folder (directory)
		logger.info(": \n" + "source Directory: " + srcDir + "\n"
				+ "Target Directory: " + tgtDir);
		if (!Files.isDirectory(srcFolder)) {
			return;
		}
		
		// target folder is a folder (directory)
				logger.info(": \n" + "target Directory: " + tgtDir + "\n"
						+ "Target Directory: " + tgtDir);
				if (!Files.isDirectory(tgtFolder)) {
					return;
				}

		// Read the files list.
		try (DirectoryStream<Path> fileList = Files.newDirectoryStream(srcFolder)) {
			for (Path file : fileList) {
				String newElementName = file.getFileName().toString();
				String newElementDate = Files.getLastModifiedTime(file).toString();
				String newElementSize = String.valueOf(Files.size(file));
				dirTableModel.addElement(new Element(newElementName,
						newElementDate, newElementSize));

				System.out.print("\t\t"
						+ ((Files.isDirectory(file)) ? file + "/" : file));
				System.out.println("\n");
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
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
