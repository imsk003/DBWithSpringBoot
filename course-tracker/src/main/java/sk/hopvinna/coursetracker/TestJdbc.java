package sk.hopvinna.coursetracker;


import java.sql.DriverManager;

public class TestJdbc {

	public static void main(String[] args) {
		String jdbcUrl = "jdbc:mysql://localhost/course_tracker?useSSL=false";
		String user = "root";
		String pass = "root";
		
		try {
			System.out.println("Connecting to database: " + jdbcUrl);
			
			DriverManager.getConnection(jdbcUrl, user, pass);
			
			System.out.println("Connection successful!!!");
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
