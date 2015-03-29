package ma.exampl.imagineapp.dao.remote;

import java.util.ArrayList;
import java.util.List;

import ma.exampl.imagineapp.R;
import ma.exampl.imagineapp.consts.ParseComConsts;
import ma.exampl.imagineapp.model.Library;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class LibraryRemoteDAO {
	
	static LayoutInflater inflater ;
	
	public LibraryRemoteDAO() {

	}
	
	public LibraryRemoteDAO(LayoutInflater layoutInflater){
		inflater = layoutInflater;
		
	}
	
	
	
	public void getLibrairies(Context context , final ListView l1 ){
		
		Parse.initialize(context,ParseComConsts.USER, ParseComConsts.PASSWORD);
		ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Librairy");
		//	query.orderByAscending("librairyName");
			final List<Library> librariesToReturn = new ArrayList<Library>();
			
			query.findInBackground(new FindCallback<ParseObject>() {

				@Override
				public void done(List<ParseObject> libraryRemoteList, ParseException parseException) {
					
					
					System.out.println(libraryRemoteList.size());
					for (ParseObject parseObject : libraryRemoteList) {
						
						Log.i("montag", "you are here "+parseObject.getClassName());
						Log.i("montag", "you are here "+parseObject.getString("librairyName"));
						
						Library library = new Library();
						library.setDateOfCreation(parseObject.getCreatedAt().toString());
						library.setLibraryName(parseObject.getString("libraryName"));
						library.setLibraryAuthorFullName(parseObject.getString("librairieAuthorFullName"));
						library.setDescription(parseObject.getString("libraryDescription"));
						library.setLanguage(parseObject.getString("language"));
						librariesToReturn.add(library);
						System.out.println(librariesToReturn.size());
						
						if(librariesToReturn.size()==libraryRemoteList.size())
						{setLibraries( librariesToReturn,l1 );}
						
					}
					
					
				}

				
			});		
		
	}
	
private void setLibraries(List<Library> librariesToReturn , ListView l1) {
	
    l1.setAdapter(new DataListAdapter((ArrayList<Library>) librariesToReturn));
//    l1.setOnItemClickListener(new OnItemClickListener() 
//    {
//		@Override
//		public void onItemClick(AdapterView<?> parent, View view,int position, long id) 
//		{
//			DataListAdapter library=(DataListAdapter) l1.getAdapter();
//		//	SharedPreferencesManager.setSelectedLibraryValue(DownloadActivity.this, library.getLibraryID(position));
//			DownloadActivity.this.finish();
//			//Log.d("SFIAN", "ok - "+library.getLibraryID(position));
//			
//		}
//	});
	
	
}

public static class DataListAdapter extends BaseAdapter 
{
	//================================================================================
    private ArrayList<Library> listLibraries;
    //================================================================================
    public DataListAdapter() 
    {
    	this.listLibraries=null;
	}
  //================================================================================
    public DataListAdapter(ArrayList<Library> listLibraries) 
    {
		this.listLibraries=listLibraries;
	}
    //================================================================================
	@Override
	public int getCount() 
	{
		// TODO Auto-generated method stub
		return listLibraries.size();
	}
	
	//================================================================================

	@Override
	public Object getItem(int arg0) 
	{
		// TODO Auto-generated method stub
		return null;
	}
	//================================================================================

	@Override
	public long getItemId(int position) 
	{
		// TODO Auto-generated method stub
		return position;
	}
	//================================================================================

	public int getLibraryID(int position) 
	{
		return listLibraries.get(position).getId();
	}
	//================================================================================

	
	
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent ) 
	{
		LayoutInflater inflater = LibraryRemoteDAO.inflater ;
        View row;
        row = inflater.inflate(R.layout.custom_download_list, parent, false);
        TextView nom, autor,langage;
        ImageView image;
        nom = (TextView) row.findViewById(R.id.nomLib);
        autor = (TextView) row.findViewById(R.id.autorLib);
        langage = (TextView) row.findViewById(R.id.langage);
        image=(ImageView)row.findViewById(R.id.imageLine);
        nom.setText(listLibraries.get(position).getLibraryName());
        autor.setText("Autor : "+listLibraries.get(position).getLibraryAuthorFullName());
        langage.setText(" - Lang : "+listLibraries.get(position).getLanguage());
        
        return (row);
	}
	//================================================================================

}




}
