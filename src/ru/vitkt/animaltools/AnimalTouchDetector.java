package ru.vitkt.animaltools;



import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;

public class AnimalTouchDetector{

	public AnimalTouchDetector(Context context) {
		mDetector = new GestureDetector(context, gListener);
	}

	// TODO Выбрать целевые версии Андройда
	// TODO Добавить картинку
	
	
	public float getTouchEmotionFactor() {

		return touchFactor;
	}

	float touchFactor = 0f;
	GestureDetector mDetector;
	GestureDetector.OnGestureListener gListener = new GestureDetector.OnGestureListener() {

		@Override
		public boolean onSingleTapUp(MotionEvent e) {
			return false;
		}

		@Override
		public void onShowPress(MotionEvent e) {
		}

		@Override
		public boolean onScroll(MotionEvent e1, MotionEvent e2,
				float distanceX, float distanceY) {
			if (touchFactor <= 1f)
				touchFactor += 0.1f;

			return false;
		}

		@Override
		public void onLongPress(MotionEvent e) {
		}

		@Override
		public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
				float velocityY) {
			return false;
		}

		@Override
		public boolean onDown(MotionEvent e) {
			return false;
		}
	};

	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			return true;
		case MotionEvent.ACTION_UP:
			touchFactor = 0.5f;
			return true;
		}
		mDetector.onTouchEvent(event);
		return true;
	}

	
}
