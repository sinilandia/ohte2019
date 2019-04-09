/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package didiraidthat;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.Scanner;

/**
 *
 * @author siniliu
 */



public class Main {
  public static void main(String[] args) {
      
	
      ArrayList allRaids = new ArrayList<>();
      ArrayList allGyms = new ArrayList<>();
      ArrayList userRaided = new ArrayList<>();
      
      //create gyms
      Gym laru = new Gym("Laru");
      Gym otaniemi = new Gym("Otaniemi");
      Gym exa = new Gym("Exactum");
      
      //add gyms to list allGyms
      allGyms.add(laru);
      allGyms.add(otaniemi);
      allGyms.add(exa);
           
      //create raids
      Raid raidLaru = new Raid("5", laru);
      Raid raidOtaniemi = new Raid("4", otaniemi);
      Raid raidExa = new Raid("3", exa);
      
      //add raids to list allRaids
      allRaids.add(raidLaru);
      allRaids.add(raidOtaniemi);
      allRaids.add(raidExa);
      
      //set id's for raids >> how to do this the right way?
      int idRaidLaru = allRaids.indexOf(raidLaru);
      raidLaru.setId(idRaidLaru);
      int idRaidOtaniemi = allRaids.indexOf(raidOtaniemi);
      raidOtaniemi.setId(idRaidOtaniemi);
      int idRaidExa = allRaids.indexOf(raidExa);
      raidExa.setId(idRaidExa);
      
      //create Logic
      Logic logic = new Logic();
      
      //UI
      Scanner userInput = new Scanner( System.in );
      String reply = "";

      while (!reply.equalsIgnoreCase("x")){
        System.out.println("Menu:\n" + 
                "1. I want to sign up for raids.\n" +
                "2. View all raids.\n"+
                "3. View all my raids.\n"+
                "4. View all gyms. \n"+
                "Type in x to quit.\n"+
                "What is your choice?"
        );  
      
        reply = userInput.next();
        
        //show raids one at a time from list allRaids 
        if (reply.equalsIgnoreCase("1")){  
            ArrayList list = logic.userSignsUpForRaid(allRaids);        
            for (int i = 0 ; i<list.size();i++){
                userRaided.add(list.get(i)) ;
            }           
        }
        
        //show list allRaids
        if (reply.equalsIgnoreCase("2")){
            System.out.println(allRaids.toString());           
        }
        
        //show list userRaided
        if (reply.equalsIgnoreCase("3")){
            System.out.println(userRaided.toString());           
        }
        
        //show list allGyms
        if (reply.equalsIgnoreCase("4")){
            System.out.println(allGyms.toString());           
        }
 
      }
      
      System.out.println("Goodbye."); 
      
   }
    
}
