package BusinessLayer;

public class Counter {
	private long c;
	
	public Counter(){ c=0; }
	
	public synchronized long getC() 
	{ 
		long value = c;
		if( c>9999999 )
			c = 0;
		c++;
		return value;
	}
}
