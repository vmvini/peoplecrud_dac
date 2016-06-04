/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vmvini.peoplecrudcore;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author marcusviniv
 * 
 * database name: peopleCrud
 * 
 * CREATE TABLE person (
	name varchar (200),
	old int,
	id serial primary key
	)
 */
public class DBConnection {

    
    private static DBConnection dbc;
    
    public static DBConnection getInstance() {
        if (dbc == null)
            dbc = new DBConnection();
        return dbc;
    }
    
    
    private Connection connection;
    private String url = "jdbc:postgresql://postgres:5432/peopleCrud";
    private String senha = "12345";
    private String usuario = "postgres";
    
    private DBConnection(){
        
    }
    
    private void initConnection() {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, usuario, senha);
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println("ERRO AO CONECTAR AO BANCO");
            System.out.println(ex.getMessage());
        }
    }
    
    public Connection getConection(){
        if(connection == null)
            initConnection();
        return connection;
    }
    
    
}
