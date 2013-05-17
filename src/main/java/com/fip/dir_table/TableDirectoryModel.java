// TableDirectoryView.java
// Presentation only.  No user actions.

package com.fip.dir_table;

import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableColumn;

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

		TableColumn srcFile = new TableColumn(0, 245);
		srcFile.setHeaderValue(t.get("compTable.src"));
		columnModel.addColumn(srcFile);

		TableColumn srcDate = new TableColumn(1, 150);
		srcDate.setHeaderValue(t.get("compTable.date"));
		columnModel.addColumn(srcDate);

		TableColumn srcSize = new TableColumn(2, 80);
		srcSize.setHeaderValue(t.get("compTable.size"));
		columnModel.addColumn(srcSize);

		TableColumn comp = new TableColumn(3, 30);
		comp.setHeaderValue(t.get("compTable.action"));
		columnModel.addColumn(comp);

		TableColumn tgtFile = new TableColumn(4, 245);
		tgtFile.setHeaderValue(t.get("compTable.tgt"));
		columnModel.addColumn(tgtFile);

		TableColumn tgtDate = new TableColumn(5, 150);
		tgtDate.setHeaderValue(t.get("compTable.date"));
		columnModel.addColumn(tgtDate);

		TableColumn tgtSize = new TableColumn(6, 80);
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
             return null; // Should never happen
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
