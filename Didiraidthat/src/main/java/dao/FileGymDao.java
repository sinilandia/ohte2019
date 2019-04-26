/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import domain.Gym;
import domain.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 *
 * @author siniliu
 */
public class FileGymDao implements GymDao {
    
    private Database db;
    
    public FileGymDao(Database database) {
        this.db = database;
    }  
    
    @Override
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
    
    @Override
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
    
    @Override
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
      
    @Override
    public List<Gym> getAll() throws SQLException {
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
