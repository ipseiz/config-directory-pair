// Formatter.java
package com.fip.common;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.DateFormat;
import java.util.Date;

/**
 * Provides methods in order to format numbers.
 *
 * @author Fabien Ipseiz
 */
public class Formatter {
	
	/**
	 * Returns the length of the file as a string if the File is not a
	 * directory and empty string otherwise.
	 * 
	 * @param file
	 *            The file to get the formatted length from.
	 * @return Length of the file as a string.
	 * @throws IOException 
	 */
	public final static String getLength(Path file) throws IOException {
		if (!Files.isDirectory(file))
			return "";

		return String.valueOf(Files.size(file));
	}

	/**
	 * Returns the formatted length.
	 * 
	 * @param length
	 *            The length in number of bytes.
	 * @return Formatted length.
	 */
	public final static String getLength(long length) {
		if (length < 1000) {
			return length + " b";
		} else if (length / 1024 < 1000) {
			return ((double) (length * 10 / 1024) / 10) + " KB";
		} else {
			return ((double) (length * 100 / 1048576) / 100) + " MB";
		}
	}

	/**
	 * Returns a date string for the last modification of the file.
	 * 
	 * @return Time of last modification of the file as a date string.
	 * @throws IOException 
	 */
	public final static String getLastModified(Path file) throws IOException {
		if (!Files.isDirectory(file))
			return "";

		DateFormat df = DateFormat.getDateTimeInstance(DateFormat.SHORT,
				DateFormat.SHORT);
		//return df.format(Date.parse(Files.getLastModifiedTime(file).toString()));
		return df.format(Files.getLastModifiedTime(file));
	}

	/**
	 * Replaces all separator chars by the system specific ones.
	 * 
	 * @param path
	 *            The original path.
	 * @return The modified path.
	 */
	public final static String replaceSeparatorChar(String path) {
		path = path.replace('\\', File.separatorChar);
		path = path.replace('/', File.separatorChar);

		return path;
	}

	/**
	 * Cuts the head of a string if its length exceeds the specified maximum
	 * length. If the input string has be cut, "..." is added to its head.
	 * However, its length never exceeds the maximum length.
	 * 
	 * @param in
	 *            The input string. If the is is equal to null, an empty string
	 *            is returned instead.
	 * @param max
	 *            The maximum length.
	 * @return The modified string.
	 */
	public final static String cutHead(String in, int max) {
		if (in == null || max == 0)
			return "";
		if (max == 1)
			return ".";
		if (max == 2)
			return "..";

		// At this point max is >= 3:
		int start = in.length() - (max - 3);

		if (start > 0) {
			in = "..." + in.substring(start);
		}

		return in;
	}

	/**
	 * Adapts the path of a File according to the maximum number of
	 * displayable characters. If the file is null, a string containing spaces
	 * is returned.
	 * 
	 * @see #adapt(String, int)
	 * @param in
	 *            The input file.
	 * @param max
	 *            The maximum length.
	 * @return The modified string of length max.
	 */
	public final static String adaptPath(File in, int max) {
		if (in != null) {
			return adapt(in.getPath(), max);
		} else {
			return adapt(null, max);
		}
	}

	/**
	 * Adapts a string according to the maximum number of displayable
	 * characters. If the original string has to many chars, the trailing chars
	 * are cut, if it has to few chars, spaces are added. Finally the output
	 * string is set in quotations. If the input string is null, a string of
	 * spaces is returned.
	 * 
	 * @param in
	 *            The input string.
	 * @param max
	 *            The maximum length.
	 * @return The modified string of length max.
	 */
	public  final static String adapt(String in, int max) {
		if (in == null) {
			char[] spaces = new char[max];
			for (int i = 0; i < max; i++)
				spaces[i] = ' ';
			return new String(spaces);
		}

		int length = in.length();
		String out;

		if (length < max - 2) {
			char[] spaces = new char[max - 2 - length];

			for (int i = 0; i < max - 2 - length; i++)
				spaces[i] = ' ';

			out = "'" + in + "'" + new String(spaces);
		} else if (length > max - 2) {
			out = "'" + in.substring(length - max + 2) + "'";
		} else {
			out = "'" + in + "'";
		}

		return out;
	}
}
