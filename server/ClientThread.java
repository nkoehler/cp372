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
			BufferedReader input = new BufferedReader(new InputStreamReader(this.client.getInputStream()));
			PrintWriter output = new PrintWriter(this.client.getOutputStream(), true);
			
			String msg = "";
			while((msg = input.readLine()) != null) {
				//System.out.println(msg.replace('_', ' '));
				
				Datagram d = new Datagram(msg);
				
				System.out.print(d.book.toString());
				
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
			bib.Get(data);
		}
		else if(data.msg.startsWith("REMOVE")) {
			bib.Remove(data.book);
		}
		
		//System.out.println(bib.toString());
	}
}
