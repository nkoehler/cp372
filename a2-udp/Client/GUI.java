import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JToggleButton;

public class GUI {

	private JFrame frame;
	private JTextField HostAddres;
	private JTextField RcvPort;
	private JTextField SendPort;
	private JTextField filename;
	private final JToggleButton tglbtnReliability = new JToggleButton("Reliable");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
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
	public GUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 355, 243);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		HostAddres = new JTextField();
		HostAddres.setBounds(81, 11, 221, 20);
		frame.getContentPane().add(HostAddres);
		HostAddres.setColumns(10);
		
		RcvPort = new JTextField();
		RcvPort.setBounds(81, 42, 221, 20);
		frame.getContentPane().add(RcvPort);
		RcvPort.setColumns(10);
		
		SendPort = new JTextField();
		SendPort.setBounds(81, 73, 221, 20);
		frame.getContentPane().add(SendPort);
		SendPort.setColumns(10);
		
		filename = new JTextField();
		filename.setBounds(81, 104, 221, 20);
		frame.getContentPane().add(filename);
		filename.setColumns(10);
		
		JButton btnTransfer = new JButton("Transfer");
		btnTransfer.setBounds(81, 135, 89, 23);
		frame.getContentPane().add(btnTransfer);
		
		JLabel lblPacketsReceived = new JLabel("0");
		lblPacketsReceived.setBounds(256, 169, 46, 14);
		frame.getContentPane().add(lblPacketsReceived);
		
		JLabel lblNewLabel_1 = new JLabel("Host address");
		lblNewLabel_1.setBounds(10, 14, 117, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Receiver Port");
		lblNewLabel_2.setBounds(10, 45, 117, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Sender Port");
		lblNewLabel_3.setBounds(10, 76, 117, 14);
		frame.getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Filename");
		lblNewLabel_4.setBounds(10, 107, 46, 14);
		frame.getContentPane().add(lblNewLabel_4);
		tglbtnReliability.setSelected(true);
		tglbtnReliability.setBounds(180, 135, 122, 23);
		frame.getContentPane().add(tglbtnReliability);
		
		JLabel lblNewLabel = new JLabel("Current number of received in-order packets");
		lblNewLabel.setBounds(10, 169, 236, 14);
		frame.getContentPane().add(lblNewLabel);
	}
}
