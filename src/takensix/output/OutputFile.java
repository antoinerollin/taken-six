package takensix.output;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class OutputFile implements Output {

	private final String FILE_PATH = "log.txt";
	private FileWriter file;
	
	public OutputFile() {
		try {
			File f = new File(FILE_PATH);
			
			if (!f.exists())
				f.createNewFile();
			
			this.file = new FileWriter(f, true);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Write.
	 *
	 * @param s the s
	 */
	public void write(String s){
		try {
			file.write(s);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void close() {
		try {
			this.file.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
