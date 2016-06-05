/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vmvini.peoplecrud;

import com.vmvini.peoplecrudlibrary.Person;
import com.vmvini.peoplecrudlibrary.PersonService;
import com.vmvini.peoplecrudlibrary.ServiceLocator;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author marcusviniv
 */

@Named
@RequestScoped
public class PersonController {
    
    private Person person = new Person();
    private ServiceLocator sl = new ServiceLocator();
    
    //@EJB(lookup="corbaname:iiop:pcCore:370#java:global/PeopleCrudCore/PersonServiceImpl!com.vmvini.peoplecrudlibrary.PersonService")
    private PersonService personService;
    
   
    public String addPerson(){
        personService = sl.lookup("java:global/PeopleCrudCore/PersonServiceImpl!com.vmvini.peoplecrudlibrary.PersonService", PersonService.class);
        personService.save(person);
        person = new Person();
        return null;
    }
    
    public List<Person> listPersons(){
       
        personService = sl.lookup("java:global/PeopleCrudCore/PersonServiceImpl!com.vmvini.peoplecrudlibrary.PersonService", PersonService.class);
        
        return personService.list();
    }
    
    public Person getPerson(){
        return person;
    }
    
    
    
}
