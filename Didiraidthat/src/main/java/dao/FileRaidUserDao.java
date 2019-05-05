package dao;

import domain.Gym;
import domain.Raid;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FileRaidUserDao implements RaidUserDao {
    private Database db;
    private RaidDao raidDao;
    
    public FileRaidUserDao(Database database, RaidDao raidDao) {
        this.db = database;
        this.raidDao = raidDao;
    } 
    
    @Override
    public boolean create(int raidId, int userId) {     
        try {
            Connection conn = db.getConnection();
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO RaidUser "
                    + "(raid_id, user_id) "
                    + "VALUES (?, ?)");
            stmt.setInt(1, raidId);
            stmt.setInt(2, userId);
            stmt.executeUpdate();        
            stmt.close();
            conn.close();

            return true;
        } catch (Exception e) {
            return false;
        }
    }
  
    @Override
    public List<Integer> findUsersRaids(int userId) {
        List usersRaids = new ArrayList<>();
        
        try {
            Connection conn = db.getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT raid_id FROM "
                    + "RaidUser WHERE user_id = ? "
                    + "ORDER BY raid_id DESC");   
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int raidId = rs.getInt("raid_id");            
                usersRaids.add(raidId);          
            }       

            stmt.close();
            rs.close();
            conn.close();
            return usersRaids;
        } catch (Exception e) {
            return null;
        }
    }
    
    @Override
    public List<Gym> findUsersGyms(int userId) {
          return null;
    }
}