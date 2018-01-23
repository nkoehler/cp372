
public class Datagram {
	public String msg = null;
	public Book book = null;
	
	public Datagram(String data) {
		String[] arr = data.split("\n");
		
		this.msg = arr[0];
		
		this.book = new Book();
		
		for(int i = 1; i < arr.length; i++) {
			int index = arr[i].indexOf(' ');
			String element = arr[i].substring(0, index);
			String payload = arr[i].substring(index, arr[i].length() - 1);
			
			switch(element) {
			case "ISBN":
				this.book.isbn = Integer.parseInt(payload);
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
				this.book.year = Integer.parseInt(payload);
				break;
			}
		}
	}
}
