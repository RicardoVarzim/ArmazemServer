package BusinessLayer;

import java.util.ArrayList;
import java.util.HashMap;

public class Clientes {
	private HashMap< String,Cliente >clientes;
	private HashMap< Long,ArrayList< String >>notificar;
	private Facade listner;
	
	public Clientes( Facade facade )
	{
		this.clientes = new HashMap< String,Cliente >();
		this.notificar = new HashMap< Long,ArrayList< String >>();
		this.listner = facade;
	}
	
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
	
	/*
	public boolean isAdmin( String Cliente )
	{
		return( clientes.get( Cliente ).isAdmin() );
	}
	*/
	
	public boolean is_logged_in( String cliente )
	{
		if( clientes.containsKey( cliente ) )
			return clientes.get( cliente ).getvalidado();
		
		return false;
	}
	
	public boolean pedido_notificacao( String cliente,ArrayList< Long >tarefas )
	{
		boolean b = false;
		if( clientes.containsKey( cliente ) )
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
	
	public void listen( Long tarefa_id )
	{
		if( !clientes.isEmpty() ){
                    if(!notificar.isEmpty())
                        for( String cliente : notificar.get( tarefa_id ) )
                                    if( clientes.get( cliente ).listen( tarefa_id ) )
                                            listner.notificar( cliente );
                }
			
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
