package domain;

import dao.Database;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import dao.UserDao;


public class FakeUserDao implements UserDao {
    
    private FakeDatabase db;
    
    public FakeUserDao(FakeDatabase database) {
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
    public User findByUsername(String username){
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
            return null;
        }
    }
      
    @Override
    public List<User> getAll(){
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
            return null;
        }
        
    }
    
}
