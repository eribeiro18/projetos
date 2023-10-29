/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connection;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author evandro
 */
public class SingleConnectionBanco {

    private static String url = "jdbc:postgresql://localhost:5432/curso-jsp?autoReconnect=true";
    private static String user = "postgres";
    private static String password = "PostG0B0";
    private static Connection connection = null;

    public static Connection getConnection() {
        return connection;
    }
    
    static{
        conectar();
    }
    
    public SingleConnectionBanco() {
        conectar();
    }
       
    private static void conectar(){
        try {
            if(connection == null){
                Class.forName("org.postgresql.Driver");
                connection = DriverManager.getConnection(url, user, password);
                connection.setAutoCommit(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
