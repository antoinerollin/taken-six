package takensix.output;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import takensix.utils.Clock;
import takensix.utils.StringMaker;

public class OutputFile implements Output {
	private final String FILE_PATH = "log";
	private final String FILE_NAME = "log.txt";
	
	private FileWriter file;
	
	public OutputFile() {
		String date = Clock.getFormattedDateNow().replaceAll(":", "-");
		try {
			File folder = new File(FILE_PATH);
			
			if(!folder.exists())
				folder.mkdir();
			
			File f = new File(FILE_PATH + "/" + date + "_" + FILE_NAME);
			
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
		String date = "[" + Clock.getFormattedDateNow() + "] ";
		
		s = date + s.trim();
		s = s.replace("\n", "\n"+date);
		s = StringMaker.removeLast(s, "\n"+date);
		s += "\n";
		
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
