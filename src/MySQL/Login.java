package MySQL;

import java.sql.*;

public class Login {

	 public boolean authenticate(String username, String password) {
	        boolean isAuthenticated = false;
	        try {
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/rankings", "root", "alabalaportocala1324");

	            String sql = "SELECT * FROM player WHERE username = ? AND password = ?";
	            PreparedStatement stmt = con.prepareStatement(sql);
	            stmt.setString(1, username);
	            stmt.setString(2, password);

	            ResultSet rs = stmt.executeQuery();
	            if (rs.next()) {
	                isAuthenticated = true;
	            }

	            rs.close();
	            stmt.close();
	            con.close();

	        } catch (Exception e) {
	            System.out.println(e);
	        }

	        return isAuthenticated;
	    }
	 public boolean createAccount(String username, String password) {
	        boolean isCreated = false;
	        try {
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/rankings", "root", "alabalaportocala1324");

	            String checkSql = "SELECT * FROM player WHERE username = ?";
	            PreparedStatement checkStmt = con.prepareStatement(checkSql);
	            checkStmt.setString(1, username);

	            ResultSet rs = checkStmt.executeQuery();
	            if (rs.next()) {
	                System.out.println("Username already exists.");
	            } else {
	                String sql = "INSERT INTO player (username, password, score) VALUES (?, ?, 150.05)";
	                PreparedStatement stmt = con.prepareStatement(sql);
	                stmt.setString(1, username);
	                stmt.setString(2, password);

	                int rows = stmt.executeUpdate();
	                if (rows > 0) {
	                    isCreated = true;
	                }

	                stmt.close();
	            }

	            rs.close();
	            checkStmt.close();
	            con.close();

	        } catch (Exception e) {
	            System.out.println(e);
	        }

	        return isCreated;
	    }
	
	 public void clearConsole() {
	        for (int i = 0; i < 50; i++) {
	            System.out.println();
	        }
	    }
}
