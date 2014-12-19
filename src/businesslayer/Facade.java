package BusinessLayer;

import java.util.ArrayList;
import java.util.TreeMap;

public class Facade implements BusinessIO {
	private Armazem armazem;
	private Tarefas tarefas;
	private Clientes clientes;
	private ArrayList< String >notificacoes;
	
	public Facade ()
	{
		this.armazem = new Armazem();
		this.clientes = new Clientes();
		this.tarefas = new Tarefas( armazem,clientes );
		this.notificacoes = new ArrayList< String >();
		clientes.addListner( this );
	}
	
	
	public boolean registar_cliente( String cliente,String password )
	{
		return clientes.registar_cliente( cliente,password );
	}
	
	
	public boolean cliente_login( String cliente,String password )
	{
		return clientes.cliente_login( cliente,password );
	}
	
	
	public String listar_clientes()
	{
		return clientes.toString();
	}
	
	
	public void abastecer( String item,int quantidade )
	{ 
		tarefas.abastecer( item,quantidade );
	}

	
	public boolean definir_tarefa( String nome,TreeMap< String,Integer >items )
	{	
		return tarefas.definir_tarefa( nome,items ); 
	}
	
	
	public long iniciar_tarefa( String tarefa )
	{ 
		return tarefas.iniciar_tarefa( tarefa );
	}
	
	
	public String concluir_tarefa( Long tarefa_id )
	{
		return tarefas.concluir_tarefa( tarefa_id );
	}
	
	
	public boolean pedido_notificacao( Long[] tarefas,String cliente ) 
	{
		return clientes.pedido_notificacao( tarefas,cliente );
	}
	
	
	public String listar_items()
	{
		return armazem.toString();
	}
	
	
	public String listar_tarefas()
	{ 
		return tarefas.listar_tarefas();
	}
	
	
	public String listar_tarefas_activas()
	{ 
		return tarefas.listar_tarefas_ativas();
	}
	
	
	public String listar_tarefas_concluidas()
	{ 
		return tarefas.listar_tarefas_concluidas();
	}
	
	
	public ArrayList< String >listar_notificacoes()
	{
		ArrayList< String >n = (ArrayList< String >) notificacoes.clone();
		notificacoes.clear();
		return n;
	}
	
	
	protected void notificar( String cliente )
	{
		notificacoes.add( cliente );
	}
}
