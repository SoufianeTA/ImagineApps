package ma.exampl.imagineapp.model;

import java.io.Serializable;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Category implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private int idLibrary;
	private String categoryName;
	private byte[] categoryImage;
	private int fkIdCategory;
	
	
	
	public Category() {
		super();
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdLibrary() {
		return idLibrary;
	}
	public void setIdLibrary(int idLibrary) {
		this.idLibrary = idLibrary;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public byte[] getCategoryImage() {
		return categoryImage;
	}
	public void setCategoryImage(byte[] categoryImage) {
		this.categoryImage = categoryImage;
	}
	public int getFkIdCategory() {
		return fkIdCategory;
	}
	public void setFkIdCategory(int fkIdCategory) {
		this.fkIdCategory = fkIdCategory;
	}
	
	public Bitmap getBitmapImage() {
		Bitmap bm = BitmapFactory.decodeByteArray(categoryImage, 0,
				categoryImage.length);
		return bm;
	}

	
}
