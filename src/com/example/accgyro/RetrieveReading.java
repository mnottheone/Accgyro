package com.example.accgyro;

import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class RetrieveReading extends Activity {
	TextView redtxt;
	AccSql sq;
	CustomBaseAdapter ca;
	List<AccReadings> acc;
	ListView listt;
//	AlertDialog.Builder alertDialogBuilder; 
//	int pos;
//	View v;
//	 AlertDialog alertDialog ;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sample_xml);
		redtxt = (TextView) findViewById(R.id.titlerettxt);
		listt = (ListView) findViewById(R.id.lstred);
		sq = new AccSql(this);
		//updatation by Gaurav
//		List<AccReadings> acc = new ArrayList<AccReadings>();
		//
	    List<AccReadings> acc = sq.getAllData();
		ca = new CustomBaseAdapter(this,R.layout.redlist,acc);
		listt.setAdapter(ca);
//		alertDialogBuilder = new AlertDialog.Builder(this);
//	      alertDialogBuilder.setMessage("Want to delete this item");
//	      alertDialogBuilder.setPositiveButton("OK", 
//	      new DialogInterface.OnClickListener() {
//			
//	         @Override
//	         public void onClick(DialogInterface arg0, int arg1) {
//	        
//
//	        	    final int position = listt.getPositionForView((View) v.getParent());
//	        	    acc.remove(position);
//	        	    ca.notifyDataSetChanged();
//
//				
//	         }
//	      });
//	      alertDialogBuilder.setNegativeButton("CANCEL", 
//	      new DialogInterface.OnClickListener() {
//				
//	         @Override
//	         public void onClick(DialogInterface dialog, int which) {
//	            
//			 }
//	      });
//		    
//	      alertDialog = alertDialogBuilder.create();
//	      
//		listt.setOnItemClickListener(new OnItemClickListener() {
//
//			@Override
//			public void onItemClick(AdapterView<?> parent, View v, int position,
//					long id) {
//				// TODO Auto-generated method stub
//				Object ps = (Object) parent.getItemAtPosition(position);
//				String p = String.valueOf(ps);
//				pos = position;
//				Toast.makeText(RetrieveReading.this, "" + position, Toast.LENGTH_LONG).show();
//				alertDialog.show();
//				
//			}
//		});

		
	}
	public class ViewHolder 
	{
		TextView xdata,ydata,zdata;
		
	}
	public class CustomBaseAdapter extends ArrayAdapter<AccReadings> 
	{
		Context context;
		List<AccReadings> acc;
		public CustomBaseAdapter(Context context, int resourceId , List<AccReadings> ndatas)
		{
			super(context,resourceId,ndatas);
			this.context = context;
			this.acc=ndatas;
		}
		
		public View getView(int position, View convertView, ViewGroup parent)
		{
			ViewHolder holder = null;
			AccReadings acc1 = getItem(position);
			LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
			if (convertView == null) 
			{
				convertView = mInflater.inflate(R.layout.redlist, null);
				holder = new ViewHolder();
				holder.xdata = (TextView) convertView.findViewById(R.id.xdata);
				holder.ydata = (TextView) convertView.findViewById(R.id.ydata);
				holder.zdata = (TextView) convertView.findViewById(R.id.zdata);
				convertView.setTag(holder);
			}
			else 
			{
				holder = (ViewHolder) convertView.getTag();
			}
			holder.xdata.setText(acc1.getAccX());
			holder.ydata.setText(acc1.getAccY());
			holder.zdata.setText(acc1.getAccZ());
			
			return convertView;
		}	
	}


	

}
