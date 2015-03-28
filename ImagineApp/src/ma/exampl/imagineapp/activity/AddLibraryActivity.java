package ma.exampl.imagineapp.activity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import ma.exampl.imagineapp.R;
import ma.exampl.imagineapp.dao.LibraryDAO;
import ma.exampl.imagineapp.model.Library;
import ma.exampl.imagineapp.persistence.SharedPreferencesManager;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class AddLibraryActivity extends Activity {

	private Button bCreate ;
	private EditText userName;
	private EditText libraryName;
	private EditText langue;
	private EditText description;
	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	Date date = new Date();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_library_layout);
		userName = (EditText) findViewById(R.id.userNameEdit);
		libraryName = (EditText) findViewById(R.id.libraryNameEdit);
		langue = (EditText) findViewById(R.id.langueEdit);
		description = (EditText) findViewById(R.id.descriptionEdit);
		
		bCreate = (Button) findViewById(R.id.createButton);
		bCreate.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				LibraryDAO libraryDAO = new LibraryDAO(getApplicationContext());
				Library library = new Library();
				library.setDateOfCreation(dateFormat.format(date));
				library.setLibraryName(userName.toString());
				library.setLibraryAuthorFullName(userName.toString());
				library.setDescription(description.toString());
				library.setLanguage(langue.toString());
				library.setId(libraryDAO.getAllLibraries().size()+1);
				
				libraryDAO.addLibrary(library);
				SharedPreferencesManager.Selected_library_toAdd_Value(getApplicationContext() , library.getId());
				startActivity(new Intent(AddLibraryActivity.this,AddRessourceActivity.class));
				
			}
		});
		
		
	}
	
	
	
}
