package net.clonecomputers.directmetro.app;

import net.clonecomputers.directmetro.app.Exit;
import net.clonecomputers.directmetro.app.Transfer;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

import java.util.ArrayList;

/**
 * Created by giorescigno on 5/13/14.
 *
 * this holds all the data need for a station included
 * TrainLines that stop at it.
 */
public class Station {

    private String Name;//holds the name of the Station
    private double Distance;
    private ArrayList<Exit> Exits = new ArrayList<Exit>();
    private ArrayList<Transfer> Transfers = new ArrayList<Transfer>();
    private double Xcor, Ycor;
    private int SwipeNum;  //holds the number of metrocard swipes at a given station
    private ArrayList<String> exitKeys;
    private boolean hasCrossOver;



    public Station(ArrayList<Transfer> transfers, String name, ArrayList<Exit> exits,
                   double xcor, double ycor, int swipeNum, boolean cross){

        Transfers = transfers;
        Name = name;
        Exits = exits;
        Xcor = xcor;
        Ycor = ycor;
        SwipeNum = swipeNum;
        exitKeys = HelperExitKeys();
        hasCrossOver = cross;
    }

    public Station(String name, double xcor, double ycor, boolean cross){
        Name = name;
        Xcor = xcor;
        Ycor = ycor;
        hasCrossOver = cross;
    }

    /**
    * this method is a helper method that gets the key map
    * and returns the keys
    *
    * @return  ArrayList<String>
    * @todo finnish this method
    * */
    private ArrayList<String> HelperExitKeys(){
        return null;
    }

    /**
    * returns the name of the station in question
    *
    * @return String
    * */
    public String getName(){
        return Name;
    }
    /**
    * this method checks to see if there are any trasfrus
    *
    * @return boolean           returns true if there are
    * returns false if there are not
    * */
    public boolean hasTransfers(){
        return Transfers.size() > 0;
    }

    /**
    * this method returns the all of the transfers in a station
    *
    * @return     ArrayList<Transfer>
    * */
    public ArrayList<Transfer> getTransfers(){
        return Transfers;
    }

    /**
    * this method returns all the exits in the Station
    *
    * @return ArrayList<Exit>
    * */
    public ArrayList<Exit> getExits(){
        return Exits;
    }

    /**
     * this method gets the keys of the Station both
     * for the larger data file which contates all of the
     * Swipe data
     *
     * @return ArrayList<String>
     * */
    public ArrayList<String> getStationKey(){
        return exitKeys;
    }

    /**
    * this methoed retuns the Logitued of the Station
    *
    * @return double
    * */
    public double getLong(){
        return Xcor;
    }

    /**
    * this method returns the Latitued of the Station
    *
    * @return double
    * */
    public double getLat(){
        return Ycor;
    }

    /**
     * this methoed returns weather or not a station has a cross over
     *
     * @return boolean
     * */
    public boolean isCrossOver(){
        return hasCrossOver;
    }
    /**
     * this methoed adds an exit to the Station
     *
     * @param exit
     * */
    public void addExit(Exit exit){
        Exits.add(exit);
    }
}
