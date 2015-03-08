package ma.exampl.imagineapp.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Ressource {
	// ==================================================================================

	private int id;
	private String ressouceName;
	private byte[] ressourceImage;
	private byte[] ressourceSound;
	private int libraryId;
	private int position;

	// ==================================================================================

	public Ressource() {
		super();
		// TODO Auto-generated constructor stub
	}

	// ==================================================================================

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	// ==================================================================================

	public String getRessouceName() {
		return ressouceName;
	}

	public void setRessouceName(String ressouceName) {
		this.ressouceName = ressouceName;
	}

	// ==================================================================================

	public byte[] getRessourceImage() {
		return ressourceImage;
	}

	public void setRessourceImage(byte[] ressourceImage) {
		this.ressourceImage = ressourceImage;
	}

	// ==================================================================================

	public byte[] getRessourceSound() {
		return ressourceSound;
	}

	public void setRessourceSound(byte[] ressourceSound) {
		this.ressourceSound = ressourceSound;
	}

	// ==================================================================================

	public int getLibraryId() {
		return libraryId;
	}

	public void setLibraryId(int libraryId) {
		this.libraryId = libraryId;
	}

	// ==================================================================================

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	// ==================================================================================

	public Bitmap getBitmapImage() {
		Bitmap bm = BitmapFactory.decodeByteArray(ressourceImage, 0,
				ressourceImage.length);
		return bm;
	}

	// ==================================================================================

	public FileInputStream getSoundStream(Context context) throws IOException {
		File tempMp3 = File.createTempFile("kurchina", "mp3",
				context.getCacheDir());
		tempMp3.deleteOnExit();
		FileOutputStream fos = new FileOutputStream(tempMp3);
		fos.write(ressourceSound);
		fos.close();
		return new FileInputStream(tempMp3);
	}
	// ==================================================================================

}
