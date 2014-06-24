package ru.vitkt.androidanimal;

import ru.vitkt.animaltools.AnimalTouchDetector;

import android.content.Context;
import android.graphics.Canvas;

public class AnimalProcessor {

	Context context;
	// TODO Избавиться от комментариев

	private AnimalSensor animalInput;
	public AnimalWallpaperOutput animalWallpaperOutput;
	public AnimalTouchDetector animalTouch;
	public AnimalProcessor(Context _context) {
		context = _context;

		animalInput = new AnimalSensor(_context);
		animalWallpaperOutput = new AnimalWallpaperOutput();
		animalTouch = new AnimalTouchDetector(context);
		onResume();
	}

	float lightEmotion = 0f;
	float touchEmotion = 100f;
	float stabilityEmotion = 100f;
	float safetyEmotion = 100f;
	int counter = 0;
	final int COUNTER_PERIOD = 10;

	public void process(Canvas canvas) {
		if (counter != 0) {
			animalWallpaperOutput.updateLastCell(counter);
			animalWallpaperOutput.draw(canvas);
			counter	= (counter+1)%COUNTER_PERIOD;
			
			return;
		}
		counter= (counter+1)%COUNTER_PERIOD;
		int proximity = animalInput.getProximity();

		lightEmotion = 0f;
		if (proximity != 0)
			lightEmotion += 50f;

		int light = animalInput.getLight();

		lightEmotion += ((float) light / 300.0f) * 50f;
	

		stabilityEmotion = animalInput.getStability() * 100f;

		float unstable = animalInput.getUnstableValue();
		if (unstable >= 10f) {
			if (unstable > 30f)
				unstable = 30f;
			float unstableFactor = (unstable - 10f) / 20f;
			safetyEmotion = 100f - (unstableFactor * 100f);
		} else {
			// safetyEmotion += 1f;
			// if (safetyEmotion > 100f) // Memory for bad safety emotions
			safetyEmotion = 100f;
		}
		 touchEmotion = animalTouch.getTouchEmotionFactor()*100;
		showEmotions(canvas);
	}

	private void showEmotions(Canvas canvas) {
		if (lightEmotion > 100)
			lightEmotion = 100;
		if (touchEmotion > 100)
			touchEmotion = 100;
		if (stabilityEmotion > 100)
			stabilityEmotion = 100;
		int a, r, g, b;

		a = (int) ((safetyEmotion / 100f) * 255);
		r = (int) ((touchEmotion / 100f) * 255);
		g = (int) ((lightEmotion / 100f) * 255);
		b = (int) ((stabilityEmotion / 100f) * 255);

		if (safetyEmotion < 50f) {

			r *= (safetyEmotion / 100f);
			g *= (safetyEmotion / 100f);
			b *= (safetyEmotion / 100f);
		}


		animalWallpaperOutput.setColor(a, r, g, b);
		animalWallpaperOutput.draw(canvas);
	}

	public void onResume() {
		animalInput.onResume();
	}

	public void onPause() {
		animalInput.onPause();
	}

}
