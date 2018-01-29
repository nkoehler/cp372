import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Bibliography {
	List<Book> list = null;
	
	public Bibliography() {
		this.list = Collections.synchronizedList(new ArrayList<Book>());
	}
	
	public synchronized String Add(Book b) {
		for(int i = 0; i < this.list.size(); i++)
			if(this.list.get(i).isbn == b.isbn)
				return "Book already in bibliography.";

		this.list.add(b);
		
		return "Book successfully added.";
	}
	
	public synchronized String Remove(Book b) {
		if(this.list.removeIf(obj -> obj.isbn == b.isbn))
			return "Book successfully removed.";
		else
			return "Book does not exist in bibliography.";
	}
	
	public synchronized String Update(Book b) {
		for(int i = 0; i < this.list.size(); i++) {
			if(this.list.get(i).isbn == b.isbn) {
				this.list.set(i, this.list.get(i).Update(b));
				return "Book successfully updated.";
			}
		}
			
		return "Book does not exist in bibliography.";	
	}
	
	public String Get(Datagram data) {
		if(data.getAll) {
			String s = "";
			for(int i = 0; i < this.list.size(); i++)
				s += this.list.get(i).toString();
			return s;
		}
		else {
			Book b = data.book;
			ArrayList<Book> books = new ArrayList<Book>();
			
			for(int i = 0; i < this.list.size(); i++) {
				Book tmp = this.list.get(i);
				
				if(tmp.isbn != "" && tmp.isbn == b.isbn) {
					books.add(tmp);
					break;
				}
				
				// algorithm goes here
			}
			
			
			String s = "";
			for(int i = 0; i < books.size(); i++)
				s += books.get(i).toString();
			return s;
		}
	}
	
	public String toString() {
		String s = "";
		for(int i = 0; i < this.list.size(); i++)
			s += (i + 1) + ".\n" + this.list.get(i).toString();
		if(s.isEmpty())
			s = "Empty bibliography.";
		return s;
	}
	
}
