package domain;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

public class RaidServiceGymTest {
    
    private FakeGymDao gymDao;
    private FakeDatabase db;
    //private User loggedIn;
    
    @Before
    public void setUp() throws ClassNotFoundException {
        this.db = new FakeDatabase("jdbc:sqlite:raidTest.db");
        this.gymDao = new FakeGymDao(this.db);
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
