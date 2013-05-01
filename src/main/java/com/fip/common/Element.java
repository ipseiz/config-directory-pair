// Element.java
package com.fip.common;

/**
 * Description
 *
 * @author Fabien Ipseiz
 */
public class Element {
	
	private String name;
	private String date;
	private String size;
		
	/**
	 * @param name
	 * @param date
	 * @param size
	 */
	public Element(String name, String date, String size) {
		super();
		this.name = name;
		this.date = date;
		this.size = size;
	}
	
	/**
	 * @return the name
	 */
	public final String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public final void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the size
	 */
	public final String getSize() {
		return size;
	}
	/**
	 * @param size the size to set
	 */
	public final void setSize(String size) {
		this.size = size;
	}
	/**
	 * @return the date
	 */
	public final String getDate() {
		return date;
	}
	/**
	 * @param date the date to set
	 */
	public final void setDate(String date) {
		this.date = date;
	}
	
}
