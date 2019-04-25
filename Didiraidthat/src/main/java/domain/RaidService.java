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
    
//    public List<Raid> getRaided() {
////        if (loggedIn == null) {
////            return new ArrayList<>();
////        }
//          
//        return raidDao.getAll()
//            .stream()
//            .filter(t-> t.isRaided() == true)
//            .collect(Collectors.toList());
//    }
   

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
    * @return  created User
    */
    
    public User addNewUser() {
        Scanner userInput = new Scanner( System.in );
        System.out.println("What's your username?");
        String username = userInput.next().toString();
        
        try {
            //add: check for existing User
            userDao.create(username);
            User u = userDao.findByUsername(username);
            return u;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
    
    /**
    * Find a user by username
    * @return  found User or null
    */
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