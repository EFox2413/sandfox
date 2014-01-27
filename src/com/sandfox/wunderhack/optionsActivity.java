package com.sandfox.wunderhack;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.Html;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class optionsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.options_activity);
    }
    
    public void onRadioButtonClicked(View v) {
    	
    }
    
    public void saveOptions(View view) {
    	finish();
    }
}