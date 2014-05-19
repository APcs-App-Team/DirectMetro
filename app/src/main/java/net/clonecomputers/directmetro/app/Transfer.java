package net.clonecomputers.directmetro.app;

import net.clonecomputers.directmetro.app.PlatfromLoc;

import java.util.ArrayList;

/**
 * Created by giorescigno on 5/13/14.
 */
public class Transfer {

    private Line TransfureTo;
    private String LineName;
    private PlatfromLoc ExitLocation;
    private boolean isTransSet = false;

    public Transfer(String lineName, PlatfromLoc exitLocation){

        LineName = lineName;
        ExitLocation = exitLocation;
    }
    /*
    * this method takes the Whole List of Lines and finds the right
    * line object to so to load it into the Transfer. this is not done
    * in the constructor to stop a recursive loop.
    *
    * @param     ArrayList<Line> lines
    * */
    public void setTransfureLine(ArrayList<Line> lines){

        for(Line line : lines){
            if(line.getLine().equals(LineName)) {
                TransfureTo = (Line) line;
                isTransSet = true;
            }
        }
    }
    /*
    * this method returns the line that the transfer connects to
    * if the Line has not been set it will return null
    *
    * @return       Line
    * */
    public Line getTransfuseLine(){
        if(isTransSet) return TransfureTo;
        return null;
    }
    /*
    * this method returns the Exit Location of the
    * the Transfur
    *
    * @return       PlatfromLoc
    * */
    public PlatfromLoc getExitLocation(){
        return ExitLocation;
    }
    /*
    * this method gets the line name which is a
    * String. this method DOSE NOT RETURN THE LINE OBJ
    *
    * @return      String LineName
    * */
    public String getLineName(){
        return LineName;
    }


}
