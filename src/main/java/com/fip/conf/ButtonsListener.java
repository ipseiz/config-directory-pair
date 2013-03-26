package com.fip.conf;

import java.awt.event.ActionEvent;

public interface ButtonsListener {
	
	void okPerformed(ActionEvent e);
	void cancelPerformed(ActionEvent e);
	void changeSrcPerformed(ActionEvent e);
	void changeTgtPerformed(ActionEvent e);

}
