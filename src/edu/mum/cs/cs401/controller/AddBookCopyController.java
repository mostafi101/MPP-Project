package edu.mum.cs.cs401.controller;

import java.io.IOException;

import edu.mum.cs.cs401.dao.BookCopyDAO;
import edu.mum.cs.cs401.dao.impl.BookCopyDAOImpl;
import edu.mum.cs.cs401.entity.BookCopy;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddBookCopyController extends Controller {

	private BookCopyDAO bookCopyDAO = BookCopyDAOImpl.getInstance();
	
	@FXML
	TextField copyNumber;


	public void addBookCopy(ActionEvent actionEvent) throws IOException {

		BookCopy bookCopy = new BookCopy();

		
		bookCopyDAO.addBookCopy(bookCopy);
		Stage popupStage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
		popupStage.close();
	}
}
