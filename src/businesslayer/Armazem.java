package BusinessLayer;

import java.util.HashMap;
import java.util.Map;
/*
 * 
 * 
 */
public class Armazem {
	//private ReentrantLock counter_lock = new ReentrantLock();
	//private int counter=0;															
	//private List< Item >quantidades = new ArrayList< Item >(); 													
	//private List< Condition >itemLocks = new ArrayList< Condition >();
	//private ReentrantLock chave_armazem = new ReentrantLock();
	private Map< String,Item >armazem = new HashMap<>();
	
	public void consumir( Map< String,Integer >items ) throws InterruptedException 
	{
		for( Map.Entry< String,Integer >entry : items.entrySet() ) 
		{
			Item i = armazem.get( entry.getKey() );
			if( i!=null )
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
}
