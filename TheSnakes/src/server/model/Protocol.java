package server.model;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
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
	 * change to inputstreamreader? i.e trivia
	 */

		   private Socket client;
		   private DataInputStream fromClient;
		   private DataOutputStream toClient;
		   private int player;
		   private int output[] = {0,0,0,0};
		   private ArrayList<Snake> snakes = new ArrayList<Snake>();
		   public Protocol(Socket client, int player) 
		   {
		     this.client = client;
		     this.player = player;
		   }
		   
		   public void run() {

			   while(true){
			      try {

			         fromClient = new DataInputStream(client.getInputStream());
			         toClient = new DataOutputStream(client.getOutputStream());
			         		        	 
			            int move = fromClient.readInt();
			            
			            switch(move){
			            case MOVE_NONE:
			            	System.out.println("No move made ");
			            	output[player] = MOVE_NONE;
			            	break;
			            case MOVE_LEFT:
			            	System.out.println("Left move made ");
			            	output[player] = MOVE_LEFT;
			            	break;
			            case MOVE_RIGHT:
			            	System.out.println("Right move made ");
			            	output[player] = MOVE_RIGHT;
			            	break;
			            case MOVE_FASTER:
			            	System.out.println("Faster move made ");
			            	output[player] = MOVE_FASTER;
			            	break;
			            case MOVE_SLOWER:
			            	System.out.println("Slower move made ");
			            	output[player] = MOVE_SLOWER;
			            	break;	
			            case MOVE_EXIT:	
			            	System.out.println("Exit move made ");
			            	output[player] = MOVE_EXIT;
			            	client.close();
			            	//needs to notify other threads.
			            	break;	
			            case MOVE_ERROR:	
			            	System.out.println("Exit move made ");
			            	output[player] = MOVE_ERROR;
			            	client.close();
			            	//needs to notify other threads.
			            	break;
			            }

			        	 //send snakes. need to implement serializable.


			         
			    }
			    catch(Exception ex) {
			      System.err.println(ex);
			      
			    }
			   }
		   }
		  public void setSnakes(ArrayList<Snake> s1){
			   snakes = s1;
		   }
			}
		   
