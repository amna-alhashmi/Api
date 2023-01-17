import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.Statement;

public class Create {
	public static void createTable() {

		String url = "jdbc:mysql://localhost:3306/SqlText";
		String user = "root";
		String pass = "root";

		String sqlDB = "CREATE TABLE BigTable " + "(id INTEGER not NULL AUTO_INCREMENT,"+"cca2 VARCHAR(100),"
				+ "ccn3 VARCHAR(100)," + "cca3 VARCHAR(100)," + "cioc VARCHAR(100),"+"independent VARCHAR(100),"+
				"status VARCHAR(100),"+"unMember VARCHAR(100),"+"region VARCHAR(100),"
				+"subregion VARCHAR(100),"+"landlocked VARCHAR(100),"+
						"fifa VARCHAR(100),"+"startOfWeek VARCHAR(100),"+" PRIMARY KEY ( id ))";

		Connection conn = null;
		try {
			Driver driver = (Driver) Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			DriverManager.registerDriver(driver);
			conn = DriverManager.getConnection(url, user, pass);
			Statement st = conn.createStatement();
			int m = st.executeUpdate(sqlDB);
			if (m >= 1) {
				System.out.println("Created table in given database...");

			} else {
				System.out.println(" table already Created in given database...");
			}
			conn.close();
		} catch (Exception ex) {
			System.err.println(ex);
		}

	}
}
