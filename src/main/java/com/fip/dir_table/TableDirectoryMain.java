// TableDirectoryMain.java
package com.fip.dir_table;

import javax.swing.UIManager;

import com.fip.common.DirectoryPair;

/**
 * Description
 *
 * @author Fabien Ipseiz
 */
public class TableDirectoryMain {

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

		DirectoryPair pair = new DirectoryPair("F:/Utilisateurs/Fabien/Mes documents/Java", "F:/Utilisateurs/Fabien/Mes documents/Mes recettes/");
		SyncTableDirectoryView view = new SyncTableDirectoryView(pair);
		
		view.pack();
		view.setLocation(300, 200);
		view.setVisible(true);

	}

}
