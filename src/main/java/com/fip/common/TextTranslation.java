package com.fip.common;
// TextTranslation.java



import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 *  Translates the text of config-directory-pair application. 
 *
 * @author Fabien Ipseiz
 */
public enum TextTranslation {
	
	INSTANCE;

	private final Locale locale;
	private final ResourceBundle bundle;

	/**
	 * Sets some default values for the object.
	 */
	private TextTranslation() {
		//locale = Locale.getDefault();
		locale = Locale.ENGLISH;
		bundle = ResourceBundle.getBundle("Translation",locale);
	}

	/**
	 * Returns the reference of the only Text object.
	 * 
	 * @return The only Text instance.
	 */
	public final static TextTranslation getInstance() {
		return INSTANCE;
	}

	/**
	 * Returns the translated string for a certain key.
	 * 
	 * @param key
	 *            The key.
	 * @return Translated string.
	 */
	public String get(String key) {
		try {
			return bundle.getString(key);
		} catch (MissingResourceException e) {
			return key;
		}
	}
}
