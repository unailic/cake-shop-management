/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.mesto;

import java.util.List;
import model.Mesto;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author HP
 */
public class KreirajMestoSO extends ApstraktnaGenerickaOperacija{

    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if (!(objekat instanceof Mesto)) {
            throw new Exception("Parametar nije tipa Mesto!");
        }
        
        Mesto mesto = (Mesto) objekat;
        List<Mesto> svaMesta = (List<Mesto>) broker.getAll(new Mesto(), null);

        for (Mesto postojaceMesto : svaMesta) {
            // Poređenje naziva, ignorišući velika i mala slova
            if (postojaceMesto.getNazivMesta().equalsIgnoreCase(mesto.getNazivMesta())) {
                throw new Exception("Mesto sa tim nazivom već postoji u bazi!");
            }
        }
    }
    
    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        broker.add((Mesto)objekat);
    }
    
}
