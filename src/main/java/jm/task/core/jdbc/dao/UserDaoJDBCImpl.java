package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.metamodel.internal.JpaStaticMetaModelPopulationSetting;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/*public class UserDaoJDBCImpl implements UserDao {

*//*    String insertUser = "INSERT INTO Users (name, lastName, age) VALUES (?,?,?)";
    String deleteUser = "DELETE FROM Users WHERE id = ?";
    String getAllUsers = "SELECT * FROM Users";
    String clearTable = "Truncate table Users";
    String createTable = "CREATE TABLE Users " +
            "(id INTEGER not NULL AUTO_INCREMENT, " +
            "name VARCHAR(255), " +
            "lastName VARCHAR(255), " +
            "age INTEGER, " +
            "PRIMARY KEY (id))";
    String dropTable = "DROP TABLE IF EXISTS Users";

    Connection connection = Util.getCon();

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (PreparedStatement pstm = connection.prepareStatement(createTable)) {
            pstm.executeUpdate();
            connection.commit();
            System.out.println("Создана таблица Users");
        } catch (SQLException e) {
            System.out.println("Произошло исключение " + e);
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void dropUsersTable() {
        try (PreparedStatement pstm = connection.prepareStatement(dropTable)) {
            pstm.executeUpdate();
            connection.commit();
            System.out.println("Удалена таблица Users");
        } catch (SQLException e) {
            System.out.println("Произошло исключение " + e);
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (PreparedStatement pstm = connection.prepareStatement(insertUser)) {
            pstm.setString(1, name);
            pstm.setString(2, lastName);
            pstm.setByte(3, age);
            System.out.println(pstm.executeUpdate());
            System.out.println("Пользователь с именем " + name + " добавлен в БД");
            connection.commit();
        } catch (SQLException e) {
            System.out.println("Произошло исключение " + e);
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void removeUserById(long id) {
       try (PreparedStatement pstm = connection.prepareStatement(deleteUser)) {
            pstm.setLong(1, id);
            pstm.executeUpdate();
            connection.commit();
           System.out.println("Удален пользователь с ID: " + id);
        } catch (SQLException e) {
           System.out.println("Произошло исключение " + e);
           try {
               connection.rollback();
           } catch (SQLException ex) {
               ex.printStackTrace();
           }
       }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        User user = null;
        try (PreparedStatement pstm = connection.prepareStatement(getAllUsers);
             ResultSet rs = pstm.executeQuery(getAllUsers)) {
                while (rs.next()) {
                    user = new User();
                    user.setId(rs.getLong("id"));
                    user.setName(rs.getString("name"));
                    user.setLastName(rs.getString("lastName"));
                    user.setAge(rs.getByte("age"));
                    users.add(user);
                }
                connection.commit();
        } catch (SQLException e) {
            System.out.println("Произошло исключение " + e);
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return users;
    }

    public void cleanUsersTable() {
        try (PreparedStatement pstm = connection.prepareStatement(clearTable)) {
            pstm.executeUpdate();
            connection.commit();
            System.out.println("Очищена таблица Users");
        } catch (SQLException e) {
            System.out.println("Произошло исключение " + e);
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }*//*
}*/
