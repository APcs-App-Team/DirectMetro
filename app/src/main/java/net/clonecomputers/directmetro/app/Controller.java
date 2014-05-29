package net.clonecomputers.directmetro.app;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by giorescigno on 5/18/14.
 */
public class Controller implements Controllable {

    private double userLong, userLat;
    private ArrayList<Line> LineList;

    public Controller(double userLong, double userLat, String StationJson){
        this.userLong = userLong;
        this.userLat = userLat;
        constructData(StationJson);
    }

    @Override
    public void constructData(String yourJsonString) {

        ArrayList<Line> temp = new ArrayList<Line>();
        ArrayList<Exit> S1exits = new ArrayList<Exit>();
        S1exits.add(new Exit(2, 12, 27));
        S1exits.add(new Exit(1, 12, 22));

        ArrayList<Exit> S2exits = new ArrayList<Exit>();
        S2exits.add(new Exit(0, 12, 40));
        S2exits.add(new Exit(2, 12, 46));

        ArrayList<Exit> S3exits = new ArrayList<Exit>();
        S2exits.add(new Exit(0, 12, 66));
        S2exits.add(new Exit(2, 12, 66));

        ArrayList<Station> Stations = new ArrayList<Station>();
        Stations.add(new Station(null, "1st Street", S1exits, 12, 25, 100, false));
        Stations.add(new Station(null, "2nd Street", S2exits, 12, 43, 100, false));
        Stations.add(new Station(null, "3nd Street", S3exits, 12, 43, 100, false));

        temp.add(new Line(Stations, "S", "NO IMAGE"));

        ///////////////////////////////////////////////////


        /*Gson gson = new Gson();
        Type stringStringMap = new TypeToken<Map<String, String>>(){}.getType();
        Map<String,String> map = gson.fromJson(yourJsonString, stringStringMap);

        System.out.println(map.toString());*/
        LineList = temp;
    }


    /*private Map<String, String> JSONParcerOfDoom(String JsonString){

    }*/

    @Override
    public Station[] getClosetsStations() {

        Station[] Nearest = new Station[3];

        for(Line Lines : LineList){
            for(Station stations : Lines.getStations()){

                double CurrentDistance = Math.sqrt(Math.pow(stations.getLat() - userLat, 2) + Math.pow(stations.getLong() - userLong, 2));

                if(Nearest[0] == null){
                    Nearest[0] = stations;

                }else if(CurrentDistance < Math.sqrt(Math.pow(Nearest[0].getLat() - userLat, 2) +
                        Math.pow(Nearest[0].getLong() - userLong, 2))){
                    Station temp = Nearest[0];
                    Nearest[0] = stations;
                    Nearest[2] = Nearest[1];
                    Nearest[1] = temp;

                }else if(CurrentDistance < Math.sqrt(Math.pow(Nearest[1].getLat() - userLat, 2) +
                        Math.pow(Nearest[1].getLong() - userLong, 2))){
                    Station temp = Nearest[1];
                    Nearest[1] = stations;
                    Nearest[2] = temp;

                }else if(CurrentDistance < Math.sqrt(Math.pow(Nearest[2].getLat() - userLat, 2) +
                        Math.pow(Nearest[2].getLong() - userLong, 2))){
                    Nearest[2] = stations;
                }
            }
        }
        return Nearest;
    }

    @Override
    /**
    * this mehtod reutrns the the Station that has the same name of which is
    * spsified if it finds it it will return the Line object
    *
    * @param    String
    * @return   Line
    * */
    public Line getDestinationLine(String StationName) {
        for(Line SearchLine : LineList){
            if(SearchLine.getStation(StationName) != null){
                return SearchLine;
            }
        }
        return null;
    }

    @Override
    public Station getDestinationStation(String StationName) {
        for(Line SearchLine : LineList){
            Station temp = SearchLine.getStation(StationName);
            if(temp != null){
                return temp;
            }
        }
        return null;
    }

    @Override
    public ArrayList<Line> getCorrespondingStations(String StationName) {
        return null;
    }

    public ArrayList<Line> getLines(){
        return LineList;
    }
}
