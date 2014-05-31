package ru.vitkt.androidanimal;

import android.app.Activity;
import android.content.Context;
import android.os.Vibrator;

public class AnimalOutput {

	private Activity activity;
	private Vibrator vibrator;
	private AnimalScreenView animalScreen;
	public AnimalOutput(Activity _activity) {
		activity = _activity;
		vibrator =(Vibrator) activity.getSystemService(Context.VIBRATOR_SERVICE);
		
		animalScreen = new AnimalScreenViewGrid(activity);
		activity.setContentView(animalScreen);
		

	}
	float getTouchEmotionFactor()
	{
		return animalScreen.getTouchEmotionFactor(); 
	}
	public  void setColorToScreen(final int a, final int r, final int g, final int b) {


		activity.runOnUiThread(new Runnable() {
			
			@Override
			public void run() {
				animalScreen.setColor(a,r,g,b);
				
			}
		});
		
	}
	public void vibrate()
	{
		//long [] signal1 = {200,200,200,200,30,100,100,30};
		//vibrator.vibrate(signal1, -1);
		vibrator.vibrate(300);
	}
}
