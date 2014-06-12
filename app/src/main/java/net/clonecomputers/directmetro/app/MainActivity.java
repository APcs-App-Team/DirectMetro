package net.clonecomputers.directmetro.app;

import android.content.Context;
import android.content.Intent;
import android.location.GpsStatus;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.provider.Settings;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.location.LocationListener;

import com.google.gson.Gson;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;


public class MainActivity extends ActionBarActivity {

    private Controller con;
    private ListView listView;
    public final static String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    public final static String DATASTRUCT_MESSAGE = "com.example.myfirstapp.DATA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //setup code
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    /*
    * this methoed is a supporter method for the contorler constructor
    * */

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void getStationOption(View view){
        Intent intent = new Intent(this, StationSelect.class);

        //this gets the message form the text box
        EditText stationMessage = (EditText) findViewById(R.id.StationName);
        String message = stationMessage.getText().toString();

        //the beggining station
        intent.putExtra(EXTRA_MESSAGE, message);

        startActivity(intent);
    }

    private Location getCurrentLocation(){

        //LocationManager mgr =
        //        (LocationManager)getActivity().getSystemService(Context.LOCATION_SERVICE);
        //System.out.println(carrierName);
        LocationManager locationManager =
                (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        final boolean gpsEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        final boolean isNetworkEnabled = locationManager
                .isProviderEnabled(LocationManager.NETWORK_PROVIDER);

        Location location = null;
        System.out.println("GPS PROVIDER: " + LocationManager.GPS_PROVIDER);
        System.out.println("GPS IS: " + gpsEnabled);

        if(!gpsEnabled)
            enableLocationSettings();
        else{
            location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        }
        //System.out.println(location == null);
        return location;
    }

    private void enableLocationSettings() {
        Intent settingsIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        startActivity(settingsIntent);
    }
}
