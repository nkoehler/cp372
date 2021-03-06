
public class Datagram {
	public String msg = null;
	public Book book = null;
	public boolean getAll = false;
	
	public Datagram(String data) {
		String[] arr = data.split("_");
		
		this.msg = arr[0];
		
		this.book = new Book();
		
		for(int i = 1; i < arr.length; i++) {
			if(arr[i].startsWith("ALL")) {
				getAll = true;
				return;
			}
			
			int index = arr[i].indexOf(' ');
			String element = arr[i].substring(0, index);
			String payload = arr[i].substring(index + 1, arr[i].length());
			
			switch(element) {
			case "ISBN":
				this.book.isbn = payload;
				break;
			
			case "TITLE":
				this.book.title = payload;
				break;
				
			case "AUTHOR":
				this.book.author = payload;
				break;
				
			case "PUBLISHER":
				this.book.publisher = payload;
				break;
				
			case "YEAR":
				this.book.year = payload;
				break;
			}
		}
	}
}
