package com.fip.conf;

import java.awt.event.ActionEvent;

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

	public DirectoryConfigController(DirectoryPair pair, DirectoryConfigView view) {
		this.dirPair = pair;
		this.dirConfView = view;
		//... Add listeners to the view.
		view.addButtonsListener(this);
	}
	
	@Override
	public void okPerformed(ActionEvent e) {
		dirPair.setSrc(dirConfView.getSrcText());
		dirPair.setTgt(dirConfView.getTgtText());

		System.out.println("source directory : " + dirPair.getSrc() + "\n"
				+ "target directory : " + dirPair.getTgt());
		
		// TODO check if the directories exist
		
	}
	
	@Override
	public void cancelPerformed(ActionEvent e) {
		
	}
	
}

