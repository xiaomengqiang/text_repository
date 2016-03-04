package com.example.reading;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import com.example.cet.R;
import com.example.tools.CharacterParser;
import com.example.tools.ClearEditText;
import com.example.tools.PinyinComparator;
import com.example.tools.Preference;
import com.example.tools.SideBar;
import com.example.tools.SideBar.OnTouchingLetterChangedListener;
import com.example.tools.SortAdapter;
import com.example.tools.SortModel;

import android.R.string;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.os.Environment;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class Cetreading extends Activity {
	private ListView sortListView;
	private SideBar sideBar;
	private TextView dialog;
	private SortAdapter adapter;
	private ClearEditText mClearEditText;
//	private String n[];
	private String n[]={"0","1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23",
			            "24","25","26","27","28","29","30","31","32","33","34","35"};
	private int code;
	private String takeplace;
	private Preference preference;
	/**
	 * 汉字转换成拼音的类
	 */
	private CharacterParser characterParser;
	private List<SortModel> SourceDateList;
	private  TextView pracel;
	private String parcelgetplace;
	SQLiteOpenHelper openHelper;
	/**
	 * 根据拼音来排列ListView里面的数据类
	 */
	private PinyinComparator pinyinComparator;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_taskaddress);
		preference = new Preference(this);
		initViews();
		selectfromdatabase();
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

	private void findsqllite() {
		
//		SQLiteDatabase db1=openHelper.getReadableDatabase();
//		String sql1="insert into listening(Num,Ans) values(?,?)";
//		db1.execSQL(sql1, new String[]{"22","33"});
//		System.out.println(getDate());
		
		// TODO Auto-generated method stub
		SQLiteDatabase db=openHelper.getReadableDatabase();
		String sql="select Ans from listening where Num=?";
		Cursor c = db.rawQuery(sql,new String[]{"1"});
		while(c.moveToNext()){
			String user=c.getString(0);
//			date=c.getString(1);
//			context=c.getString(2);
//			Log.v("ceshi", user+date+context);
			System.out.println("hhhhhhhhhhh here is sqllite value"+user);
			Toast.makeText(this, user, 1).show();
		}
	}

	private void initViews() {
		//实例化汉字转拼音类
		characterParser = CharacterParser.getInstance();
		
		pinyinComparator = new PinyinComparator();
		
//		pracel = (TextView) findViewById(R.id.compdialog);
//		pracel.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View arg0) {
//				// TODO Auto-generated method stub
////				showInfo();
//			}
//		});
		sideBar = (SideBar) findViewById(R.id.sidrbar);
		dialog = (TextView) findViewById(R.id.dialog);
		sideBar.setTextView(dialog);
		
		//设置右侧触摸监听
		sideBar.setOnTouchingLetterChangedListener(new OnTouchingLetterChangedListener() {
			
			@Override
			public void onTouchingLetterChanged(String s) {
				//该字母首次出现的位置
				int position = adapter.getPositionForSection(s.charAt(0));
				if(position != -1){
					sortListView.setSelection(position);
				}
				
			}
		});
		
		sortListView = (ListView) findViewById(R.id.country_lvcountry);
		
		sortListView.setOnItemLongClickListener(new OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view,
                    int position, long id) {
//            	String str = ((SortModel)adapter.getItem(position)).getName();
//
//            	
//            	SourceDateList.remove(position);
//
//            	
//            	adapter.notifyDataSetChanged();
 
                return true;
            }
 
        });
		
		sortListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				//这里要利用adapter.getItem(position)来获取当前position所对应的对象
//				Toast.makeText(getApplication(), ((SortModel)adapter.getItem(position)).getName(), Toast.LENGTH_SHORT).show();
//				Intent resultIntent = new Intent();
//				Bundle bundle = new Bundle();
////				if(code == 1)
////				{
//				bundle.putString("result", ((SortModel)adapter.getItem(position)).getName());
////				}
////				else {
////					bundle.putString("result", preference.getpracelplace());
////				}
//				resultIntent.putExtras(bundle);
//				ShopCartActivity.this.setResult(1, resultIntent);
//				finish();
//				Intent intent = new Intent();
//				intent.setClass(goods.this, InformationActivity.class);
//				startActivity(intent);
				
				SQLiteDatabase db=openHelper.getReadableDatabase();
				String sql="select Ans from listening where Num=?";
//				Toast.makeText(Cetlistening.this, n[position], 1).show();
				Cursor c = db.rawQuery(sql,new String[]{((SortModel)adapter.getItem(position)).getName()});
				while(c.moveToNext()){
					String user=c.getString(0);
//					date=c.getString(1);
//					context=c.getString(2);
//					Log.v("ceshi", user+date+context);
					System.out.println("hhhhhhhhhhh here is sqllite value"+user);
					preference.SetUserReading(user);
					Toast.makeText(Cetreading.this, user, 1).show();
				}
			}
		});
		
		
			SourceDateList = filledData(n);
		// 根据a-z进行排序源数据
		Collections.sort(SourceDateList, pinyinComparator);
		adapter = new SortAdapter(this, SourceDateList);
		sortListView.setAdapter(adapter);
		
		
		mClearEditText = (ClearEditText) findViewById(R.id.filter_edit);
		
		//根据输入框输入值的改变来过滤搜索
		mClearEditText.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				//当输入框里面的值为空，更新为原来的列表，否则为过滤数据列表
				filterData(s.toString());
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
			}
		});
	}
	  
    /*
     * showinfo  弹出框
     */
	
	 
	/**
	 * 为ListView填充数据
	 * @param date
	 * @return
	 */
	private List<SortModel> filledData(String [] date){
		List<SortModel> mSortList = new ArrayList<SortModel>();
		
		for(int i=0; i<date.length; i++){
			SortModel sortModel = new SortModel();
			sortModel.setName(date[i]);
			//汉字转换成拼音
			String pinyin = characterParser.getSelling(date[i]);
			if(pinyin.length()>0)
			{
				String sortString = pinyin.substring(0, 1).toUpperCase();
				// 正则表达式，判断首字母是否是英文字母
				if(sortString.matches("[A-Z]")){
					sortModel.setSortLetters(sortString.toUpperCase());
				}else{
					sortModel.setSortLetters("#");
				}
			mSortList.add(sortModel);
			}
		}
		return mSortList;
		
	}
	
	/**
	 * 根据输入框中的值来过滤数据并更新ListView
	 * @param filterStr
	 */
	private void filterData(String filterStr){
		List<SortModel> filterDateList = new ArrayList<SortModel>();
		
		if(TextUtils.isEmpty(filterStr)){
			filterDateList = SourceDateList;
		}else{
			filterDateList.clear();
			for(SortModel sortModel : SourceDateList){
				String name = sortModel.getName();
				if(name.indexOf(filterStr.toString()) != -1 || characterParser.getSelling(name).startsWith(filterStr.toString())){
					filterDateList.add(sortModel);
				}
			}
		}
		
		// 根据a-z进行排序
		Collections.sort(filterDateList, pinyinComparator);
		adapter.updateListView(filterDateList);
	}
	
}
