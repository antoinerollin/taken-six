package takensix.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Reader {
	static BufferedReader scan;
	
	static {
		scan = new BufferedReader(new InputStreamReader(System.in));	
	}
	
	public static final int nextInt(){
		int selected = -1;
		try {
			selected = Integer.parseInt(scan.readLine());
		} catch (NumberFormatException | IOException e) {
		}
		return selected;
	}
	
	public static final void enter() {
		Printer.pressEnter();
		try {
			scan.readLine();
		} catch (IOException e) {
		}
	}
}
