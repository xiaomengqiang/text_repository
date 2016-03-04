package com.example.cet;


import com.example.tools.Preference;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.R.integer;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class ResultActivity extends Activity {
	TextView backtext,totalscore,listeningscore,multiscore,readingscore,writingscore;
	Preference preference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        preference = new Preference(this);
        initview();
        }
	private void initview() {
		// TODO Auto-generated method stub
		backtext = (TextView)findViewById(R.id.mimedetail_back);
		backtext.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(ResultActivity.this,MainActivity.class);
				startActivity(intent);
				finish();
			}
		});
		
//		String totalnumString = preference.GetUserListening()
		double listennum =  Double.parseDouble(preference.GetUserListening());
		double multinum = Double.parseDouble(preference.GetUserMultiing());
		double readnum = Double.parseDouble(preference.GetUserReading());
		double writnum = Double.parseDouble(preference.GetUserWritinging());
//		int totalnum = Integer.parseInt(preference.GetUserListening())+Integer.parseInt(preference.GetUserMultiing())+
//				       Integer.parseInt(preference.GetUserReading())+Integer.parseInt(preference.GetUserWritinging());
		totalscore = (TextView)findViewById(R.id.mtv_03);
		totalscore.setText(listennum+multinum+readnum+writnum+"");
		
		listeningscore = (TextView)findViewById(R.id.mtv_listeningscore);
		listeningscore.setText(preference.GetUserListening());
		
		multiscore = (TextView)findViewById(R.id.mtv_multtingscore);
		multiscore.setText(preference.GetUserMultiing());
		
		readingscore = (TextView)findViewById(R.id.mtv_readingscore);
		readingscore.setText(preference.GetUserReading());
		
		writingscore = (TextView)findViewById(R.id.mtv_writingscore);
		writingscore.setText(preference.GetUserWritinging());
		
	}
}
