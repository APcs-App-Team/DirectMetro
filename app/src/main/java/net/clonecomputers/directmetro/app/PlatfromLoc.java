package net.clonecomputers.directmetro.app;

/**
 * Created by giorescigno on 5/14/14.
 *
 * this class holds the location of were somthing is
 * on a the form
 */
public class PlatfromLoc{

    private int PartOfPalt;//holds the direction int

    /*
    * the construct take in an int which indicates
    * the side end of the plat form that the exit
    * is on. the direction is form the vantage point
    * of the downtown direction.
    *
    * 0 - meaning from
    * 1 - meaning middle
    * 2 - meaning end
    *
    * @peram        int partOfPlat
    * */
    public PlatfromLoc(int partOfPlat){

        PartOfPalt = partOfPlat;
    }
    /*
    * this method take in one parameter which is the
    * direction the users wishes to go. it is set to
    * true if you want to go downtown and set to
    * false if you want to go uptown.
    *
    * @param    boolean Direction
    * */
    public String PlatFormEnd(boolean Direction){
        //when Direction is true it means it is going downtown
        if(Direction) {
            switch (PartOfPalt) {
                case 0:
                    return "Front";
                case 2:
                    return "Back";
            }
        }else if(!Direction){
            switch (PartOfPalt){
                case 0:
                    return "Back";
                case 2:
                    return "Front";
            }
        }
        return "Middle";
    }
}
