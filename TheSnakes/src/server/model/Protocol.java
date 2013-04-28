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

		   private Socket client;
		   private DataInputStream fromClient;
		   private ObjectOutputStream toClient;
		   private int player;
		   private ArrayList<Snake> snakes = new ArrayList<Snake>();

		   public Protocol(Socket client, int player) 
		   {
		     this.client = client;
		     this.player = player;
		   }
		   
		   //need to modify. get input takes int. this converts. 
		   //client listener always checks if stream != 0(aka no move)
		   public void getInput(){
			   try{
			   fromClient = new DataInputStream(client.getInputStream());

		         		        	 
		            int move = fromClient.readInt();
		            int dx,dy;
		            switch(move){
		            case MOVE_NONE:
		            	System.out.println("No move made ");

		            	break;
		            case MOVE_LEFT:
		            	System.out.println("Left move made ");

			    		 dx = -1;
			    		 dy = 0;
			    		 snakes.get(player).move(dx, dy);
		            	break;
		            case MOVE_RIGHT:
		            	System.out.println("Right move made ");
			    		 dx = 1;
			    		 dy = 0;
			    		 snakes.get(player).move(dx, dy);
		            	break;
		            case MOVE_FASTER:
		            	System.out.println("Faster move made ");
			    		 dx = 0;
			    		 dy = -1;
			    		 snakes.get(player).move(dx, dy);

		            	break;
		            case MOVE_SLOWER:
		            	System.out.println("Slower move made ");
			    		 dx = 0;
			    		 dy = 1;
			    		 snakes.get(player).move(dx, dy);

		            	break;	
		            case MOVE_EXIT:	
		            	System.out.println("Exit move made ");
		            	client.close();
		            	//needs to notify other threads.
		            	break;	
		            case MOVE_ERROR:	
		            	System.out.println("Exit move made ");
		            	client.close();
		            	//needs to notify other threads.
		            	break;
		            }
		            
		           
		        	 //send snakes. need to implement serializable.
			   }catch(Exception e){
				   
			   }
			   



		         
		    }


		   public void sendPacket(Packet p1){
			   
			   try{
		         toClient = new ObjectOutputStream(client.getOutputStream());
		         toClient.writeObject(p1);
			   }catch(Exception e){
				   System.out.println(e.getStackTrace().toString());
			   }
		   }

			        
		   
		  public void setSnakes(ArrayList<Snake> s1){
			   snakes = s1;
		   }
			}

