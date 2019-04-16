/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import domain.Gym;


/**
 *
 * @author siniliu
 */
public class FileGymDao implements GymDao {
    
    public List<Gym> gyms;
    private String file;

    public FileGymDao(String file) throws Exception {
        gyms = new ArrayList<>();
        this.file = file;
        try {
            Scanner reader = new Scanner(new File(file));
            while (reader.hasNextLine()) {
                String[] parts = reader.nextLine().split(";");
                int id = Integer.parseInt(parts[0]);
                boolean EX = Boolean.parseBoolean(parts[2]);
                Gym gym = new Gym(id, parts[1], EX); //id, name, EX raid
                gyms.add(gym);
            }
        } catch (Exception e) {
            FileWriter writer = new FileWriter(new File(file));
            writer.close();
        }
        
    }
    
    private int generateId() {
        return gyms.size() + 1;
    }
    
    private void save() throws Exception{
        try (FileWriter writer = new FileWriter(new File(file))) {
            for (Gym gym : gyms) {
                writer.write(gym.getId() + ";" + gym.getName() + ";" + gym.isEX() + "\n");
            }
        }
    }   

    @Override
    public Gym create(Gym gym) throws Exception {
        gym.setId(generateId());
        gyms.add(gym);
        save();
        return gym;
    }

    @Override
    public List<Gym> getAll() {
        return gyms;
    }

}
