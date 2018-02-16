import java.nio.ByteBuffer;

public class Ack {
	int sequence;
	
	public Ack(int sequence) {
		this.sequence = sequence;
	}
	
	public Ack(byte[] packet) {
		this.sequence = this.convertByteSequenceToSequence(packet);
	}
	
	public byte[] GetPacket() {
		byte[] result = ByteBuffer.allocate(4).putInt(this.sequence).array(); // Support up to 214gb file
		return result;
	}
	
	public boolean Validate(Packet a) {
		return a.sequence == this.sequence;
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
	
}
