
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
public class MainTest {
    ArrayList userRaids;
    Gym gymExa;
    Raid raidExa;

    @Before
    public void setUp() {
        gymExa = new Gym("Exactum");
        raidExa = new Raid("5", gymExa);
        userRaids = new ArrayList<>();
    }
    
    @Test
    
    public void userSignsUpForRaid() {
        
    }
}
