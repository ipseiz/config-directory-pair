// DirectoryConfigMain.java
package com.fip.conf;

import javax.swing.UIManager;


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
