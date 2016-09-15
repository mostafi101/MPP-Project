package edu.mum.cs.cs401.controller;

import java.awt.TextField;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import edu.mum.cs.cs401.dao.BookDAO;
import edu.mum.cs.cs401.dao.PersonDAO;
import edu.mum.cs.cs401.dao.impl.BookDAOImpl;
import edu.mum.cs.cs401.dao.impl.PersonDAOImpl;
import edu.mum.cs.cs401.entity.Address;
import edu.mum.cs.cs401.entity.Author;
import edu.mum.cs.cs401.entity.Book;
import edu.mum.cs.cs401.entity.Person;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

public class AddBookController extends Controller{
	
	private BookDAO bookDAO = BookDAOImpl.getInstance();
	@FXML
	TextField title;
	
	@FXML
	TextField isbn;
	
	@FXML
	TextField description;
	
	@FXML
	TextField firstName;
	
	@FXML
	TextField lastName;
	
	@FXML
	TextField authorsBio;
	
	public void addNewBook(ActionEvent actionEvent) throws IOException {
		
			Book book = new Book();
			book.setTitle(title.getText());
			book.setIsbn(isbn.getText());
			book.setDescription(description.getText());

			Author author = new Author();
			author.setFirstName(firstName.getText());
			author.setLastName(lastName.getText());
			author.setBio(authorsBio.getText());
			
			List<Author> authors = new ArrayList<Author>();
			authors.add(author);
			
			book.setAuthors(authors);
			
			List<Book> list = new ArrayList<Book>();
			list.add(book);
			
			bookDAO.addBooks(list);
			// TODO save member
		
	}
	
}
