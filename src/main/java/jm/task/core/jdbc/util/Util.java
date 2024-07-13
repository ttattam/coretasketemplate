package jm.task.core.jdbc.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String URL = "jdbc:mysql://localhost:3306/mysql"; // Имя вашей базы данных
    private static final String USER = "root"; // Имя пользователя базы данных
    private static final String PASSWORD = "131296"; // Пароль пользователя базы данных

    private static final SessionFactory sessionFactory = buildSessionFactory();

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    private static SessionFactory buildSessionFactory() {
        try {
            return new Configuration()
                    .setProperty("hibernate.connection.url", URL)
                    .setProperty("hibernate.connection.username", USER)
                    .setProperty("hibernate.connection.password", PASSWORD)
                    .setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect")
                    .setProperty("hibernate.hbm2ddl.auto", "update")
                    .setProperty("hibernate.show_sql", "true")
                    .addAnnotatedClass(jm.task.core.jdbc.model.User.class)
                    .buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void checkConnection() {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            if (conn != null) {
                System.out.println("Соединение успешно установлено!");
            } else {
                System.out.println("Не удалось установить соединение.");
            }
        } catch (SQLException e) {
            System.out.println("Не удалось установить соединение.");
            e.printStackTrace();
        }
    }
}
