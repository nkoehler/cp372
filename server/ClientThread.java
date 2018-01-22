import java.io.IOException;
import java.net.Socket;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ClientThread extends Thread {
	Socket client = null;
	
	public ClientThread(Socket client) {
		this.client = client;
	}
	
	public void run() {
		try {
			// read client commands in the future
			BufferedReader input = new BufferedReader(new InputStreamReader(client.getInputStream()));
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
				this.client.close();
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
}
