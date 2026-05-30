/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package konfiguracija;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author HP
 */
public class Konfiguracija {
    private static Konfiguracija instance;
    private Properties konfiguracija;

    private Konfiguracija() {
        try {
            konfiguracija = new Properties();
            konfiguracija.load(new FileInputStream("C:\\Users\\HP\\Documents\\NetBeansProjects\\0_Seminarski_Server\\config\\config.properties"));
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static Konfiguracija getInstance() {
        if(instance == null)
            instance = new Konfiguracija();
        return instance;
    }
    
    public String getProperty(String key){
        return konfiguracija.getProperty(key, "n/a");
    }
    
    public void setProperty(String key, String value){
        konfiguracija.setProperty(key, value);
    }
    
    public void sacuvajIzmene(){
        try{
            konfiguracija.store(new FileOutputStream("C:\\Users\\HP\\Documents\\NetBeansProjects\\0_Seminarski_Server\\config\\config.properties"), null);
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }
    
}
