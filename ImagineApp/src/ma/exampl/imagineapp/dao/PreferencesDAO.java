package ma.exampl.imagineapp.dao;

import ma.exampl.imagineapp.model.Library;
import ma.exampl.imagineapp.model.Preference;
import ma.exampl.imagineapp.persistence.DataBaseHelper;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class PreferencesDAO {

	// ==================================================================================

	private SQLiteDatabase database;
	private DataBaseHelper dataBaseHelper;

	private String[] allColumns = { "_id", "PreferenceName", "PreferenceValue" };

	// ==================================================================================

	public PreferencesDAO(Context context) {
		dataBaseHelper = new DataBaseHelper(context);
		dataBaseHelper.openDataBase();
		database = dataBaseHelper.getDataBase();
	}

	// ==================================================================================

	private Preference cursorToPreference(Cursor cursor) {
		Preference preference = new Preference();
		preference.setId(cursor.getInt(0));
		preference.setPreferenceName(cursor.getString(1));
		preference.setPreferenceValue(cursor.getString(2));

		return preference;
	}

	// ==================================================================================

	public Library getSelectedLibrary(Context context) {

		Cursor cursor = null;
		try {
			cursor = database.query(DataBaseHelper.TABLE_PREFRENCES,
					allColumns, "preferenceName=?",
					new String[] { "selectedLibrary" }, null, null, null);
			cursor.moveToFirst();
			Preference preference = cursorToPreference(cursor);

			LbraryDAO lbraryDAO = new LbraryDAO(context);
			Library library = lbraryDAO.getLibraryById(Integer
					.parseInt(preference.getPreferenceValue()));
			return library;

		}

		catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	// ==================================================================================

	public void updateSelectedLibrary(int id) {
		ContentValues cv = new ContentValues();
		cv.put("preferenceValue", new Integer(id).toString()); // These Fields
																// should be
																// your String
																// values of
																// actual column
																// names

		database.update(DataBaseHelper.TABLE_PREFRENCES, cv,
				"preferenceName = 'selectedLibrary'", null);
	}
	// ==================================================================================

}
