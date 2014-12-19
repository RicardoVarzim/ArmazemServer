package BusinessLayer;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Tarefas {
	private Armazem armazem;
	private Clientes clientes;
	
	private Counter counter;
	private HashMap< String,Tarefa >tarefas;
	private HashMap< Long,String >activas;
	private HashMap< Long,String >concluidas;
	private HashMap< Long,TarefaThread >running;
	
	public Tarefas( Armazem armazem, Clientes clientes )
	{
		this.armazem = armazem;
		counter = new Counter();
		tarefas = new HashMap< String,Tarefa >();
		activas = new HashMap< Long,String >();
		concluidas = new HashMap< Long,String >();
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
	
	
	public long iniciar_tarefa( String item )
	{
		long tarefa_id = counter.getC();
		TarefaThread thread = new TarefaThread( armazem,tarefa_id,tarefas.get( item ) );
		thread.addListner( this );
		activas.put( tarefa_id,item );
		running.put( tarefa_id,thread );
		thread.start();
		return tarefa_id;
	}
	
	
	public String concluir_tarefa( Long tarefa_id )
	{
            String res = "false";
            TarefaThread thread;

            if( activas.containsKey( tarefa_id ) )
            {
                    thread = running.get( tarefa_id );
                    thread.interrupt();
                    activas.remove( tarefa_id );
                    running.remove( tarefa_id );
                    clientes.listen( tarefa_id,"Interrompida" );
                    res="Interrompida";
            }

            if( concluidas.containsKey( tarefa_id ) )
            {
                    clientes.listen( tarefa_id,"Concluida" );
                    res="Concluida";
            }
            return res;
	}
	
	public void listen( Long tarefa_id )
	{
		if( activas.containsKey( tarefa_id ) )
		{
			concluidas.put( tarefa_id , activas.get( tarefa_id ) );
			activas.remove( tarefa_id );
		}
	}
	
	public synchronized String listar_tarefas()
	{
		StringBuilder s = new StringBuilder();
		for( Map.Entry< String,Tarefa >entry : tarefas.entrySet() ) 
		  s.append( entry.getKey()+":"+entry.getValue().toString()+" ");
		return s+"\n";
	}
	
	public synchronized String listar_tarefas_ativas()
	{
		StringBuilder s = new StringBuilder();
		s.append("Tarefas Em Execucao: \n");
		for( Map.Entry< Long,String >entry : activas.entrySet() ) 
		  s.append( entry.toString()+"\n");
		return s.toString();
	}
	
	public synchronized String listar_tarefas_concluidas()
	{
		StringBuilder s = new StringBuilder();
		s.append("Tarefas Ja Executadas: \n");
		for( Map.Entry< Long,String >entry : concluidas.entrySet() ) 
		  s.append( entry.toString()+"\n");
		return s+"\n";
	}

}
