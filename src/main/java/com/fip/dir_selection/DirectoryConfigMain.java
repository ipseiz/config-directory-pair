// DirectoryConfigMain.java
package com.fip.dir_selection;

import javax.swing.UIManager;

import com.fip.common.DirectoryPair;


/**
 * Description
 * 
 * @author Fabien Ipseiz
 */
public class DirectoryConfigMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		// Set the GUI look and feel:
		try {
			// Set System L&F
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}

		DirectoryPair pair = new DirectoryPair("", "");
		DirectoryConfigView view = new DirectoryConfigView(pair);
		@SuppressWarnings("unused")
		DirectoryConfigController controller = new DirectoryConfigController(
				pair, view);

		view.pack();
		view.setLocation(300, 200);
		view.setVisible(true);

	}
}
