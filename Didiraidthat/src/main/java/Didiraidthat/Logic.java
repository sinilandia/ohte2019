/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package didiraidthat;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author siniliu
 */
public class Logic {
     Scanner userInput;
     String reply;
     ArrayList raids;
     ArrayList usersRaids;
     
    public ArrayList userSignsUpForRaid(ArrayList raidsList) {
        Scanner userInput = new Scanner( System.in );
        String reply = "";
        raids = new ArrayList<>();
        for (int i = 0 ; i<raidsList.size();i++){
            raids.add(raidsList.get(i)) ;
        }
        usersRaids = new ArrayList<>();
      
        
        //problem: user signing up twice for the same raid >> fix!
        for (int counter = 0; counter < raids.size(); counter++) { 		      
                System.out.println("\n" + raids.get(counter)); 
                System.out.println("Do you want to sign up for this raid? Y/N");
                reply = userInput.next();
                
                if(reply.equalsIgnoreCase("y")){
                    usersRaids.add(raids.get(counter));
                    System.out.println("You signed up for the raid.");
                }
        }
        
        return usersRaids;
    }
}
