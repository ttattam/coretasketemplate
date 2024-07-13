package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;
import jm.task.core.jdbc.model.User;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Проверка наличия JDBC драйвера
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        // Проверка соединения
        Util utils = new Util();
        utils.checkConnection();

        // Основные операции с базой данных
        UserDao userDao = new UserDaoJDBCImpl();

        userDao.createUsersTable();

        userDao.saveUser("John", "Doe", (byte) 30);
        userDao.saveUser("Jane", "Doe", (byte) 25);
        userDao.saveUser("Mike", "Smith", (byte) 35);
        userDao.saveUser("Anna", "Johnson", (byte) 28);

        // Убедитесь, что метод getAllUsers() не возвращает null
        List<User> users = userDao.getAllUsers();
        if (users != null) {
            users.forEach(user -> System.out.println(user.toString()));
        } else {
            System.out.println("Список пользователей пуст.");
        }

        userDao.cleanUsersTable();
        userDao.dropUsersTable();
    }
}
