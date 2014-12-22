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
    
    public Command ResolveMessage(Command c) {
        this.cmd = c;
        
        if(cmd!=null){
            switch(cmd.type){
                case "registar_cliente":{
                    if(cmd.args.size == 2){
                        cmd.result = registar_cliente((String)cmd.args.listArgs.get(0),(String)cmd.args.listArgs.get(1));
                    }
                    return c;
                }
                case "cliente_login":{
                    if(cmd.args.size == 2){
                        cmd.result = cliente_login((String)cmd.args.listArgs.get(0),(String)cmd.args.listArgs.get(1));  
                    }
                    return cmd;
                }
                case "listar_clientes":{
                    cmd.result = listar_clientes();
                    return cmd;
                }
                case "abastecer":{
                    if(cmd.args.size == 2){
                        String item = (String) cmd.args.listArgs.get(0);
                        int quantidade = (int)cmd.args.listArgs.get(1);
                        abastecer(item,quantidade);
                        cmd.result = "Abastecer " + quantidade +" " + item + "\n";
                    }
                    else cmd.result = "Erro: Abastecer\n";
                    return cmd;
                }
                case "definir_tarefa":{
                    if(cmd.args.size == 2){
                        String nome =(String) cmd.args.listArgs.get(0);
                        cmd.result = definir_tarefa(nome,(TreeMap< String,Integer >)cmd.args.listArgs.get(1));
                        
                    }
                    return cmd;
                }
                case "iniciar_tarefa":{
                    if(cmd.args.size == 1){
                        //TODO: return id tarefa
                        cmd.result = iniciar_tarefa((String)cmd.args.listArgs.get(0));
                    }
                    return cmd;
                }
                case "concluir_tarefa":{
                    if(cmd.args.size == 1){
                        long id = (Long)cmd.args.listArgs.get(0);
                        cmd.result = concluir_tarefa(id);
                    }
                    return cmd;
                }
                case "pedido_notificacao":{
                    if(cmd.args.size == 2){
                        cmd.result = pedido_notificacao((Long[])cmd.args.listArgs.get(0),(String)cmd.args.listArgs.get(0));
                    }
                    return cmd;
                }
                case "listar_notificacoes":{
                    cmd.result = listar_notificacoes();
                    return cmd;
                }
                case "listar_items":{
                    cmd.result = listar_items();
                    return cmd;
                }
                case "listar_tarefas":{
                    cmd.result = listar_tarefas();
                    return cmd;
                }
                case "listar_tarefas_activas":{
                    cmd.result = listar_tarefas_activas();
                    return cmd;
                }
                case "listar_tarefas_concluidas":{
                    cmd.result = listar_tarefas_concluidas();
                    return cmd;
                }
                default:
                    cmd.result ="Invalid Command!\n";
                    return cmd;
            }
        }else{
            cmd.result ="Invalid Command!\n";
            return cmd;
        }
        
    }
    

    @Override
    public boolean registar_cliente(String cliente, String password) {
        return facade.registar_cliente(cliente, password);
    }

    @Override
    public boolean cliente_login(String cliente, String password) {
        return facade.cliente_login(cliente, password);
    }

    @Override
    public String listar_clientes() {
        return facade.listar_clientes();
    }

    @Override
    public void abastecer(String item, int quantidade) {
        facade.abastecer(item, quantidade);
    }

    @Override
    public boolean definir_tarefa(String nome, TreeMap<String, Integer> items) {
        return facade.definir_tarefa(nome, items);
    }

    @Override
    public long iniciar_tarefa(String tarefa) {
        return facade.iniciar_tarefa(tarefa);
    }

    @Override
    public String concluir_tarefa(Long tarefa_id) {
        return facade.concluir_tarefa(tarefa_id);
    }

    @Override
    public boolean pedido_notificacao(Long[] tarefas, String cliente) {
        return facade.pedido_notificacao(tarefas, cliente);
    }

    @Override
    public ArrayList<String> listar_notificacoes() {
        return facade.listar_notificacoes();
    }

    @Override
    public String listar_items() {
        return facade.listar_items();
    }

    @Override
    public String listar_tarefas() {
        return facade.listar_tarefas();
    }

    @Override
    public String listar_tarefas_activas() {
        return facade.listar_tarefas_activas();
    }

    @Override
    public String listar_tarefas_concluidas() {
        return facade.listar_tarefas_concluidas();
    }

}
