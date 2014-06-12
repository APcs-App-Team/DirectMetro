package net.clonecomputers.directmetro.app;

import com.google.gson.Gson;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.ArrayList;

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

        System.out.println(yourJsonString);
        Gson gson = new Gson();//crates a new json object
        JsonStationObj[] entrys = gson.fromJson(yourJsonString, JsonStationObj[].class);

        ArrayList<Line> LineTemp = new ArrayList<Line>();
        for(int i = 0; i < entrys.length; i++){
            String[] routes = {
                    entrys[i].route1, entrys[i].route2, entrys[i].route3, entrys[i].route4,
                    entrys[i].route4, entrys[i].route6};

            for(int z = 0; z < routes.length && routes[z] != null; z++) {
                int lineIndex;

                if ((lineIndex = hasLine(LineTemp, routes[z])) != -1) {

                    //creates a temporagry exit object
                    Exit tempNewExit = new Exit(0, entrys[i].entrance_latitude,
                            entrys[i].entrance_longitude);

                    //this if statment checks to see if an exit was seucsefuly added if not it will add a new station
                    if (!LineTemp.get(lineIndex).addExit(entrys[i].station_name, tempNewExit)) {
                        //adds new station
                        LineTemp.get(lineIndex).addStation(new
                                        Station(entrys[i].station_name, entrys[i].station_latitude,
                                        entrys[i].station_longitude, entrys[i].free_crossover,
                                        getStationDistance(entrys[i].station_latitude,
                                                entrys[i].station_longitude)
                                , routes[z])
                        );
                        LineTemp.get(lineIndex).addExit(entrys[i].station_name, tempNewExit);
                        //creates a new trasfurs if there are some
                        if(routes.length > 1){
                            LineTemp.get(lineIndex).getStation(entrys[i].station_name).addTransfers(
                                    routes, routes[z]);
                        }
                    }

                } else {

                    ArrayList<Station> tempStationArrayList = new ArrayList<Station>();

                    tempStationArrayList.add(new Station(
                                    entrys[i].station_name, entrys[i].station_latitude,
                                    entrys[i].station_longitude, entrys[i].free_crossover,
                                    getStationDistance(entrys[i].station_latitude,
                                            entrys[i].station_longitude), routes[z]
                            )
                    );

                    Exit tempExit = new Exit(0, entrys[i].entrance_latitude, entrys[i].entrance_longitude);

                    LineTemp.add(new Line(tempStationArrayList, routes[z], ""));
                    LineTemp.get(LineTemp.size() - 1).addExit(entrys[i].station_name, tempExit);

                }
            }
        }

        /*ArrayList<Line> temp = new ArrayList<Line>();
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

        temp.add(new Line(Stations, "S", "NO IMAGE"));*/
        LineList = LineTemp;
    }

    @Override
    public Station[] getClosetsStations(String Des) {

        ArrayList<Station> closetStation = new ArrayList<Station>();

        for(int i = 0; i < LineList.size(); i++){

            Station[] stationsTemp = LineList.get(i).getStations().toArray(
                    new Station[LineList.get(i).getStations().size()]
            );
            Arrays.sort(stationsTemp);
            closetStation.add(stationsTemp[0]);

        }
        Station[] closestStationsArray = closetStation.toArray(
                new Station[closetStation.size()]
        );
        Arrays.sort(closestStationsArray);

        //return Arrays.copyOfRange(closestStationsArray, 0, 3);
        return getWorkingCloset(closestStationsArray, Des);
    }
    /**
     * this method
     * */
    private Station[] getWorkingCloset(Station[] sortedArray, String Des){

        ArrayList<Station> working = new ArrayList<Station>();
        //Station[] working = new Station[3];
        int internalWorking = 0;

        for(int i = 0; i < sortedArray.length && internalWorking < 3; i++){

            if(getRoute(sortedArray[i], Des) != null) {
                working.add(sortedArray[i]);
                internalWorking++;
            }
        }

        return working.toArray(new Station[working.size()]);
    }

    /**
     * this method takes in the Starting Station and the ending station
     * name and then returns the route ArrayList
     * EX:
     * {"Start: 1st Street", "Front", "Line: 1", "Transfer: 2st",
     *              "Street Line: 2", "Middle", "End: 4th Street", "Line: 2", "Back"}
     *
     * @param Start
     * @param end
     *
     * @return route
     * */
    public ArrayList<String> getRoute(Station Start, String end){

        ArrayList<String> actions = new ArrayList<String>();
        ArrayList<Line> candidates = new ArrayList<Line>();
        Line stationLines = getLine(Start.getLine());
        boolean atDest = false;

        actions.add(Start.getName());
        actions.add(Start.getLine());
        actions.add("Middle");

        if(stationLines.hasStation(end)){
            atDest = true;
            actions.add(stationLines.getStation(end).getName());
            actions.add(stationLines.getLine());
            actions.add("Middle");
        }
        if(atDest)
            return actions;
        else{
            return null;
        }
    }

     /**
     * this method finds if the line exists
     *
     * @param testLineArray<Line>
     * @param line
     *
     * @return boolean
     * */
    private int hasLine(ArrayList<Line> testLineArray, String line){

        for(int i = 0; i < testLineArray.size(); i++){
            if(testLineArray.get(i).getLine().equals(line)) return i;
        }
        return -1;
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

    private double getStationDistance(double StationLat, double StationLong){
        double xResalt = Math.pow(Math.abs(StationLong - userLong), 2);
        double yResalt = Math.pow(Math.abs(StationLat - userLat), 2);
        return Math.sqrt(xResalt + yResalt);
    }
    /**
     * this method gets a line baced off of its Name
     * and then returns a Line object
     *
     * @param name
     * @return Line
     * */
    public Line getLine(String name){
        for(int i = 0; i < LineList.size(); i++){
            if(LineList.get(i).getLine().equals(name)){
                return LineList.get(i);
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
    private class JsonStationObj{
        boolean ada;
        String corner;
        String division;
        String east_west_street;
        double entrance_latitude;
        entranceLocation entrance_location;
        double entrance_longitude;
        String entrance_type;
        String entry;
        boolean free_crossover;
        String line;
        String north_south_street;
        String route1;
        String route2;
        String route3;
        String route4;
        String route5;
        String route6;
        String staffing;
        double station_latitude;
        Stationloc station_location;
        double station_longitude;
        String station_name;
        String vending;

    }
    private class entranceLocation{
        double latitude;
        double longitude;
        boolean needs_recoding;
    }
    private class Stationloc{
        double latitude;
        double longitude;
        boolean needs_recoding;
    }
}

