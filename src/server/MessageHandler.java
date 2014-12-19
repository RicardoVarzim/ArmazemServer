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
                    if(cmd.args.size == 2){
                        if(cliente_login(cmd.args.listArgs.get(0),cmd.args.listArgs.get(1)))
                            return "Login efetuado com sucesso\n";
                    }
                    return "Erro: Login Cliente\n";
                }
                case "abastecer":{
                    if(cmd.args.size == 2){
                        String item = cmd.args.listArgs.get(0);
                        int quantidade = Integer.valueOf(cmd.args.listArgs.get(1));
                        abastecer(item,quantidade);
                        return "Abastecer " + quantidade +" " + item + "\n";
                    }
                    return "Erro: Abastecer\n";
                }
                case "definir_tarefa":{
//                    if(cmd.args.size == 2){
//                        String nome = cmd.args.listArgs.get(0);
//                        definir_tarefa(nome,quantidade);
//                        return "Abastecer " + quantidade +" " + item + "\n";
//                    }
                    return "Erro: Não implementado\n";
                }
                case "iniciar_tarefa":{
                    if(cmd.args.size == 1){
                        //TODO: return id tarefa
                        iniciar_tarefa(cmd.args.listArgs.get(0));
                    }
                }
                case "concluir_tarefa":{
                    if(cmd.args.size == 1){
                        long id = Long.valueOf(cmd.args.listArgs.get(0));
                        return concluir_tarefa(id);
                    }
                    return "Erro: Concluir tarefa\n";
                }
                case "pedido_notificacao":{
                    //TODO:
                    return "Erro: Não implementado\n";
                }
                case "listar_notificacoes":{
                    //TODO:
                    return "Erro: Não implementado\n";
                }
                case "listar_items":{
                    return listar_items();
                }
                case "listar_tarefas":{
                    return listar_tarefas();
                }
                case "listar_tarefas_activas":{
                    return listar_tarefas_activas();
                }
                case "listar_tarefas_concluidas":{
                    return listar_tarefas_concluidas();
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
