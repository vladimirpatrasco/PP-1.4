package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.metamodel.internal.JpaStaticMetaModelPopulationSetting;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    String insertUser = "INSERT INTO Users (name, lastName, age) VALUE (?,?,?)";
    String deleteUser = "DELETE FROM Users WHERE id = ?";
    String getAllUsers = "SELECT * FROM Users";
    String clearTable = "Truncate table Users";

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Statement stm = Util.getCon().createStatement()) {
            String createTable = "CREATE TABLE Users " +
                    "(id INTEGER not NULL AUTO_INCREMENT, " +
                    "name VARCHAR(255), " +
                    "lastName VARCHAR(255), " +
                    "age INTEGER, " +
                    "PRIMARY KEY (id))";
            stm.executeUpdate(createTable);
            System.out.println("Создана таблица Users");
        } catch (SQLException e) {
            System.out.println("Произошло исключение " + e);
        }
    }

    public void dropUsersTable() {
        try (Statement stm = Util.getCon().createStatement()) {
            String dropTable = "DROP TABLE Users";
            stm.executeUpdate(dropTable);
            System.out.println("Удалена таблица Users");
        } catch (SQLException e) {
            System.out.println("Произошло исключение " + e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (PreparedStatement stm = Util.getCon().prepareStatement(insertUser)) {
            stm.setString(1, name);
            stm.setString(2, lastName);
            stm.setByte(3, age);
            stm.executeUpdate();
            System.out.println("Пользователь с именем " + name + " добавлен в БД");
        } catch (SQLException e) {
            System.out.println("Произошло исключение " + e);
        }
    }

    public void removeUserById(long id) {
       try (PreparedStatement pstm = Util.getCon().prepareStatement(deleteUser)) {
            pstm.setLong(1, id);
            pstm.executeUpdate();
           System.out.println("Удален пользователь с ID: " + id);
        } catch (SQLException e) {
           System.out.println("Произошло исключение " + e);
       }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        User user = null;
        try (Statement stm = Util.getCon().createStatement(); ResultSet rs = stm.executeQuery(getAllUsers)) {
                while (rs.next()) {
                    user = new User();
                    user.setId(rs.getLong("id"));
                    user.setName(rs.getString("name"));
                    user.setLastName(rs.getString("lastName"));
                    user.setAge(rs.getByte("age"));
                    users.add(user);
                }
        } catch (SQLException e) {
            System.out.println("Произошло исключение " + e);
        }
        return users;
    }

    public void cleanUsersTable() {
        try (Statement stm = Util.getCon().createStatement()) {
            stm.executeUpdate(clearTable);
            System.out.println("Очищена таблица Users");
        } catch (SQLException e) {
            System.out.println("Произошло исключение " + e);
        }

    }
}
