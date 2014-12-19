/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import BusinessLayer.BusinessIO;
import BusinessLayer.Facade;
import comands.Command;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.TreeMap;

/**
 *
 * @author Ricardo
 */
public class MessageHandler implements BusinessIO{
    
    private Facade facade;
    private Command cmd;
    
    public MessageHandler(Facade f){
        facade = f;
    }
    
    public MessageHandler(Facade f, Command cmd){
        this.facade = f;
        this.cmd = cmd;
    }
    
    public String ResolveMessage(Command c) {
        this.cmd = c;
        
        if(cmd!=null){
            switch(cmd.type){
                case "registar_cliente":{
                    if(cmd.args.size == 2){
                        if(registar_cliente(cmd.args.listArgs.get(0),cmd.args.listArgs.get(1)))
                            return "Cliente registado com sucesso\n";
                    }
                    return "Erro: Registar Cliente\n";
                }
                case "cliente_login":{
                    
                }
                case "abastecer":{
                    
                }
                case "definir_tarefa":{
                    
                }
                case "iniciar_tarefa":{
                    
                }
                case "concluir_tarefa":{
                    
                }
                case "pedido_notificacao":{
                    
                }
                case "listar_notificacoes":{
                    
                }
                case "listar_items":{
                    
                }
                case "listar_tarefas":{
                    
                }
                case "listar_tarefas_activas":{
                    
                }
                case "listar_tarefas_concluidas":{
                    
                }
                default:
                    return "Invalid Command!\n";
            }
        }else{
            return "Invalid Command!\n";
        }
        
    }
    

    @Override
    public boolean registar_cliente(String cliente, String password) {
        return facade.registar_cliente(cliente, password);
    }

    @Override
    public boolean cliente_login(String cliente, String password) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String listar_clientes() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void abastecer(String item, int quantidade) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean definir_tarefa(String nome, TreeMap<String, Integer> items) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public long iniciar_tarefa(String tarefa) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String concluir_tarefa(Long tarefa_id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean pedido_notificacao(Long[] tarefas, String cliente) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<String> listar_notificacoes() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String listar_items() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String listar_tarefas() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String listar_tarefas_activas() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String listar_tarefas_concluidas() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


}
