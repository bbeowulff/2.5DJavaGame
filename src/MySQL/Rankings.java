package MySQL;

import java.math.RoundingMode;
import java.sql.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Rankings {

    public static class PlayerScore {
        private int id;
        private double score;

        public PlayerScore(int id, double score) {
            this.id = id;
            this.score = score;
        }

        public int getId() {
            return id;
        }

        public double getScore() {
            return score;
        }

        @Override
        public String toString() {
            return "PlayerScore{id=" + id + ", score=" + score + "}";
        }
    }
    public String GetUsername(int playerId) {
        String username = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/rankings", "root", "alabalaportocala1324");
            Statement stmt = con.createStatement();
            String sql = "SELECT username FROM player WHERE id = " + playerId;
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                username = rs.getString("username");
            }
            rs.close();
            stmt.close();
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return username;
    }

    public int GetID(String playerUsername) {
        int ID = -1;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/rankings", "root", "alabalaportocala1324");
            Statement stmt = con.createStatement();
            String sql = "SELECT id FROM player WHERE username = '" + playerUsername + "'";
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                ID = rs.getInt("id");
            }
            rs.close();
            stmt.close();
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return ID;
    }

    public double GetScore(int playerId) {
        double score = 0.0;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/rankings", "root", "alabalaportocala1324");
            Statement stmt = con.createStatement();
            String sql = "SELECT score FROM player WHERE id = " + playerId;
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                score = rs.getDouble("score");
            }
            rs.close();
            stmt.close();
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return score;
    }

    public void UpdateScore(int playerId, double score) {
        try {
        	
        	DecimalFormat df = new DecimalFormat("#.####");
            df.setRoundingMode(RoundingMode.HALF_UP);
            String formattedValue = df.format(score);
        	
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/rankings", "root", "alabalaportocala1324");
            Statement stmt = con.createStatement();
            String sql = "UPDATE player SET score = " + formattedValue + " WHERE id = " + playerId;
            stmt.executeUpdate(sql);
            stmt.close();
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public List<PlayerScore> Get5Scores() {
        List<PlayerScore> top5Scores = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/rankings", "root", "alabalaportocala1324");
            Statement stmt = con.createStatement();
            String sql = "SELECT id, score FROM player ORDER BY score ASC LIMIT 5";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt("id");
                double score = rs.getDouble("score");
                top5Scores.add(new PlayerScore(id, score));
            }

            rs.close();
            stmt.close();
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }

        return top5Scores;
    }
}
