import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JTextPane;

public class ServerCommunication {
	Socket server = null;
	BufferedReader input;
	PrintWriter output;
	String host;
	int port;
	
	JTextPane display;
	Thread readThread;
	
	public ServerCommunication(String host, int port, JTextPane display) {
		this.host = host;
		this.port = port;
		this.display = display;
		
		try {
			this.server = new Socket(this.host, this.port);
			System.out.println("Connected to server " + this.server.getInetAddress().toString() + " : " + this.server.getLocalPort());
			
			this.input = new BufferedReader(new InputStreamReader(this.server.getInputStream()));
			this.output = new PrintWriter(this.server.getOutputStream(), true);
			
			this.readThread = new Thread(new ServerReadThread(input, display));
			this.readThread.start();
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
	}
	
	public void Close() {
		try {
			this.readThread.interrupt();
			this.server.close();
			System.out.println("Disconnected.");
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public synchronized void Send(String msg) {
		output.println(msg);
	}
	
}
