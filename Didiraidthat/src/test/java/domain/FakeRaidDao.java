/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import dao.Database;
import dao.FileGymDao;
import dao.GymDao;
import dao.RaidDao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;

/**
 *
 * @author siniliu
 */
public class FakeRaidDao {
    private FakeDatabase db;
    FakeGymDao gymDao;
    
    public FakeRaidDao(FakeDatabase database) {
        this.db = database;
        this.gymDao = new FakeGymDao(database);
    }
    
    public boolean create(Raid raid) throws Exception {
        Connection conn = db.getConnection();
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO Raid (gym_id, "
                + "level, date, time ) VALUES (?, ?, ?, ?)");
        stmt.setInt(1, raid.getGym().getId());
        stmt.setString(2, raid.getLevel());
        stmt.setDate(3, Date.valueOf(raid.getDate()));
        stmt.setTime(4, Time.valueOf(raid.getTime()));
        stmt.executeUpdate();        
        stmt.close();
        conn.close();

        return true;
    }

    public List<Raid> getAll() throws SQLException {
        ArrayList raids = new ArrayList();
        Connection conn = db.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Raid order by id");       
        ResultSet rs = stmt.executeQuery();
        
        while (rs.next()) {
            int gymId = rs.getInt("gym_id");
            Gym gym = gymDao.findbyGymId(gymId);
            Raid r = new Raid(rs.getInt("id"), gym, rs.getString("level"), 
                    rs.getDate("date").toLocalDate(), rs.getTime("time").toLocalTime());
            raids.add(r);
        }       
        
        stmt.close();
        rs.close();
        conn.close();
        
        return raids;
    }
}