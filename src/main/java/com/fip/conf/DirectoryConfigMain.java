// DirectoryConfigMain.java
package com.fip.conf;


/**
 * Description
 *
 * @author Fabien
 */
public class DirectoryConfigMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		DirectoryPair pair = new DirectoryPair("","");
		DirectoryConfigView view = new DirectoryConfigView(pair);
		@SuppressWarnings("unused")
		DirectoryConfigController controller = new DirectoryConfigController(pair, view);
		
		view.pack();
		view.setLocation(300, 200);
		view.setVisible(true);

	}

}
