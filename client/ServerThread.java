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
			System.out.println("Connected to server " + this.server.getInetAddress().toString() + " : " + this.server.getLocalPort());
			
			BufferedReader input = new BufferedReader(new InputStreamReader(this.server.getInputStream()));
			PrintWriter output = new PrintWriter(this.server.getOutputStream(), true);
		
			output.println(
					"SUBMIT_" + 
					"ISBN 9783161484100_" + 
					"TITLE Modular Algorithms in Symbolic Summation and Symbolic Integration_" + 
					"AUTHOR Gerhard_" + 
					"PUBLISHER Mir");
			
			output.println(
					"SUBMIT_" + 
					"ISBN 9783161484101_" + 
					"TITLE Nick's Guide On How To Get Graham To Do Something_" + 
					"YEAR 2018");
			
			output.println(
					"UPDATE_" + 
					"ISBN 9783161484100_" + 
					"AUTHOR Koehler_" + 
					"YEAR 2018");
			
			output.println(
					"REMOVE_" + 
					"ISBN 9783161484100_");
			
			System.out.println("Sent data.");
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
				System.out.println("Disconnected.");
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
