package BusinessLayer;

import java.util.ArrayList;

public class Cliente {
	private String nome;
	private String password;
	private boolean validado = false;
	//private boolean admin = false;
	private ArrayList< Long >tarefas;

	public Cliente( String nome,String password )
	{
		this.nome = nome;
		this.password = password;
		this.tarefas = new ArrayList< Long >();
	}
	
	public String getNome(){ return this.nome; }
	public String getPassword(){ return this.password; }
	public boolean getvalidado(){ return this.validado; }
	//public boolean isAdmin(){ return this.admin; }
	
	public void addTarefa( Long tarefa_id ){ this.tarefas.add( tarefa_id ); }
	//public void setAdmin(){ this.admin = true; }
	
	public boolean login( String password )
	{ 
		this.validado = this.password.equals( password );
		return validado;
	}

	public boolean listen( Long tarefa_id )
	{
		tarefas.remove( tarefa_id );
		return tarefas.isEmpty();
	}
	
	@Override
	public String toString()
	{
		StringBuilder s = new StringBuilder();
		s.append( "Nome :"+nome+" , " );
		s.append( "Validado :"+validado );
		return s.toString();
	}
}
