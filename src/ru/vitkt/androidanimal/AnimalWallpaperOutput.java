package ru.vitkt.androidanimal;

import android.animation.ArgbEvaluator;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;


public class AnimalWallpaperOutput {

	 int N=4, M = 4;
	int [][] colorMatrix = new int [N][M];
	
	final ArgbEvaluator rgbEval = new ArgbEvaluator();
	
	void setMatrixSize(int rows, int columns)
	{
		N=rows;
		M=columns;
		colorMatrix = new int [N][M];
		
	}
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
	int width,height;
	private int getHeight() {
		// TODO Auto-generated method stub
		return height;
	}

	private int getWidth() {
		// TODO Auto-generated method stub
		return width;
	}
	
	public void setWidth(int _width)
	{
		width=_width;
	}
	public void setHeight(int _height)
	{
		height=_height;
	}
	Paint paint = new Paint();
	
	public void draw(Canvas canvas) {
		 canvas.drawColor(Color.WHITE);
		for(int i=0;i<N;i++)
		{
			for(int j=0;j<M;j++)
			{
				paint.setColor(colorMatrix[i][j]);
				canvas.drawRect(getRectByIndex(i,j), paint);
			}
		}
	}
	
	public void updateLastCell(int counter)
	{
		counter++;
		float fract = ((float)counter)/10.0f;
		colorMatrix[curI][curJ]=(Integer)rgbEval.evaluate(fract, fromColor, toColor);
	}
	boolean isFirst = true;
	int fromColor;
	int toColor;
	int curI=-1,curJ=-1;
	public void setColor(int a, int r, int g, int b) {
		if (isFirst)
		{
			fromColor = Color.argb(a, r, g, b);
			toColor = fromColor;
			isFirst = false;
		}
		fromColor = toColor;
		toColor = Color.argb(a, r, g, b);
		curI = (int)(Math.random()*N);
		curJ = (int)(Math.random()*M);
		colorMatrix[curI][curJ]=fromColor;

	
	}




}
