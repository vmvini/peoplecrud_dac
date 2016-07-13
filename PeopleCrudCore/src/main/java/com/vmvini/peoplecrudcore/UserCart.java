/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vmvini.peoplecrudcore;

import com.vmvini.peoplecrudlibrary.UserCartInterface;
import javax.ejb.Remote;
import javax.ejb.Remove;
import javax.ejb.Stateful;
import java.util.List;
import java.util.ArrayList;
import javax.ejb.EJB;
/**
 *
 * @author vmvini
 */

@Stateful
@Remote(UserCartInterface.class)
public class UserCart implements UserCartInterface {
    
    
    private List<String> items = new ArrayList();
    
    @EJB
    private Logger logger;
    
    @Override
    public void add(String item) {
        items.add(item);
        logger.saveMessage("item adicionado: " + item);
    }
    
    @Override
    @Remove
    public void finalize(){
        System.out.println("EXIBINDO LISTA");
        logger.saveMessage("Finalizando compra!!!");
        logger.saveMessage("ITENS: ");
        logger.saveMessage("------------------");
        for(String item : items){
            System.out.println(item);
           
            System.out.println("numero de items: " + items.size());
            
             logger.saveMessage(item);
        }
        System.out.println("COMPRA FINALIZADA");
        logger.saveMessage("------------------");
        
    }

    
}
