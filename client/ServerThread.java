import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;

public class ServerThread extends Thread {
	Socket server = null;
	String host;
	int port;
	
	public ServerThread(String host, int port) {
		this.host = host;
		this.port = port;
	}
	
	public void run() {
		try {
			this.server = new Socket(this.host, this.port);
			
			BufferedReader input = new BufferedReader(new InputStreamReader(this.server.getInputStream()));
			PrintWriter output = new PrintWriter(this.server.getOutputStream(), true);
		
			output.print(
					"SUBMIT\r\n" + 
					"ISBN 9783161484100\r\n" + 
					"TITLE Modular Algorithms in Symbolic Summation and Symbolic Integration\r\n" + 
					"AUTHOR Gerhard\r\n" + 
					"PUBLISHER Mir\r\n" +
					"\r\n");
		}
		catch(UnknownHostException e) {
			System.err.println("Unable to find host '" + this.host + "'.");
		}
		catch(ConnectException e) {
			System.err.println(this.host + " refused connection.");
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
				this.server.close();
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
