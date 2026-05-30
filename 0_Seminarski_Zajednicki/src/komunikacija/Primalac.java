/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package komunikacija;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author HP
 */
public class Primalac {
    private Socket socket;

    public Primalac(Socket socket) {
        this.socket = socket;
    }
    
    public Object primi(){
        try {
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            return in.readObject();
        }catch(IOException ex){
            if(ex instanceof SocketException)
                System.out.println("Socket exception: "+ex.getMessage());
        }catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
