/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package didiraidthat;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author siniliu
 */
public class RaidTest {
    
    Raid raidExa;
    Raid raidChe;
    Raid raidPhy;
    Gym gymExa;
    Gym gymChe;
    Gym gymPhy;
    
    
    @Before
    public void setUp() {
        gymExa = new Gym("Exactum");
        raidExa = new Raid("5", gymExa);
//        gymChe = new Gym("Chemicum");
//        raidChe = new Raid("4", gymChe);
//        gymPhy = new Gym("Physicum");
//        raidPhy = new Raid("3", gymPhy);   
    }

    @Test
    public void constructorCreatesRaidCorrectly() {
        assertEquals(0, raidExa.getId()); 
        assertEquals("5", raidExa.getStar());
        assertEquals(gymExa, raidExa.getGym());
        assertEquals(java.time.LocalDate.now(), raidExa.getDate());
        assertEquals(java.time.LocalTime.now(), raidExa.getTime());
        assertFalse(raidExa.getRaided());
    }
    
    @Test
    public void setsRaidedCorrectly() {
        raidExa.setRaided();
        assertTrue(raidExa.getRaided());
    }
    
    @Test
    public void setsIdCorrectly() {
        raidExa.setId(500);
        assertEquals(500, raidExa.getId());
    }
    
    @Test
    public void toStringIsCorrect() {
        String text = raidExa.toString();
        assertEquals(raidExa.getGym() + " " + raidExa.getStar() + "*\n" + 
                raidExa.getDate()+ "\n" + raidExa.getTime()+ "\n", text);
    }
 
}
