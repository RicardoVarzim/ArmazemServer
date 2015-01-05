/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import BusinessLayer.Facade;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ricardo
 */
public class noteHandler implements Runnable {

    private Facade facade;
    private ArrayList<ClientWorker> clientes;
    
    public noteHandler(Facade f){
        this.facade = f;
        this.clientes = new ArrayList<ClientWorker>();
    }
    
    public void addClient(ClientWorker client){
        clientes.add(client);
    }
    
    @Override
    public void run() {
        
        ArrayList<String> notes;
        while(true){
            try {
                Thread.sleep(2000);
                notes = facade.listar_notificacoes();
                for(ClientWorker cw :clientes)
                    cw.sendNotification(notes);
            } catch (InterruptedException ex) {
                
            }
        }
    }
    
}
