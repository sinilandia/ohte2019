package domain;

import java.util.ArrayList;
import java.util.List;
import dao.RaidDao;

/**
 *
 * @author siniliu
 */
public class FakeRaidDao implements RaidDao {
    
    List<Raid> raids;
    
    public FakeRaidDao() {
        raids = new ArrayList<>();
    }   
   
    @Override
    public List<Raid> getAll() {
        return raids;
    }
    
    @Override
    public Raid create(Raid raid) {
        raid.setId(raids.size()+1);
        raids.add(raid);
        return raid;
    }     
}
