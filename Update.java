import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;

public class Update {
	public static void updateById() {
		
		String url1 = "jdbc:mysql://localhost:3306/sqltext";
		String username1 = "root";
		String password1 = "root";
		Connection con1 = null;

		try {

			Driver driver = (Driver) Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			DriverManager.registerDriver(driver);
			con1 = DriverManager.getConnection(url1, username1, password1);
			java.sql.Statement st = con1.createStatement();

			Scanner sa1 = new Scanner(System.in);
			System.out.println("PLS Enter any id to Update:");
			int user_input = sa1.nextInt();
			System.out.println("Please Enter the new cca2:");
			String cca2 = sa1.next();
			System.out.println("Please Enter the new ccn3:");
			String ccn3 = sa1.next();
		
			
			String sql1 = "UPDATE BigTable SET cca2='" + cca2 + "',ccn3='" + ccn3+
					 "' WHERE id='" + user_input + "'";

		
			int result = st.executeUpdate(sql1);

		}

		catch (Exception ex) {

			System.err.println(ex);

		}
	
		
	}
	
	
	public static void deleteById() {

		String url = "jdbc:mysql://localhost:3306/sqltext";
		String username = "root";
		String password = "root";
		Connection con = null;
		try {

			Driver driver = (Driver) Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			DriverManager.registerDriver(driver);
			con = DriverManager.getConnection(url, username, password);
			Statement st = con.createStatement();

			Scanner sa = new Scanner(System.in);
			System.out.println("Please Enter the id you want to delet it:");
			String enter = sa.next();
			String sql = "delete from BigTable where id ='" + enter + "'";
			int result = st.executeUpdate(sql);

		}

		catch (Exception ex) {

			System.err.println(ex);

		}

	}
}
