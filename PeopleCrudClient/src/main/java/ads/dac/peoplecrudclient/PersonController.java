/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ads.dac.peoplecrudclient;

import com.vmvini.peoplecrudlibrary.Person;
import com.vmvini.peoplecrudlibrary.PersonService;
import com.vmvini.peoplecrudlibrary.ServiceLocator;
import java.util.List;

/**
 *
 * @author vmvini
 */
public class PersonController {
    
   private PersonService buildPersonService(){
       return buildServiceLocator("0.0.0.0", "370").lookup("java:global/PeopleCrudCore/PersonServiceImpl!com.vmvini.peoplecrudlibrary.PersonService", PersonService.class);
   }
   
   private ServiceLocator buildServiceLocator(String host, String port){
       ServiceLocator sl = buildServiceLocator();
       sl.setHost(host);
       sl.setPort(port);
       return sl;
   }
   
   private ServiceLocator buildServiceLocator(){
       return new ServiceLocator();
       
   }
    
   public void savePerson(String name, String old){
       
        System.out.println("saving person name: " + name + " old: " + old);
        Person p = new Person();
        p.setName(name);
        p.setOld(Integer.parseInt(old));
        buildPersonService().save(p);
        System.out.println("Person Saved");
        
   }
   
   public Object[] getPersons(){
       List<Person> persons = buildPersonService().list();
       return persons.toArray();
   }
   
   
    
}
