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

	private List<Category> categories;
	private Category category;
	private List<Ressource> listeRessources;
	private int[] categoryHistory=new int[50];

	private int selectedLibraryId;
	private int imageRessourceSize;
	private int nbrElementsByLine;

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

		/* get selected library id FROM shared preferences + other sharedPref */
		selectedLibraryId = 1;
		imageRessourceSize = 200;

		/* nember of Elements in one Tablerow */
		nbrElementsByLine = this.getResources().getDisplayMetrics().widthPixels
				/ imageRessourceSize;

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

		int i = 0; // iterator
		while (i < listeRessources.size()) {

			/* instantiate new TableRow */
			TableRow oneTableRow = new TableRow(this);
			oneTableRow.setLayoutParams(new LayoutParams(
					LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));

			int j = 0; // iterator
			while (j < nbrElementsByLine && i < listeRessources.size()) {

				Ressource ressource = listeRessources.get(i);

				/* instansiate imageView that represent a ressource */
				ImageView imageView = new ImageView(this);
				imageView.setTag(i);
//				Bitmap resizedbitmap = Bitmap.createScaledBitmap(
//						ressource.getBitmapImage(), imageRessourceSize,
//						imageRessourceSize, true);
//				imageView.setImageBitmap(resizedbitmap);
				imageView.setImageBitmap(ressource.getBitmapImage());
				imageView.setOnLongClickListener(this);

				// -------------- Test ----------------
//				 Log.d(LOG_TAG, String.valueOf(i));
				 Log.d(LOG_TAG, String.valueOf(ressource.getId()));
				// ------------ Test END --------------

				/* Load the sound of the ressource */
				final MediaPlayer mediaPlayer = new MediaPlayer();
				try {
					FileInputStream fileInputStream = ressource
							.getSoundStream(this);
					mediaPlayer.setDataSource(fileInputStream.getFD());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				/* Play sound Onclick Imageview */
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

				/* add imageView to the TableRow */
				oneTableRow.addView(imageView);

				/* increment */
				j++;
				i++;
			}
			// -------- End Of the Loop j -----------

			/* add the TableRow To The TableLayout */
			tableLayoutRessources.addView(oneTableRow,
					new TableLayout.LayoutParams(LayoutParams.MATCH_PARENT,
							LayoutParams.WRAP_CONTENT));

		}
		// -------- End Of the Loop i -----------

		// ---------- END onCreate ------------
	}

	// ==================================================================================

	private View.OnClickListener getOnClickChooseCategory(
			Button buttonCategorie2, final int idCategory) {
		return new View.OnClickListener() {
			public void onClick(View v) {
				// -------------- Test ----------------
				// Log.d(LOG_TAG, String.valueOf(idCategory));
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
	

}
