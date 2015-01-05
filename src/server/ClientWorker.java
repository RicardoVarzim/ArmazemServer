/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import BusinessLayer.Facade;
import comands.Command;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author Ricardo
 */
class ClientWorker implements Runnable {
    
    private Socket client;
    private Facade facade;
    private ObjectInputStream in;
    private ObjectOutputStream out;

    //Constructor
    ClientWorker(Socket client, Facade facade) 
    {
        this.client = client;
        this.facade = facade;
    }
    
    public void run()
    {
//        ObjectInputStream in = null;
//        ObjectOutputStream out = null;
        MessageHandler messageHandler;
        Command cmd = null;
        //Object res = null;
        
        try{
            in = new ObjectInputStream(client.getInputStream());
            out = new ObjectOutputStream(client.getOutputStream());
        } catch (IOException e) {
            System.out.println("in or out failed");
            System.exit(-1);
        }

        messageHandler = new MessageHandler(facade);
        
        while(true){
            try{
                cmd = (Command) in.readObject();
                System.out.println("Incoming Command:" + cmd.type);
                cmd = messageHandler.ResolveMessage(cmd);
                
                if(messageHandler.hasResponse){
                    System.out.println("Outcoming Result:" + cmd.type);
                    out.writeObject(cmd);
                    out.flush();
                }
                
            }catch (Exception e) {
                System.out.println("Read failed: " + e.getMessage())   ;
                break;
            }
        }
        
        finalize();
    }
    
    public void sendNotification(ArrayList<String> notifications){
        try{
            Command temp = new Command("notify","",new Object[]{notifications});
            out.writeObject(temp);
            out.flush();
        }catch(Exception e){
            System.out.println("Read failed: " + e.getMessage());
        }
        
    }
    
    protected void finalize(){
    //Objects created in run method are finalized when
    //program terminates and thread exits
        try{
            client.close();
        } catch (IOException e) {
            System.out.println("Could not close socket");
            System.exit(-1);
        }
    }
    
//    public void run()
//    {
//        ObjectInputStream in = null;
//        ObjectOutputStream out = null;
//        try {
//            in = new ObjectInputStream(client.getInputStream());
//            out = new ObjectOutputStream(client.getOutputStream());
//        } catch (IOException ex) {
//            Logger.getLogger(ClientWorker.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
//        
//        while (true){
//            try {
//                Command cmd = (Command) in.readObject();
//                MessageHandler handler = new MessageHandler(facade,cmd);
//                handler.ResolveMessage();
//            } catch (Exception ex) {
//                System.out.println(" ERROR reading: " + ex.getMessage());
//                break;
//            } 
//        }
//    }
}
