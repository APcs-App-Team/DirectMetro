package net.clonecomputers.directmetro.app;

import java.util.ArrayList;

/**
 * Created by Gio Rescigno on 5/13/14.
 * this class holds all of the data for each line
 * inclueding all of the stations on the line
 */
public class Line {

    private ArrayList<Station> Stations;
    private String Line;//the Line Char Ex: F, 6 or L
    private String LineImage;//holds the image of the line

    public Line(ArrayList<Station> stations, String line, String lineImage){

        Stations = stations;
        Line = line;
        LineImage = lineImage;
    }
   /**
   * this method returns all of the stations you can transfer
   * to a certain line. if there are none it will return null
   *
   * @param        LineName
   * @return       ArrayList<Station>
   * */
    public ArrayList<Station> getTransfersStations(String LineName){

        ArrayList<Station> temp = new ArrayList<Station>();

        for(int i = 0; i < Stations.size(); i++){
            if(Stations.get(i).hasTransfers()){
                for(Transfer tempTrans : Stations.get(i).getTransfers() ){
                    if(tempTrans.getLineName().equals(LineName)) temp.add(Stations.get(i));
                }
            }
        }
        if(temp.size() > 0) return temp;
        else return null;
    }
    /**
     * this method returns all of the stations you can transfer
     * to a certain line. if there are none it will return null
     *
     * @return       ArrayList<Station>
     * */
    public ArrayList<Station> getTransferStations(){

        ArrayList<Station> temp = new ArrayList<Station>();

        for(int i = 0; i < Stations.size(); i++){
            if(Stations.get(i).hasTransfers()){
                temp.add(Stations.get(i));
            }
        }
        return temp;
    }

    /**
    * this method finds the frist station with the Name spiced
    * if there is no such station the method returns null.
    *
    * @param StationName    a string that holds the Station name
    * @return Station       returns the station that you are looking for
    * */
    public Station getStation(String StationName){

        for(int i = 0; i < Stations.size(); i++){
            //noinspection EqualsBetweenInconvertibleTypes
            if(Stations.get(i).getName().equals(StationName)){
                return Stations.get(i);
            }
        }
        return null;
    }

    /**
     * this methoed checks to see if a station object exists
     * if it dose i returns true if it dose not it returns
     * false
     *
     * @param StationName
     * @return boolean
     * */
    public boolean hasStation(String StationName){
        for(int i = 0; i < Stations.size(); i++){
            if(Stations.get(i).getName().equals(StationName)){
                return true;
            }
        }
        return false;
    }

    /**
     * this methoed gets adds an exit object to a station spesified
     * by name. if it finds the station and can add the exit it will
     * return true if it can not find the station it will return false
     *
     * @param stationName
     * @param exitobj
     *
     * @return boolean
     * */
    public boolean addExit(String stationName, Exit exitobj){
        for(int i =0; i < Stations.size(); i++){
            if(Stations.get(i).getName().equals(stationName)){
                Stations.get(i).addExit(exitobj);
                return true;
            }
        }
        return false;
    }
    /**
     * this method takes in one Station parameter and
     * adds it to the array list of stations. it returns
     * the index at which it was added
     *
     * @param newS
     *@return index
     * */
    public int addStation(Station newS){
        Stations.add((Station)newS);
        return Stations.size()-1;
    }

    /**
    * this method returns the Stations along the route
    * simply give all of them.
    *
    * @return       ArrayList<Station>
    * */
    public ArrayList<Station> getStations(){
        return Stations;
    }

    /*
    * this method returns the Line as a string
    *
    * @return      String
    * */
    public String getLine(){
        return Line;
    }

    /**
    * this method returns the Line image URL
    *
    * @return   String
    * */
    public String getLineImage(){
        return LineImage;
    }

}
