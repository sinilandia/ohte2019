package dao;

import domain.Gym;
import domain.Raid;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FileRaidUserDao {
    private Database db;
    private RaidDao raidDao;
    private UserDao userDao;
    
    public FileRaidUserDao(Database database, RaidDao raidDao, UserDao userDao) {
        this.db = database;
        this.userDao = userDao;
        this.raidDao = raidDao;
    } 
    
    public boolean create(int raidId, int userId) throws Exception {
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
    }
  
    public List<Raid> findUsersRaids(int userId) throws Exception {
        List<Raid> usersRaids = new ArrayList();
        
        Connection conn = db.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT raid_id FROM RaidUser WHERE user_id = ?");   
        stmt.setInt(1, userId);
        ResultSet rs = stmt.executeQuery();
        
        while (rs.next()) {
            int raidId = rs.getInt("raid_id");
            Raid raid = raidDao.findRaidById(raidId);
            usersRaids.add(raid);          
        }       
        
        stmt.close();
        rs.close();
        conn.close();
        
        return usersRaids;
    }
    
    public List<Gym> findUsersGyms(int userId) throws Exception {
        
        List<Gym> usersGyms = new ArrayList();
        List<Raid> usersRaids = findUsersRaids(userId);
        
        for (int i = 0; i < usersRaids.size(); i++) {
            Gym gym = usersRaids.get(i).getGym();
            usersGyms.add(gym);
        }
        
        return usersGyms;
    }
}