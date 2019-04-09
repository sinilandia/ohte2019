/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Didiraidthat;

import Didiraidthat.Gym;
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
public class GymTest {
    
    Gym gymExa;
    
    @Before
    public void setUp() {
        gymExa = new Gym("Exa");
    }
    
    
     @Test
     public void getsNameCorrectly() {
        String name = gymExa.getName();
        assertEquals("Exa", name);
     }
}
