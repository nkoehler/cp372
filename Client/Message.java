
public class Message {
	String type,ISBN,title,author,publisher,year;
	String message;
	
	public Message(String type, String ISBN,String title,String author,String publisher,String year) {
		this.type = type;
		this.ISBN = ISBN;
		this.title = title;
		this.author = author;
		this.publisher = publisher;
		this.year = year;
	}
	
	boolean ISBN_check(String ISBN) {
		if(ISBN.length() == 13) {
			try{
				int x[] = new int[13];
				for(int i=0; i<13;i++) {
					x[i] = Character.getNumericValue(ISBN.charAt(i));
				}
				int s = x[1] + (3*x[2]) + x[3] + (3*x[4]) + x[5] + (3*x[6]) + x[7] + (3*x[8]) + x[9] + (3*x[10]) + x[11] + (3*x[12]);
				s = 10 - (s%10);
				if(s == x[13]) {
					return true;
				}
			}catch(Exception e) {
				return false;
			}
		}
		return false;
	}
	boolean year_check(String year) {
		try {
			Integer.parseInt(year);
			return true;
		}catch(Exception e) {
			return false;
		}
	}
	boolean validate() {
		/*if(this.type == "SUBMIT"||this.type == "UPDATE") {
			if(ISBN_check(this.ISBN)) {
				if(this.year != "") {
					return year_check(this.year);
				}else {
					return true;
				}
			}
		}else if(this.type == "REMOVE"||this.type == "GET") {
			if(this.ISBN != "") {
				if(ISBN_check(this.ISBN)) {
					if(this.year != "") {
						return year_check(this.year);
					}else {
						return true;
					}
				}
			}
		}
		return false;*/
		return true;
	}
	public String toString() {
		this.message = this.type;
		if(this.ISBN != "") {
			this.message = this.message + "_" + "ISBN" + " "+ this.ISBN;
		}
		if(this.title != "") {
			this.message = this.message + "_" + "TITLE" + " "+ this.title;
		}
		if(this.author != "") {
			this.message = this.message + "_" + "AUTHOR" + " "+ this.author;
		}
		if(this.publisher != "") {
			this.message = this.message + "_" + "PUBLISHER" + " "+ this.publisher;
		}
		if(this.year != "") {
			this.message = this.message + "_" + "YEAR" + " "+ this.year;
		}
		System.out.println(this.message);
		return this.message;
	}
}
