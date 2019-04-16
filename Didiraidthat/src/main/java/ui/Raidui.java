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
                "4. View all EX gyms. \n"+
                "5. I want to add a gym. \n" +
                "Type in x to quit.\n"+
                "What is your choice?"
        );  
        
        //create RaidService 
        String gymFile = "gymFile";
        String raidFile = "raidFile";
        FileGymDao gymDao = new FileGymDao(gymFile);
        FileRaidDao raidDao = new FileRaidDao(raidFile, gymDao);       
        RaidService service = new RaidService(gymDao, raidDao);

        reply = userInput.next();
        
        //show all EX Gyms
        if (reply.equalsIgnoreCase("4")){
            System.out.println(service.getEXGyms());
        }
        
        //add a new gym
        if (reply.equalsIgnoreCase("5")){
            System.out.println("Name of gym?");
            String name = userInput.next();
            System.out.println("Is the gym an EX gym? Yes/No");
            String ex = userInput.next();
            boolean exGym = false;
            if (ex.equalsIgnoreCase("yes")) {
                exGym = true;
            }
            service.createGym(name, exGym);
            System.out.println("Added gym: " + name);           
        }
     
      }
      
      System.out.println("Goodbye."); 
      
   }
    
}
