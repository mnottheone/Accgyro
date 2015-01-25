package com.example.accgyro;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class Gyroscope extends Activity implements SensorEventListener {

	TextView txtX,txtY,txtZ;
	private SensorManager senmng;
	private Sensor gyro;
	private long lastUpdate = 0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gyroscope);
        txtX = (TextView) findViewById(R.id.txt10);
        txtY = (TextView) findViewById(R.id.txt11);
        txtZ = (TextView) findViewById(R.id.txt12);
        senmng = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        gyro = senmng.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        senmng.registerListener(this,gyro,SensorManager.SENSOR_DELAY_NORMAL);
	}

	@Override
	public void onAccuracyChanged(Sensor arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		// TODO Auto-generated method stub
		Sensor mySensor = event.sensor;
		if(mySensor.getType()==Sensor.TYPE_GYROSCOPE) {
			float x = event.values[0];
			float y = event.values[1];
			float z = event.values[2];
			long curTime = System.currentTimeMillis();
			if((curTime-lastUpdate)>1000) {                     // 1 second delay
			txtX.setText(Float.toString(x));
			txtY.setText(Float.toString(y));
			txtZ.setText(Float.toString(z));
			lastUpdate = curTime;
			}
		}
	}
	
	protected void onPause() {
		//Log.d("Paused Gaurav", "App unregistered");
		super.onPause();
		senmng.unregisterListener(this);
	}
	
	
	protected void onResume() {
		super.onResume();
		senmng.registerListener(this,gyro,SensorManager.SENSOR_DELAY_NORMAL);
	}

}