package edu.mum.cs.cs401.view;

import javafx.scene.Scene;

public class AddBookView extends View{
	public AddBookView() {
		
	}
	
	private static AddBookView bookCopy = new AddBookView();
	
	public static AddBookView getInstance(){
		return bookCopy;
	}
	
	@Override
	public Scene getScene() {
		return super.getScene("AddBookView.fxml");
	}
}
