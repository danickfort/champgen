/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AQ;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author garynietlispach
 */
public class DataBaseTool {
    private static Statement getStatement() throws Exception
    {
        Connection con = null;
        String url = "jdbc:mysql://localhost:3306/";
        String db = "champgen";
        String driver = "com.mysql.jdbc.Driver";
        String user = "root";
        String pass = "root";
        Class.forName(driver).newInstance();
        con = DriverManager.getConnection(url+db, user, pass);
        return  con.createStatement();
    }
    
    public static void executeSQLfromFile(String fileName) throws Exception
    {
        BufferedReader in = new BufferedReader(new FileReader(fileName));
        Statement stmt = getStatement();
        String str;
        StringBuilder sb = new StringBuilder();
        while ((str = in.readLine()) != null) {
            sb.append(str + "\n");
        }
        String[] commands = sb.toString().split("\n");
        in.close();
        for (String command : commands)
            stmt.executeUpdate(command);
    }
    
}
