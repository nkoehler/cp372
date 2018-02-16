
public class Server {

	public static void main(String[] args) {
		if(args.length < 5) {
			System.err.println("Usage: host dataPort ackPort filename timeout");
			return;
		}
		
		String host = args[0];
		int dataPort = Integer.parseInt(args[1]);
		int ackPort = Integer.parseInt(args[2]);
		String filename = args[3];
		int timeout = Integer.parseInt(args[4]);
		
		Sender s = new Sender(host, dataPort, ackPort, filename, timeout);
		s.Transmit();

	}

}
