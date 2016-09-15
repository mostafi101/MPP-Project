package edu.mum.cs.cs401.dao.impl;

import java.util.ArrayList;
import java.util.List;

import edu.mum.cs.cs401.dao.BookCopyDAO;
import edu.mum.cs.cs401.dao.DataAccess;
import edu.mum.cs.cs401.entity.BookCopy;

public class BookCopyDAOImpl implements BookCopyDAO{
	
	private String cdr = System.getProperty("user.dir");
	private String bookJson =cdr + "/src/edu/mum/cs/cs401/dao/bookcopy.json";
	private static List<BookCopy> list;
	
	private BookCopyDAOImpl(){
		loadList();
	}
	
	private static BookCopyDAOImpl bookCopyDAO = new BookCopyDAOImpl();
	
	public static BookCopyDAOImpl getInstance() {
		return bookCopyDAO;
	}

	@Override
	public List<BookCopy> getAll() {
		return list;
	}

	@Override
	public void addBookCopies(List<BookCopy> bookcopy) {
		list.addAll(bookcopy);
		DataAccess.save(list, bookJson);
	}

	@Override
	public List<BookCopy> searchBookCopies(String isbn) {
		List<BookCopy> listBook = new ArrayList<BookCopy>();
		for (BookCopy bc : list) {
			if (bc.getIsbn().equals(isbn)) {
				listBook.add(bc);
			}
		}
		return listBook;
	}

	public void loadList(){
		list = DataAccess.getBookCopyList(bookJson);
	}
}
