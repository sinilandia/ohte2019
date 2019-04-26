package domain;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class FakeDatabase {
    private String databaseAddress;
    
    public FakeDatabase(String databaseAddress) {
         this.databaseAddress = databaseAddress;
    }
    
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(databaseAddress);
    }    
    
}
