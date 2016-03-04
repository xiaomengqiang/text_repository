package com.example.cet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import com.example.listen.Cetlistening;
import com.example.multiing.Cetmulti;
import com.example.reading.Cetreading;
import com.example.tools.Preference;
import com.example.tools.SortModel;
import com.example.writing.Cetwriting;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.R.integer;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Build;

public class MainActivity extends Activity {

	private RelativeLayout listen,reading,multi,writing;
	private Button exitbutton,listenadd,listensub,multiadd,multisub,readadd,readsub,writeadd,writesub;
	private TextView listennum,multinum,readnum,writenum;
	SQLiteOpenHelper openHelper;
	private Preference preference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        preference = new Preference(this);
    	selectfromdatabase();
        initview();
        
        }
    
	private void selectfromdatabase() {
		// TODO Auto-generated method stub
		String dbName=Environment.getExternalStorageDirectory().getAbsolutePath()+"/cetdb.db";
		try{
			File file=new File(dbName);
			if(!file.exists()){
				InputStream fis=getAssets().open("database/cetdb.db");
				FileOutputStream fos=new FileOutputStream(dbName,false);
				byte[] buff=new byte[1024];
				int len=0;
				while((len=fis.read(buff))>0){
					fos.write(buff, 0,len);
				}
				fos.close();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		openHelper=new SQLiteOpenHelper(this,dbName,null,1) {
			@Override
			public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
				// TODO Auto-generated method stub
			}
			@Override
			public void onCreate(SQLiteDatabase db) {
				// TODO Auto-generated method stub
			}
		};
		
//		findsqllite();
	}
	
	private void initview() {
		// TODO Auto-generated method stub
		listen = (RelativeLayout)findViewById(R.id.cetlisten);
		listen.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MainActivity.this,Cetlistening.class);
				startActivity(intent);
			}
		});
		
		reading = (RelativeLayout)findViewById(R.id.cetread);
		reading.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MainActivity.this,Cetreading.class);
				startActivity(intent);
			}
		});
		multi = (RelativeLayout)findViewById(R.id.cetmulti);
		
		multi.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MainActivity.this,Cetmulti.class);
				startActivity(intent);
			}
		});
		
		writing = (RelativeLayout)findViewById(R.id.cetwriting);
		writing.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MainActivity.this,Cetwriting.class);
				startActivity(intent);
			}
		});
		
		/*
		 * 点击下一步进入计算按钮
		 */
		exitbutton = (Button)findViewById(R.id.exit_login_btn);
		exitbutton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				OperateCetPoints();
				Intent intent = new Intent(MainActivity.this,ResultActivity.class);
				startActivity(intent);
			}
		});
		
		listennum = (TextView)findViewById(R.id.listenet01);
		listennum.setText("20");
		
		listenadd = (Button)findViewById(R.id.listenbt01);
		listenadd.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				String listennumstring = listennum.getText().toString();
				int listeninteager = Integer.valueOf(listennumstring);
				if(listeninteager >= 0 && listeninteager < 35)
				{
					listeninteager ++;
					listennum.setText(listeninteager+"");
				}
				// TODO Auto-generated method stub
//				Intent intent = new Intent(MainActivity.this,ResultActivity.class);
//				startActivity(intent);
			}
		});
		
		listensub = (Button)findViewById(R.id.listenbt02);
		listensub.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String listennumstring = listennum.getText().toString();
				int listeninteager = Integer.valueOf(listennumstring);
				if(listeninteager > 0 && listeninteager <= 35)
				{
					listeninteager --;
					listennum.setText(listeninteager+"");
				}
			}
		});
		
		multinum = (TextView)findViewById(R.id.multiet01);
		multinum.setText("20");
		
		multiadd = (Button)findViewById(R.id.multibt01);
		multiadd.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				String listennumstring = multinum.getText().toString();
				int listeninteager = Integer.valueOf(listennumstring);
				if(listeninteager >= 0 && listeninteager < 35)
				{
					listeninteager ++;
					multinum.setText(listeninteager+"");
				}
				// TODO Auto-generated method stub
//				Intent intent = new Intent(MainActivity.this,ResultActivity.class);
//				startActivity(intent);
			}
		});
		
		multisub = (Button)findViewById(R.id.multibt02);
		multisub.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String listennumstring = multinum.getText().toString();
				int listeninteager = Integer.valueOf(listennumstring);
				if(listeninteager > 0 && listeninteager <= 35)
				{
					listeninteager --;
					multinum.setText(listeninteager+"");
				}
			}
		});
		
		
		readnum = (TextView)findViewById(R.id.readet01);
		readnum.setText("20");
		
		readadd = (Button)findViewById(R.id.readbt01);
		readadd.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				String listennumstring = readnum.getText().toString();
				int listeninteager = Integer.valueOf(listennumstring);
				if(listeninteager >= 0 && listeninteager < 35)
				{
					listeninteager ++;
					readnum.setText(listeninteager+"");
				}
				// TODO Auto-generated method stub
