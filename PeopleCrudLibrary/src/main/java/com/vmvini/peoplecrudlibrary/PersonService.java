/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vmvini.peoplecrudlibrary;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author marcusviniv
 */
public interface PersonService extends Serializable{
    
    
    public void save(Person p);
    
    public List<Person> list();
    
    
}
