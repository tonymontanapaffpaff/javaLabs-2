package by.gsu.pms;

import com.mysql.cj.jdbc.MysqlDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class PoolConnection {
    private static PoolConnection serviceDb = null;
    private static MysqlDataSource dataSource;

    private PoolConnection(String dbName, String user, String password) {
        final String URL_HEADER = "jdbc:mysql://localhost:3306/";
        final String URL_FOOTER = "?serverTimezone=Europe/Minsk";

        dataSource = new MysqlDataSource();
        dataSource.setUrl(URL_HEADER + dbName + URL_FOOTER);
        dataSource.setUser(user);
        dataSource.setPassword(password);
    }

    public static void init(String dbName, String user, String password) {
        if (serviceDb != null) {
            return;
        }
        serviceDb = new PoolConnection(dbName, user, password);
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
