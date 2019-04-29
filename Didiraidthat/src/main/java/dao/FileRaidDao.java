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
        
    
    
//    public List<Raid> raids;
//    private String file;
//    
//    public FileRaidDao(String file, GymDao gyms) throws Exception {
//        raids = new ArrayList<>();
//        this.file = file;
//        try {
//            Scanner reader = new Scanner(new File(file));
//            while (reader.hasNextLine()) {
//                String[] parts = reader.nextLine().split(";");
//                int id = Integer.parseInt(parts[0]);
//                Gym gym = gyms.getAll().stream().filter(g->g.getName().equals(parts[2])).findFirst().orElse(null);
//                LocalDate date = LocalDate.parse(parts[3]);
//                DateTimeFormatter parser = DateTimeFormatter.ofPattern("HH:mm");
//                LocalTime time = LocalTime.parse(parts[4], parser);
//
//                //Raid: id, level, gym, date, time, boolean raided (need to be changed to arraylist later?)
//                Raid raid = new Raid(id, gym, parts[1], date, time, Boolean.parseBoolean(parts[5]));
//                raids.add(raid);
//            }
//        } catch (Exception e) {
//            FileWriter writer = new FileWriter(new File(file));
//            writer.close();
//        }
//        
//    }
//
//    private int generateId() {
//        return raids.size() + 1;
//    }
//    
//    private void save() throws Exception {
//        try (FileWriter writer = new FileWriter(new File(file))) {
//            for (Raid raid : raids) {
//                writer.write(raid.getId() + ";" + raid.getLevel() + ";" + raid.getDate() 
//                         + ";" + raid.getTime() + ";" + raid.isRaided() + "\n");
//            }
//        }
//    } 
//    
//    @Override
//    public Raid create(Raid raid) throws Exception {
//        raid.setId(generateId());
//        raids.add(raid);
//        save();
//        return raid;    
//    }
//
//    @Override
//    public List<Raid> getAll() {
//        return raids;
//    }