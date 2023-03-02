package SQL_Task_JDBC;

import java.sql.*;

public class CreateTable {
	
	  public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
	      String url = "jdbc:sqlserver://localhost:1433;" +
	               "databaseName=HotelDBMS;" +
	               "encrypt=true;" +
	               "trustServerCertificate=true";
	      String user = "said";
	      String pass = "root";
	      Connection conn = null;
	      try  {
	Driver driver = (Driver) Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
	DriverManager.registerDriver(driver);
	            conn = DriverManager.getConnection(url, user, pass);
	 Statement st = conn.createStatement();
	         String createHotelsTable = "CREATE TABLE Hotels ("
	                  + "id INTEGER PRIMARY KEY IDENTITY,"
	                  + "hotel_name VARCHAR(255) NOT NULL,"
	                  + "hotel_location VARCHAR(255),"
	                  + "created_date DATE NOT NULL,"
	                  + "updated_date DATE,"
	                  + "is_active TINYINT NOT NULL);";
	         st.executeUpdate(createHotelsTable);
	         String createRoomTypeTable = "CREATE TABLE Room_Type ("
	                     + "id INT PRIMARY KEY,"
	                     + "room_type_name VARCHAR(255) NOT NULL,"
	                     + "created_date DATE,"
	                     + "updated_date DATE,"
	                     + "is_active TINYINT NOT NULL);";
	         st.executeUpdate(createRoomTypeTable);
	         String createRoomsTable = "CREATE TABLE Rooms ("
	                  + "id INT PRIMARY KEY,"
	                  + "room_type_id INT REFERENCES Room_Type(id),"
	                  + "hotel_id INT REFERENCES Hotels(id),"
	                  + "created_date DATE NOT NULL,"
	                  + "updated_date DATE,"
	                  + "is_active TINYINT NOT NULL);";
	         st.executeUpdate(createRoomsTable);
	         String createGuestsTable = "CREATE TABLE Guests ("
	                   + "id INT PRIMARY KEY,"
	                   + "guest_name VARCHAR(255) NOT NULL,"
	                   + "guest_phone VARCHAR(255) NOT NULL,"
	                   + "guest_accompanying_members INT NOT NULL,"
	                   + "guest_payment_amount INT NOT NULL,"
	                   + "room_id INT REFERENCES Rooms(id),"
	                   + "hotel_id INT REFERENCES Hotels(id),"
	                   + "created_date DATE NOT NULL,"
	                   + "updated_date DATE,"
	                   + "is_active TINYINT NOT NULL);";
	         st.executeUpdate(createGuestsTable);
	         String createEmployeeTypeTable = "CREATE TABLE Employee_Type ("
	                         + "id INT PRIMARY KEY,"
	                         + "employee_type_name VARCHAR(255) NOT NULL,"
	                         + "created_date DATE NOT NULL,"
	                         + "updated_date DATE,"
	                         + "is_active TINYINT NOT NULL);";
	         st.executeUpdate(createEmployeeTypeTable);
	         String createEmployeesTable = "CREATE TABLE Employees ("
	                      + "id INT PRIMARY KEY,"
	                      + "employee_type_id INT REFERENCES Employee_Type(id),"
	                      + "room_id INT REFERENCES Rooms(id),"
	                      + "created_date DATE NOT NULL,"
	                      + "updated_date DATE,"
	                      + "is_active TINYINT NOT NULL);";
	         st.executeUpdate(createEmployeesTable);
	         System.out.println("Tables created successfully.");
	      } catch (SQLException e) {
	         System.out.println(e.getMessage());
	      }
	   }
}
