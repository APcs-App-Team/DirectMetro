package net.clonecomputers.directmetro.app;

import java.util.ArrayList;

/**
 * Created by giorescigno on 5/18/14.
 */
public class Controller implements Controllable {

    private double userLong, userLat;
    private ArrayList<Line> LineList;

    public Controller(double userLong, double userLat){
        this.userLong = userLong;
        this.userLat = userLat;
    }

    @Override
    public void constructData() {
        //construct Transfers
        Transfer tran1 = new Transfer("1", new PlatfromLoc(0));
        ArrayList<Transfer> tranI = new ArrayList<Transfer>();
        tranI.add(tran1);

        Transfer tran2 = new Transfer("2", new PlatfromLoc(0));
        ArrayList<Transfer> tranII = new ArrayList<Transfer>();
        tranII.add(tran2);

        //Construct Exits
        ArrayList<Exit> S1L1E = new ArrayList<Exit>();
        S1L1E.add(new Exit(new PlatfromLoc(1), 0, 0.33));
        S1L1E.add(new Exit(new PlatfromLoc(2), 0.33, 0));

        //construct stations

        Station S1L1 = new Station(tranI, "1 Street", S1L1E, 0, 0, 10);
    }

    @Override
    public ArrayList<Line> getClosetsLines() {
        return null;
    }

    @Override
    public ArrayList<Station> getClosetsStations() {
        return null;
    }

    @Override
    public Line getDestinationLine(String StationName) {
        return null;
    }

    @Override
    public Station getDestinationStation(String StationName) {
        return null;
    }

    @Override
    public ArrayList<Line> getCorrespondingStations(String StationName) {
        return null;
    }
}
