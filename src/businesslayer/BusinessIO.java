package BusinessLayer;

import java.util.ArrayList;
import java.util.TreeMap;

public interface BusinessIO {
	
	public boolean registar_cliente( String cliente,String password );
	public boolean cliente_login( String cliente,String password );
	public String listar_clientes();
	public void abastecer( String item,int quantidade );
	public boolean definir_tarefa( String nome,TreeMap< String,Integer >items );
	public long iniciar_tarefa( String tarefa );
	public String concluir_tarefa( Long tarefa_id );
	public boolean pedido_notificacao( Long[] tarefas,String cliente );
	public ArrayList< String >listar_notificacoes();
	public String listar_items();
	public String listar_tarefas();
	public String listar_tarefas_activas();
	public String listar_tarefas_concluidas();
}
