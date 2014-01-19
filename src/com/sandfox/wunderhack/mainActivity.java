package com.sandfox.wunderhack;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.google.android.gms.maps.*;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.model.*;

import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class mainActivity extends FragmentActivity 
		implements
		OnMapClickListener {

	private GoogleMap mMap;
	private int addMarkCheck = 0;
	private double lat, lon;
	private String FILENAME = "coords";
	private byte [] fisbyte;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        initMap();
        
        try {
			FileInputStream fis = openFileInput(FILENAME);
		//	String coords = String(fis.read());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
        mMap.setOnMapClickListener(new OnMapClickListener() {
    	
        	@Override
    		public void onMapClick(LatLng arg0) {
    			if (addMarkCheck == 1){
    				mMap.addMarker(new MarkerOptions()
    						.position(arg0));
    				
    				lat = arg0.latitude;
    				lon = arg0.longitude;
    				
    				Toast.makeText(getApplicationContext(), arg0.latitude + "" 
    								+ arg0.longitude, 1).show();
    				    				
    				    				
    				addMarkCheck = 0;
    			}
    		}
    	});
    }
    
    public void writeToInternal() throws IOException {
        FileOutputStream fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
        fos.write((lat+""+lon).getBytes());
        fos.close();
    }
    

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    @Override
    protected void onResume() {
        super.onResume();
        initMap();
    }
    
    private void initMap() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
        
        
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.menu_marker:
                addMarkCheck = 1;
                Intent i = new Intent(this, optionsActivity.class);
				startActivity(i);
				return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    
    private void setUpMap() {
        
    }

	@Override
	public void onMapClick(LatLng arg0) {
		// TODO Auto-generated method stub
		
	}
    
}