package ru.vitkt.androidanimal;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

public class AnimalScreenViewGrid extends AnimalScreenView {

	public AnimalScreenViewGrid(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		
	}
	
	final int N=30, M = 20;
	int [][] colorMatrix = new int [N][M];
	
	Rect getRectByIndex(int i, int j)
	{
		int rectWidth = (int)((float)getWidth() / (float) M);
		int rectHeight = (int)((float)getHeight() / (float) N);
		Rect rect = new Rect();
		rect.top = i*rectHeight;
		rect.bottom = rect.top+rectHeight;
		rect.left = j*rectWidth;
		rect.right = rect.left+rectWidth;
		
		return rect;
	}
	
	Paint paint = new Paint();
	@Override
	public void draw(Canvas canvas) {
	
		super.draw(canvas);
		 
		for(int i=0;i<N;i++)
		{
			for(int j=0;j<M;j++)
			{
				paint.setColor(colorMatrix[i][j]);
				canvas.drawRect(getRectByIndex(i,j), paint);
			}
		}
	}
	
	@Override
	public void setColor(int a, int r, int g, int b) {
		int i = (int)(Math.random()*N);
		int j = (int)(Math.random()*M);
		colorMatrix[i][j]=Color.argb(255,r, g, b);
		invalidate();
	
	}

}
