package co.riverrunners.jaws.rc.workers.util;

/**
 * Some utilities for dealing with parsing CSV reports that will most likely
 * need to be re-used.
 * 
 * @author David Nelson <madhacker@tangouniform.org>
 *
 */
public class CSVUtils {

	/**
	 * Removes single or double quotes from a string if its quoted. For input
	 * "mystr1" output will mystr1 for input 'mystr2' output will be mystr2
	 *
	 * @param String
	 *            value to be unquoted.
	 * @return value unquoted, null if input is null.
	 *
	 */
	public static String unquote(String s) {

		if (s != null
				&& ((s.startsWith("\"") && s.endsWith("\"")) || (s
						.startsWith("'") && s.endsWith("'")))) {

			s = s.substring(1, s.length() - 1);
		}
		return s;
	}

	/**
	 * Does the same as unqoute but for an entire string array.
	 * 
	 * @param s
	 *            String array to process.
	 * @return an unquotesd string array.
	 */
	public static String[] unquote(String[] s) {
		for (int i = 0; i < s.length; i++) {
			s[i] = unquote(s[i]);
		}
		return s;
	}

}
