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
	
      ArrayList allRaids = new ArrayList<>();
      ArrayList allGyms = new ArrayList<>();
      ArrayList userRaided = new ArrayList<>();
      
      //create gyms
      Gym laru = new Gym("Hietsu", false);
      Gym otaniemi = new Gym("Otaniemi", true);
      Gym exa = new Gym("Exactum", true);
      
      //add gyms to list allGyms
      allGyms.add(laru);
      allGyms.add(otaniemi);
      allGyms.add(exa);
           
      //create raids
      Raid raidLaru = new Raid("5", laru, "17", "00");
      Raid raidOtaniemi = new Raid("4", otaniemi, "18", "01");
      Raid raidExa = new Raid("3", exa, "19", "02");
      
      //add raids to list allRaids
      allRaids.add(raidLaru);
      allRaids.add(raidOtaniemi);
      allRaids.add(raidExa);
      
      
      
      
      
      
      
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
            service.createGym("Kallio", false);
            service.createGym("Kalasatama", true);
            System.out.println("added gym");           
        }
     
      }
      
      System.out.println("Goodbye."); 
      
   }
    
}
