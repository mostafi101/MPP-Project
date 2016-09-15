package edu.mum.cs.cs401.controller;

import java.util.ArrayList;
import java.util.List;

import edu.mum.cs.cs401.constant.ContextDataKey;
import edu.mum.cs.cs401.context.ApplicationDataContext;
import edu.mum.cs.cs401.dao.impl.BookCopyDAOImpl;
import edu.mum.cs.cs401.dao.impl.BookDAOImpl;
import edu.mum.cs.cs401.entity.Book;
import edu.mum.cs.cs401.entity.BookCopy;
import edu.mum.cs.cs401.entity.Person;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class CheckoutController extends Controller {
	
	@FXML
	private Label personCheckoutLabel;
	
	@FXML
	private TableView<Book> bookTableView;
	
	@FXML
	private TableView<BookCopy> bookCopyTableView;
	
	@FXML
	private TableColumn<Book, String> tableColumnIsbn;
	
	@FXML
	private TableColumn<Book, String> tableColumTitle;
	
	@FXML
	private TableColumn<Book, String> tableColumnDescription;
	
	@FXML
	private TextField searchIsbnTextField;
	
	@FXML
	private TableColumn<BookCopy, String> tableColumCopynumber;
	
	@FXML
	private TableColumn<BookCopy, Boolean> tableColumnAvailability;

	@Override
	public void prepareUI() {
		super.prepareUI();
		//set label
		Person person = (Person) ApplicationDataContext.getInstance().get(ContextDataKey.CHECKOUT_MEMBER);
		personCheckoutLabel.setText("Checking out for " + person.getFirstName() + " id: " + person.getId());
		
		// setup table
		tableColumnIsbn.setCellValueFactory(new PropertyValueFactory<Book, String>("isbn"));
		tableColumTitle.setCellValueFactory(new PropertyValueFactory<Book, String>("title"));
		tableColumnDescription.setCellValueFactory(new PropertyValueFactory<Book, String>("description"));
		setAllMemberToTable();
		tableColumCopynumber.setCellValueFactory(new PropertyValueFactory<BookCopy, String>("copyNumber"));
		tableColumnAvailability.setCellValueFactory(new PropertyValueFactory<BookCopy, Boolean>("isAvailable"));
		
		//bind table
		bookTableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
		    if (newSelection != null) {
		    	List<BookCopy> bookCopies = BookCopyDAOImpl.getInstance().searchBookCopies(newSelection.getIsbn());
		    	ObservableList<BookCopy> data = FXCollections.observableArrayList(bookCopies);
		    	bookCopyTableView.setItems(data);
		    } else {
		    	bookCopyTableView.getItems().clear();
		    }
		});
	}
	
	public void searchIsbnButton(ActionEvent actionEvent) {
		String isbn = searchIsbnTextField.getText();
		if (isbn.isEmpty()) {
			setAllMemberToTable();
		} else {
			Book search = BookDAOImpl.getInstance().searchBook(isbn);
			ObservableList<Book> data = null;
			if (search != null) {
				List<Book> list = new ArrayList<Book>();
				list.add(search);
				data = FXCollections.observableArrayList(list );
			}
			bookTableView.setItems(data);
		}
	}

	public void checkoutButton(ActionEvent actionEvent) {
		
	}

	private void setAllMemberToTable() {
		List<Book> list = BookDAOImpl.getInstance().getAll();
		ObservableList<Book> data = FXCollections.observableArrayList(list);
		bookTableView.setItems(data);
	}
}
