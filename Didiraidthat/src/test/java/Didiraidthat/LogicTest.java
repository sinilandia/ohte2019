/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package didiraidthat;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author siniliu
 */
public class LogicTest {
    Raid raidExa;
    Raid raidChe;
    Raid raidPhy;
    Gym gymExa;
    Gym gymChe;
    Gym gymPhy;
    ArrayList list;
    ArrayList listRaids;
    
    @Before
    public void setUp() {
        gymExa = new Gym("Exactum");
        raidExa = new Raid("5", gymExa);
        gymChe = new Gym("Chemicum");
        raidChe = new Raid("4", gymChe);
        gymPhy = new Gym("Physicum");
        raidPhy = new Raid("3", gymPhy);  
        list = new ArrayList<>();
        listRaids = new ArrayList<>();
    }
    
    //how do you test user input and lists?
    @Test
    public void userSignsUpForRaidCorrect() {
        list.add(raidExa);
       
    }

}
