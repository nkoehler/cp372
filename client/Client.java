import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

public class Client {
	public ServerCommunication server;
	private JFrame frame;
	private JTextField textField_Type;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JTextField textField_ISBN;
	private JLabel lblNewLabel_2;
	private JTextField textField_Title;
	private JLabel lblNewLabel_3;
	private JTextField textField_Author;
	private JLabel lblNewLabel_4;
	private JTextField textField_Publisher;
	private JLabel lblNewLabel_5;
	private JTextField textField_Year;
	
	private JTextPane display;
	
	private int port;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					if(args.length <= 0) {
						System.out.println("Please enter a port as the argument.");
						System.exit(-1);
					}
						
					int port = Integer.parseInt(args[0]);
					
					System.out.println("Starting client on port " + port);
					
					Client window = new Client(port);
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
	public Client(int port) {
		this.port = port;
		initialize();
		ConnectToServer();
	}
	
	public void ConnectToServer() {
		this.server = new ServerCommunication("localhost", this.port, display);
	}
	
	public void DisconnectFromServer() {
		if(this.server != null)
			this.server.Close();
	}
	
	public void SendToServer(String msg) {
		if(this.server != null)
			this.server.Send(msg);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 800, 400);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		        DisconnectFromServer();
		        System.exit(0);
		    }
		});
		frame.getContentPane().setLayout(null);
		
		JButton btnSend = new JButton("Send");
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Message message = new Message(textField_Type.getText(),textField_ISBN.getText(),textField_Title.getText(),textField_Author.getText(),textField_Publisher.getText(),textField_Year.getText());
				if(message.validate()) {
					// send message.message
					SendToServer(message.toString());
					
				}else {
					//display error on the display pane
					display.setText("ERROR");
				}
			}
		});
		btnSend.setBounds(355, 38, 89, 23);
		frame.getContentPane().add(btnSend);
		
		JButton btnClearL = new JButton("<< Clear");
		btnClearL.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_Type.setText("");
				textField_ISBN.setText("");
				textField_Title.setText("");
				textField_Author.setText("");
				textField_Publisher.setText("");
				textField_Year.setText("");
				
			}
		});
		btnClearL.setBounds(355, 72, 89, 23);
		frame.getContentPane().add(btnClearL);
		
		textField_Type = new JTextField();
		textField_Type.setBounds(10, 39, 314, 20);
		frame.getContentPane().add(textField_Type);
		textField_Type.setColumns(10);
		
		lblNewLabel = new JLabel("Type");
		lblNewLabel.setBounds(10, 14, 46, 14);
		frame.getContentPane().add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("ISBN");
		lblNewLabel_1.setBounds(10, 70, 46, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		textField_ISBN = new JTextField();
		textField_ISBN.setBounds(10, 95, 314, 20);
		frame.getContentPane().add(textField_ISBN);
		textField_ISBN.setColumns(10);
		
		lblNewLabel_2 = new JLabel("Title");
		lblNewLabel_2.setBounds(10, 126, 46, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		textField_Title = new JTextField();
		textField_Title.setBounds(10, 151, 314, 20);
		frame.getContentPane().add(textField_Title);
		textField_Title.setColumns(10);
		
		lblNewLabel_3 = new JLabel("Author");
		lblNewLabel_3.setBounds(10, 182, 46, 14);
		frame.getContentPane().add(lblNewLabel_3);
		
		textField_Author = new JTextField();
		textField_Author.setBounds(10, 207, 314, 20);
		frame.getContentPane().add(textField_Author);
		textField_Author.setColumns(10);
		
		lblNewLabel_4 = new JLabel("Publisher");
		lblNewLabel_4.setBounds(10, 238, 89, 14);
		frame.getContentPane().add(lblNewLabel_4);
		
		textField_Publisher = new JTextField();
		textField_Publisher.setBounds(10, 263, 314, 20);
		frame.getContentPane().add(textField_Publisher);
		textField_Publisher.setColumns(10);
		
		lblNewLabel_5 = new JLabel("Year");
		lblNewLabel_5.setBounds(10, 294, 46, 14);
		frame.getContentPane().add(lblNewLabel_5);
		
		textField_Year = new JTextField();
		textField_Year.setBounds(10, 313, 314, 20);
		frame.getContentPane().add(textField_Year);
		textField_Year.setColumns(10);
		
		JButton btnGetAll = new JButton("Get All");
		btnGetAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String message = "GET_ALL";
				SendToServer(message);
			}
		});
		btnGetAll.setBounds(355, 140, 89, 23);
		frame.getContentPane().add(btnGetAll);
		
		display = new JTextPane();
		display.setBounds(454, 38, 320, 295);
		//frame.getContentPane().add(display);
		
		JScrollPane sp = new JScrollPane(display);
		sp.setBounds(454, 38, 320, 295);
	    frame.getContentPane().add(sp);
	    
	    JButton btnClearR = new JButton("Clear >>");
	    btnClearR.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent arg0) {
	    		display.setText("");
	    	}
	    });
	    btnClearR.setBounds(355, 106, 89, 23);
	    frame.getContentPane().add(btnClearR);
	}
}
