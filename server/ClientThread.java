import java.io.IOException;
import java.net.Socket;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class ClientThread extends Thread {
	Socket client = null;
	Bibliography bib = null;
	BufferedReader input;
	PrintWriter output;
	
	public ClientThread(Socket client, Bibliography bib) {
		this.client = client;
		this.bib = bib;
	}
	
	public void run() {
		try {
			this.input = new BufferedReader(new InputStreamReader(this.client.getInputStream()));
			this.output = new PrintWriter(this.client.getOutputStream(), true);
			
			String msg = "";
			while((msg = input.readLine()) != null) {
				System.out.println(msg.replace('_', ' '));
				
				Datagram d = new Datagram(msg);
				
				parse(d, output);
				
				msg = "";
			}
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
	
	private void parse(Datagram data, PrintWriter out) {
		if(data.msg.startsWith("SUBMIT")) {
			String r = bib.Add(data.book);
			this.output.println(r);
		}
		else if(data.msg.startsWith("UPDATE")) {
			String r = bib.Update(data.book);
			this.output.println(r);
		}
		else if(data.msg.startsWith("GET")) {
			String r = bib.Get(data);
			this.output.println(r);
		}
		else if(data.msg.startsWith("REMOVE")) {
			String r = bib.Remove(data.book);
			this.output.println(r);
		}
		
		System.out.println(bib.toString());
	}
}
