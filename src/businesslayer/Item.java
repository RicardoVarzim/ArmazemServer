package BusinessLayer;
/*
 * 
 * 
 */
public class Item {
	private String nome = "-Empty-";
	private int quantidade = 0;
	
	public Item( String nome,int quantidade )
	{
		this.nome=nome;
		this.quantidade=quantidade;
	}
	
	//public synchronized int getQuantidade(){ return this.quantidade; }
	
	public synchronized void remove( int quantidade ) throws InterruptedException
	{ 
		while( this.quantidade<quantidade )
		{
			//System.out.println("Waiting...");
			wait();
		}
		this.quantidade-=quantidade;
		notifyAll();
	}
	
	public synchronized void add( int quantidade )
	{ 
		this.quantidade+=quantidade;
		notifyAll();
	}
	
	@Override
	public synchronized String toString()
	{
		return nome+" : "+quantidade+"\n";
	}
}
