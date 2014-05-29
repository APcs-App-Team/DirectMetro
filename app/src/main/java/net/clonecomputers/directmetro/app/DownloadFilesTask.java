package net.clonecomputers.directmetro.app;

import android.os.AsyncTask;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by giorescigno on 5/28/14.
 */
public class DownloadFilesTask extends AsyncTask<Void, Integer, String> {
    @Override
    protected String doInBackground(Void... voids) {
        try {
            return getJsonFromStationData();
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }
    private String getJsonFromStationData() throws IOException {

        System.out.println("data function is running");
        DefaultHttpClient httpclient = new DefaultHttpClient();

        //HttpGet httppost = new HttpGet("http://data.ny.gov/resource/i9wp-a4ja.json");
        HttpGet httppost = new HttpGet("https://dl.dropboxusercontent.com/u/92495851/data.json");
        HttpResponse response = httpclient.execute(httppost);
        HttpEntity ht = response.getEntity();

        BufferedHttpEntity buf = new BufferedHttpEntity(ht);
        InputStream is = buf.getContent();
        BufferedReader r = new BufferedReader(new InputStreamReader(is));

        String yourJsonString = "";
        String line;
        while ((line = r.readLine()) != null) {
            System.out.println("Prosseing "+line);
            yourJsonString += (line + "\n");
        }
        System.out.println(yourJsonString.length());
        return yourJsonString;
    }
}
