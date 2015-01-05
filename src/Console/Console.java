package Console;
/*
 *
 *
 */
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;
import java.util.TreeMap;
import BusinessLayer.BusinessIO;
import BusinessLayer.Facade;


public class Console implements Observer, Runnable{
	private static BusinessIO facade;
        private static Scanner s;

        public Console(Facade f){
            this.facade = f;
        }
        
	public static String start_menu()
	{
		StringBuilder s = new StringBuilder();
		s.append("\n1 : Login\n");
		s.append("2: Registar\n");
		s.append("0 : Sair");
		return s.toString();
	}
	
	public static boolean startHandler( int op, BusinessIO facade )
	{
		boolean b = false;
		
		switch( op )
		{
			case 1 : b = facade.cliente_login( read( "Nome de Utilizador" ),read( "Password") );
							 if( !b ) 
								 System.out.println( "Dados invalidos." );
							 break;
			
			case 2 : b = facade.registar_cliente( read( "Nome de Utilizador" ),read( "Password") );
							 if( !b ) 
								 System.out.println( "Nome de utilizador em uso." );
			
			default : break;
		}
		
		return b;
	}
	
	
	public static String menu()
	{  
		StringBuilder s = new StringBuilder();
		s.append("\n1 : Registar Cliente\t\t2 : Cliente Login\n");
		s.append("3 : Abastecer\t\t\t4 : Definir Tarefa\n");
		s.append("5 : Iniciar Tarefa\t\t6 : Concluir Tarefa\n");
		s.append("7 : Pedido Notificacao\t\t8 : Listar Notificacoes\n");
		s.append("9 : Listar Items\t\t10: Listar Tarefas\n");
		s.append("11: Listar Tarefas Activas\t12: Listar Tarefas Concluidas\n");
		s.append("0 : Sair");
		return s.toString();
	}

	public static void menuHandler( int op, BusinessIO facade )
	{
		Long l;
		String s;
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
							 
			case 3 : facade.abastecer( read( "Item" ), readInt( "Quantidade" ) );
							 System.out.println( facade.listar_items() );
				 			 break;
				 			 
			case 4 : b=facade.definir_tarefa( read( "Nome da Tarefa" ),readTree() );
							 System.out.print( b+" :" );
							 System.out.println( facade.listar_tarefas() );
							 break;
							 
			case 5 : l=facade.iniciar_tarefa( read( "Nome da Tarefa" ) );
							 System.out.print( l+" :" );
							 System.out.println( facade.activas()+facade.listar_tarefas_concluidas() );
							 break;
							 
			case 6 : s=facade.concluir_tarefa( readLong( "Tarefa_id" ) );
							 System.out.print( s+" \n" );
							 break;
							 
			case 7 : b=facade.pedido_notificacao( read( "Cliente" ),readLongs( "Tarefa" ) );
							 System.out.print( b+" \n" );
							 break;
							 
			case 8 : System.out.print( facade.listar_notificacoes().toString() );
							 break;
							 
			case 9 : System.out.print( facade.listar_items() );
			 				 break;
			 				 
			case 10 : System.out.print( facade.listar_tarefas() );
			 					break;
			 					
			case 11 : System.out.print( facade.activas() );
								break;
								
			case 12 : System.out.print( facade.executadas() );
								break;
								
			case 13 : System.out.println( facade.listar_notificacoes().toString() );
								break;
			
			default : break;
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
				System.out.println( "Opcao invalida." );
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
	
	private static int readInt( String campo )
	{
		int op=0;
		s = new Scanner( System.in );
		while( op==0 )
		{
			System.out.println( campo+" :" );
			try{
				op = s.nextInt();
			} catch( InputMismatchException e )
			{ 
				System.out.println( "Valor invalido." );
				s.next();
			}
		}
		return op;
	}
	
	private static Long readLong( String campo )
	{
		Long op = new Long(-1);
		s = new Scanner( System.in );
		while( op<0 )
		{
			System.out.println( campo+" :" );
			try{
				op = s.nextLong();
			} catch( InputMismatchException e )
			{ 
				System.out.println( "Valor invalido." );
				s.next();
			}
		}
		return op;
	}
	
	private static ArrayList< Long > readLongs( String campo )
	{
		Long l = new Long( 0 );
		ArrayList< Long >tarefas = new ArrayList< Long >();
		while( l>=0 )
		{
			l = readLong( campo );
			tarefas.add( l );
			System.out.println( "Continuar?(y/n): " );
			if( !read( "" ).equals("y") )
				l=(long) -1;
		}
		return tarefas;
	}
	
	private static TreeMap< String,Integer >readTree()
	{
		int in=0;
		String item;
		TreeMap< String,Integer >items = new TreeMap< String,Integer >();
		s = new Scanner( System.in );
		
		while( in>=0 )
		{
			item = read( "Item" );
			in = readInt( "Quantidade" );
			items.put( item,in );
			System.out.println( "Continuar?(y/n): " );
			if( !read( "" ).equals("y") )
				in = -1;
		}
		return items;
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
	  System.out.println( facade.activas() );
	  System.out.println( facade.executadas() );
	}

	public void update( Observable o,ArrayList< String > arg )
	{
		
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

    public void run() {
        boolean logIn = false;
        int op=-1;

        while( !logIn )
        {
                System.out.println( start_menu() );
                op = readOp( 2 );
                logIn = startHandler( op,facade );
        }
        while( op != 0 )
        {
                System.out.println( menu() );
                op = readOp( 13 );
                menuHandler( op,facade );
        }
    }
}
