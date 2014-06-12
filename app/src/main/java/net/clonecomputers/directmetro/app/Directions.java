package net.clonecomputers.directmetro.app;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;


public class Directions extends ActionBarActivity {

    private String[] directions;
    private String[] dir_raw;
    public final static String EXTRA_MESSAGE = "net.clonecomputers.DirectMetro.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_directions);

        Intent intent = getIntent();
        directions = intent.getStringArrayExtra(StationSelect.DIRECTION_KEY);
        dir_raw = new String[directions.length/3];

        ListView listView = (ListView) findViewById(R.id.listView);
        int count = 0;
        for(int i = 0; i < directions.length; i+=3){
            dir_raw[count] = directions[i] + "             (" + directions[i+1] + ")\n"+
                    directions[i+2];
            count++;
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, dir_raw);

        listView.setAdapter(adapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.directions, menu);
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
}
