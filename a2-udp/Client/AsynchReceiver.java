
public class AsynchReceiver extends Thread{
	Client r;
	AsynchReceiver(Client r){
		this.r = r;
	}
	public void run() {
		r.Collect();
	}
}
