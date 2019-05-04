package dao;

import domain.Gym;
import domain.Raid;
import java.util.List;


public interface RaidUserDao {
    
    boolean create(int raidId, int userId);

    List<Integer> findUsersRaids(int userId);

    List<Gym> findUsersGyms(int userId);

}
