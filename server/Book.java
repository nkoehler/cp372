
public class Book {
	public String isbn;
	public String year;
	public String title;
	public String author;
	public String publisher;
	
	public Book() {
		this.isbn = "";
		this.year = "";
		this.title = "";
		this.author = "";
		this.publisher = "";
	}
	
	public Book(String isbn, String year, String title, String author, String publisher) {
		this.isbn = isbn;
		this.year = year;
		this.title = title;
		this.author = author;
		this.publisher = publisher;
	}
	
	public Book Update(Book b) {
		if(!b.year.isEmpty())
			this.year = b.year;
		if(!b.title.isEmpty())
			this.title = b.title;
		if(!b.author.isEmpty())
			this.author = b.author;
		if(!b.publisher.isEmpty())
			this.publisher = b.publisher;
		
		return this;
	}
	
	public String toString() {
		String s = "ISBN " + this.isbn + "\n";

		if(this.title.length() > 0)
			s += "TITLE " + this.title + "\n";
		if(this.author.length() > 0)
		    s += "AUTHOR " + this.author + "\n";
		if(this.publisher.length() > 0)
			s += "PUBLISHER " + this.publisher + "\n";
		if(this.year.length() > 0)
			s += "YEAR " + this.year + "\n";
		
		s += "\n";
		
		return s;
	}
}
