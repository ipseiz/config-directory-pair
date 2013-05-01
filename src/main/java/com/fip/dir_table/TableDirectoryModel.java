// TableDirectoryView.java
// Presentation only.  No user actions.

package com.fip.dir_table;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableColumn;

import com.fip.common.DirectoryPair;
import com.fip.common.Element;
import com.fip.common.TextTranslation;


/**
 * This class is responsible for displaying the synchronization table.
 * TableModel. This model is NOT completely independent of the user interface. 
 * The structure of the table (column model) is defined in this class.
 *
 * @author Fabien Ipseiz
 */
public class TableDirectoryModel extends AbstractTableModel{

	private static final long serialVersionUID = 1L;
	private final JTable table;
	private final int columnNb;
	private final ArrayList<Element> elements = new ArrayList<Element>();


	public TableDirectoryModel() {
		
		// Get translation object and set default locale:
		TextTranslation t = TextTranslation.getInstance();
		
		// Create column model:
		DefaultTableColumnModel columnModel = new DefaultTableColumnModel();

		TableColumn srcFile = new TableColumn(0, 205);
		srcFile.setHeaderValue(t.get("compTable.src"));
		columnModel.addColumn(srcFile);

		TableColumn srcDate = new TableColumn(1, 120);
		srcDate.setHeaderValue(t.get("compTable.date"));
		columnModel.addColumn(srcDate);

		TableColumn srcSize = new TableColumn(2, 60);
		srcSize.setHeaderValue(t.get("compTable.size"));
		columnModel.addColumn(srcSize);

		//TableColumn comp = new TableColumn(3, 30);
		//comp.setHeaderValue(t.get("compTable.action"));
		//columnModel.addColumn(comp);

		TableColumn tgtFile = new TableColumn(4, 205);
		tgtFile.setHeaderValue(t.get("compTable.tgt"));
		columnModel.addColumn(tgtFile);

		TableColumn tgtDate = new TableColumn(5, 120);
		tgtDate.setHeaderValue(t.get("compTable.date"));
		columnModel.addColumn(tgtDate);

		TableColumn tgtSize = new TableColumn(6, 60);
		tgtSize.setHeaderValue(t.get("compTable.size"));
		columnModel.addColumn(tgtSize);
		
		columnNb = columnModel.getColumnCount();

		// Create table:
		table = new JTable(this, columnModel);
		// TableRenderer renderer = new TableRenderer(table);
		// table.setDefaultRenderer(String.class, renderer);
		table.getTableHeader().setReorderingAllowed(false);
		// Set row height:
		table.setRowHeight(table.getRowHeight() + 2);

		// Add static data in the table (to test the code)
		elements.add(new Element("Johnathan", "Sykes", "12546"));
		elements.add(new Element("Nicolas", "Van de Kampf", "84541"));
		elements.add(new Element("Damien", "Cuthbert", "125"));
		elements.add(new Element("Corinne", "Valance", "9985445"));
		elements.add(new Element("Emilie", "Schrödinger", "5896"));
		elements.add(new Element("Delphine", "Duke", "1006"));
		elements.add(new Element("Eric", "Trump", "856"));

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.table.TableModel#getRowCount()
	 */
	@Override
	public int getRowCount() {
		return elements.size();
	}

	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#getColumnCount()
	 */
	@Override
	public int getColumnCount() {
		return columnNb;
	}

	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#getValueAt(int, int)
	 */
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		 switch(columnIndex){
         case 0:
             return elements.get(rowIndex).getName();
         case 1:
             return elements.get(rowIndex).getDate();
         case 2:
             return elements.get(rowIndex).getSize();
         default:
             return null; //Ne devrait jamais arriver
     }
	}

	
	
	public void addElement(Element element) {
		elements.add(element);

		fireTableRowsInserted(elements.size() - 1, elements.size() - 1);
	}

	public void removeElement(int rowIndex) {
		elements.remove(rowIndex);

		fireTableRowsDeleted(rowIndex, rowIndex);
	}
	
	/**
	 * @return Returns the JTable object.
	 */
	public final JTable getJTable() {
		return table;
	}
}
