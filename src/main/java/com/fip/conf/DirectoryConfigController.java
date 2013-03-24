package com.fip.conf;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// DirectoryConfigController.java
// Handles user interaction with listeners.
// Calls View and Model as needed.
/**
 * Description
 *
 * @author Fabien Ipseiz
 */
public class DirectoryConfigController implements ButtonsListener {

	private final DirectoryPair dirPair;
	private final DirectoryConfigView dirConfView;
	
	/** SLF4J bound to logback-classic. */
	private static final Logger logger = LoggerFactory.getLogger(DirectoryConfigController.class);
	

	public DirectoryConfigController(DirectoryPair pair, DirectoryConfigView view) {
		this.dirPair = pair;
		this.dirConfView = view;
		//... Add listeners to the view.
		view.addButtonsListener(this);
	}
	
	@Override
	public void okPerformed(ActionEvent e) {
		String srcPath = dirConfView.getSrcText();
		String tgtPath = dirConfView.getTgtText();
		try {
			createDirectory(srcPath);
			createDirectory(tgtPath);
		} catch (IOException ioe){
			logger.error("An error occurs during Directory creation process");
		}
		
		dirPair.setSrc(srcPath);
		dirPair.setTgt(tgtPath);

		System.out.println("source directory : " + dirPair.getSrc() + "\n"
				+ "target directory : " + dirPair.getTgt());
	}
	
	@Override
	public void cancelPerformed(ActionEvent e) {

	}

	/**
	 * Check if a specified file path is a folder and create a folder if it does
	 * not exist.
	 * 
	 * @param folderPath
	 *            A folder path.
	 */
	public static final void createDirectory(String folderPath) throws IOException {
		final Path folder = Paths.get(folderPath);
		
		if (Files.notExists(folder)) {
			// Get translation object: 
			TextTranslation t = TextTranslation.getInstance();
			
			// Create dialog panel:
			JPanel panel = new JPanel(new GridLayout(3, 1));
			JLabel msg = new JLabel(t.get("profile.dir.create.message"));
			JLabel question = new JLabel(t.get("profile.dir.create.question"));
			JTextField directory = new JTextField(folderPath);
			panel.add(msg);
			panel.add(question);
			panel.add(directory);

			int selection = JOptionPane.showConfirmDialog(null, panel,
					t.get("profile.dir.create.title"),
					JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);

			if (selection == JOptionPane.OK_OPTION) {
				Files.createDirectory(folder);
			}
		}
	}
}
