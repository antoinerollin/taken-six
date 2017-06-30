package takensix.output;

import takensix.utils.StringMaker;

public class OutputConsole implements Output {
	
	/**
	 * Write.
	 *
	 * @param s the s
	 */
	public void write(String s){
		if (s.trim().endsWith(StringMaker.CONSOLE_INPUT_SYMBOL))
			System.out.print(s);
		else
			System.out.println(s);
	}

	@Override
	public void close() {
	}
}
