/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vmvini.peoplecrudcore;

import com.vmvini.peoplecrudlibrary.Person;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author marcusviniv
 */
public class PersonDao {
    
    
    private DBConnection dbConnection = DBConnection.getInstance();
    
    public void save(Person p) throws SQLException{
        String sql = "INSERT INTO person (name, old) VALUES ( ?, ?)";
        if(dbConnection == null){
            System.out.println("DBConnection ESTÁ NULO!");
        }
        else{
            if(dbConnection.getConection() == null)
                System.out.println("CONEXAO NULA");
        }
        PreparedStatement ps = dbConnection.getConection().prepareStatement(sql);
        ps.setString(1, p.getName());
        ps.setInt(2, p.getOld());
        ps.executeUpdate();
        
    }
    
    public List<Person> list() throws SQLException{
        List<Person> persons = new ArrayList<>();
        Person person;
        String sql = "SELECT * FROM person";
        Statement s = dbConnection.getConection().createStatement();
        ResultSet rs = s.executeQuery(sql);
        while(rs.next()){
            person = new Person();
            person.setName(rs.getString("name"));
            person.setOld(rs.getInt("old"));
            persons.add(person);
        }
        
        return persons;
    }
    
    
}
