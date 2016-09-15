package edu.mum.cs.cs401.dao;

import java.util.List;

import edu.mum.cs.cs401.entity.AvailableStatus;
import edu.mum.cs.cs401.entity.BookCopy;

public interface BookCopyDAO {

	public List<BookCopy> getAll();
	
	public void addBookCopies(List<BookCopy> bookcopy);
	
	public List<BookCopy> searchBookCopies(String isbn);
	
	public void updateBookCopyStatus(String copyNumber, AvailableStatus status);
}
