
public class Packet {
	public int sequence;
	public boolean eof;
	public int size;
	public byte[] data;
	
	public Packet(int sequence, boolean eof, int size, byte[] data) {
		this.sequence = sequence;
		this.eof = eof;
		this.size = size;
		this.data = data;
	}
	
	public Packet(byte[] packet) {
		this.sequence = packet[0];
		this.eof = this.getEOFStatus(packet[1]);
		this.size = packet[2];
		this.data = this.getData(packet);
	}
	
	public byte[] GetPacket() {
		byte seq = (byte) this.sequence;
		byte end = (byte) (this.eof ? 1 : 0);
		byte siz = (byte) this.size;
		
		byte[] header = this.createByteHeaderArray(seq, end, siz);
		byte[] packet = this.concatenateByteArrays(header, this.data);
		
		return packet;
	}
	
	private byte[] concatenateByteArrays(byte[] a, byte[] b) {
	    byte[] result = new byte[a.length + b.length]; 
	    System.arraycopy(a, 0, result, 0, a.length); 
	    System.arraycopy(b, 0, result, a.length, b.length); 
	    return result;
	}
	
	private byte[] createByteHeaderArray(byte seq, byte end, byte siz) {
		byte[] result = new byte[3];
		result[0] = seq;
		result[1] = end;
		result[2] = siz;
		return result;
	}
	
	private boolean getEOFStatus(byte packetEOF) {
		if(packetEOF == 1)
			return true;
		else
			return false;
	}
	
	private byte[] getData(byte[] packet) {
		byte[] result = new byte[packet.length - 3];
		System.arraycopy(packet, 3, result, 0, packet.length - 3);
		return result;
	}
}
