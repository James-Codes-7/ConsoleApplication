package schoolattendancealter.connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class JDBCConnection {

    private static JDBCConnection jdbcConnection;
    private static Statement statement;
    public static JDBCConnection getInstance() {
        if (jdbcConnection == null) jdbcConnection = new JDBCConnection();
        return jdbcConnection;
    }
    private Connection connection;
    public Statement dataBaseConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            if (connection == null) {
                connection = DriverManager.getConnection("jdbc:mysql:"
                                + "//localhost:3306/students?useSSL=false",
                        "root", "James124@");
                statement=connection.createStatement();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return statement;
    }
}
