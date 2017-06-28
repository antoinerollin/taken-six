package takensix.output;

/**
 * The Interface Output.
 */
public interface Output {
	
	/**
	 * Write the string into the output.
	 *
	 * @param s the string
	 */
	public void write(String s);
	
	/**
	 * Close the output.
	 */
	public void close();
}
