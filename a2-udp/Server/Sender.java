import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.Arrays;

public class Sender {
	DatagramSocket udpData;
	DatagramSocket udpAck;
	InetAddress receiverAddress;
	
	String host;
	int dataPort;
	int ackPort;
	String filename;
	int timeout;
	
	int dataSize = 100; // 100 bytes of data per packet
	
	public Sender(String host, int dataPort, int ackPort, String filename, int timeout) {
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
			this.udpData = new DatagramSocket(this.dataPort, this.receiverAddress);
			this.udpAck = new DatagramSocket(this.ackPort, this.receiverAddress);
			this.udpAck.setSoTimeout(this.timeout);
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}
	
	public void Transmit() {
        char[] buffer = new char[dataSize];
        int read = 0;
        int sequence = 0;
        boolean eof = false;

        try {

            FileReader fileReader = new FileReader(this.filename);

            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while((read = bufferedReader.read(buffer)) > 0) {
            	if(read < dataSize) eof = true;
            	
            	byte[] data = this.toBytes(buffer);  // Get data
                
                Packet p = new Packet(sequence, eof, read, data); // Create new packet
                
                byte[] packet = p.GetPacket(); // Get sendable packet
                		
                DatagramPacket dp = new DatagramPacket(packet, packet.length); // Create packet to send
                
                byte[] ackPacket = new byte[1]; // Initialize empty ack buffer
                
                DatagramPacket ack = new DatagramPacket(ackPacket, ackPacket.length); // Create empty ack packet
            	
            	boolean ackValid = false;
            	while(!ackValid) {
            		this.udpData.send(dp);
                    
                    try {
                    	this.udpAck.receive(ack);
                    	
                    	Ack a = new Ack(ack.getData());
                    	if(a.Validate(p) == false) throw new IOException("Invalid ack");
                    	ackValid = true;
                    }
                    catch(IOException ex) { // will also catch SocketTimeoutException
                    	// do nothing, let loop handle it
                    }
            	}
            	
            	// Sent packet and received valid ack for it
            	// Increment sequence number and continue
                sequence++;
            }   

            
            bufferedReader.close();       
            fileReader.close();
        }
        catch(FileNotFoundException ex) {
            System.out.println("Unable to open " + this.filename);               
        }
        catch(IOException ex) {
            System.out.println("Error reading " + this.filename);
        }
	}
	
	
	private byte[] toBytes(char[] chars) {
	    CharBuffer charBuffer = CharBuffer.wrap(chars);
	    ByteBuffer byteBuffer = Charset.forName("UTF-8").encode(charBuffer);
	    byte[] bytes = Arrays.copyOfRange(byteBuffer.array(),
	            byteBuffer.position(), byteBuffer.limit());
	    Arrays.fill(charBuffer.array(), '\u0000'); // clear sensitive data
	    Arrays.fill(byteBuffer.array(), (byte) 0); // clear sensitive data
	    return bytes;
	}
}
