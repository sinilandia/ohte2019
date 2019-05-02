package dao;

import java.util.List;
import domain.User;
import java.sql.*;

public interface UserDao {
    User create(String username) throws SQLException;

    User findByUsername(String username);

    List<User> getAll();

}
