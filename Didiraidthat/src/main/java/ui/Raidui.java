/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
    
    public static void main(String[] args) throws Exception {

      //UI
      Scanner userInput = new Scanner( System.in );
      String reply = "";
      
      while (!reply.equalsIgnoreCase("x")){
        System.out.println("Menu:\n" + 
                "1. Add new user. \n"+
                "2. Find user. \n"+
                "3. Find all users. \n" +
                "4. View all gyms. \n"+
                "5. I want to add a gym. \n" +
                "Type in x to quit.\n"+
                "What is your choice?"
        );  //missing find a gym by name -method missing from RaidService
        
        //create RaidService 
        String gymFile = "gymFile";
        String raidFile = "raidFile";
        Database database = new Database("jdbc:sqlite:raid.db");
        FileGymDao gymDao = new FileGymDao(database);
        FileRaidDao raidDao = new FileRaidDao(raidFile, gymDao);       
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
      }
      
      System.out.println("Goodbye."); 
      
   }
    
}
