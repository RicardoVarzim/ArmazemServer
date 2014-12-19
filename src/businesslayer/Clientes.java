package BusinessLayer;

import java.util.ArrayList;
import java.util.HashMap;

public class Clientes {
	private HashMap< String,Cliente >clientes;
	private HashMap< Long,ArrayList< String >>notificar;
	private Facade listner;
	
	public Clientes()
	{
		this.clientes = new HashMap< String,Cliente >();
		this.notificar = new HashMap< Long,ArrayList< String >>();
	}
	
	public void addListner( Facade listner ){ this.listner = listner; }
	
	public boolean registar_cliente( String cliente,String password )
	{ 
		if( clientes.containsKey( cliente ) )
			return false;
		
		clientes.put( cliente,new Cliente( cliente,password ) );
		return true;
	}
	
	
	public boolean cliente_login( String cliente,String password )
	{ 
		if( !clientes.containsKey( cliente ) )
			return false;
		
		return clientes.get( cliente ).login( password );
	}
	
	public boolean pedido_notificacao( Long[] tarefas,String cliente )
	{
		boolean b = false;
		if( clientes.containsKey( cliente ) )
			if( clientes.get( cliente ).getvalidado() )
				b = true;
				for( Long tarefa : tarefas )
				{
					if( notificar.containsKey( tarefa ) )
						notificar.get( tarefa ).add( cliente );
					else
					{
						ArrayList< String > arraylist = new ArrayList< String >();
						arraylist.add( cliente );
						notificar.put( tarefa,arraylist );
					}
				}
		return b;
	}
	
	public void listen( Long tarefa_id,String estado )
	{
		for( String cliente : notificar.get( tarefa_id ) )
			if( clientes.get( cliente ).listen( tarefa_id,estado ) )
				listner.notificar( cliente );
	}
	
	@Override
	public String toString()
	{
		StringBuilder s = new StringBuilder();
		s.append( ": Clientes :\n" );
		for( Cliente cliente : clientes.values() )
			s.append( cliente.toString() );
		return s.toString();
	}
}
