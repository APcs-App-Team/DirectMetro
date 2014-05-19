package net.clonecomputers.directmetro.app;

import java.util.ArrayList;

/**
 * Created by giorescigno on 5/17/14.
 */
public interface Controllable {

    public void constructData();
    public ArrayList<Line> getClosetsLines();
    public ArrayList<Station> getClosetsStations();
    public Line getDestinationLine(String StationName);
    public Station getDestinationStation(String StationName);
    public ArrayList<Line> getCorrespondingStations(String StationName);

}
