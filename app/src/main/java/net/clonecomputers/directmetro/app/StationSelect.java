package net.clonecomputers.directmetro.app;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.concurrent.ExecutionException;


public class StationSelect extends ActionBarActivity {

    private Controller con;
    private String Destination;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();

        Destination = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        AsyncTask<Void, Integer, String> execute = new DownloadFilesTask().execute((Void[]) null);

        try {
            con = new Controller(-73.99188, 40.68610, execute.get());
            //con = new Controller(getCurrentLocation().getLongitude(), getCurrentLocation().getLatitude(), execute.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        setContentView(R.layout.activity_station_select);
        //init ListView
        listView = (ListView) findViewById(R.id.List);
        //setting Text View
        TextView textView = (TextView) findViewById(R.id.StationName);
        textView.setText(Destination);

        //loads the Station Data
        Station[] StationObjects = con.getClosetsStations(Destination);
        String[] StationHolder = new String[con.getClosetsStations(Destination).length];

        //turns the Station Data (as object) to String data so to be displayed
        for(int i = 0; i < StationHolder.length; i++){
            StationHolder[i] = StationObjects[i].getName();
        }

        //adapted is delcared and then impended
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, StationHolder);
        System.out.println(listView == null);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String  itemValue  = (String) listView.getItemAtPosition(i);
                System.out.println(con.getDestinationLine(itemValue).getLine());
                System.out.println(con.getClosetsStations(Destination)[i].getLat());
                System.out.println(con.getClosetsStations(Destination)[i].getLong());
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.station_select, menu);
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
}
