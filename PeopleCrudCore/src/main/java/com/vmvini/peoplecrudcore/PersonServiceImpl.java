/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vmvini.peoplecrudcore;

import com.vmvini.peoplecrudlibrary.Person;
import com.vmvini.peoplecrudlibrary.PersonService;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author marcusviniv
 */

@Stateless
@Remote(PersonService.class)
public class PersonServiceImpl implements PersonService {

    
    private PersonDao personDao = new PersonDao();
    
    @Override
    public void save(Person p){
        try{
            personDao.save(p);
        }
        catch(SQLException e){
            System.out.println("ERRO AO SALVAR PESSOA");
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Person> list() {
        try{
            return personDao.list();
        }
        catch(SQLException e){
            System.out.println("ERRO AO LISTAR PESSOAS");
            System.out.println(e.getMessage());
        }
        return Collections.EMPTY_LIST;
    }
    
}
