package edu.mum.cs.cs401.controller;

import java.util.ArrayList;
import java.util.List;

import edu.mum.cs.cs401.constant.ContextDataKey;
import edu.mum.cs.cs401.context.ApplicationDataContext;
import edu.mum.cs.cs401.context.Context;
import edu.mum.cs.cs401.dao.impl.PersonDAOImpl;
import edu.mum.cs.cs401.entity.Person;
import edu.mum.cs.cs401.entity.Role;
import edu.mum.cs.cs401.view.AddMemberView;
import edu.mum.cs.cs401.view.CheckoutView;
import edu.mum.cs.cs401.view.RecordView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class DashBoardController extends Controller {
	
	@FXML
	private TabPane tabPane;

	@FXML
	private Tab adminTab;
	
	@FXML
	private Tab libTab;
	
	@FXML
	private TextField searchMemberIdText;
	
	@FXML
	private TableView<Person> memberTableView;
	
	@FXML
	private TableColumn<Person, Integer> tableColumnId;
	
	@FXML
	private TableColumn<Person, String> tableColumnFirstName;
	
	@FXML
	private TableColumn<Person, String> tableColumnLastName;
	
	@FXML
	private Button addBookCopyButton;
	
	@FXML
	private Button addBookButton;
	
	@Override
	public void prepareUI() {
		super.prepareUI();
		List<Role> roles = Context.getInstance().getUser().getRoles();
		tabPane.getTabs().clear();
		if (roles.contains(Role.ADMIN)) {
			tabPane.getTabs().add(adminTab);
			addBookCopyButton.setDisable(false);
			addBookButton.setDisable(false);
		} else {
			addBookCopyButton.setDisable(true);
			addBookButton.setDisable(true);
		}
		if (roles.contains(Role.LIBRARIAN)) {
			tabPane.getTabs().add(libTab);
			
			// init table
			tableColumnId.setCellValueFactory(new PropertyValueFactory<Person, Integer>("id"));
			tableColumnFirstName.setCellValueFactory(new PropertyValueFactory<Person, String>("firstName"));
			tableColumnLastName.setCellValueFactory(new PropertyValueFactory<Person, String>("lastName"));
			
			setAllMemberToTable();
		}
	}
	
	@Override
	public void backDashBoard(ActionEvent actionEvent) {
	}

	public void checkout(ActionEvent actionEvent) {
		Context.getInstance().changeScreen(actionEvent, CheckoutView.getInstance());
	}
	
	public void addMember(ActionEvent actionEvent) {
		Context.getInstance().changeScreen(actionEvent, AddMemberView.getInstance());
	}
	
	public void searchMember(ActionEvent actionEvent) {
		String id = searchMemberIdText.getText();
		if (id.isEmpty()) {
			setAllMemberToTable();
		} else {
			Person search = PersonDAOImpl.getInstance().search(id, Role.MEMBER);
			ObservableList<Person> data = null;
			if (search != null) {
				List<Person> list = new ArrayList<Person>();
				list.add(search);
				data = FXCollections.observableArrayList(list );
			}
			memberTableView.setItems(data);
		}
	}
	
	public void checkoutSceneButton(ActionEvent actionEvent) {
		Person selectedItem = memberTableView.getSelectionModel().getSelectedItem();
		if (selectedItem != null) {
			ApplicationDataContext.getInstance().put(ContextDataKey.CHECKOUT_MEMBER, selectedItem);
			Context.getInstance().changeScreen(actionEvent, CheckoutView.getInstance());
		}
	}
	
	private void setAllMemberToTable() {
		List<Person> list = PersonDAOImpl.getInstance().getAll(Role.MEMBER);
		ObservableList<Person> data = FXCollections.observableArrayList(list);
		memberTableView.setItems(data);
	}
	
	public void getRecords(ActionEvent actionEvent) {
		Person selectedItem = memberTableView.getSelectionModel().getSelectedItem();
		if (selectedItem != null) {
			ApplicationDataContext.getInstance().put(ContextDataKey.RECORD_MEMBER, selectedItem);
			Context.getInstance().changeScreen(actionEvent, RecordView.getInstance());
		}
	}
	
	public void addBookCopyButton(ActionEvent actionEvent) {
		
	}
}
