package ui;

import dao.*;
import domain.*;
//import domain.User;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author siniliu
 */
public class Raidui {
    
    /**
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {

      //UI
      Scanner userInput = new Scanner( System.in );
      userInput.useDelimiter(System.getProperty("line.separator"));
      String reply = "";
      
      while (!reply.equalsIgnoreCase("x")){
        System.out.println("Menu:\n" + 
                "1. Add new user. \n"+
                "2. Find user. \n"+
                "3. Find all users. \n" +
                "4. View all gyms. \n"+
                "5. Add a gym. \n" +
                "6. Add a new raid. \n" +
                "7. Sign up for a raid. \n" +
                "8. Find all raids. \n" +
                "Type in x to quit.\n"+
                "What is your choice?"
        );  //missing find a gym by name -method missing from RaidService
        
        //create RaidService 
        String gymFile = "gymFile";
        String raidFile = "raidFile";
        Database database = new Database("jdbc:sqlite:raid.db");
        FileGymDao gymDao = new FileGymDao(database);
        FileRaidDao raidDao = new FileRaidDao(database);       
        FileUserDao userDao = new FileUserDao(database);
        RaidService service = new RaidService(gymDao, raidDao, userDao);

        reply = userInput.next();

        //add new user, 1
        if (reply.equalsIgnoreCase("1")) {
            System.out.println(service.addNewUser().toString());
        }
        
        //find user, 2
        if (reply.equalsIgnoreCase("2")) {
            System.out.println(service.findUser().toString());
        }
        
        //find all users in a list, 3
        if (reply.equalsIgnoreCase("3")) {
            System.out.println(service.getAllUsers());
        }
        
        //find all Gyms, 4
        if (reply.equalsIgnoreCase("4")){
            System.out.println(service.getAllGyms());
        }
        
        //add a new gym, 5
        if (reply.equalsIgnoreCase("5")){
            System.out.println("Name of the gym?");
            String name = userInput.next(); //spaces cut the word - need to fix
            System.out.println("Is the gym an EX gym? Yes/No");
            String ex = userInput.next();
            boolean exGym = false;
            if (ex.equalsIgnoreCase("yes")) {
                exGym = true;
            }
            System.out.println("Added new gym: \n" + service.createGym(name, exGym).toString());           
        }  
        
        //add a new raid, 6
        if (reply.equalsIgnoreCase("6")){
            System.out.println("Name of the gym?");
            String gymName = userInput.next(); 
            System.out.println("Raid level? 1-5 or EX");
            String level = userInput.next();
            System.out.println("Start time in hours? HH");
            String timeHours = userInput.next();
            System.out.println("Start time in minutes? mm");
            String timeMinutes = userInput.next();
            
            service.createRaid(gymName, level, timeHours, timeMinutes);
           
            System.out.println("Raid added: raid info");
        }
        
        //find all raids, 8
        if (reply.equalsIgnoreCase("8")){
            System.out.println(raidDao.getAll());           
        }
      }
      
      System.out.println("Goodbye."); 
      
   }
    
}