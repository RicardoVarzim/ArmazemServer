/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import BusinessLayer.BusinessIO;
import BusinessLayer.Facade;
import Console.Console;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author Ricardo
 */
public class Server {
    
    private static ServerSocket server;
    private static final int PORT = 1200;
    private static Facade business = new Facade();
    
    public static void main(String[] args){
        
        Console console= new Console(business);
        Thread consoleThread = new Thread(console);
        consoleThread.start();
        
        try
        {
           server = new ServerSocket(PORT);
        }
        catch(IOException ex)
        {
            System.out.println(ex.getMessage());
            System.exit(1);
        }
        
        while(true){
            handler();
        }
    }
    public static void handler(){
        
        //Socket connection = null;
        ClientWorker w;
        try
        {
            w = new ClientWorker(server.accept(),business);
            Thread t = new Thread(w);
            t.start();
        }
        catch(IOException ioEx)
        {
            ioEx.printStackTrace();
        }
    }
    
    
    protected void finalize(){
        try{
            server.close();
        } catch (IOException e) {
            System.out.println("Could not close socket");
            System.exit(-1);
        }
    }
}
