package Generate;

public class Utils {

	public static int randomInRange(int min, int max) {
		return (int) Math.floor(Math.random() * (max - min + 1) + min);
	}

	public static String repeat(int count, String with) {
		return new String(new char[count]).replace("\0", with);
	}

}
