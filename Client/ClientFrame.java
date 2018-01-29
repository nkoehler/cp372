import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JScrollBar;
import javax.swing.JTextPane;
import javax.swing.JTextArea;

public class ClientFrame {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientFrame window = new ClientFrame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ClientFrame() {
		initialize();
		ConnectToServer();
	}
	
	public void ConnectToServer() {
		Thread serverThread = new Thread(new ServerThread("localhost", 5656));
		
		serverThread.start();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 600, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnSend_Command = new JButton("Send");
		btnSend_Command.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//send button
			}
		});
		btnSend_Command.setBounds(459, 38, 89, 23);
		frame.getContentPane().add(btnSend_Command);
		
		JButton btnClear_Command = new JButton("Clear");
		btnClear_Command.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//top clear button
			}
		});
		btnClear_Command.setBounds(459, 166, 89, 23);
		frame.getContentPane().add(btnClear_Command);
		
		JButton btnClear_Response = new JButton("Clear");
		btnClear_Response.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//bottom clear button
			}
		});
		btnClear_Response.setBounds(459, 312, 89, 23);
		frame.getContentPane().add(btnClear_Response);
		
		JTextArea textArea_Command = new JTextArea();
		textArea_Command.setBounds(10, 37, 439, 152);
		frame.getContentPane().add(textArea_Command);
		
		JTextArea textArea_Response = new JTextArea();
		textArea_Response.setBounds(10, 200, 439, 135);
		frame.getContentPane().add(textArea_Response);
	}
}
