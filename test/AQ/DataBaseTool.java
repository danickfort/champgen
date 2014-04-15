/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AQ;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author garynietlispach
 */
public class DataBaseTool {
    
    public static void restartDB()
    {
        Connection con = null;
        String url = "jdbc:mysql://localhost:3306/";
        String db = "champgen";
        String driver = "com.mysql.jdbc.Driver";
        String user = "root";
        String pass = "root";
        try{
        Class.forName(driver).newInstance();
        con = DriverManager.getConnection(url+db, user, pass);
        Statement st = con.createStatement();
        st.executeUpdate("SET FOREIGN_KEY_CHECKS=0;");
        st.executeUpdate("TRUNCATE TABLE championship;");
        st.executeUpdate("TRUNCATE TABLE game;");
        st.executeUpdate("TRUNCATE TABLE matchday;");
        st.executeUpdate("TRUNCATE TABLE user;");
        st.executeUpdate("TRUNCATE TABLE team;");
        st.executeUpdate("TRUNCATE TABLE user_group;");
        st.executeUpdate("SET FOREIGN_KEY_CHECKS=1;");
        st.executeUpdate("INSERT INTO user(password, username) VALUES(\"admin\", \"admin\");");
        st.executeUpdate("INSERT INTO user_group(groupName, user_id) VALUES(\"ADMIN\", 1);");
        
        }
        catch (SQLException s){
        System.out.println("SQL code does not execute.");
        }  
        catch (Exception e){
        e.printStackTrace();
        }
    }
    
}
