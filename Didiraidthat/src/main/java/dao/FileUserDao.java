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
import java.sql.*;
import domain.User;

/**
 *
 * @author siniliu
 */
public class FileUserDao implements UserDao {
    
    private Database db;
    
    public FileUserDao(Database database) {
        this.db = database;
    }    
      
    @Override
    public List<User> getAll() throws SQLException{
        ArrayList users = new ArrayList();
        Connection conn = db.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM User");       
        ResultSet rs = stmt.executeQuery();
        
        while (rs.next()) {
            User u = new User(rs.getString("username"), rs.getInt("id"));
            users.add(u);
        }       
        
        stmt.close();
        rs.close();
        conn.close();
        
        //returns list of users > need loop over for usernames + id
        return users;   
    }
    
    @Override
    public User findByUsername(String username) throws SQLException{
        Connection conn = db.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT id FROM User WHERE username = ?");
        stmt.setString(1, username);
        
        ResultSet rs = stmt.executeQuery();
        boolean hasOne = rs.next();
        if (!hasOne) {
            return null;
        }
        
        User user = new User(username, rs.getInt("id"));
        
        stmt.close();
        rs.close();
        conn.close();
        
        return user;
        
//        return users.stream()
//            .filter(u->u.getUsername()
//            .equals(username))
//            .findFirst()
//            .orElse(null);
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
}
