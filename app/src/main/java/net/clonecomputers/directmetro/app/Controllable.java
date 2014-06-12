package net.clonecomputers.directmetro.app;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by giorescigno on 5/17/14.
 */
public interface Controllable {

    public ArrayList<Line> getLines();
    public void constructData(String yourJsonString);
    public Station[] getClosetsStations(String Des);
    public Line getDestinationLine(String StationName);
    public Station getDestinationStation(String StationName);
    public ArrayList<Line> getCorrespondingStations(String StationName);
    public Line getLine(String name);
    public ArrayList<String> getRoute(Station Start, String end) throws NullPointerException;

}
