
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
				int s = x[0] + (3*x[1]) + x[2] + (3*x[3]) + x[4] + (3*x[5]) + x[6] + (3*x[7]) + x[8] + (3*x[9]) + x[10] + (3*x[11]);
				s = 10 -(s%10);
				if(s == x[12]) {
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
		if(this.type.equals("SUBMIT")||this.type.equals("UPDATE")) {
			if(ISBN_check(this.ISBN)) {
				if(!this.year.equals("")) {
					return year_check(this.year);
				}else {
					return true;
				}
			}
		}else if(this.type.equals("REMOVE")||this.type.equals("GET")) {
			if(!this.ISBN.equals("")) {
				if(ISBN_check(this.ISBN)) {
					if(!this.year.equals("")) {
						return year_check(this.year);
					}else {
						return true;
					}
				}
			}
		}
		return false;
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
