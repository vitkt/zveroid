package ru.vitkt.androidanimal;

import android.content.Context;
import android.graphics.Color;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

public class AnimalScreenView extends TextView {

	public AnimalScreenView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public void setColor(int a, int r, int g, int b) {
		setBackgroundColor(Color.argb(255, r, g, b));
		// setText(a+";"+r+";"+g+";"+b+";"); for Debug
	}

	public float getTouchEmotionFactor() {

		return touchFactor;
	}

	float touchFactor = 0f;
	
	GestureDetector mDetector = new GestureDetector(new GestureDetector.OnGestureListener() {
		
		@Override
		public boolean onSingleTapUp(MotionEvent e) {
			// TODO Auto-generated method stub
			return false;
		}
		
		@Override
		public void onShowPress(MotionEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
				float distanceY) {
			if (touchFactor<=1f)
				touchFactor+=0.1f;
			// TODO Auto-generated method stub
			return false;
		}
		
		@Override
		public void onLongPress(MotionEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
				float velocityY) {
			// TODO Auto-generated method stub
			return false;
		}
		
		@Override
		public boolean onDown(MotionEvent e) {
			// TODO Auto-generated method stub
			return false;
		}
	});
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			
			return true;
		case MotionEvent.ACTION_UP:
			touchFactor = 0.5f;
			return true;
		}
		mDetector.onTouchEvent(event);
		return super.onTouchEvent(event);
	}

}
