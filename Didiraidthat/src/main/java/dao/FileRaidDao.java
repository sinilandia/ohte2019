package dao;

import domain.Raid;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import domain.Gym;
import java.sql.*;
import java.text.SimpleDateFormat;  
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class FileRaidDao implements RaidDao {
    private Database db;
    private GymDao gymDao;
    
    public FileRaidDao(Database database) {
        this.db = database;
        this.gymDao = new FileGymDao(database);
    } 
    
    @Override
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
       
    @Override
    public Raid findRaidById(int raidId) {
        try {
            Connection conn = db.getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Raid WHERE id = ?"); 
            stmt.setInt(1, raidId);
            ResultSet rs = stmt.executeQuery();
            
            int gymId = rs.getInt("gym_id");
            Gym gym = gymDao.findbyGymId(gymId);

            Raid raid = new Raid(rs.getInt("id"), gym, rs.getString("level"), 
                        rs.getDate("date").toLocalDate(), rs.getTime("time").toLocalTime());
            stmt.close();
            rs.close();
            conn.close();

            return raid;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Raid> getAll() {
        ArrayList raids = new ArrayList();
        
        try {
            Connection conn = db.getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Raid "
                    + "ORDER BY id DESC");       
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
        } catch (Exception e) {           
        }      
        return raids;
    }
    
    public List<Raid> findActiveRaids() {
        List<Raid> activeRaids = new ArrayList<>();
        List<Raid> allRaids = getAll();
        
        for (int i = 0; i < allRaids.size(); i++) {
            Raid raid = allRaids.get(i);
            int compare = raid.getTime().compareTo(LocalTime.now());                    
            if (raid.getDate().equals(LocalDate.now()) && compare >= 0) {
                activeRaids.add(raid);
            }
        }
        
        return activeRaids;
    }
}