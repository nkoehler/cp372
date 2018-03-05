import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

public class Sender {

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
		
		Server s = new Server(host, dataPort, ackPort, filename, timeout);
		ZonedDateTime now = ZonedDateTime.now();
		s.Transmit();
		long seconds = now.until(ZonedDateTime.now(), ChronoUnit.MILLIS);
		System.out.println("Total Transmission Time is " + seconds + "ms");
	}

}
