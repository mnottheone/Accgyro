package com.example.accgyro;

import java.util.LinkedList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AccSql extends SQLiteOpenHelper {
	private static String DATABASE_NAME = "AccData";
	private static int DATABASE_VERSION = 1;

	public AccSql(Context context) {
		super(context,DATABASE_NAME,null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase arg0) {
		// TODO Auto-generated method stub
		String CREATE_TABLE = "CREATE TABLE AccReading ("+"id INTEGER PRIMARY KEY AUTOINCREMENT, "+"xdir TEXT, "+"ydir TEXT, "
		                                                   +"zdir TEXT )";
		arg0.execSQL(CREATE_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		arg0.execSQL("DROP TABLE IF EXISTS routes");
		this.onCreate(arg0);
	}
	
	private static final String TABLE_NAME = "AccReading";
    private static final String KEY_ID = "id";
    private static final String KEY_X = "xdir";
    private static final String KEY_Y = "ydir";
    private static final String KEY_Z = "zdir";
    
    public void addData(AccReadings ar) {
  	  SQLiteDatabase db = this.getWritableDatabase();
  	     ContentValues values = new ContentValues();
  	     values.put(KEY_X,ar.getAccX());
  	     values.put(KEY_Y,ar.getAccY());
  	     values.put(KEY_Z,ar.getAccZ());
  	     db.insertOrThrow(TABLE_NAME, null, values);
  	     db.close();
    }
    
    public List<AccReadings> getAllData() {
    	List<AccReadings> accrdng = new LinkedList<AccReadings>();
    	String query = "SELECT * FROM "+ TABLE_NAME;
    	SQLiteDatabase db = this.getWritableDatabase();
    	Cursor cursor = db.rawQuery(query, null);
    	AccReadings acr = null;
    	if(cursor.moveToFirst()) {
    	do {
    		acr = new AccReadings();
    		acr.setAccId(Integer.parseInt((cursor.getString(cursor.getColumnIndex(KEY_ID)))));
			acr.setAccX((cursor.getString(cursor.getColumnIndex(KEY_X))));
			acr.setAccY((cursor.getString(cursor.getColumnIndex(KEY_Y))));
			acr.setAccZ(cursor.getString(cursor.getColumnIndex(KEY_Z)));
    		accrdng.add(acr);
    	}while(cursor.moveToNext());
    	
    	}
		return accrdng;
    	
    }
    
//    public String Test() {
//    	String query = "SELECT * FROM "+ TABLE_NAME;
//    	SQLiteDatabase db = this.getWritableDatabase();
//    	Cursor cursor = db.rawQuery(query, null);
//    	AccReadings acr;
//    	String s="Gaurav";
//    	int i = 0;
//    	if(cursor.moveToFirst()) {
//    	do {
//    		acr = new AccReadings();
//    		//acr.setAccId(Integer.parseInt((cursor.getString(cursor.getColumnIndex(KEY_ID)))));
//			if(i==1) {
//             s=((cursor.getString(cursor.getColumnIndex(KEY_X))));
//			}
//			acr.setAccY((cursor.getString(cursor.getColumnIndex(KEY_Y))));
//			acr.setAccZ(cursor.getString(cursor.getColumnIndex(KEY_Z)));
//    		//accrdng.add(acr);
//    	}while(cursor.moveToNext());
//   
//    	
//    }
//		return s;
//    
//    }
}
    
    
    
    
    