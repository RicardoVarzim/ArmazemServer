package BusinessLayer;

/*
 * 
 * 
 */
public class TarefaThread extends Thread {
	private Armazem armazem;
	private Long tarefa_id;
	private Tarefa tarefa;
	private Tarefas listner;
	
	public TarefaThread( Armazem armazem,Long tarefa_id,Tarefa tarefa )
	{
		this.armazem = armazem;
		this.tarefa_id = tarefa_id;
		this.tarefa = tarefa;
	}
	
	public void addListner( Tarefas listner ){ this.listner = listner; }
	public void notifyListner(){ listner.listen( this.tarefa_id ); }
	
	public void run() 
	{
		try {
			armazem.consumir( tarefa.getItems() );
			notifyListner();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{}
	}

}
