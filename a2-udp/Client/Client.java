
public class Client {

	public static void main(String[] args) {
		if(args.length < 4) {
			System.err.println("Usage: host dataPort ackPort filename");
			return;
		}
		
		String host = args[0];
		int dataPort = Integer.parseInt(args[1]);
		int ackPort = Integer.parseInt(args[2]);
		String filename = args[3];
		
		Receiver receiver = new Receiver(host, dataPort, ackPort, filename);
		receiver.Collect();

	}

}
