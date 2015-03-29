package ma.exampl.imagineapp.activity;

import java.util.ArrayList;

import ma.exampl.imagineapp.R;
import ma.exampl.imagineapp.dao.LibraryDAO;
import ma.exampl.imagineapp.model.Library;
import ma.exampl.imagineapp.persistence.SharedPreferencesManager;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;

public class CustomizeLibraryActivity extends Activity implements OnClickListener{
	// ================================================================================

	private TextView txtMessage;
	private ListView l1;

	private int selectedLibraryId;
	private ArrayList<Library> libraries;
	private Button ButtonAddLibrary;
	private LibraryDAO libraryDao;

	// ================================================================================
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_manage_library);
		// ------------------------

		/* get selected library id */
		selectedLibraryId = SharedPreferencesManager
				.getSelectedLibraryValue(this);


		ButtonAddLibrary=(Button) findViewById(R.id.ManageLibrary_AddLibrary);
		ButtonAddLibrary.setOnClickListener(this);
		/* Dao lib */
		
		/* Dao lib */
		libraryDao = new LibraryDAO(this);

		libraries = (ArrayList<Library>) libraryDao.getAllLibraries();
		Log.d("SFIAN", "ok - 1 ");
		if (libraries.size() == 0) {
			txtMessage = (TextView) findViewById(R.id.SelectLibrary_textMessage);
			txtMessage.setText("VIDE");
			txtMessage.setLayoutParams(new LayoutParams(
					LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));

			// txtview msg vide
		} else {

			l1 = (ListView) findViewById(R.id.ManageLibrary_listViewLib);
			l1.setAdapter(new DataListAdapter(libraries));

		}

		// ========================
	}

	// ================================================================================
	// ================================================================================
	public class DataListAdapter extends BaseAdapter {
		// ================================================================================
		private ArrayList<Library> listLibraries;

		// ================================================================================
		public DataListAdapter() {
			this.listLibraries = null;
		}

		// ================================================================================
		public DataListAdapter(ArrayList<Library> listLibraries) {
			this.listLibraries = listLibraries;
		}

		// ================================================================================
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return listLibraries.size();
		}

		// ================================================================================

		@Override
		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		// ================================================================================

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		// ================================================================================

		public int getLibraryID(int position) {
			return listLibraries.get(position).getId();
		}

		// ================================================================================

		@Override
		public View getView(final int position, View convertView,
				ViewGroup parent) {
			LayoutInflater inflater = getLayoutInflater();
			View row;
			row = inflater.inflate(R.layout.custom_list_manage, parent, false);
			TextView nom, autor, langage;
			ImageView image;
			Button deleteLib, modifLib;

			nom = (TextView) row.findViewById(R.id.nomLib);
			autor = (TextView) row.findViewById(R.id.autorLib);
			langage = (TextView) row.findViewById(R.id.langage);
			image = (ImageView) row.findViewById(R.id.imageLine);
			deleteLib = (Button) row.findViewById(R.id.buttonDelete);
			modifLib = (Button) row.findViewById(R.id.buttonModify);

			nom.setText(listLibraries.get(position).getLibraryName());
			autor.setText("Autor : "
					+ listLibraries.get(position).getLibraryAuthorFullName());
			langage.setText(" - Lang : "
					+ listLibraries.get(position).getLanguage());

			deleteLib.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					Log.d("SFIAN",
							String.valueOf(listLibraries.get(position).getId()));
						deleteLibrary(listLibraries.get(position).getId());
				}

				
			});

			modifLib.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					Log.d("SFIAN",
							String.valueOf(listLibraries.get(position).getId()));

				}
			});

			return (row);
		}
		// ================================================================================
		
	}
	// ================================================================================
	// ================================================================================

	private void deleteLibrary(final int id) {
		new AlertDialog.Builder(this)
	    .setTitle("Delete entry")
	    .setMessage("Are you sure you want to delete this library?")
	    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
	        public void onClick(DialogInterface dialog, int which) { 
	           LibraryDAO libraryDAO = new LibraryDAO(getApplicationContext());
	           libraryDAO.deleteLibrary(id);
	           finish();
	           startActivity(getIntent());
	        }
	     })
	    .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
	        public void onClick(DialogInterface dialog, int which) { 
	            // do nothing
	        }
	     })
	    .setIcon(android.R.drawable.ic_dialog_alert)
	     .show();
		 
		
	}
	@Override
	public void onClick(View v) {

		switch (v.getId()) {

		case R.id.ManageLibrary_AddLibrary:

			startActivity(new Intent(CustomizeLibraryActivity.this,
					AddLibraryActivity.class));

			break;

		}
	}
	
}
