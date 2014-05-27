package net.clonecomputers.directmetro.app;

import net.clonecomputers.directmetro.app.Exit;
import net.clonecomputers.directmetro.app.Transfer;

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
    private ArrayList<Exit> Exits;
    private ArrayList<Transfer> Transfers;
    private double Xcor, Ycor;
    private int SwipeNum;  //holds the number of metrocard swipes at a given station



    public Station(ArrayList<Transfer> transfers, String name, ArrayList<Exit> exits,
                   double xcor, double ycor, int swipeNum){

        Transfers = transfers;
        Name = name;
        Exits = exits;
        Xcor = xcor;
        Ycor = ycor;
        SwipeNum = swipeNum;
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
        if(Transfers.size() > 0) return true;
        return false;
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
    * this methoed retuns the Logitued of the Station
    *
    * @return double
    * */
    public double getLong(){
        return Xcor;
    }

    /**
    * this methoed retuns the Latitued of the Station
    *
    * @return double
    * */
    public double getLat(){
        return Ycor;
    }
}
