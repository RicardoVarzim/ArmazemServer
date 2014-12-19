package BusinessLayer;

import java.io.EOFException;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.TreeMap;
import server.Server;
//ricardovarzim@gmail.com
public class Console {
	private static BusinessIO facade;
	private static Scanner s;
	private static Server server;
        
	public static void main( String[] args ) throws InterruptedException
	{
            facade = new Facade();
            int op=-1;

            while( op != 0 )
            {
                    System.out.println( menu() );
                    op = readOp( 12 );
                    menuHandler( op,facade );
            }
	}
	
	public void teste1( BusinessIO facade )
	{
		TreeMap<String, Integer> items, items1, items2;
		
		facade.abastecer("a", 10);
		facade.abastecer("b", 10);
		facade.abastecer("c", 10);
		facade.abastecer("d", 10);
		facade.abastecer("e", 10);
		facade.abastecer("f", 10);
		facade.abastecer("g", 10);
		
		items  = new TreeMap< String,Integer >();
		items1 = new TreeMap< String,Integer >();
		items2 = new TreeMap< String,Integer >();
		
		items.put(  "a",4 );
		items1.put( "b",1 );
		items2.put( "e",2 ); items2.put( "c",2 );
	
		facade.definir_tarefa( "Tarefa0",items  );
		facade.definir_tarefa( "Tarefa1",items1 );
		facade.definir_tarefa( "Tarefa2",items2 );
		
		facade.iniciar_tarefa( "Tarefa0" );
		facade.iniciar_tarefa( "Tarefa0" );
		facade.iniciar_tarefa( "Tarefa0" );
		facade.abastecer( "a",10 );
		
		System.out.println( facade.listar_items() );
		System.out.println( facade.listar_tarefas() );
	  System.out.println( facade.listar_tarefas_activas() );
	  System.out.println( facade.listar_tarefas_concluidas() );
	}
	
	public static String menu()
	{  
		StringBuilder s = new StringBuilder();
		s.append("\n1 : Registar Cliente\t\t2 : Cliente Login\n");
		s.append("3 : Abastecer\t\t\t4 : Definir Tarefa\n");
		s.append("5 : Iniciar Tarefa\t\t6 : Concluir Tarefa\n");
		s.append("7 : Pedido Notificacao\t\t8 : Listar Notificacoes\n");
		s.append("9 : Lstar Items\t\t\t10: Listar Tarefas\n");
		s.append("11: Listar Tarefas Activas\t12: Listar Tarefas Concluidas\n");
		s.append("0 : Sair");
		return s.toString();
	}
	
	public static void menuHandler( int op, BusinessIO facade )
	{
		boolean b;
		
		switch( op )
		{
			case 1 : b=facade.registar_cliente( read( "Nome" ),read( "Password" ) );
			 				 System.out.print( b+" :" );
							 System.out.println( facade.listar_clientes() );
							 break;
			
			case 2 : b=facade.cliente_login( read( "Nome" ),read( "Password" ) );
			 				 System.out.print( b+" :" );
							 System.out.println( facade.listar_clientes() );
							 break;
							 
			case 3 : 
				 			 break;
		}
		
	}
	
	private static int readOp( int max )
	{
		int op = -1;
		s = new Scanner( System.in );
		while( op<0 )
		{
			try{
				op = s.nextInt();
				if( op>max )
					op = -1;
			} catch( InputMismatchException e )
			{ 
				System.out.println( "Opcao Invalida!" );
				s.next();
			}
		}
		return op;	
	}
	
	private static String read( String campo )
	{
		s = new Scanner( System.in );
		
		System.out.println( campo+" :" );
		return s.nextLine();
	}
}
