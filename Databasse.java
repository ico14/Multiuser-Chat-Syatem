/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.*;
/**
 *
 * @author dell
 */
public class Databasse {
    static Connection c;
    static Statement s;
    
    public static Statement makeConnection()
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            c=DriverManager.getConnection("jdbc:mysql://localhost:3306/server_database","root","");
            s=c.createStatement();
        }
        catch(Exception e)
        {
            
        }
                return s;
    }
    
    public static void closeConnecton()
    {
        try{
        s.close();
        c.close();
        }
        catch(Exception e)
        {}
    }

    private static Statement createStatement(String sql) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
