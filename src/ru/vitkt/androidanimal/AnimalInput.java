package ru.vitkt.androidanimal;

import ru.vitkt.animaltools.SoundMeter;
import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;

public class AnimalInput implements SensorEventListener {
	private Context context;
	
	private final SensorManager mSensorManager;
	private final Sensor mProximity;
	private final Sensor mLight;
	private final Sensor mAccelerometer;
	//private SoundMeter soundMeter;

	
	
	public AnimalInput(Context _context) {

		context = _context;
		
		mSensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
		mProximity = mSensorManager
				.getDefaultSensor(Sensor.TYPE_PROXIMITY);
		
		mLight = mSensorManager
				.getDefaultSensor(Sensor.TYPE_LIGHT);
		
		mAccelerometer = mSensorManager
				.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		//soundMeter = new SoundMeter();
		//soundMeter.start();
	}
	private int lastProximity = 0;
	private int lastLight = 0;
	private float stability = 1.0f;
	private float unstableValue = 0f;
	
	int getProximity() {
		return lastProximity;
	}
	
	int getLight() {
		return lastLight;
	}
	
	public float getStability() {
		return stability;
	}
	
	public float getUnstableValue() {
		return unstableValue;
	}
//public double getAmplitude()
//{
//	return soundMeter.getAmplitude();
//	}
	@Override
	public void onAccuracyChanged(Sensor arg0, int arg1) {
		// TODO Auto-generated method stub

	}
	
	
	final int ACCEL_BUFFER_SIZE = 20;
	float [] xBuffer = new float[ACCEL_BUFFER_SIZE];
	float [] yBuffer = new float[ACCEL_BUFFER_SIZE];
	float [] zBuffer = new float[ACCEL_BUFFER_SIZE];
	
	int measureCounter = 0;
	
	void updateStability()
	{
		float avrX=0f,avrY=0f,avrZ=0f;
		for(int i=0;i<ACCEL_BUFFER_SIZE;i++)
		{
			avrX+=xBuffer[i];
			avrY+=yBuffer[i];
			avrZ+=zBuffer[i];
		}
		avrX/=(float)ACCEL_BUFFER_SIZE;
		avrY/=(float)ACCEL_BUFFER_SIZE;
		avrZ/=(float)ACCEL_BUFFER_SIZE;
		
		
		float dX=0f,dY=0f,dZ=0f;
		for(int i=0;i<ACCEL_BUFFER_SIZE;i++)
		{
			xBuffer[i]=Math.abs(xBuffer[i]-avrX);
			yBuffer[i]=Math.abs(yBuffer[i]-avrY);
			zBuffer[i]=Math.abs(zBuffer[i]-avrZ);

			dX+=xBuffer[i];
			dY+=yBuffer[i];
			dZ+=zBuffer[i];
		}
		
		dX/=(float)ACCEL_BUFFER_SIZE;
		dY/=(float)ACCEL_BUFFER_SIZE;
		dZ/=(float)ACCEL_BUFFER_SIZE;
		
		float dResult = dX+dY+dZ;
		if (dResult>10f)
		{
			unstableValue = dResult;
			dResult=10f;
			
		}
		else
			unstableValue = 0f;
		stability = 1.0f - (dResult/10.0f);
		
		
	}
	
	@Override
	public void onSensorChanged(SensorEvent event) {
		Sensor sensor = event.sensor;
		if (sensor.getType() == Sensor.TYPE_PROXIMITY)
		{
			lastProximity = (int)event.values[0];
		}
		else if (sensor.getType() == Sensor.TYPE_LIGHT)
		{
			lastLight = (int)event.values[0];
		}
		else if (sensor.getType() == Sensor.TYPE_ACCELEROMETER)
		{
			xBuffer[measureCounter] = event.values[0];
			yBuffer[measureCounter] = event.values[1];
			zBuffer[measureCounter] = event.values[2];
			
			++measureCounter;
			if (measureCounter == ACCEL_BUFFER_SIZE)
			{
				updateStability();
				measureCounter = 0;
			}
			

		}

	}

	public void onResume() {
		 mSensorManager.registerListener(this, mProximity, SensorManager.SENSOR_DELAY_NORMAL);
		 mSensorManager.registerListener(this, mLight, SensorManager.SENSOR_DELAY_NORMAL);
		 mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_FASTEST);
		// soundMeter.start();
	}

	public void onPause() {
		mSensorManager.unregisterListener(this);
		//soundMeter.stop();
		
	}


}
