package takensix.output;

public class OutputConsole implements Output {
	
	/**
	 * Write.
	 *
	 * @param s the s
	 */
	public void write(String s){
		System.out.println(s);
	}

	@Override
	public void close() {
	}
}
