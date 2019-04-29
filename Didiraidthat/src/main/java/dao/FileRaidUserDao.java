package dao;

import domain.Raid;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Time;
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
        //usersId > RaidUser: raid_id > raidDao.findRaidById().collectors collect to list
        
        
        
        
        return null;
    }
}
