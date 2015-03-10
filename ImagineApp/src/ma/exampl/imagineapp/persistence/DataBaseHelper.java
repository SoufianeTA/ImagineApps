package ma.exampl.imagineapp.persistence;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataBaseHelper extends SQLiteOpenHelper {

	// ==================================================================================

	private static String DB_NAME = "DatabaseImagine.db";
	private static String DB_PATH = "/data/data/ma.exampl.imagineapp/databases/";

	public static final String TABLE_LIBRARIES = "Library";
	public static final String TABLE_CATEGORIES = "Category";
	public static final String TABLE_RESSOURCES = "Ressource";


	private Context context;
	private SQLiteDatabase myDataBase;

	// ==================================================================================
	public DataBaseHelper(Context context) {
		super(context, DB_NAME, null, 1);
		this.context = context;
		checkExistAndCreate();
	}

	// ==================================================================================
	@Override
	public void onCreate(SQLiteDatabase arg0) {
		// TODO Auto-generated method stub
	}

	// ==================================================================================
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
	}

	// ==================================================================================
	private void checkExistAndCreate() {
		File dbtest = new File(DB_PATH + DB_NAME);
		if (dbtest.exists()) {
			// what to do if it does exist
			Log.d("soufiane", "ok existe");
		} else {
			SQLiteDatabase db = this.getReadableDatabase();
			// what to do if it doesn't exist
			Log.d("soufiane", "ok n existe pas");
			InputStream is;
			try {
				Log.d("soufiane", "start copy");
				is = context.getAssets().open(DB_NAME);
				OutputStream os = new FileOutputStream(DB_PATH + DB_NAME);
				byte[] buffer = new byte[1024];
				while (is.read(buffer) > 0) {
					os.write(buffer);
				}
				os.flush();
				os.close();
				is.close();
				db.close();
				Log.d("soufiane", "finish copy");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	// ==================================================================================

	public void openDataBase() throws SQLException {

		// Open the database
		String myPath = DB_PATH + DB_NAME;
		myDataBase = SQLiteDatabase.openDatabase(myPath, null,
				SQLiteDatabase.OPEN_READWRITE);

	}

	// ==================================================================================

	public SQLiteDatabase getDataBase() {
		return myDataBase;
	}

	// ==================================================================================

	@Override
	public synchronized void close() {

		if (myDataBase != null)
			myDataBase.close();

		super.close();

	}
	// ==================================================================================

}
