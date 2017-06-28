package takensix.output;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The Interface Output.
 */
public class OutputCollection {
	
	/** The outputs. */
	private List<Output> outputs;

	/**
	 * Instantiates a new output collection.
	 */
	public OutputCollection() {
		this.outputs = new ArrayList<>();
	}

	public void add(Output... outputs) {
		this.outputs.addAll(Arrays.asList(outputs));
	}
	
	public void close(){
		for (Output o : outputs)
			o.close();
	}
	
	/**
	 * Prints the string.
	 *
	 * @param s the s
	 */
	public void print(String s){
		for (Output o : outputs)
			o.write(s);
	}
	
	/**
	 * Println the line.
	 *
	 * @param s the s
	 */
	public void println(String s){
		for (Output o : outputs)
			o.write(s + "\n");
	}

	/**
	 * Prints only into console.
	 *
	 * @param s the s
	 */
	public void printDebug(String s) {
		for (Output o : outputs)
			if (o instanceof OutputConsole)
				o.write(s);
	}
	
	/**
	 * Prints line only into console.
	 *
	 * @param s the s
	 */
	public void printlnDebug(String s) {
		for (Output o : outputs)
			if (o instanceof OutputConsole)
				o.write(s + "\n");
	}
}
