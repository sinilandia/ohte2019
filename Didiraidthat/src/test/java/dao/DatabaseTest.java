/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import static org.junit.Assert.*;
import java.sql.*;

public class DatabaseTest {
    private String databaseAddress;
    
    public DatabaseTest(String databaseAddress) {
        this.databaseAddress = databaseAddress;       
    }

   public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(databaseAddress);
    }
}
