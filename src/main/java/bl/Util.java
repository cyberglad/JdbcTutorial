package bl;

import org.apache.commons.dbcp2.BasicDataSource;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
/**
 * Created by glady on 30.06.2017.
 */
public class Util
{

    private     String URL = "jdbc:sqlserver://test-db-prod;instanceName=JCAPS;database=db_jdbc_tutorial"; // ;user=xxx;password=xxx";
    private     String USERNAME = "test";
    private     String PASSWORD = "test";
    private     String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

    public  Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Connection ok");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return connection;
    }

}
