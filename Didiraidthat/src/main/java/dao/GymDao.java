/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;
import java.util.List;
import domain.Gym;

/**
 *
 * @author siniliu
 */
public interface GymDao {
    
    Gym create(Gym todo) throws Exception;

    List<Gym> getAll();

}