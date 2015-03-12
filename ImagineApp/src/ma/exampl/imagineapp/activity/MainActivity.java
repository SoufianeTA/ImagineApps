package ma.exampl.imagineapp.activity;

import ma.exampl.imagineapp.R;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.app.Activity;
import android.content.Intent;


public class MainActivity extends Activity implements OnClickListener{
	//================================================================================
 
	private Button bStart;
	private Button bSelectLibrary;
	//================================================================================

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		bStart = (Button) findViewById(R.id.MainActivity_buttonStart);
		bStart.setOnClickListener(this);
		bSelectLibrary=(Button) findViewById(R.id.MainActivity_buttonSelectLibrary);
		bSelectLibrary.setOnClickListener(this);
		
	}
	//================================================================================
	@Override
	public void onClick(View v) 
	{
		// TODO Auto-generated method stub
		switch (v.getId()) 
		{
        case R.id.MainActivity_buttonStart: 
        	startActivity(new Intent(MainActivity.this, TableActivity.class));
        	break;
        case R.id.MainActivity_buttonSelectLibrary: 
        	startActivity(new Intent(MainActivity.this, SelectLibraryActivity.class));
        	break;

		}
	}
	//================================================================================
	//================================================================================
	

}
