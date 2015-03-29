package ma.exampl.imagineapp.activity;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import ma.exampl.imagineapp.R;
import ma.exampl.imagineapp.dao.LibraryDAO;
import ma.exampl.imagineapp.dao.RessourceDAO;
import ma.exampl.imagineapp.model.Library;
import ma.exampl.imagineapp.model.Ressource;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.media.MediaPlayer.OnCompletionListener;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class AddRessourceActivity extends Activity {

	Button takePic;
	Button choosePic;
	ImageView takenPic;
	private static final int SELECT_PHOTO = 100;
	private static final String LOG_TAG = "AudioRecordTest";
	private static String mFileName = null;
	private Button mButtonAdd;
	private Button mRecordButton;
	private MediaRecorder mRecorder = null;
	private Button mPlayButton;
	private MediaPlayer mPlayer = null;
	boolean mStartRecording = true;
	boolean mStartPlaying = true;
	TextView mtextView ;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_ressource_layout);

		final Intent intent = getIntent();
		
		mtextView = (TextView) findViewById(R.id.ressourceName);
		takePic = (Button) findViewById(R.id.takePic);
		takePic.setOnClickListener(new OnClickListener() {
// ==================================================================================

			@Override
			public void onClick(View v) {

				Intent takePictureIntent = new Intent(
						MediaStore.ACTION_IMAGE_CAPTURE);
				if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
					startActivityForResult(takePictureIntent, 0);
				}

			}
		});
		
		
// ==================================================================================

		choosePic = (Button) findViewById(R.id.uploadPic);
		
// ==================================================================================

		choosePic.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
				photoPickerIntent.setType("image/*");
				startActivityForResult(photoPickerIntent, SELECT_PHOTO);

			}
		});
// ==================================================================================

		mRecordButton = (Button) findViewById(R.id.recordButton);
// ==================================================================================

		mRecordButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				onRecord(mStartRecording);
				if (mStartRecording) {
					mRecordButton.setBackgroundResource(R.drawable.stoprecord);
					if (mPlayButton.getVisibility() != View.VISIBLE) {
						mPlayButton.setVisibility(View.VISIBLE);
						;
					}

				} else {
					mRecordButton.setBackgroundResource(R.drawable.record);
				}
				mStartRecording = !mStartRecording;

			}
		});
// ==================================================================================
		mPlayButton = (Button) findViewById(R.id.playButton);
// ==================================================================================		
		mPlayButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				onPlay(mStartPlaying);
				if (mStartPlaying) {
					mPlayButton.setBackgroundResource(R.drawable.stoprecord);
				} else {
					mPlayButton.setBackgroundResource(R.drawable.player);
				}
				mStartPlaying = !mStartPlaying;
			}

		});

		mButtonAdd = (Button) findViewById(R.id.addRessourceButton);
// ==================================================================================
		mButtonAdd.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				Ressource ressource = new Ressource();
				Log.i("TAAAAAAAAAAAAAAAAAAAAG", String.valueOf(getIntent().getIntExtra("categorieId", 0)));
				ressource.setCategoryId(getIntent().getIntExtra("categorieId", 0));

				ByteArrayOutputStream stream = new ByteArrayOutputStream();
				Bitmap bmp = ((BitmapDrawable) takenPic.getDrawable())
						.getBitmap();
				bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
				ressource.setRessourceImage(stream.toByteArray());

				int bytesRead;

				FileInputStream is = null;
				try {
					is = new FileInputStream (new File(mFileName));
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				ByteArrayOutputStream bos = new ByteArrayOutputStream();

				byte[] b = new byte[1024];

				try {
					while ((bytesRead = is.read(b)) != -1) {

						bos.write(b, 0, bytesRead);

					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				byte[] bytes = bos.toByteArray();
				ressource.setRessourceSound(bytes);
				ressource.setRessouceName(mtextView.getText().toString());
				RessourceDAO ressourceDAO = new RessourceDAO(getApplicationContext());
				ressourceDAO.addRessource(ressource);
				
			}
		});

	}
// ==================================================================================
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		takenPic = (ImageView) findViewById(R.id.takenPic);
		if (requestCode == 0 && resultCode == RESULT_OK) {
			Bundle extras = data.getExtras();
			Bitmap imageBitmap = (Bitmap) extras.get("data");
			takenPic.setImageBitmap(imageBitmap);
		}
		if (requestCode == SELECT_PHOTO && resultCode == RESULT_OK) {
			Uri selectedImage = data.getData();
			InputStream imageStream = null;
			try {
				imageStream = getContentResolver().openInputStream(
						selectedImage);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			takenPic.setImageBitmap(BitmapFactory.decodeStream(imageStream));
		}

	}
	// ==================================================================================
	private void onRecord(boolean start) {
		if (start) {
			startRecording();
		} else {
			stopRecording();
		}
	}
	// ==================================================================================
	private void onPlay(boolean start) {
		if (start) {
			startPlaying();
		} else {
			stopPlaying();
		}
	}
	// ==================================================================================
	private void startPlaying() {
		mPlayer = new MediaPlayer();
		try {
			mPlayer.setDataSource(mFileName);
			mPlayer.prepare();
			mPlayer.start();
		} catch (IOException e) {
			Log.e(LOG_TAG, "prepare() failed");
		}
		mPlayer.setOnCompletionListener(new OnCompletionListener() {

			@Override
			public void onCompletion(MediaPlayer mp) {

				mPlayButton.setBackgroundResource(R.drawable.player);

			}
		});
	}
	// ==================================================================================
	private void stopPlaying() {
		mPlayer.release();
		mPlayer = null;
	}
	// ==================================================================================
	private void startRecording() {
		mRecorder = new MediaRecorder();
		mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
		mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);

		mRecorder.setOutputFile(mFileName);
		mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

		try {
			mRecorder.prepare();
		} catch (IOException e) {
			Log.e(LOG_TAG, "prepare() failed");
			e.printStackTrace();
		}

		mRecorder.start();
	}
	// ==================================================================================
	private void stopRecording() {
		mRecorder.stop();
		mRecorder.release();
		mRecorder = null;
	}

	public AddRessourceActivity() {
		mFileName = Environment.getExternalStorageDirectory().getAbsolutePath()
				+ "/mysound.3gp";
	}
	// ==================================================================================
	@Override
	public void onPause() {
		super.onPause();
		if (mRecorder != null) {
			mRecorder.release();
			mRecorder = null;
		}

		if (mPlayer != null) {
			mPlayer.release();
			mPlayer = null;
		}
	}

}
