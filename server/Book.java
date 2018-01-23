
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
}
