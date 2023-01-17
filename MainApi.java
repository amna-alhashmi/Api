import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class MainApi {

	public static void main(String[] args) {
		Scanner sa = new Scanner(System.in);
		Boolean Exit=true;
		
		while(Exit) {
			System.out.println("PLS CHOOSE ONE OPTION:");
			System.out.println("1.read the API");
			System.out.println("2.create table Api");
			System.out.println("3.Isert Api");
			System.out.println("4.read Api");
			System.out.println("5.update Api");
			System.out.println("6.delete Api");

			String a = sa.next();
			int option = Integer.parseInt(a);
			switch (option) {

			case 1:
				Read read=new Read();
				read.ReadApi();
				
				break;
				
			case 2:
				Create create=new Create();
				create.createTable();
				
				
				break;
				
			case 3:
				try {
					String url = "jdbc:mysql://localhost:3306/sqltext";
					String user = "root";
					String pass = "root";
					
					HttpClient client = HttpClient.newHttpClient();
					HttpRequest request = HttpRequest.newBuilder()
							.uri(URI.create("https://restcountries.com/v3.1/all")).build();
					HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
					String uglyJsonString = response.body();
//						System.out.println(uglyJsonString);
					Gson gson = new GsonBuilder().setPrettyPrinting().create();
					JsonParser jp = new JsonParser();
					JsonElement je = jp.parse(uglyJsonString);
					String prettyJsonString = gson.toJson(je);
					System.out.println(prettyJsonString);
					BigApi [] api=gson.fromJson(prettyJsonString,BigApi[].class);
					for(BigApi api1:api) {
						
//						String tld= api1.getTld()[0];
						String cca2=api1.getCca2();
						String ccn3=api1.getCcn3();
						String cca3=api1.getCca3();
						String cioc=api1.getCioc();
						String independent=api1.getIndependent();
						String status=api1.getStatus();
						String unMember=api1.getUnMember();
//						String capital=api1.getCapital()[0];
//						String altSpellings=api1.getAltSpellings()[0];
						String region=api1.getRegion();
						String subregion=api1.getSubregion();
//						String latlng=api1.getLatlng()[0];
						String landlocked=api1.getLandlocked();

//						String flag=api1.getFlag();
//						Integer population=api1.getPopulation();
						String fifa=api1.getFifa();
//						String timezones=api1.getTimezones()[0];
//						String continents=api1.getContinents()[0];
						String startOfWeek=api1.getStartOfWeek();


						
						  String sql = "insert into BigTable (cca2, ccn3, cca3, cioc, independent, status, unMember, region, subregion, landlocked,fifa, startOfWeek)"
					               
								  + " values('" + cca2 + "', '" + ccn3
									+ "','" + cca3 + "' ,'" +cioc +"' , '"+independent+"','"
								  +status+"','"+unMember+"','"
									+region+"','"+subregion+"','"+landlocked+
									"','"+fifa+"','"+startOfWeek+"')";

						Connection conn = null;

						Driver driver = (Driver) Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
						DriverManager.registerDriver(driver);
						conn = DriverManager.getConnection(url, user, pass);
						java.sql.Statement st1 = conn.createStatement();
						int m = st1.executeUpdate(sql);
						if (m >= 1) {
							System.out.println("Created table in given database...");

						} else {
							System.out.println(" table already Created in given database...");
						}
						conn.close();
					}
				} catch (Exception ex) {
					System.err.println(ex);
				}	
				
				break;
			case 4:
				String url = "jdbc:mysql://localhost:3306/sqltext";
				String username = "root";
				String password = "root";

				String sql = "SELECT * FROM BigTable";
				// Connection, Driver, DriverRegister lines will be exactly same

				java.sql.Connection conn = null;
				try {
					Driver driver = (Driver) Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
					DriverManager.registerDriver(driver);
					conn = DriverManager.getConnection(url, username, password);
					java.sql.Statement st = conn.createStatement();
					ResultSet resultSet = st.executeQuery(sql);
					int count = 1;
					while (resultSet.next()) {
						
						String cca2 = resultSet.getString("cca2");
						String ccn3 = resultSet.getString("ccn3");
						String cca3 = resultSet.getString("cca3");
						String cioc = resultSet.getString("cioc");
						String independent = resultSet.getString("independent");
						String status = resultSet.getString("status");
						String unMember = resultSet.getString("unMember");
						String region = resultSet.getString("region");
						String subregion = resultSet.getString("subregion");
						String landlocked = resultSet.getString("landlocked");
						String fifa = resultSet.getString("fifa");
						String startOfWeek = resultSet.getString("startOfWeek");

						
						
						
						
						System.out.println(cca2 + " " + ccn3 + " " + cca3 + " "
								+ cioc + " " + independent+" "+status+" "+unMember+" "+region+" "+subregion+" "+landlocked+" "+fifa+" "+startOfWeek);
						count++;
					}

					conn.close();
				} catch (Exception ex) {
					System.err.println(ex);
				}

				break;
			case 5:
				Update update=new Update();
				update.updateById();
		
				break;
				
			case 6:
				Update delete=new Update();
				delete.deleteById();
				
				
				
				
				
				break;
	}

}
		Exit=false;
}
}