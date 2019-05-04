package dao;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import domain.User;

public class FileUserDao implements UserDao {
    
    private Database db;
    
    public FileUserDao(Database database) {
        this.db = database;
    }  
    
    @Override
    public User create(String username) throws SQLException {       
        Connection conn = db.getConnection();
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO User (username) VALUES (?)");
        stmt.setString(1, username);        
        stmt.executeUpdate();        
        stmt.close();
        conn.close();
        
        User u = findByUsername(username);
        
        return u;
    }     
    
    @Override
    public User findByUsername(String username) {
        try {
            Connection conn = db.getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT id FROM User WHERE username = ?");
            stmt.setString(1, username);

            ResultSet rs = stmt.executeQuery();
            boolean hasOne = rs.next();
            if (!hasOne) {
                return null;
            }

            User user = new User(rs.getInt("id"), username);

            stmt.close();
            rs.close();
            conn.close();            
            return user;
            
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
      
    @Override
    public List<User> getAll() {
        ArrayList users = new ArrayList();
        
        try {
            Connection conn = db.getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM User");       
            ResultSet rs = stmt.executeQuery();
        
            while (rs.next()) {
                User u = new User(rs.getInt("id"), rs.getString("username"));
                users.add(u);
            }       
        
            stmt.close();
            rs.close();
            conn.close();

            return users;  
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
}
