package takensix.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * The Class Constants.
 */
public abstract class Clock {
	static SimpleDateFormat dateFormatter;

	private final static String H_DELIMITER = "***";
	private final static String M_DELIMITER = "+++";
	private final static String S_DELIMITER = "---";

	static {
		dateFormatter = new SimpleDateFormat("dd-MM-yyyy_HH" + H_DELIMITER + "mm" + M_DELIMITER + "ss" + S_DELIMITER);
	}

	public static String getFormattedDateNow() {
		return arrange(dateFormatter.format(Calendar.getInstance().getTime()));
	}

	public static String formatDate(Date date) {
		return arrange(dateFormatter.format(date));
	}

	public static Date getDate() {
		return Calendar.getInstance().getTime();
	}

	private static String arrange(String s) {
		return s.replace(H_DELIMITER, "h").replace(M_DELIMITER, "m").replace(S_DELIMITER, "s");
	}
}
