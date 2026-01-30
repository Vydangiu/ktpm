package bai5;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {


    private static final String URL =
            "jdbc:sqlserver://localhost:1433;" +
                    "databaseName=Lab5_Testing;" +
                    "encrypt=true;" +
                    "trustServerCertificate=true";

    private static final String USER = "sa";
    private static final String PASSWORD = "ThaoVy123@";

//    public static Connection getConnection() throws Exception {
//        return DriverManager.getConnection(URL, USER, PASSWORD);
//
//    }
public static Connection getConnection() throws Exception {
    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); // ⭐ BẮT BUỘC
    return DriverManager.getConnection(URL, USER, PASSWORD);
}
}

