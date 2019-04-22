/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.time.LocalDate;
import java.util.function.Function;
import java.util.Scanner;
import java.sql.*;
import dao.GymDao;
import dao.RaidDao;
import dao.UserDao;

/**
 * Application logic class
 */
public class RaidService {
    
    private GymDao gymDao;
    private RaidDao raidDao;
    private UserDao userDao;
    //private User loggedIn;
    
    
    public RaidService(GymDao gymDao, RaidDao raidDao, UserDao userDao) {
        this.gymDao = gymDao;
        this.raidDao = raidDao;
        this.userDao = userDao;
    }
    
    
    /**
    * Create a new gym
    *
    * @param   name of the Gym
    * @param   EX true if the Gym is an EX Gym
    */
    
    public boolean createGym(String name, boolean ex) {
        Gym gym = new Gym(name, ex);
        try {   
            gymDao.create(gym);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
    
    /**
    * Create a new raid
    *
    * @param   level of the raid
    * @param   gym is the raid location
    * @param   hours in string, when raid starts
    * @param   minutes in string, when raid starts
    */
    
    public boolean createRaid(String level, Gym gym, String hours, String minutes) {
        Raid raid = new Raid(level, gym, hours, minutes);
        try {   
            raidDao.create(raid);
        } catch (Exception ex) {
            return false;
        }
        return true;
    }
    
    /**
    * Fetch all raids that the user has attended
    * 
    * @return raids which the user has attended
    */
    
    public List<Raid> getRaided() {
//        if (loggedIn == null) {
//            return new ArrayList<>();
//        }
          
        return raidDao.getAll()
            .stream()
            .filter(t-> t.isRaided() == true)
            .collect(Collectors.toList());
    }
   

    /**
    * Fetch all gyms where the user is eligible for an EX raid from the past 2 weeks
    * 
    * @return EX gyms where the user has raided in the past 2 weeks
    */
    
    //NB: now returning all EX Gyms where the user has raided
    
    public List<Gym> getEXGyms() {
    //        if (loggedIn == null) {
    //            return new ArrayList<>();
    //        }

        LocalDate now = LocalDate.now();
            
            //NB: how to get string name getName()? tried: map and filter but neither works
        return gymDao.getAll()
                    .stream()
                    .filter(t-> t.isEx() == true)       
                    .collect(Collectors.toList());
    }
    
    public String sqlTest() {       
        try {
            Connection connection = DriverManager.getConnection("jdbc:sqlite:testi.db");

            PreparedStatement statement = connection.prepareStatement("SELECT 1");

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                System.out.println("Hei tietokantamaailma!");
            } else {
                System.out.println("Yhteyden muodostaminen epäonnistui.");
            }
        } catch (SQLException e) {
           return e.toString();
        }
        
        return "onnistui";
        
    }
    
    public User addNewUser() {
        Scanner userInput = new Scanner( System.in );
        System.out.println("What's your username?");
        String username = userInput.next().toString();
        
        try {
            userDao.create(username);
            User u = userDao.findByUsername(username);
            return u;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
    
    public User findUser() {
        Scanner userInput = new Scanner( System.in );
        System.out.println("Who are you looking for?");
        String username = userInput.next().toString();
        
        try {
            User u = userDao.findByUsername(username);
            return u;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
    
   
}    