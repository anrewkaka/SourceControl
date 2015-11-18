package utils;

import java.text.Normalizer;
import java.util.regex.Pattern;

public class StringUtils {
	public static boolean isStrValid(String input) {
		if (null == input) {
			return false;
		}

		if ("".equals(input)) {
			return false;
		}

		return true;
	}

	public static String unAccent(String s) {
		String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
		Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");

		return pattern.matcher(temp).replaceAll("").replaceAll("Đ", "D")
				.replace("đ", "d");
	}

	public static boolean isStrEquals(String value, String... strings) {

		for (String string : strings) {
			if (value.equals(string)) {
				return true;
			}
		}
		return false;
	}
}
