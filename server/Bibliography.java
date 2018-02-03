import java.util.ArrayList;

public class Bibliography {
	ArrayList<Book> list = null;
	
	public Bibliography() {
		this.list = new ArrayList<Book>();
	}
	
	public synchronized String Add(Book b) {
		for(int i = 0; i < this.list.size(); i++)
			if(this.list.get(i).isbn.equals(b.isbn))
				return "Book already in bibliography.";

		this.list.add(b);
		
		return "Book successfully added.";
	}
	
	public synchronized String Remove(Book b) {
		ArrayList<Book> books = new ArrayList<Book>();
		
		for(int i = 0; i < this.list.size(); i++) {
			Book tmp = this.list.get(i);
			
			if(		
					(b.isbn.isEmpty() || tmp.isbn.equals(b.isbn)) &&
					(b.title.isEmpty() || tmp.title.equals(b.title)) &&
					(b.author.isEmpty() || tmp.author.equals(b.author)) &&
					(b.publisher.isEmpty() || tmp.publisher.equals(b.publisher)) &&
					(b.year.isEmpty() || tmp.year.equals(b.year))
					) {
				books.add(tmp);
				
			}
				
		}
		
		for(int i = 0; i < books.size(); i++)
			this.list.remove(books.get(i));
		
		if(books.size() > 0)
			return "Book(s) successfully removed.";
		else
			return "Book(s) does not exist in bibliography.";
	}
	
	public synchronized String Update(Book b) {
		for(int i = 0; i < this.list.size(); i++) {
			if(this.list.get(i).isbn.equals(b.isbn)) {
				this.list.set(i, this.list.get(i).Update(b));
				return "Book successfully updated.";
			}
		}
			
		return "Book does not exist in bibliography.";	
	}
	
	public String Get(Datagram data) {
		if(data.getAll) {
			return this.toString();
		}
		else {
			Book b = data.book;
			ArrayList<Book> books = new ArrayList<Book>();
			
			for(int i = 0; i < this.list.size(); i++) {
				Book tmp = this.list.get(i);
				
				if(
						(b.isbn.isEmpty() || tmp.isbn.equals(b.isbn)) &&
						(b.title.isEmpty() || tmp.title.equals(b.title)) &&
						(b.author.isEmpty() || tmp.author.equals(b.author)) &&
						(b.publisher.isEmpty() || tmp.publisher.equals(b.publisher)) &&
						(b.year.isEmpty() || tmp.year.equals(b.year))
						) {
					books.add(tmp);
					
				}
					
			}
			
			String s = "";
			
			if(books.size() > 0)
				for(int i = 0; i < books.size(); i++)
					s += books.get(i).toString();
			else
				s = "Book(s) does not exist in bibliography.";
			
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
