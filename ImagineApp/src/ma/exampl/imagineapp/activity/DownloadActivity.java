package ma.exampl.imagineapp.activity;

import ma.exampl.imagineapp.R;
import ma.exampl.imagineapp.dao.remote.LibraryRemoteDAO;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

public class DownloadActivity extends Activity {

	private ListView l1;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_download);
		LibraryRemoteDAO libraryRemoteDAO = new LibraryRemoteDAO(getLayoutInflater());
		l1=(ListView)findViewById(R.id.SelectLibrary_listViewProd);
		libraryRemoteDAO.getLibrairies(this,l1);
		
	}

}
