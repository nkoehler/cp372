import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Server {
	DatagramSocket udpData;
	DatagramSocket udpAck;
	InetAddress receiverAddress;
	
	String host;
	int dataPort;
	int ackPort;
	String filename;
	int timeout;
	
	int dataSize = 100; // 100 bytes of data per packet
	
	public Server(String host, int dataPort, int ackPort, String filename, int timeout) {
		this.host = host;
		this.dataPort = dataPort;
		this.ackPort = ackPort;
		this.filename = filename;
		this.timeout = timeout;
		
		try {
			this.receiverAddress = InetAddress.getByName(this.host);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		
		try {
			this.udpData = new DatagramSocket();
			this.udpAck = new DatagramSocket(this.ackPort, this.receiverAddress);
			this.udpAck.setSoTimeout(this.timeout);
		} catch (SocketException e) {
			e.printStackTrace();
		}
		
	}
	
	public void Transmit() {
        byte[] buffer = new byte[dataSize];
        int read = 0;
        int sequence = 0;
        boolean eof = false;

        try {

        	FileInputStream fp = new FileInputStream(this.filename);

            while((read = fp.read(buffer)) > 0) {
            	if(read < dataSize) eof = true;
            	
            	byte[] data = buffer;  // Get data
                
                Packet p = new Packet(sequence, eof, read, data); // Create new packet
                
                byte[] packet = p.GetPacket(); // Get sendable packet
					
                DatagramPacket dp = new DatagramPacket(packet, packet.length, this.receiverAddress, this.dataPort); // Create packet to send
                
                Ack fakeAck = new Ack(-1);
                
                byte[] ackPacket = fakeAck.GetPacket(); // Initialize fake empty ack buffer
                
                DatagramPacket ack = new DatagramPacket(ackPacket, ackPacket.length, this.receiverAddress, this.ackPort); // Create empty ack packet
            	
            	boolean ackValid = false;
            	while(!ackValid) {
                    try {
                    	System.out.println("Attempting send...");

                    	this.udpData.send(dp);
                    	
                    	this.udpAck.receive(ack);
                    	
                    	System.out.println("Sent " + packet.length + " bytes with payload of " + p.size + " bytes for sequence " + p.sequence);
                    	
                    	Ack a = new Ack(ack.getData());
                    	if(a.Validate(p) == false) throw new IOException("Invalid ack");
                    	ackValid = true;
                    	
                    	System.out.println("Received ack for sequence " + a.sequence);
                    }
                    catch(IOException ex) { // will also catch SocketTimeoutException
                    	// do nothing, let loop handle it
                    }
            	}
            	
            	// Sent packet and received valid ack for it
            	// Increment sequence number and continue
                sequence++;
            }   
            
            System.out.println("Transmission complete, " + this.filename + " sent");
            
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