//				Intent intent = new Intent(MainActivity.this,ResultActivity.class);
//				startActivity(intent);
			}
		});
		
		readsub = (Button)findViewById(R.id.readbt02);
		readsub.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String listennumstring = readnum.getText().toString();
				int listeninteager = Integer.valueOf(listennumstring);
				if(listeninteager > 0 && listeninteager <= 35)
				{
					listeninteager --;
					readnum.setText(listeninteager+"");
				}
			}
		});
		
		writenum = (TextView)findViewById(R.id.writinget01);
		writenum.setText("20");
		
		writeadd = (Button)findViewById(R.id.writingbt01);
		writeadd.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				String listennumstring = writenum.getText().toString();
				int listeninteager = Integer.valueOf(listennumstring);
				if(listeninteager >= 0 && listeninteager < 35)
				{
					listeninteager ++;
					writenum.setText(listeninteager+"");
				}
				// TODO Auto-generated method stub
//				Intent intent = new Intent(MainActivity.this,ResultActivity.class);
//				startActivity(intent);
			}
		});
		
		writesub = (Button)findViewById(R.id.writingbt02);
		writesub.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String listennumstring = writenum.getText().toString();
				int listeninteager = Integer.valueOf(listennumstring);
				if(listeninteager > 0 && listeninteager <= 35)
				{
					listeninteager --;
					writenum.setText(listeninteager+"");
				}
			}
		});
	}
	protected void OperateCetPoints() {
		// TODO Auto-generated method stub
		String listenString = listennum.getText().toString();
		String multiString = multinum.getText().toString();
		String readString = readnum.getText().toString();
		String wriString = writenum.getText().toString();
		
		Toast.makeText(MainActivity.this,listenString, 1).show();
		Toast.makeText(MainActivity.this,multiString, 1).show();
		Toast.makeText(MainActivity.this,readString, 1).show();
		Toast.makeText(MainActivity.this,wriString, 1).show();
		
		Getlistennumber(listenString);
		Getmultinumber(multiString);
		Getreadnumber(readString);
		Getwritingnumber(wriString);
	}

	private void Getwritingnumber(String wriString) {
		// TODO Auto-generated method stub
		SQLiteDatabase db=openHelper.getReadableDatabase();
		String sql="select Ans from writing where Num=?";
//		Toast.makeText(Cetlistening.this, n[position], 1).show();
		Cursor c = db.rawQuery(sql,new String[]{wriString});
		while(c.moveToNext()){
			String user=c.getString(0);
//			date=c.getString(1);
//			context=c.getString(2);
//			Log.v("ceshi", user+date+context);
			System.out.println("hhhhhhhhhhh here is sqllite value"+user);
			preference.SetUserWriting(user);
			Toast.makeText(MainActivity.this, user, 1).show();
		}
	}

	private void Getreadnumber(String readString) {
		// TODO Auto-generated method stub
		SQLiteDatabase db=openHelper.getReadableDatabase();
		String sql="select Ans from reading where Num=?";
//		Toast.makeText(Cetlistening.this, n[position], 1).show();
		Cursor c = db.rawQuery(sql,new String[]{readString});
		while(c.moveToNext()){
			String user=c.getString(0);
//			date=c.getString(1);
//			context=c.getString(2);
//			Log.v("ceshi", user+date+context);
			System.out.println("hhhhhhhhhhh here is sqllite value"+user);
			preference.SetUserReading(user);
			Toast.makeText(MainActivity.this, user, 1).show();
		}
	}

	private void Getmultinumber(String multiString) {
		// TODO Auto-generated method stub
		SQLiteDatabase db=openHelper.getReadableDatabase();
		String sql="select Ans from multiing where Num=?";
//		Toast.makeText(Cetlistening.this, n[position], 1).show();
		Cursor c = db.rawQuery(sql,new String[]{multiString});
		while(c.moveToNext()){
			String user=c.getString(0);
//			date=c.getString(1);
//			context=c.getString(2);
//			Log.v("ceshi", user+date+context);
			System.out.println("hhhhhhhhhhh here is sqllite value"+user);
			preference.SetUserMultiing(user);
			Toast.makeText(MainActivity.this, user, 1).show();
		}
	}

	private void Getlistennumber(String listenString) {
		// TODO Auto-generated method stub
		SQLiteDatabase db=openHelper.getReadableDatabase();
		String sql="select Ans from listening where Num=?";
//		Toast.makeText(Cetlistening.this, n[position], 1).show();
		Cursor c = db.rawQuery(sql,new String[]{listenString});
		while(c.moveToNext()){
			String user=c.getString(0);
//			date=c.getString(1);
//			context=c.getString(2);
//			Log.v("ceshi", user+date+context);
			System.out.println("hhhhhhhhhhh here is sqllite value"+user);
			preference.SetUserListening(user);
			Toast.makeText(MainActivity.this, user, 1).show();
		}
	}
}
