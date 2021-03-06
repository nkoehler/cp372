import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import javax.swing.JLabel;


public class Client {
	DatagramSocket udpData;
	DatagramSocket udpAck;
	InetAddress senderAddress;
	
	String host;
	int dataPort;
	int ackPort;
	String filename;
	boolean reliable;
	JLabel packetNum;
	JLabel status;
	
	int dataSize = 100; // 100 bytes of data per packet
	
	public Client(String host, int dataPort, int ackPort, String filename, boolean reliable, JLabel packetNum, JLabel status) {
		this.host = host;
		this.dataPort = dataPort;
		this.ackPort = ackPort;
		this.filename = filename;
		this.reliable = reliable;
		this.packetNum = packetNum;
		this.status = status;
		
		try {
			this.senderAddress = InetAddress.getByName(this.host);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		
		try {
			this.udpData = new DatagramSocket(this.dataPort, this.senderAddress);
			this.udpAck = new DatagramSocket();
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}
	
	public void Collect() {
        int sequence = 0;
        int rcheck = 0;
        boolean eof = false;
        status.setText("Transmitting");

        try {
        	FileOutputStream fp = new FileOutputStream(this.filename);
        	
        	while(!eof) {
        		Packet fakePacketForBufferOnly = new Packet(sequence, eof, this.dataSize, new byte[this.dataSize]);
        		byte[] buffer = fakePacketForBufferOnly.GetPacket();
        		
        		DatagramPacket dp = new DatagramPacket(buffer, buffer.length, this.senderAddress, this.dataPort);
        		try {
        			System.out.println("Waiting for data...");
        			
        			this.udpData.receive(dp);
        			
        			Packet p = new Packet(dp.getData());
        			
        			if(sequence < p.sequence) throw new IOException("Out of order packet");
        			else if(sequence == p.sequence) fp.write(p.data, 0, p.size);
        			
        			System.out.println("Received " + dp.getData().length + " bytes with payload of " + p.size + " bytes for sequence " + p.sequence);
        			
        			Ack a = new Ack(p.sequence); // Ack the sequence received, not our current sequence
        			
        			byte[] ackbuf = a.GetPacket();
        			
        			DatagramPacket ack = new DatagramPacket(ackbuf, ackbuf.length, this.senderAddress, this.ackPort);
        			if(this.reliable == false) {
        				if(rcheck++%10 != 0) {
        					this.udpAck.send(ack);
        					packetNum.setText(Integer.toString(sequence));
        				}
        			}else {
        				this.udpAck.send(ack);
    					packetNum.setText(Integer.toString(sequence));
        			}
        			
        			
        			System.out.println("Sent ack for sequence " + p.sequence);
        			
        			if(sequence == p.sequence) {
        				sequence++; 
        				eof = p.eof;
        			}
        			
        		}
        		catch(IOException ex) {
        			
        		}
        		
        	}
        	
        	System.out.println("Transmission complete, " + this.filename + " written");
        	status.setText("Transmission Finished");
            
            fp.close();
        }
        catch(FileNotFoundException ex) {
            System.out.println("Unable to open " + this.filename);               
        }
        catch(IOException ex) {
            System.out.println("Error reading " + this.filename);
        }
	}
}
