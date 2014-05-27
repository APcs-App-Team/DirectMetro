package net.clonecomputers.directmetro.app;

/**
 * Created by giorescigno on 5/16/14.
 */
public class Exit {

    private PlatfromLoc ExitLocation;
    private double Latitude, longitude;


    public Exit(int exitLocation, double lat, double lon) {
        ExitLocation = new PlatfromLoc(exitLocation);
        Latitude = lat;
        longitude = lon;
    }
    /**
    * this method returns the Plaform Location
    * of an exit
    *
    * @return PlatfromLoc
    * */
    public PlatfromLoc getExitLocation(){
        return ExitLocation;
    }
    /**
    * this method gets the Latitude of the Exit
    *
    * @return double
    * */
    public double getLatitude(){
        return Latitude;
    }
    /**
    * this method gets the Longitude of the Exit
    *
    * @return double
    * */
    public double getLongitude(){
        return longitude;
    }
}
