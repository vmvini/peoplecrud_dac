package ads.dac.peoplecrudclient;

import com.vmvini.peoplecrudlibrary.Person;
import com.vmvini.peoplecrudlibrary.PersonService;
import com.vmvini.peoplecrudlibrary.ServiceLocator;

/**
 * Enterprise Application Client main class.
 *
 */
public class Main {
    
    public static void main( String[] args ) {
        
        System.out.println("teste salvando pessoa");
        ServiceLocator sl = new ServiceLocator();
        sl.setHost("0.0.0.0");
        sl.setPort("370");
        PersonService ps = sl.lookup("java:global/PeopleCrudCore/PersonServiceImpl!com.vmvini.peoplecrudlibrary.PersonService", PersonService.class);
        Person p = new Person();
        p.setName("CLIENTE DESKTOP");
        p.setOld(11);
        ps.save(p);
        System.out.println("salvou pessoa");
        
    }
}
