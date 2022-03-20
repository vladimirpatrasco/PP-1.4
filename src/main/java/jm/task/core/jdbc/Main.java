package jm.task.core.jdbc;


import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

       UserServiceImpl us = new UserServiceImpl();
       us.createUsersTable();
       us.saveUser("Ivan", "Ivanov", (byte) 31);
       us.saveUser("Ivan", "Popov", (byte) 32);
       us.saveUser("Sidor", "Sidorov", (byte) 33);
       us.saveUser("Petr", "Petrov", (byte) 34);
       System.out.println(us.getAllUsers());
       us.cleanUsersTable();
       us.dropUsersTable();
    }
}