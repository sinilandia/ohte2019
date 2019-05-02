package domain;

import dao.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.time.LocalDate;
import java.util.function.Function;
import java.util.Scanner;
import java.sql.*;

/**
 * Application logic class
 */
public class RaidService {
    
    private Database db;
    private GymDao gymDao;
    private RaidDao raidDao;
    private UserDao userDao;
    private User loggedIn;
    
    /**
     *
     * @param gymDao
     * @param raidDao
     * @param userDao
     */
    
    public RaidService() {
        try {     
            this.db = new Database("jdbc:sqlite:raid.db");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RaidService.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.gymDao = new FileGymDao(db);
        this.raidDao = new FileRaidDao(db);
        this.userDao = new FileUserDao(db);
        this.loggedIn = null;
    }   
    
    
    /**
    * Create a new gym
    *
    * @param   name of the Gym
    * @param   ex true if the Gym is an EX Gym
    * 
    * @return  created Gym
    */
    
    public Gym createGym(String name, boolean ex) {
        Gym gym = new Gym(name, ex);
        try {   
            Gym g = gymDao.create(gym);
            return g;
        } catch (Exception e) {
            return null;
        }
    }
    
    /**
    * Create a new raid
    *
    * @param   gymName is the name of Gym where raid will take place
    * @param   level of the raid
    * @param   hours in string, when raid starts
    * @param   minutes in string, when raid starts
    * 
    * @return true if Raid was created
    */
    
    public boolean createRaid(String gymName, String level, String hours, String minutes) {       
        try {
            Gym gym = gymDao.findByGymName(gymName);
            Raid raid = new Raid(gym, level, hours, minutes);
            raidDao.create(raid);
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }       
        return true;
        
        
//        Raid raid = new Raid(gym, level, hours, minutes);
//        try {   
//            raidDao.create(raid);
//        } catch (Exception ex) {
//            return false;
//        }
//        return true;
    }
    
    /**
    * Fetch all raids that the user has attended
    * 
    * @return raids which the user has attended
    */
    
    public List<Raid> getRaided() {
        if (loggedIn == null) {
            return new ArrayList<>();
        }
          
        try {
            return raidDao.getAll(); //returns all raids when it should return user's raids
        } catch (SQLException ex) {
            Logger.getLogger(RaidService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
   

    /**
    * Atm fetches all gyms, future: fetches all gyms where the user is eligible for an EX Raid
    * 
    * @return EX gyms where the user has raided in the past 2 weeks
    */
    
    //NB: now returning all EX Gyms where the user has raided
    
    public String getAllGyms() {
    //        if (loggedIn == null) {
    //            return new ArrayList<>();
    //        }

        //LocalDate now = LocalDate.now();
            
        String text = "";
        
        try {
            List gyms = gymDao.getAll();
            for (int i = 0; i < gyms.size(); i++) {               
                text += gyms.get(i).toString();
            }
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
              
        return text;
    }
    
    /**
    * Create a new User
    * @param username as string
    * @return  true, if successful
    */
    
    public boolean addNewUser(String username) { 
        try {
            //add: check for existing User
            userDao.create(username);
            User u = userDao.findByUsername(username);
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }
    
    /**
    * Find a user by username
    * @param username as String
    * @return  true if user exists
    */
    public boolean login(String username) {       
        User u = userDao.findByUsername(username);
        this.loggedIn = u;        
        if (loggedIn == null) {
            return false;
        }
        return true;
    }
    
    /**
    * Find all Users 
    * @return  found Users in string or null
    */
    
    public String getAllUsers() {
        String text = "All the users: \n";
        
        try {
            List users = userDao.getAll();
            for (int i = 0; i < users.size(); i++) {
                text += users.get(i).toString();
            }           
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
        return text;
        
    }
    
    /**
    * Get gym with its name, case sensitive maybe?
    * @param gymName name of the gym in String
    * @return Gym object
    */
    public Gym getGym(String gymName) {
        try {
            Gym gym = gymDao.findByGymName(gymName);
            return gym;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
    
   
}    