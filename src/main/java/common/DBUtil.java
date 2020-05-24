package common;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// 借助这个类和数据库建立连接，进一步操作数据库
public class DBUtil {

    private static final String URL = "jdbc:mysql://127.0.0.1:3306/java_oj?characterEncoding=utf8&useSSL=true";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    private volatile static DataSource dataSource = null;

    private DBUtil() {};

    public static DataSource getDataSource() {
        if (dataSource == null) {
           synchronized (DBUtil.class) {
               if (dataSource == null) {
                   dataSource = new MysqlDataSource();
                   ((MysqlDataSource)dataSource).setURL(URL);
                   ((MysqlDataSource)dataSource).setUser(USERNAME);
                   ((MysqlDataSource)dataSource).setPassword(PASSWORD);
               }
           }
        }
        return dataSource;
    }

    public static Connection getConnection() {
        try {
            return getDataSource().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void close(Connection connection, Statement statement, ResultSet resultSet) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
