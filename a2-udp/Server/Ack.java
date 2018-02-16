
public class Ack {
	int sequence;
	
	public Ack(int sequence) {
		this.sequence = sequence;
	}
	
	public Ack(byte[] packet) {
		this.sequence = packet[0];
	}
	
	public byte[] GetPacket() {
		byte[] result = new byte[1];
		result[0] = (byte) this.sequence;
		return result;
	}
	
	public boolean Validate(Packet a) {
		return a.sequence == this.sequence;
	}
}
