package com.fip.conf;
// DirectoryConfigView.java


import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;



/**
 * This dialog manages adding/changing directory pairs.
 * 
 * @author Fabien Ipseiz
 */
public class DirectoryConfigView extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	/** The source element. */
	private final JTextField srcElement;

	/** The target element. */
	private final JTextField tgtElement;

	/** The directory pair to modify. */
	private final DirectoryPair pair;
	
	private final JButton okButton;
	private final JButton cancelButton;

	/**
	 * Create the dialog.
	 */
	public DirectoryConfigView() {
		
		// Get the translation object:
		TextTranslation t = TextTranslation.getInstance();
		
		pair = new DirectoryPair("","");
		
		// Create the modal dialog:
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setTitle(t.get("profile.dir.title"));
		setResizable(false);

		//setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());

		// Create source row:
		JLabel srcLabel = new JLabel(t.get("profile.dir"));
		//srcElement = new JTextField(pair.getSrc(), 40); restore the saved configuration
		srcElement = new JTextField(40);
		JPanel srcRow1Panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		srcRow1Panel.add(srcLabel);
		srcRow1Panel.add(srcElement);
		JPanel srcRowPanel = new JPanel(new BorderLayout());
		srcRowPanel.setBorder(new TitledBorder(t.get("profile.dir.table.src")));
		srcRowPanel.add(srcRow1Panel, BorderLayout.NORTH);

		// Create target row:
		JLabel tgtLabel = new JLabel(t.get("profile.dir"));
		//tgtElement = new JTextField(pair.getTgt(), 40); restore the saved configuration
		tgtElement = new JTextField(40);
		JPanel tgtRow1Panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		tgtRow1Panel.add(tgtLabel);
		tgtRow1Panel.add(tgtElement);
		JPanel tgtRowPanel = new JPanel(new BorderLayout());
		tgtRowPanel.setBorder(new TitledBorder(t.get("profile.dir.table.tgt")));
		tgtRowPanel.add(tgtRow1Panel, BorderLayout.NORTH);

		// Create source and target panel:
		JPanel dirPanel = new JPanel(new GridLayout(2, 1));
		dirPanel.add(srcRowPanel);
		dirPanel.add(tgtRowPanel);
		getContentPane().add(dirPanel, BorderLayout.CENTER);

		// Create buttons panel:
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		okButton = new JButton(t.get("button.ok"));
		okButton.setActionCommand("OK");
		okButton.setName("OK");
		buttonPane.add(okButton);
		okButton.addActionListener(this);
		//getRootPane().setDefaultButton(okButton);   

		cancelButton = new JButton(t.get("button.cancel"));
		cancelButton.setActionCommand("Cancel");
		cancelButton.setName("Cancel");
		buttonPane.add(cancelButton);
	}
	
	public void actionPerformed(ActionEvent event) {
		String cmd = event.getActionCommand();

		if (cmd.equals("OK")) {
			pair.setSrc(srcElement.getText());
			pair.setTgt(tgtElement.getText());

			System.out.println("source directory : " + pair.getSrc() + "\n"
					+ "target directory : " + pair.getTgt());

			// TODO Test for existing directory:
		}
	}

}
