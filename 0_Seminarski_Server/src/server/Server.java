/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import niti.ObradaKlijentskihZahteva;

/**
 *
 * @author HP
 */
public class Server extends Thread{
    boolean kraj = false;
    ServerSocket ss;
    List<ObradaKlijentskihZahteva> klijenti;

    public Server() {
        klijenti = new ArrayList<>();
    }

    @Override
    public void run() {  //pokreniServer
        try{
            ss = new ServerSocket(9000);
            System.out.println("Server ceka klijente...");
            while(!kraj){
                Socket s = ss.accept();
                System.out.println("Klijent se povezao na server!");
            
                ObradaKlijentskihZahteva okz = new ObradaKlijentskihZahteva(s);
                klijenti.add(okz);
                okz.start();
            }
        }catch(IOException ex){
            if(ex instanceof SocketException)
                System.out.println("Veza sa klijentima je prekinuta: " +ex.getMessage());
//            ex.printStackTrace();
        }
    }
    
    public void zaustaviServer(){
        kraj = true;
        try {
            for (ObradaKlijentskihZahteva okz : klijenti) {
                okz.prekini();
            }
            klijenti.clear();
            ss.close();
        } catch (IOException ex) {
            if(ex instanceof SocketException)
                System.out.println("Veza sa klijentima je prekinuta: " +ex.getMessage());
//            ex.printStackTrace();
        }finally{
            JOptionPane.showMessageDialog(null, "Server je uspešno zaustavljen.", "Zaustavljanje servera", JOptionPane.INFORMATION_MESSAGE);
//            System.exit(0);
        }
    }
    
}
