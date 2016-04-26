/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tad.proyecto.madrigalgutierrezpedroantonio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author PedroMadrigal
 */
public class Configuration {
    
    private Connection con;
    private Statement stmt;
    private ResultSet rs = null;

    public Configuration() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/tad16?jdbcCompliantTruncation=false";
            con = DriverManager.getConnection(url, "root", "");
            stmt = con.createStatement();
        } catch (Exception e) {
            System.out.println(e.getMessage() + "\n Class not found Exception.");
            System.out.println("getConnection poikkeus:" + " " + e);
        }
    }
}
