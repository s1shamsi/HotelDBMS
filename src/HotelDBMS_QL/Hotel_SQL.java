package HotelDBMS_QL;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
public class Hotel_SQL {
	 private static void insertHotel(Connection conn, String name, String location, boolean isActive) throws SQLException {
	        String sql = "INSERT INTO Hotels (hotel_name, hotel_location, created_date,updated_date, is_active) VALUES (?, ?, ?, ?, ?)";

	        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
	            pstmt.setString(1, name);
	            pstmt.setString(2, location);
	            pstmt.setDate(3, new java.sql.Date(System.currentTimeMillis()));
	            pstmt.setDate(4, new java.sql.Date(System.currentTimeMillis()));
	            pstmt.setBoolean(5, isActive);
	            pstmt.executeUpdate();

	            System.out.println("Inserted 1 hotel successfully!");
	        } catch (SQLException e) {
	            System.err.println("Failed to insert hotel: " + e.getMessage());
	        }
	    }
    private static void insertHotels(Connection conn, int count) throws SQLException {
    	String sql = "INSERT INTO Hotels (hotel_name, hotel_location, created_date, updated_date, is_active) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            for (int i = 1; i <= count; i++) {
            	
                pstmt.setString(1, "Hotel " + i);
                pstmt.setString(2, "Location " + i);
                pstmt.setDate(3, new java.sql.Date(System.currentTimeMillis()));
                pstmt.setDate(4, new java.sql.Date(System.currentTimeMillis()));
                pstmt.setBoolean(5, true);
                pstmt.executeUpdate();
            }

            System.out.println("Inserted " + count + " hotels successfully!");
        } catch (SQLException e) {
            System.err.println("Failed to insert hotels: " + e.getMessage());
        }
    }
    
    private static void printHotels(Connection conn, int count) throws SQLException {
        String sql = "SELECT TOP (?) * FROM Hotels";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, count);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("hotel_name");
                String location = rs.getString("hotel_location");
                boolean isActive = rs.getBoolean("is_active");
                System.out.println(id + " | " + name + " | " + location + " | " + isActive);
            }
        } catch (SQLException e) {
            System.err.println("Failed to print hotels: " + e.getMessage());
        }
    }

    private static void updateHotelsActiveStatus(Connection conn, int startId, int endId, boolean isActive) throws SQLException {
        String sql = "UPDATE Hotels SET is_active = ? WHERE id >= ? AND id <= ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setBoolean(1, isActive);
            pstmt.setInt(2, startId);
pstmt.setInt(3, endId);
int rowsUpdated = pstmt.executeUpdate();
System.out.println(rowsUpdated + " hotels updated.");
} catch (SQLException e) {
System.out.println("Error updating hotels active status: " + e.getMessage());
}
}
    private static void printHotelInfoById(Connection conn, int hotelId) throws SQLException {
        String sql = "SELECT * FROM Hotels WHERE id = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, hotelId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                System.out.println("Hotel Information:");
                System.out.println("ID: " + rs.getInt("id"));
                System.out.println("Name: " + rs.getString("hotel_name"));
                System.out.println("Location: " + rs.getString("hotel_location"));
                System.out.println("Created Date: " + rs.getDate("created_date"));
                System.out.println("Updated Date: " + rs.getDate("updated_date"));
                System.out.println("Is Active: " + rs.getBoolean("is_active"));
            } else {
                System.out.println("Hotel with ID " + hotelId + " not found.");
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving hotel information: " + e.getMessage());
        }
    }

		
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 String url = "jdbc:sqlserver://localhost:1433;" +
	             "databaseName=HotelDBMS;" +
	             "encrypt=true;" +
	             "trustServerCertificate=true";
	     String user = "said";
	     String pass = "root";
	     boolean stat = true;
	 	Connection conn = null;

	     try {
	Driver driver = (Driver) Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
	DriverManager.registerDriver(driver);
	         conn = DriverManager.getConnection(url, user, pass);

	         System.out.println("Connected to database successfully.");
	         System.out.println("");
	         System.out.println("");
	            Scanner scanner = new Scanner(System.in);
	            while (true) {
	                System.out.println("\nHello, this Said_JDBC programe please tell me which service you want?");
	                System.out.println("1. Insert Hotels Data");
	                System.out.println("2. Update Active/Non-Active Hotels");
	                System.out.println("3. Show Top Hotels ");
	                System.out.println("4. Show Hotel Details by ID ");
	                System.out.println("5. Exit");

	                int option = 0;
	                System.out.print("Please enter a number: ");
	                String input = scanner.nextLine();
	                try {
	                    option = Integer.parseInt(input);
	                    if ( option > 5) {
	                        System.out.println("Invalid input. Number must be between 1 and 5.");
	                        continue;
	                    }
	                } catch (NumberFormatException e) {
	                    System.out.println("Invalid input! Please enter a valid number.");
	                    continue;
	                }

	                switch (option) {
	                
	                    case 1:
	                        int option1 = 0;
	                        System.out.print("How many hotels: ");
	                        String input1 = scanner.nextLine();
	                        try {
	                            option1 = Integer.parseInt(input1);
	                        } catch (NumberFormatException e) {
	                            System.out.println("Invalid input! Please enter a valid number.");
	                            continue;
	                        }
	                        if (option1 == 1) {
	                            String hotelName = "";
	                            String cityName = "";
	                            boolean active = false;
	                            try {
	                                System.out.print("Enter Hotel name: ");
	                                hotelName = scanner.nextLine();
	                                System.out.print("Enter City name: ");
	                                cityName = scanner.nextLine();
	                                System.out.print("The hotel is active? ");
	                                String input2 = scanner.nextLine();
	                                if (hotelName.isEmpty() || cityName.isEmpty() || input2.isEmpty()) {
	                                    throw new Exception(" fields cannot be empty");
	                                } else if (input2.equalsIgnoreCase("Y") || input2.equalsIgnoreCase("y")) {
	                                    active = true;
	                                } else if (input2.equalsIgnoreCase("N") || input2.equalsIgnoreCase("n")) {
	                                    active = false;
	                                }
	                                insertHotel(conn, hotelName, cityName, active);
	                            } catch (Exception e) {
	                                System.out.println("Invalid input: " + e.getMessage());
	                                continue;
	                            }
	                        }
	               

	    	                else {
	    	                	int count;
	    	                	count = option1;
	    	                    insertHotels(conn, count);
	    	                	
	    	                }

	                        break;
	                    case 2:

	                    	int start = 0;
	                    	int end = 0;
	                    	boolean active1 = false;
	                    	System.out.print("Please enter Hotel Id's that you want make change on them, ID from: ");
	                    	try {
	                    	    String input3 = scanner.nextLine();
	                    	    System.out.print(" To: ");
	                    	    String input4 = scanner.nextLine();
	                    	    start = Integer.parseInt(input3);
	                    	    end = Integer.parseInt(input4);

	                    	    System.out.print("The hotel is active? (Y/N): ");
	                    	    String input5 = scanner.nextLine();

	                    	    if (input3.isEmpty() || input4.isEmpty() || input5.isEmpty()) {
	                    	        throw new Exception(" fields cannot be empty");
	                    	    } else if (input5.equalsIgnoreCase("Y")) {
	                    	        active1 = true;
	                    	    } else if (input5.equalsIgnoreCase("N")) {
	                    	        active1 = false;
	                    	    } else {
	                    	        throw new Exception("Invalid input. The answer should be either Y or N.");
	                    	    }

	                    	    updateHotelsActiveStatus(conn, start, end, active1);
	                    	} catch (Exception e) {
	                    	    System.out.println("Invalid input: " + e.getMessage());
	                    	}

    	  	                  
	                   
	                       
	                        break;
	                    case 3:
	                        int option3 = 0;
	    	                System.out.print("Enter number of Top Hotels you want");
	    	                String input6 = scanner.nextLine();
	    	                try {
	    	                    option3 = Integer.parseInt(input6);
	    	        
	    	                } catch (NumberFormatException e) {
	    	                    System.out.println("Invalid input! Please enter a valid number.");
	    	                }
	                      
	                        printHotels(conn, option3);
	                        break;
	                    case 4:
	                    	   System.out.print("Enter hotel ID to retrieve information: ");
	                    	   int hotelId = scanner.nextInt();
	                    	   scanner.nextLine(); // consume remaining newline character
	                    	   printHotelInfoById(conn, hotelId);
	                    	   break;

	                    case 5:
	                        System.out.println("Thank you for using our system, GoodBye!");
	                        return;
	                }

	                System.out.print("Do you want to perform another operation? (Y/N): ");
	                String answer = scanner.nextLine().trim().toLowerCase();
	                if (!answer.equals("y")) {
	                    System.out.println("Thank you for using our system, GoodBye!");
	                    return;
	                    
	                }
	    
	            }
      
	        
        
	} catch (Exception ex) {
	System.err.println(ex);
	}
     
	}
	

}
