package common;

public class Utility {
	
	public static boolean isSymbolDecimal(String str) {
		try {
			Integer.valueOf(str);
			return true;
		}
		catch(NumberFormatException e) {
			return false;
		}
	}
}
