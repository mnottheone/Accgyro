 package com.example.accgyro;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class Accelerometer extends Activity implements SensorEventListener {
	
	private SensorManager senmng;
	private Sensor accn;
	private long lastUpdate = 0;
	TextView txtx,txty,txtz;               //Displays X,Y,Z value of accelerometer
	Button btnsave;
	AccSql sq;
	private int KEY_SAVE=0;                //Used to achieve "start and stop" saving functionality 
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accelerometer);
        senmng = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accn = senmng.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        senmng.registerListener(this,accn,SensorManager.SENSOR_DELAY_NORMAL);
        sq = new AccSql(this);
        txtx = (TextView)findViewById(R.id.txtx);
        txty = (TextView)findViewById(R.id.txty);
        txtz = (TextView)findViewById(R.id.txtz);
        btnsave = (Button) findViewById(R.id.btnsave);
      btnsave.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
//				Log.v("X value",(txtx.getText().toString()));
                KEY_SAVE=1;				
                sq.addData(new AccReadings("Reading","Started",""));
                Toast.makeText(Accelerometer.this,"Saving started",Toast.LENGTH_SHORT).show();
//				String a = txtx.getText().toString();
//				String b = txty.getText().toString();
//				String c = txtz.getText().toString();
//				// TODO Auto-generated method stub
//				sq.addData(new AccReadings(a,b,c));
//				sq.addData(new AccReadings("Reading","Taken",""));
//				AccReadings accr = new AccReadings();
//				accr.setAccX(a);
//				accr.setAccY(b);
//				accr.setAccZ(c);
//				Log.d("X value",a);
//				sq.addData(accr);
//				Toast.makeText(Accelerometer.this,sq.Test() ,Toast.LENGTH_LONG).show();
//				Intent i = new Intent(Accelerometer.this,RetrieveReading.class);
//				startActivity(i);
			}
		});
    }

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		// TODO Auto-generated method stub
		Sensor mySensor = event.sensor;
		if(mySensor.getType()==Sensor.TYPE_ACCELEROMETER) {
			float x = event.values[0];
			float y = event.values[1];
			float z = event.values[2];
			long curTime = System.currentTimeMillis();
			if((curTime-lastUpdate)>1000) {                      //accelerometer values will be displayed in textfield after 
			txtx.setText(Float.toString(x));                     // every 1 second 
			txty.setText(Float.toString(y));
			txtz.setText(Float.toString(z));
			if(KEY_SAVE==1) {
				String a = txtx.getText().toString();
				String b = txty.getText().toString();
				String c = txtz.getText().toString();
				// TODO Auto-generated method stub
				sq.addData(new AccReadings(a,b,c));
//				sq.addData(new AccReadings("Reading","Taken",""));	
			}
		    lastUpdate = curTime;
		   
			}
		}
	}
	
//	protected void onPause() {                                  
//		Log.d("Paused Gaurav", "App unregistered");
//		super.onPause();
//		senmng.unregisterListener(this);
//	}
	
	
//	protected void onResume() {
//		super.onResume();
//		senmng.registerListener(this,accn,SensorManager.SENSOR_DELAY_NORMAL);
//	}
//public void OnClick(View v) {
//	Log.d("X value",(txtx.getText().toString()));
//	String a = txtx.getText().toString();
//	String b = txty.getText().toString();
//	String c = txtz.getText().toString();
//	// TODO Auto-generated method stub
//	 sq = new AccSql(Accelerometer.this);
//	AccReadings accr = new AccReadings();
//	accr.setAccX(a);
//	accr.setAccY(b);
//	accr.setAccZ(c);
//	Log.d("X value",a);
//	sq.addData(accr);
//	Toast.makeText(Accelerometer.this,sq.Test() ,Toast.LENGTH_LONG).show();
//	Intent i = new Intent(Accelerometer.this,Saving.class);
//	i.putExtra("Check", a);
//	startActivity(i);
//}
   
	
	public void stopSave(View v) {
		KEY_SAVE = 0;
		sq.addData(new AccReadings("Reading","Stopped",""));
		Toast.makeText(Accelerometer.this,"Saving stopped",Toast.LENGTH_SHORT).show();
	}
	
	public void showList(View v) {
		Intent intent = new Intent(this,RetrieveReading.class);
		startActivity(intent);
	}
	
	public void showGyro(View v) {
		Intent intent = new Intent(this,Gyroscope.class);
		startActivity(intent);
	}
	
//	public void delSql() {                     // Not added delete functionality.
//	
//	}
	
    protected void onDestroy() {
    	super.onDestroy();
    	senmng.unregisterListener(this);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
