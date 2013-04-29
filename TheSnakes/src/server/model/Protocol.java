package server.model;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class Protocol implements SnakeInterface{

	/*This class will hopefully be called on each thread.
	 * (client)
	 * iterates through all clients.. each thread can use this? or just server
	 * near-simultaneously sends messages?
	 * need a broadcast method of sorts?
	 * goal: get int from clients.
	 * 		 send type ArrayList<Snake> to all.
	 * change to inputstreamreader? i.e trivia
	 */
/*
		   private Socket client;
		   private DataInputStream fromClient;
		   private ObjectOutputStream toClient;
		   private int player;
		   private ArrayList<Snake> snakes = new ArrayList<Snake>();
*/
		   public Protocol() 
		   {
		   }
		   
		   //need to modify. get input takes int. this converts. 
		   //client listener always checks if stream != 0(aka no move)
		   public void getInput(int status) {
			   
			   try {
		            int dx,dy;
		            switch(status){
		            case MOVE_NONE:
		            	System.out.println("No move made ");

		            	break;
		            case MOVE_LEFT:
		            	System.out.println("Left move made ");
			    		 dx = -1;
			    		 dy = 0;
		            	break;
		            case MOVE_RIGHT:
		            	System.out.println("Right move made ");
			    		 dx = 1;
			    		 dy = 0;
		            	break;
		            case MOVE_FASTER:
		            	System.out.println("Faster move made ");
			    		 dx = 0;
			    		 dy = -1;
		            	break;
		            case MOVE_SLOWER:
		            	System.out.println("Slower move made ");
			    		 dx = 0;
			    		 dy = 1;
		            	break;	
		            case MOVE_EXIT:	
		            	System.out.println("Exit move made ");
		            	break;	
		            case MOVE_ERROR:	
		            	System.out.println("Exit move made ");
		            	break;
		            }
		            
		           
		        	 //send snakes. need to implement serializable.
			   }catch(Exception e){
				   
			   }
			   



		         
		    }

}

