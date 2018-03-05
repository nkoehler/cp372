import java.nio.ByteBuffer;

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
		this.sequence = this.convertByteSequenceToSequence(packet);
		this.eof = this.convertByteEOFToEOF(packet);
		this.size = this.convertByteSizeToSize(packet);
		this.data = this.getData(packet);
	}
	
	public byte[] GetPacket() {
		byte[] seq = ByteBuffer.allocate(4).putInt(this.sequence).array(); // Support up to 214gb file
		byte[] end = ByteBuffer.allocate(4).putInt(this.convertBooleanToInt(this.eof)).array();
		byte[] siz = ByteBuffer.allocate(4).putInt(this.size).array();
		
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
	
	private byte[] createByteHeaderArray(byte[] seq, byte[] end, byte siz[]) {
		byte[] result = new byte[seq.length + end.length + siz.length];
		result = this.concatenateByteArrays(seq, end);
		result = this.concatenateByteArrays(result, siz);
		return result;
	}
	
	private int convertByteSequenceToSequence(byte[] packet)
	{
		byte[] result = new byte[4];
		result[0] = packet[0];
		result[1] = packet[1];
		result[2] = packet[2];
		result[3] = packet[3];
		return ByteBuffer.wrap(result).getInt();
	}
	
	private boolean convertByteEOFToEOF(byte[] packet)
	{
		byte[] result = new byte[4];
		result[0] = packet[4];
		result[1] = packet[5];
		result[2] = packet[6];
		result[3] = packet[7];
		int res = ByteBuffer.wrap(result).getInt();
		if(res == 1)
			return true;
		else
			return false;
	}
	
	private int convertByteSizeToSize(byte[] packet)
	{
		byte[] result = new byte[4];
		result[0] = packet[8];
		result[1] = packet[9];
		result[2] = packet[10];
		result[3] = packet[11];
		return ByteBuffer.wrap(result).getInt();
	}
	
	private byte[] getData(byte[] packet) {
		byte[] result = new byte[packet.length - 12];
		System.arraycopy(packet, 12, result, 0, packet.length - 12);
		return result;
	}
	
	private int convertBooleanToInt(boolean b) {
		return this.eof ? 1 : 0;
	}
}
