
public class Book {
	public int isbn;
	public int year;
	public String title;
	public String author;
	public String publisher;
	
	public Book() {
		this.isbn = -1;
		this.year = -1;
		this.title = "";
		this.author = "";
		this.publisher = "";
	}
	
	public Book(int isbn, int year, String title, String author, String publisher) {
		this.isbn = isbn;
		this.year = year;
		this.title = title;
		this.author = author;
		this.publisher = publisher;
	}
	
	public String toString() {
		String s = "ISBN " + this.isbn + "\n";

		if(this.title.length() > 0)
			s += "TITLE " + this.title + "\n";
		if(this.author.length() > 0)
		    s += "AUTHOR " + this.author + "\n";
		if(this.publisher.length() > 0)
			s += "PUBLISHER " + this.publisher + "\n";
		if(this.year >= 0)
			s += "YEAR " + this.year + "\n";
		
		s += "\n";
		
		return s;
	}
}
