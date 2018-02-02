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
		frame.setBounds(100, 100, 800, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnSend_Command = new JButton("Send");
		btnSend_Command.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//send button
			}
		});
		btnSend_Command.setBounds(355, 38, 89, 23);
		frame.getContentPane().add(btnSend_Command);
		
		JButton btnClear_Response = new JButton("Clear");
		btnClear_Response.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_Type.setText("");
				textField_ISBN.setText("");
				textField_Title.setText("");
				textField_Author.setText("");
				textField_Publisher.setText("");
				textField_Year.setText("");
				//bottom clear button
			}
		});
		btnClear_Response.setBounds(355, 72, 89, 23);
		frame.getContentPane().add(btnClear_Response);
		
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
		lblNewLabel_4.setBounds(10, 238, 46, 14);
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
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(467, 37, 307, 298);
		frame.getContentPane().add(textArea);
	}
}
