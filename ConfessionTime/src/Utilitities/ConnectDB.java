
package Utilitities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {

        
    public static Connection conn;
    
    public ConnectDB(){
    }
    
    public void DoConnect(){
        if (conn == null){
            try{
               //connect to the database
               String host = "jdbc:derby://localhost:1527/ConfessionTimeDB";
               String uName = "SWIFTIES";
               String uPass = "12345";
               conn = DriverManager.getConnection( host, uName, uPass );

           }catch(SQLException err){
               err.printStackTrace();
           }
        }
    }
   
    public void CloseDB(){
        try{
            if (conn != null) conn.close();
        }catch(SQLException err){
             err.printStackTrace();
        }
    }
    
}
