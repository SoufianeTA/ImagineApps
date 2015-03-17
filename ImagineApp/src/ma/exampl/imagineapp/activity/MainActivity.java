package ma.exampl.imagineapp.activity;

import ma.exampl.imagineapp.R;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.app.Activity;
import android.content.Intent;


public class MainActivity extends Activity implements OnClickListener{
	//================================================================================
 
	private Button bStart;
	private Button bSelectLibrary;
	private Button bAbout;
	private ImageView backgroundbuttonStart;
	private Animation animat;
	private LinearLayout layoutLogo;
	//================================================================================

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		backgroundbuttonStart=(ImageView) findViewById(R.id.Main_ImageBg_Start);
		animat= AnimationUtils.loadAnimation(this,R.anim.animation_bsync);
		backgroundbuttonStart.startAnimation(animat);
		
		bStart = (Button) findViewById(R.id.Main_ButtonStart);
		bStart.setOnClickListener(this);
		animat= AnimationUtils.loadAnimation(this,R.anim.animation_frame_bstart);
		bStart.startAnimation(animat);
		
		bSelectLibrary=(Button) findViewById(R.id.MainActivity_buttonSelectLibrary);
		bSelectLibrary.setOnClickListener(this);
		bSelectLibrary.setAnimation(animat);
		
		bAbout=(Button) findViewById(R.id.MainActivity_buttonAbout);
		bAbout.setOnClickListener(this);
		bAbout.setAnimation(animat);
		
		
		
		animat= AnimationUtils.loadAnimation(this,R.anim.animation_main_logo_show);
		layoutLogo=(LinearLayout) findViewById(R.id.MainActivity_LogoLayout);
		layoutLogo.setAnimation(animat);
		
		
	}
	//================================================================================
	@Override
	public void onClick(View v) 
	{
		// TODO Auto-generated method stub
		switch (v.getId()) 
		{
        case R.id.Main_ButtonStart: 
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
