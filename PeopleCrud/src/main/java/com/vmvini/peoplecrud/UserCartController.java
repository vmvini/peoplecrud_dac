/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vmvini.peoplecrud;

import com.vmvini.peoplecrudlibrary.ServiceLocator;
import com.vmvini.peoplecrudlibrary.UserCartInterface;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author vmvini
 */

@Named
@SessionScoped
public class UserCartController implements Serializable {
    
    private List<String> items;
    private String one_item;
    
    public UserCartController(){
        userCartService = new ServiceLocator().lookup("java:global/PeopleCrudCore/UserCart!com.vmvini.peoplecrudlibrary.UserCartInterface", UserCartInterface.class);
        items = new ArrayList();
        one_item = "";
    }
    
    private UserCartInterface userCartService;
    
    public void setOne_item(String one_item){
        this.one_item = one_item;
    }
    
    public List<String> getItems(){
        return items;
    }
    
    public String getOne_item(){
        return one_item;
    }
    
    public String add(){
        items.add(one_item);
       
        userCartService.add(one_item);
         one_item = "";
         return null;
    }
    
    public String  finalizar(){
        items.clear();
        userCartService.finalize();
        userCartService = new ServiceLocator().lookup("java:global/PeopleCrudCore/UserCart!com.vmvini.peoplecrudlibrary.UserCartInterface", UserCartInterface.class);
        
        return null;
    }
    
}
