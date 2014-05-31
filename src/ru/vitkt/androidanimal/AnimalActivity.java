package ru.vitkt.androidanimal;

import java.util.Timer;
import java.util.TimerTask;



import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.Window;
import android.view.WindowManager;

public class AnimalActivity extends Activity {
	
	AnimalProcessor animalProcessor;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON |
			    WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON        
			);
		
		timer = new Timer();
		
		animalProcessor = new AnimalProcessor(this);

		timer.schedule(callProcess, 10,10);
		
	}
	Timer timer = new Timer();
	TimerTask callProcess = new TimerTask() {
		
		@Override
		public void run() {
			//animalProcessor.process();
			
		}
	};
	
	@Override
	protected void onResume() {
		super.onResume();
		animalProcessor.onResume();
	};
	
	@Override
	protected void onPause() {
		super.onPause();
		animalProcessor.onPause();
	}
	

}
