import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class Read {
	public static void ReadApi() {
	try {
		URL url = new URL("https://restcountries.com/v3.1/all");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.connect();
		StringBuilder informationString = new StringBuilder();
		int responseCode = conn.getResponseCode();
		if (responseCode != 200) {
			throw new RuntimeException("HttpresponseCode" + responseCode);

		} else {

			Scanner scanner = new Scanner(url.openStream());
			while (scanner.hasNext()) {
				informationString.append(scanner.nextLine());
			}
			scanner.close();
			System.out.println(informationString);
		}
	} catch (Exception e) {
		System.out.println(e);
	}
	
	}
	
	public static void ReadApi1() {
		
		
		
	}
}

