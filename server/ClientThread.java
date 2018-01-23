import java.io.IOException;
import java.net.Socket;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class ClientThread extends Thread {
	Socket client = null;
	Bibliography bib = null;
	
	public ClientThread(Socket client, Bibliography bib) {
		this.client = client;
		this.bib = bib;
	}
	
	public void run() {
		try {
			// read client commands in the future
			BufferedReader input = new BufferedReader(new InputStreamReader(client.getInputStream()));
			PrintWriter output = new PrintWriter(client.getOutputStream(), true);
			
			String msg = "";
			while(true) {
				while((msg += input.readLine()) != null);
				
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
			bib.Add(data.book);
		}
		else if(data.msg.startsWith("UPDATE")) {
			bib.Update(data.book);
		}
		else if(data.msg.startsWith("GET")) {
			// out.write(book.tostring or something);
		}
		else if(data.msg.startsWith("REMOVE")) {
			bib.Remove(data.book);
		}
	}
}
