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
	
	public synchronized int getQuantidade(){ return this.quantidade; }
	public String getNome(){ return this.nome; }
	
	public synchronized void remove( int quantidade )
	{ 
		while( this.quantidade<quantidade )
		{
			//System.out.println("Waiting...");
			try {
				wait();
			} catch (InterruptedException e) {}
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
