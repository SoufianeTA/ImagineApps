package ma.exampl.imagineapp.dao;

import java.util.ArrayList;
import java.util.List;



import ma.exampl.imagineapp.persistence.DataBaseHelper;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class CategoryDAO {
	// ==================================================================================

	private SQLiteDatabase database;
	private DataBaseHelper dataBaseHelper;

	private String[] allColumns = { "_id", "library_id", "category_name",
			"category_image", "fk_id_category" };

	// ==================================================================================

	public CategoryDAO(Context context) {
		dataBaseHelper = new DataBaseHelper(context);
		dataBaseHelper.openDataBase();
		database = dataBaseHelper.getDataBase();
	}

	// ==================================================================================

}
