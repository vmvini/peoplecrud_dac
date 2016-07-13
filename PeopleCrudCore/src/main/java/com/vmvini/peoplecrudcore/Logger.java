/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vmvini.peoplecrudcore;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import java.io.File;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
/**
 * @author vmvini
 */

@Singleton
@ConcurrencyManagement(ConcurrencyManagementType.CONTAINER)
public class Logger {
    
    private File f;
    private BufferedWriter output;
    
    @PostConstruct
    public void init(){
       f = new File("/usr/glassfish4/mylogs.txt");
       f.setWritable(true);
       if(!f.exists()){
           try{
            f.createNewFile();
           }
           catch(IOException e){
               System.out.println("ERRO AO CRIAR ARQUIVO mylogs.txt");
           }
       }
       
    }
    
    public void saveMessage(String message){
        try{
            output = new BufferedWriter(new FileWriter(f));
            output.append(message + "\n");
        }
        catch(IOException e){
            System.out.println("ERRO AO ESCREVER LOG EM mylogs.txt");
        }
        finally{
            if(output != null){
                try{
                    output.close();
                }
                catch(IOException e){
                    System.out.println("ERRO AO FECHAR BUFFER DE ARQUIVO mylogs.txt");
                }
            }
        }
    }
    
}
