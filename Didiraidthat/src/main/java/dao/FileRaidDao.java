/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import domain.Raid;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import domain.Gym;
import java.text.SimpleDateFormat;  
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
//import domain.User;

/**
 *
 * @author siniliu
 */
public class FileRaidDao implements RaidDao {
    
    public List<Raid> raids;
    private String file;
    
    public FileRaidDao(String file, GymDao gyms) throws Exception {
        raids = new ArrayList<>();
        this.file = file;
        try {
            Scanner reader = new Scanner(new File(file));
            while (reader.hasNextLine()) {
                String[] parts = reader.nextLine().split(";");
                int id = Integer.parseInt(parts[0]);
                boolean done = Boolean.parseBoolean(parts[2]);  
                Gym gym = gyms.getAll().stream().filter(g->g.getName().equals(parts[2])).findFirst().orElse(null);
                LocalDate date = LocalDate.parse(parts[3]);
                DateTimeFormatter parser = DateTimeFormatter.ofPattern("hh:mm a");
                LocalTime time = LocalTime.parse(parts[4], parser);

                //Raid: id, level, gym, date, time, boolean raided (need to be changed to arraylist later?)
                Raid raid = new Raid(id, parts[1], gym, date, time, Boolean.parseBoolean(parts[5]));
                raids.add(raid);
            }
        } catch (Exception e) {
            FileWriter writer = new FileWriter(new File(file));
            writer.close();
        }
        
    }

    @Override
    public Raid create(Raid raid) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Raid> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

  
    
    
}
