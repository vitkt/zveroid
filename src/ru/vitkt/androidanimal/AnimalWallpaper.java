package ru.vitkt.androidanimal;

import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.service.wallpaper.WallpaperService;
import android.view.MotionEvent;
import android.view.SurfaceHolder;

public class AnimalWallpaper extends WallpaperService {
	// TODO Сделать нормальную архитектуру
	
	AnimalProcessor animalProcessor;
	@Override
	public Engine onCreateEngine() {
		// TODO Auto-generated method stub
		animalProcessor = new AnimalProcessor(this);
		return new SuperEngine();
	}
	
	

	private class SuperEngine extends Engine {
		private final Handler handler = new Handler();
		private final Runnable drawRunner = new Runnable() {
			@Override
			public void run() {
				draw();
			}

		};
		

		private boolean visible = true;


		public SuperEngine() {
			SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(AnimalWallpaper.this);
			animalProcessor.animalWallpaperOutput.setMatrixSize(
			Integer
	          .valueOf(prefs.getString("rows", "4")),
				
				Integer
		          .valueOf(prefs.getString("columns", "4")));
				handler.post(drawRunner);
		}

		@Override
		public void onVisibilityChanged(boolean visible) {
			this.visible = visible;
			if (visible) {
				handler.post(drawRunner);
			} else {
				handler.removeCallbacks(drawRunner);
			}
		}

		@Override
		public void onSurfaceDestroyed(SurfaceHolder holder) {
			super.onSurfaceDestroyed(holder);
			this.visible = false;
			handler.removeCallbacks(drawRunner);
		}

		@Override
		public void onSurfaceChanged(SurfaceHolder holder, int format,
				int width, int height) {
			animalProcessor.animalWallpaperOutput.setWidth(width);
			animalProcessor.animalWallpaperOutput.setHeight(height);
			super.onSurfaceChanged(holder, format, width, height);
		}

		@Override
		public void onTouchEvent(MotionEvent event) {
			animalProcessor.animalTouch.onTouchEvent(event);
		}

		private void draw() {
			SurfaceHolder holder = getSurfaceHolder();
			Canvas canvas = null;
			try {
				canvas = holder.lockCanvas();
				
				if (canvas != null) {
					animalProcessor.process(canvas);

				}
			} finally {
				if (canvas != null)
					holder.unlockCanvasAndPost(canvas);
			}

			if (visible) {
				handler.postDelayed(drawRunner, 1);
			}
		}

		
	}
}
