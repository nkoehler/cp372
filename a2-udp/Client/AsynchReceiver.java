
public class AsynchReceiver extends Thread{
	Receiver r;
	AsynchReceiver(Receiver r){
		this.r = r;
	}
	public void run() {
		r.Collect();
	}
}
