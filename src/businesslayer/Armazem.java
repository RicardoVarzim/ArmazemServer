package BusinessLayer;

import java.util.HashMap;
import java.util.Map;
/*
 * 
 * 
 */
public class Armazem {
	private Map< String,Item >armazem;
	
	
	public Armazem(){ this.armazem = new HashMap< String,Item >(); }
	
	
	public void consumir( Map< String,Integer >items ) throws InterruptedException 
	{
		for( Map.Entry< String,Integer >entry : items.entrySet() ) 
		{
			Item i = armazem.get( entry.getKey() );
			if ( i == null )
				armazem.put( entry.getKey() , new Item( entry.getKey(),0 ));
			i = armazem.get( entry.getKey() );
			i.remove( entry.getValue() );
		}
	}
	
	
	public void abastecer( String item,int quantidade )
	{
		if( armazem.containsKey( item ) )
			armazem.get( item ).add( quantidade );
		else
			armazem.put( item, new Item( item,quantidade ));
	}
	
	@Override
	public synchronized String toString()
	{
		StringBuilder s = new StringBuilder();
		
		s.append("\n");
		for( Item item : armazem.values() ) 
		  s.append( item.toString());
		
		return s.toString();
	}
	
	
	public synchronized HashMap< String,Integer >listar_items()
	{
		HashMap< String,Integer >lista = new HashMap< String,Integer >();
		
		for( Item i : armazem.values() )
			lista.put( i.getNome(), i.getQuantidade() );
		
		return lista;
	}
}
