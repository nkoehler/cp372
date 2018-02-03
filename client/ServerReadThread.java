import java.io.BufferedReader;
import java.io.IOException;
import javax.swing.JTextPane;

public class ServerReadThread extends Thread {

	public BufferedReader input;
	JTextPane display;
	
	public ServerReadThread(BufferedReader input, JTextPane display) {
		this.input = input;
		this.display = display;
	}
	
	public void run() {
		String msg = "";
		try {
			while((msg = this.input.readLine()) != null) {
				this.display.setText(this.display.getText() + msg + "\n");
				
				msg = "";
			}
		} catch (IOException e) {
			//e.printStackTrace();
		}
	}
	
}
