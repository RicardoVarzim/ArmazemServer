package BusinessLayer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Tarefas {
	private Armazem armazem;
	private Clientes clientes;
	
	private Counter counter;
	private HashMap< String,Tarefa >tarefas;
	private HashMap<Long, String> activas;
	private HashMap< Long,String >executadas;
	private HashMap< Long,TarefaThread >running;
	
	public Tarefas( Armazem armazem, Clientes clientes )
	{
		this.armazem = armazem;
		this.clientes = clientes;
		counter = new Counter();
		tarefas = new HashMap< String,Tarefa >();
		activas = new HashMap< Long,String >();
		executadas = new HashMap< Long,String >();
		running = new HashMap< Long,TarefaThread >();
	}
	
	
	public void abastecer( String item,int quantidade )
	{
		new AbastecerThread( armazem,item,quantidade ).start();
	}
	
	
	public boolean definir_tarefa( String nome,TreeMap< String,Integer >items )
	{
		if( tarefas.containsKey( nome ))
			return false;
		
		tarefas.put( nome,new Tarefa( nome,items ));
		return true;
	}
	
	
	public long iniciar_tarefa( String tarefa )
	{
		if( !tarefas.containsKey( tarefa ) )
			return -1;
		
		long tarefa_id = counter.getC();
		TarefaThread thread = new TarefaThread( armazem,tarefa_id,tarefas.get( tarefa ) );
		thread.addListner( this );
		activas.put( tarefa_id,tarefa );
		running.put( tarefa_id,thread );
		thread.start();
		return tarefa_id;
	}
	
	
	public String concluir_tarefa( Long tarefa_id )
	{
		TarefaThread thread;
		
		if( activas.containsKey( tarefa_id ) )
		{
			thread = running.get( tarefa_id );
			thread.interrupt();
			activas.remove( tarefa_id );
			running.remove( tarefa_id );
			clientes.listen( tarefa_id );
			return "Interrompida";
		}
		
		else
			if( executadas.containsKey( tarefa_id ) )
			{
				clientes.listen( tarefa_id );
				return "Concluida";
			}
		
		return "Inexistente";
	}
	
	
	public void listen( Long tarefa_id )
	{
		if( activas.containsKey( tarefa_id ) )
		{
			executadas.put( tarefa_id , activas.get( tarefa_id ) );
			activas.remove( tarefa_id );
		}
	}
	
	
	public synchronized String string_tarefas()
	{
		StringBuilder s = new StringBuilder();
		for( Map.Entry< String,Tarefa >entry : tarefas.entrySet() ) 
		  s.append( entry.getKey()+":"+entry.getValue().toString()+" ");
		return s+"\n";
	}
	
	
	public synchronized String ativas()
	{
		StringBuilder s = new StringBuilder();
		s.append("Tarefas Em Execucao: \n");
		for( Map.Entry< Long,String >entry : activas.entrySet() ) 
		  s.append( entry.toString()+"\n");
		return s.toString();
	}
	
	
	public synchronized String executadas()
	{
		StringBuilder s = new StringBuilder();
		s.append("Tarefas Ja Executadas: \n");
		for( Map.Entry< Long,String >entry : executadas.entrySet() ) 
		  s.append( entry.toString()+"\n");
		return s+"\n";
	}
	
	public synchronized TreeMap< String,TreeMap< String,Integer >> listar_tarefas()
	{
		TreeMap< String,TreeMap< String,Integer >>tm = new TreeMap< String,TreeMap< String,Integer >>();
		for( Map.Entry< String,Tarefa >entry : tarefas.entrySet() )
		{ 
			tm.put( entry.getKey(),entry.getValue().getItems() );
		}
		return tm;
	}
	
	public synchronized ArrayList< HashMap< Long,String >> listar_tarefas_concluidas()
	{
		ArrayList< HashMap< Long,String >> lista = new ArrayList< HashMap< Long,String >>();
		lista.add( activas ); lista.add( executadas );
		return lista;
	}
}
