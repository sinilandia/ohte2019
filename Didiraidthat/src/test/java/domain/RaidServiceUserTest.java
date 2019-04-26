/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import dao.Database;
import dao.GymDao;
import dao.RaidDao;
import dao.UserDao;
import java.sql.*;
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
public class RaidServiceUserTest {
   
    private FakeUserDao userDao;
    private FakeDatabase db;
    //private User loggedIn;
    
    @Before
    public void setUp() throws ClassNotFoundException, SQLException {
        this.db = new FakeDatabase("jdbc:sqlite:raidTest.db");
        this.userDao = new FakeUserDao(this.db);
        userDao.create("eka");
        userDao.create("toka");
    }
   
    @After
    public void tearDown() throws SQLException { 
        Connection conn = db.getConnection();
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM User");      
        stmt.executeUpdate();    
        stmt.close();
        conn.close(); 
    }
    
    @Test
    public void getAllUsersWorksCorrectly() throws SQLException {
        String compare = "Username: eka\t id: 1\n"
                + "Username: toka\t id: 2\n";
                
        String text = "";
        
        List users = userDao.getAll();
        for (int i = 0; i < users.size(); i++) {
            text += users.get(i).toString();
        }
        assertEquals(compare, text);
    }

    @Test
    public void addNewUserWorksCorrectly() throws SQLException {
        User u1 = userDao.create("lilja");
        User u2 = new User("lilja");
        assertTrue(u1.getUsername().equals(u2.getUsername()));
    }
    
    @Test
    public void findUserWorksCorrectly() throws SQLException {
        User u = userDao.findByUsername("eka");
        assertEquals("eka", u.getUsername());
    }
    
    
  
}