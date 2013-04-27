package server.model;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class Protocol extends Thread implements SnakeInterface{

	/*This class will hopefully be called on each thread.
	 * (client)
	 * iterates through all clients.. each thread can use this? or just server
	 * near-simultaneously sends messages?
	 * need a broadcast method of sorts?
	 * goal: get int from clients.
	 * 		 send type ArrayList<Snake> to all.
	 */

		   private ArrayList<Socket> clients;
		   private ArrayList<DataInputStream> fromClients;
		   private ArrayList<DataOutputStream> toClients;
		   private int output[] = {0,0,0,0};
		   private ArrayList<Snake> snakes = new ArrayList<Snake>();
		   public Protocol(ArrayList<Socket> clients) 
		   {
		     this.clients = clients;
		   }
		   
		   public void run() {
			   int i = 0;
			      try {
			         for(; i < clients.size();i++){
			         fromClients.add(new DataInputStream(clients.get(1).getInputStream()));
			         toClients.add(new DataOutputStream(clients.get(1).getOutputStream()));
			         		        	 
			            int move = fromClients.get(i).readInt();
			            
			            switch(move){
			            case MOVE_NONE:
			            	System.out.println("No move made ");
			            	output[i] = MOVE_NONE;
			            	break;
			            case MOVE_LEFT:
			            	System.out.println("Left move made ");
			            	output[i] = MOVE_LEFT;
			            	break;
			            case MOVE_RIGHT:
			            	System.out.println("Right move made ");
			            	output[i] = MOVE_RIGHT;
			            	break;
			            case MOVE_FASTER:
			            	System.out.println("Faster move made ");
			            	output[i] = MOVE_FASTER;
			            	break;
			            case MOVE_SLOWER:
			            	System.out.println("Slower move made ");
			            	output[i] = MOVE_SLOWER;
			            	break;	
			            case MOVE_EXIT:	
			            	System.out.println("Exit move made ");
			            	output[i] = MOVE_EXIT;
			            	clients.get(i).close();
			            	//needs to notify other threads.
			            	break;	
			            }
			            //output sent to update snake pos.
			            //constantly resets so it always goes 
			            // from thread 1-4 over and over.
			         if(i == clients.size() -1)
			        	 i = 0;
			         for(int j = 0; j < clients.size();j++){
			        	 //send snakes. need to implement serializable.
			         }
			         }
			         
			    }
			    catch(Exception ex) {
			      System.err.println(ex);
			    }
			      
			  }
		  public void setSnakes(ArrayList<Snake> s1){
			   snakes = s1;
		   }
			}
