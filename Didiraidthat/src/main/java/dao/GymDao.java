package dao;
import java.util.List;
import domain.Gym;
import domain.User;
import java.sql.SQLException;

public interface GymDao {
    
    Gym create(Gym gym) throws SQLException;
    
    Gym findByGymName(String name) throws SQLException;
    
    Gym findbyGymId(int id) throws SQLException;

    List<Gym> getAll() throws SQLException;
}