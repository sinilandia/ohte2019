/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

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
public class RaidServiceTest {
    
    Raid raidPhy;
    FakeGymDao gymDao;
    FakeRaidDao raidDao;
    RaidService service;
    
    
    @Before
    public void setUp() {
        gymDao = new FakeGymDao();
        raidDao = new FakeRaidDao();
        Gym exa = new Gym("Exactum", true);
        Gym phy = new Gym("Physicum", true);
        gymDao.create(exa);
        gymDao.create(phy);
        Raid raidExa = new Raid("4", exa, "18", "00");
        raidPhy = new Raid("5", phy, "19", "01");
        raidDao.create(raidExa);
        raidDao.create(raidPhy);
        service = new RaidService(gymDao, raidDao);
    }
    
    @Test
    public void createGym() {
        service.createGym("Chemicum", true);       
        assertEquals(3, service.getEXGyms().size());
    }
    
    @Test
    public void getEXGyms() {
        String name = service.getEXGyms().get(0).getName();
        assertEquals("Exactum", name);
    }
    
    @Test
    public void getRaided() {
        raidPhy.setRaided();
        assertEquals(1, service.getRaided().size());
    }
 
      
}
