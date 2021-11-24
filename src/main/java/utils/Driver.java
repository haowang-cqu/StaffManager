package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Driver {
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/enterprise?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";

    static final String USER = "root";
    static final String PASS = "123456";

    private static final Driver instance = new Driver();
    private Statement statement;

    private Driver() {
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            Log.write("连接数据库...");
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Log.write("实例化Statement对象...");
            statement = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Driver getInstance() {
        return instance;
    }

    public Statement getStatement() {
        return statement;
    }
}
