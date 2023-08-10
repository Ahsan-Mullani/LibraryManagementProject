package com.example.project.Model;

import org.hibernate.annotations.DynamicUpdate;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@jakarta.persistence.Entity
@jakarta.persistence.Table(name = "BookDetails")
@DynamicUpdate

//This Library Class which is Blueprint here all Attributes are assigned

public class Book{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer bookId;
	private String bookName;
	private String authorName;
	private int price;
	private int noCopies;
	private boolean isPublished;

	//Getter and Setter Method

	public boolean isPublished() {
		return isPublished;
	}
	public void setPublished(boolean isPublished) {
		this.isPublished = isPublished;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getAuthorName() {
		return authorName;
	}
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}	
	public int getNoCopies() {
		return noCopies;
	}
	public void setNoCopies(int noCopies) {
		this.noCopies = noCopies;
	}

	//To String Method
	@Override
	public String toString() {
		return "Library [bookId=" + bookId + ", bookName=" + bookName + ", authorName=" + authorName + ", price="
				+ price + ", noCopies=" + noCopies + ", isPublished=" + isPublished + "]";
	}

}
