package domain;

import java.util.ArrayList;
import java.util.List;
import dao.GymDao;

/**
 *
 * @author siniliu
 */
public class FakeGymDao implements GymDao {
    
    List<Gym> gyms;
    
    public FakeGymDao() {
        gyms = new ArrayList<>();
    }   
   
    @Override
    public List<Gym> getAll() {
        return gyms;
    }
    
    @Override
    public Gym create(Gym gym) {
        gym.setId(gyms.size()+1);
        gyms.add(gym);
        return gym;
    }     
}
