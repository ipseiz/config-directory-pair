package com.fip.common;
// DirectoryPair.java


/**
 * Represents two directories that have to be compared against each other.
 * 
 * @author Fabien Ipseiz
 */
public class DirectoryPair {
	
	/** The source directory. */
	private String srcDir;

	/** The target directory. */
	private String tgtDir;

	/**
	 * Creates objects out of corresponding strings.
	 * 
	 * @param srcDir
	 *            String of the source directory.
	 * @param tgtDir
	 *            String of the target directory.
	 */
	public DirectoryPair(String srcDir, String tgtDir) {
		this.srcDir = srcDir;
		this.tgtDir = tgtDir;
	}

	/**
	 * Returns the source directory.
	 * 
	 * @return Source Directory.
	 */
	public final String getSrc() {
		return srcDir;
	}

	/**
	 * Sets the source directory.
	 * 
	 * @param srcDir
	 *            Source Directory.
	 */
	public final void setSrc(String srcDir) {
		this.srcDir = srcDir;
	}

	/**
	 * Returns the target directory.
	 * 
	 * @return Target Directory.
	 */
	public final String getTgt() {
		return tgtDir;
	}

	/**
	 * Sets the target directory.
	 * 
	 * @param tgtDir
	 *            Target Directory.
	 */
	public final void setTgt(String tgtDir) {
		this.tgtDir = tgtDir;
	}
}
