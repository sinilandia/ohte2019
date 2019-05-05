package dao;

import java.util.List;
import domain.Raid;
import java.sql.SQLException;

public interface RaidDao {

    boolean create(Raid raid) throws Exception;
    
    Raid findRaidById(int raidId);
    
    List<Raid> getAll();
    
}