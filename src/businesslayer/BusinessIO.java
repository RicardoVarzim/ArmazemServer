package BusinessLayer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

public interface BusinessIO {
	
	public boolean registar_cliente( String cliente,String password );
	public boolean cliente_login( String cliente,String password );
	public boolean is_logged_in( String cliente );
	public String listar_clientes();
	public void abastecer( String item,int quantidade );
	public boolean definir_tarefa( String nome,TreeMap< String,Integer >items );
	public long iniciar_tarefa( String tarefa );
	public String concluir_tarefa( Long tarefa_id );
	public boolean pedido_notificacao( String cliente,ArrayList< Long >tarefas );
	public ArrayList< String >listar_notificacoes(); // NEEDS 2 BE OBSERVABLE
	public HashMap< String,Integer > listar_items();
	public ArrayList< String > tipos_tarefas();
	public TreeMap< String,Integer >items_tarefa( String tarefa );
	public ArrayList< HashMap< Long,String >>listar_tarefas_concluidas();
	
	// Deprecated
	public String activas();
	public String executadas();
	public TreeMap<String,TreeMap< String,Integer >>listar_tarefas();
}
