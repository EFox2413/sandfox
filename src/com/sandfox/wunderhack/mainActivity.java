package com.sandfox.wunderhack;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;

import com.google.android.gms.maps.*;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.model.*;

import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class mainActivity extends FragmentActivity 
		implements
		OnMapClickListener {

	private GoogleMap mMap;
	private int addMarkCheck = 0;
	private String FILENAME = "coords";
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        initMap();
        readFromInternal();
        
        mMap.setOnMapClickListener(new OnMapClickListener() {
    	
        	@Override
    		public void onMapClick(LatLng arg0) {
    			if (addMarkCheck == 1){
    				mMap.addMarker(new MarkerOptions()
    					.position(arg0));
    				
    				double lat = arg0.latitude;
    				double lon = arg0.longitude;
    				String coord = arg0.latitude + "," + arg0.longitude + "\n";
    				
    				Toast.makeText(getApplicationContext(), coord, 1).show();
    				
    				try {
						writeToInternal(coord);
					} catch (IOException e) {
						Toast.makeText(getApplicationContext(), "Failed to write", 1).show();
						e.printStackTrace();
					}
    				addMarkCheck = 0;
    			}
    		}
    	});
    }//end of onCreate()
    
    @Override
    protected void onResume() {
        super.onResume();
        initMap();
    }
    
    public void writeToInternal(String str) throws IOException {       
    	FileOutputStream fos = openFileOutput(FILENAME, Context.MODE_APPEND); 
    	
        fos.write(str.getBytes());
        fos.close();
    }
    
    public void readFromInternal() {
    	try {
			BufferedReader in = new BufferedReader(
					new InputStreamReader(openFileInput(FILENAME)));
			 
					String str;
					StringBuffer strBuf = new StringBuffer();
								 
					while ((str = in.readLine()) != null) {
						
						String[] bits = str.toString().split(",",2);
						bits[1] = bits[1].substring (0, bits[1].length());
						
						Toast.makeText(getApplicationContext(), bits[0] + " , " + bits[1], 1).show();
						
					    double lat = Double.parseDouble(bits[0]);
					    double lon = Double.parseDouble(bits[1]);
					    LatLng coorda = new LatLng(lat, lon);
					    
					    Toast.makeText(getApplicationContext(), coorda.latitude + "" 
								+ coorda.longitude, 1).show();
					    
						mMap.addMarker(new MarkerOptions()
					    	.position(coorda));
					}
			        in.close();
		} 
        catch (FileNotFoundException e) 
        {
			e.printStackTrace();
			Toast.makeText(getApplicationContext(), "FNF", Toast.LENGTH_SHORT).show();
		} 
        catch (UnsupportedEncodingException e) 
	    {
			Toast.makeText(getApplicationContext(), e.getMessage(), 1).show();
	    } 
	    catch (IOException e) 
	    {
	    	Toast.makeText(getApplicationContext(), e.getMessage(), 1).show();
	    }
	    catch (Exception e)
	    {
	    	Toast.makeText(getApplicationContext(), e.getMessage(), 1).show();
	    }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    
    private void initMap() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
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

	@Override
	public void onMapClick(LatLng arg0) {
	
	}
	
}