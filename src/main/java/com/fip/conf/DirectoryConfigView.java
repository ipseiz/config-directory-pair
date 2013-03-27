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
public class DirectoryConfigView extends JFrame {

	private static final long serialVersionUID = 1L;

	/** The source element. */
	private final JTextField srcElement;

	/** The target element. */
	private final JTextField tgtElement;

	private final JButton okButton;
	private final JButton cancelButton;
	private final JButton changeSrcButton;
	private final JButton changeTgtButton;

	
	/**
	 * Create the dialog.
	 */
	public DirectoryConfigView(DirectoryPair pair) {
		
		// Get the translation object:
		TextTranslation t = TextTranslation.getInstance();

		// Create the modal dialog:
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setTitle(t.get("profile.dir.title"));
		setResizable(false);

		// setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());

		// Create source row:
		JLabel srcLabel = new JLabel(t.get("profile.dir"));
		//srcElement = new JTextField(pair.getSrc(), 40); restore the saved configuration
		srcElement = new JTextField(40);
		srcElement.setName("srcElement");
		changeSrcButton = new JButton(t.get("button.change"));
		changeSrcButton.setActionCommand("Change");
		changeSrcButton.setName("Change");
		JPanel srcRow1Panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		srcRow1Panel.add(srcLabel);
		srcRow1Panel.add(srcElement);
		srcRow1Panel.add(changeSrcButton);
		JPanel srcRowPanel = new JPanel(new BorderLayout());
		srcRowPanel.setBorder(new TitledBorder(t.get("profile.dir.table.src")));
		srcRowPanel.add(srcRow1Panel, BorderLayout.NORTH);

		// Create target row:
		JLabel tgtLabel = new JLabel(t.get("profile.dir"));
		//tgtElement = new JTextField(pair.getTgt(), 40); restore the saved configuration
		tgtElement = new JTextField(40);
		tgtElement.setName("tgtElement");
		changeTgtButton = new JButton(t.get("button.change"));
		changeTgtButton.setActionCommand("Change");
		changeTgtButton.setName("Change");
		JPanel tgtRow1Panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		tgtRow1Panel.add(tgtLabel);
		tgtRow1Panel.add(tgtElement);
		tgtRow1Panel.add(changeTgtButton);
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
		//getRootPane().setDefaultButton(okButton);   

		cancelButton = new JButton(t.get("button.cancel"));
		cancelButton.setActionCommand("Cancel");
		cancelButton.setName("Cancel");
		buttonPane.add(cancelButton);
	}

	public void addButtonsListener(final ButtonsListener l) {

		okButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				l.okPerformed(e);

			}
		});

		cancelButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				l.cancelPerformed(e);

			}
		});
		
		changeSrcButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				l.changeSrcPerformed(e);

			}
		});
	}

	/**
	 * Input the source element with the selected directory path 
	 * 
	 * @param dirPath
	 *            A selected directory path.
	 */
	public void setSrcText(String dirPath) {
		srcElement.setText(dirPath);
	}
	
	/**
	 * @return the source text
	 */
	public String getSrcText() {
		return srcElement.getText();
	}

	/**
	 * @return the target text
	 */
	public String getTgtText() {
		return tgtElement.getText();
	}

}
