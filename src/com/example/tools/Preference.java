package com.example.tools;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public  class Preference
{
    private static  SharedPreferences preference;
    private  Editor editor;

    public Preference(Context context)
    {
        preference = context.getSharedPreferences("share", Context.MODE_PRIVATE);
        editor = preference.edit();
    }


    public String GetUserMultiing()
    {
        String ListenScore = preference.getString("usermultiing", "");
        return ListenScore;
    }

    public void SetUserMultiing(String score)
    {
        editor.putString("usermultiing", score);
        editor.commit();
    }
    
    public String GetUserWritinging()
    {
        String ListenScore = preference.getString("userwriting", "");
        return ListenScore;
    }

    public void SetUserWriting(String score)
    {
        editor.putString("userwriting", score);
        editor.commit();
    }
    
    public String GetUserReading()
    {
        String ListenScore = preference.getString("userreading", "");
        return ListenScore;
    }

    public void SetUserReading(String score)
    {
        editor.putString("userreading", score);
        editor.commit();
    }
    
    public String GetUserListening()
    {
        String ListenScore = preference.getString("userlistening", "");
        return ListenScore;
    }

    public void SetUserListening(String score)
    {
        editor.putString("userlistening", score);
        editor.commit();
    }
}

