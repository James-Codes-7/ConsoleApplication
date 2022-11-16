package bankingalter.connection;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCConnection {

    private static JDBCConnection jdbcConnection;
    private Statement statement;

    public static JDBCConnection getInstance()
    {
        if(jdbcConnection==null)jdbcConnection=new JDBCConnection();
        return jdbcConnection;
    }

    public Statement getJdbcConnection()
    {
        if(statement==null)
        {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                statement = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank?useSSL=false","root","James124@").createStatement();
            }catch (SQLException e)
            {
                System.out.println(e.getMessage());
            }
            catch (ClassNotFoundException e)
            {
                System.out.println(e.getMessage());
            }
        }
        return statement;
    }
}
