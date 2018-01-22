import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static void main(String[] args) {
		if(args.length <= 0) {
			System.out.println("Please enter a port as the argument.");
			System.exit(-1);
		}
		
		try {
			int port = Integer.parseInt(args[0]);
			
			System.out.println("Starting server on port " + port);
			
			ServerSocket server = new ServerSocket(port);
			
			while(!server.isClosed()) {
				System.out.println("Waiting for client...");
				
				Socket client = server.accept();
				
				Thread clientThread = new Thread(new ClientThread(client));
				
				clientThread.start();
				
				System.out.println("Started thread for client " + client.getInetAddress().toString() + " : " + client.getLocalPort());
			}
			
			server.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
	}

}
