import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Bibliography {
	List<Book> list = null;
	
	public Bibliography() {
		this.list = Collections.synchronizedList(new ArrayList<Book>());
	}
	
	public void Add(Book b) {
		this.list.add(b);
	}
	
	public void Remove(Book b) {
		this.list.removeIf(obj -> obj.isbn == b.isbn);
	}
	
	public void Update(Book b) {
		for(int i = 0; i < this.list.size(); i++)
			if(this.list.get(i).isbn == b.isbn)
				this.list.set(i, b);
	}
	
}
