/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Bond
 */
public class conexãoSingleton {
    private static Connection connection;
    
    public static final String URL ="jdbc:mysql://localhost:3306/projeto";
    public static final String USERNAME = "root";
    public static final String PASSWORD = "";
    
    
    private conexãoSingleton(){
        
    }
    
    public static Connection getConnection(){
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
            } catch (SQLException ex) {
                System.out.println("Houve um erro ao acessar o BD");
            }
        }
        return connection;
    }

//    public static conexãoSingleton getInstance() {
//        if (instance == null) {
//            instance = new conexãoSingleton();
//        }
//        return instance;
//    }
    
    
    
}
