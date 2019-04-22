/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import domain.User;
import java.sql.*;

/**
 *
 * @author siniliu
 */
public interface UserDao {
    User create(String username) throws SQLException;

    User findByUsername(String username) throws SQLException;

    List<User> getAll() throws SQLException;

}
