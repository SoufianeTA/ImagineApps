package ma.exampl.imagineapp.activity;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import ma.exampl.imagineapp.R;
import ma.exampl.imagineapp.dao.CategoryDAO;
import ma.exampl.imagineapp.dao.RessourceDAO;
import ma.exampl.imagineapp.model.Category;
import ma.exampl.imagineapp.model.Ressource;
import android.R.integer;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Matrix;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnDragListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.LinearLayout.LayoutParams;

public class TableActivity extends Activity implements View.OnLongClickListener {
	// ==================================================================================
	private static String LOG_TAG = "SFIAN";

	private LinearLayout linearLayoutCategory;
	private Button buttonCategoryBack;
	private Button buttonCategorie;
	private TableLayout tableLayoutRessources;
	private LayoutParams paramButtonCategories;
	private TableRow.LayoutParams paramImageRessource;
	private int selectedLibraryId;
	private List<Category> categories;
	private Category category;
	private List<Ressource> listeRessources;

	private CategoryDAO categotyDao;
	private RessourceDAO ressourceDao;

	// ==================================================================================
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_table);

		/* Layout ressources */
		linearLayoutCategory = (LinearLayout) findViewById(R.id.TableActivity_LinearLayoutCategory);
		buttonCategoryBack = (Button) findViewById(R.id.TableActivity_buttonCategoryBack);
		tableLayoutRessources = (TableLayout) findViewById(R.id.TableActivity_TableLayoutRessources);

		/* Layout params */
		paramButtonCategories = new LayoutParams(LayoutParams.MATCH_PARENT, 140);
		paramImageRessource = new TableRow.LayoutParams(200,200);
		
		/* get selected library id FROM shared preferences */
		selectedLibraryId = 1;

		/* instantiate DAO */
		categotyDao = new CategoryDAO(this);
		ressourceDao = new RessourceDAO(this);

		/* get the default category using library id */
		category = categotyDao.getDefaultCategoryByIdLibrary(selectedLibraryId);

		// -------------- Test ----------------
		// Log.d(LOG_TAG, category.getId()+" - "+category.getCategoryName());
		// ------------ Test END --------------

		/* get categories from default category */
		categories = categotyDao.getCategoriesByCategoryId(category.getId());

		// -------------- Test ----------------
		// for (Iterator iterator = categories.iterator(); iterator.hasNext();)
		// {
		// Category category = (Category) iterator.next();
		// Log.d(LOG_TAG, category.getId()+" - "+category.getCategoryName());
		// }
		// ------------ Test END --------------

		/* fill the category scrollview */
		for (Iterator iterator = categories.iterator(); iterator.hasNext();) {
			Category category = (Category) iterator.next();

			buttonCategorie = new Button(this);
			buttonCategorie.setText(category.getCategoryName());
			linearLayoutCategory
					.addView(buttonCategorie, paramButtonCategories);

			buttonCategorie.setOnClickListener(getOnClickChooseCategory(
					buttonCategorie, category.getId()));
		}

		/* Load the ressources */
		listeRessources = ressourceDao.getRessourcesByCategoryId(category
				.getId());

		// -------------- Test ----------------
		// Log.d(LOG_TAG, String.valueOf(listeRessources.size()));
		// ------------ Test END --------------

		/* fill TableLayout with ressources */

		int i = 0;
		while (i < listeRessources.size()) {
			int j = 0;
			TableRow tr_head = new TableRow(this);
			tr_head.setId(10);
			tr_head.setBackgroundColor(Color.GRAY); // part1
			tr_head.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
					LayoutParams.WRAP_CONTENT));
			while (j < 5 && i < listeRessources.size()) {
				
				ImageView imageView = new ImageView(this);
				Ressource ressource = listeRessources.get(i);
				imageView.setTag(i);
				// TableLayout tableLayout = new TableLayout(new
				// LayoutParams(LayoutParams.WRAP_CONTENT, 50));
				
				// Bitmap bmp=BitmapFactory.decodeResource(getResources(),ressource.getBitmapImage());
				
				
				    int width=200;
				    int height=200;
				    
				    
				    //Bitmap resizedbitmap=Bitmap.createScaledBitmap(ressource.getBitmapImage(), width, height, true);
				    
				imageView.setImageBitmap(ressource.getBitmapImage());
			    
                imageView.setOnLongClickListener(this);
                imageView.getLayoutParams().width=200;
                //imageView.getLayoutParams().height=200;
                //imageView.setLayoutParams(new LayoutParams(200,200));
                
				final MediaPlayer mediaPlayer = new MediaPlayer();
				try {
					FileInputStream fileInputStream = ressource
							.getSoundStream(this);
					mediaPlayer.setDataSource(fileInputStream.getFD());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				imageView.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						try {
							mediaPlayer.prepare();
						} catch (IllegalStateException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						mediaPlayer.start();

					}
				});
				tr_head.addView(imageView);
				
				j++;
				i++;
			}
			tableLayoutRessources.addView(tr_head,new TableLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, // part4
				LayoutParams.WRAP_CONTENT));
			//i++;
			
		}

		// ---------- END onCreate ------------
	}

	// ==================================================================================

	private View.OnClickListener getOnClickChooseCategory(
			Button buttonCategorie2, final int idCategory) {
		return new View.OnClickListener() {
			public void onClick(View v) {
				// -------------- Test ----------------
				Log.d(LOG_TAG, String.valueOf(idCategory));
				// ------------ Test END --------------
			}
		};
	}

	// ==================================================================================

	@Override
	public boolean onLongClick(View v) {
		// TODO Auto-generated method stub
		return false;
	}
	// ==================================================================================
	public Bitmap getResizedBitmap(Bitmap bm, int newHeight, int newWidth) {
		 
		int width = bm.getWidth();
		 
		int height = bm.getHeight();
		 
		float scaleWidth = ((float) newWidth) / width;
		 
		float scaleHeight = ((float) newHeight) / height;
		 
		// create a matrix for the manipulation
		 
		Matrix matrix = new Matrix();
		 
		// resize the bit map
		 
		matrix.postScale(scaleWidth, scaleHeight);
		 
		// recreate the new Bitmap
		 
		Bitmap resizedBitmap = Bitmap.createBitmap(bm, 0, 0, width, height, matrix, false);
		 
		return resizedBitmap;
		 
		}

}
