/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;
import java.util.List;
import domain.Gym;
import domain.User;
import java.sql.SQLException;

/**
 *
 * @author siniliu
 */
public interface GymDao {
    
    Gym create(Gym gym) throws SQLException;
    
    Gym findByGymName(String name) throws SQLException;

    List<Gym> getAll() throws SQLException;

}