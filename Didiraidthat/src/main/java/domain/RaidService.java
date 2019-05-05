package domain;

import dao.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Application logic class
 */
public class RaidService {
    
    private Database db;
    private GymDao gymDao;
    private FileRaidDao raidDao;
    private UserDao userDao;
    private RaidUserDao raidUserDao;
    private User loggedIn;
    
    public RaidService() {
        try {     
            this.db = new Database("jdbc:sqlite:raid.db");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RaidService.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.gymDao = new FileGymDao(db);
        this.raidDao = new FileRaidDao(db);
        this.userDao = new FileUserDao(db);
        this.raidUserDao = new FileRaidUserDao(db, raidDao);
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
            g = gymDao.findByGymName(name);
            return g; 
        } catch (Exception e) {
            return null;
        }
    }
    
    /**
    * Create a new raid
    *
    * @param   gymName is the name of Gym where raid will take place
    * @param   ex tells whether the gym is an EX gym or not 
    * @param   level of the raid
    * @param   hours in string, when raid starts
    * @param   minutes in string, when raid starts
    * 
    * @return true if Raid was created
    */
    
    public boolean createRaid(String gymName, String ex, String level, String hours, String minutes) {       
        boolean exGym = true;
        try {  
            Gym gym = gymDao.findByGymName(gymName);
            if (gym==null) {
                if (ex.equals("No")) {
                    exGym = false;
                } 
                gym = createGym(gymName, exGym);
            } 
            Raid raid = new Raid(gym, level, hours, minutes);
            raidDao.create(raid);
        } catch (Exception e) {
            return false;
        }       
        return true;
    }
    
    /**
    * Fetch all raids
    * 
    * @return all raids in the database 
    */
    
    public List<Raid> getRaided() {
        if (loggedIn == null) {
            return new ArrayList<>();
        }
          
        try {
            return raidDao.getAll(); 
        } catch (Exception e) {
        }
        return null;
    }
   

    /**
    * Fetches all gyms
    * 
    * @return all gyms
    */
    
    public String getAllGyms() {
        String text = "";
        
        try {
            List gyms = gymDao.getAll();
            for (int i = 0; i < gyms.size(); i++) {               
                text += gyms.get(i).toString();
            }
        } catch (Exception e) {
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
        if (u == null) {
            return false;
        }
        loggedIn = u; 
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
    * Get gym with its name
    * @param gymName name of the gym in String
    * @return Gym object
    */
    public boolean getGym(String gymName) {
        try {
            Gym gym = gymDao.findByGymName(gymName);
            if (gym.equals(null)){
                return false;
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }
    
    /**
    * Get user's raids with user id
    * @return list of user's raids
    */
    public List<Raid> findUsersRaids() {
        List<Integer> usersRaidsId = raidUserDao.findUsersRaids(loggedIn.getId());
        
        List<Raid> usersRaids = new ArrayList<>();
        for (int i = 0; i < usersRaidsId.size(); i++) {
            int raidId = usersRaidsId.get(i);
            Raid raid = raidDao.findRaidById(raidId);
            usersRaids.add(raid);           
        }
        
        return usersRaids;
    }
    
    public boolean signUpForRaid(int raidId) {
        if (raidUserDao.create(raidId, loggedIn.getId())) {
            return true;
        }
        return false;
    }

    /**
    * Find active raids that the user can sign up to
    * @return list of upcoming raids where starting time is later today
    */
    public List<Raid> findUpcomingRaids() {
        List<Raid> upcomingRaids = raidDao.findActiveRaids(); 
        return upcomingRaids;
    }
}    