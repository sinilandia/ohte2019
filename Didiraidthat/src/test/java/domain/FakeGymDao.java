package domain;

import dao.Database;
import dao.GymDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author siniliu
 */
public class FakeGymDao {
    
    private FakeDatabase db; 
    
    public FakeGymDao(FakeDatabase database) {
        this.db = database;
    }
    
    public Gym create(Gym gym) throws SQLException {
        Connection conn = db.getConnection();
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO Gym (name, ex) VALUES (?, ?)");
        stmt.setString(1, gym.getName());
        stmt.setBoolean(2, gym.isEx());
        stmt.executeUpdate();        
        stmt.close();
        conn.close();
        
        Gym g = findByGymName(gym.getName());
        
        return g;
    }     
    
    public Gym findByGymName(String name) throws SQLException {
        Connection conn = db.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Gym WHERE name = ?");
        stmt.setString(1, name);
        
        ResultSet rs = stmt.executeQuery();
        boolean hasOne = rs.next();
        if (!hasOne) {
            return null;
        }
        
        Gym g = new Gym(rs.getInt("id"), rs.getString("name"), rs.getBoolean("ex"));
        
        stmt.close();
        rs.close();
        conn.close();
        
        return g;
    }
    
    public Gym findbyGymId(int id) throws SQLException {
        Connection conn = db.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Gym WHERE id = ?");
        stmt.setInt(1, id);
        
        ResultSet rs = stmt.executeQuery();
        boolean hasOne = rs.next();
        if (!hasOne) {
            return null;
        }
        
        Gym g = new Gym(rs.getInt("id"), rs.getString("name"), rs.getBoolean("ex"));
        
        stmt.close();
        rs.close();
        conn.close();
        
        return g;
    }
    
    public List<Gym> getAll() throws SQLException{
        ArrayList gyms = new ArrayList();
        Connection conn = db.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Gym");       
        ResultSet rs = stmt.executeQuery();
        
        while (rs.next()) {
            Gym g = new Gym(rs.getInt("id"), rs.getString("name"), rs.getBoolean("ex"));
            gyms.add(g);
        }       
        
        stmt.close();
        rs.close();
        conn.close();

        return gyms;   
    }
     
}