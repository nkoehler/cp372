import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Receiver {
	DatagramSocket udpData;
	DatagramSocket udpAck;
	InetAddress senderAddress;
	
	String host;
	int dataPort;
	int ackPort;
	String filename;
	
	int dataSize = 100; // 100 bytes of data per packet
	
	public Receiver(String host, int dataPort, int ackPort, String filename) {
		this.host = host;
		this.dataPort = dataPort;
		this.ackPort = ackPort;
		this.filename = filename;
		
		try {
			this.senderAddress = InetAddress.getByName(this.host);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		
		try {
			this.udpData = new DatagramSocket(this.dataPort, this.senderAddress);
			this.udpAck = new DatagramSocket(this.ackPort, this.senderAddress);
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}
	
	public void Collect() {
        int sequence = 0;
        boolean eof = false;

        try {
        	BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.filename), "utf-8"));
        	
        	while(!eof) {
        		Packet fakePacketForBufferOnly = new Packet(sequence, eof, this.dataSize, new byte[this.dataSize]);
        		byte[] buffer = fakePacketForBufferOnly.GetPacket();
        		
        		DatagramPacket dp = new DatagramPacket(buffer, buffer.length);
        		try {
        			this.udpData.receive(dp);
        			
        			Packet p = new Packet(dp.getData());
        			if(sequence < p.sequence) throw new IOException("Out of order packet");
        			else if(sequence == p.sequence) writer.write(new String(p.data));
        			
        			Ack a = new Ack(p.sequence); // Ack the sequence received, not our current sequence
        			
        			byte[] ackbuf = a.GetPacket();
        			
        			DatagramPacket ack = new DatagramPacket(ackbuf, ackbuf.length);
        			
        			this.udpAck.send(ack);
        			
        			if(sequence == p.sequence) {
        				sequence++; 
        				eof = p.eof;
        			}
        			
        		}
        		catch(IOException ex) {
        			
        		}
        		
        	}

            
            writer.close();
        }
        catch(FileNotFoundException ex) {
            System.out.println("Unable to open " + this.filename);               
        }
        catch(IOException ex) {
            System.out.println("Error reading " + this.filename);
        }
	}
}
