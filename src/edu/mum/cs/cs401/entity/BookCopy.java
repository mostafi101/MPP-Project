package edu.mum.cs.cs401.entity;

public class BookCopy {

	private String copyNumber;
	
	private Boolean isAvailable;
	
	private String isbn;
	
	public String getCopyNumber() {
		return copyNumber;
	}

	public void setCopyNumber(String copyNumber) {
		this.copyNumber = copyNumber;
	}
	
	public BookCopy(String copyNumber, boolean isAvailable, String isbn) {
		super();
		this.isAvailable = isAvailable;
		this.copyNumber = copyNumber;
		this.isbn = isbn;
	}

	public BookCopy() {
	}

	public boolean isAvailable() {
		return isAvailable;
	}

	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
}
