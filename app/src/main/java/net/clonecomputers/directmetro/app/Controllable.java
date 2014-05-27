package net.clonecomputers.directmetro.app;

import java.util.ArrayList;

/**
 * Created by giorescigno on 5/17/14.
 */
public interface Controllable {

    public ArrayList<Line> getLines();
    public void constructData();
    public Station[] getClosetsStations();
    public Line getDestinationLine(String StationName);
    public Station getDestinationStation(String StationName);
    public ArrayList<Line> getCorrespondingStations(String StationName);

}
