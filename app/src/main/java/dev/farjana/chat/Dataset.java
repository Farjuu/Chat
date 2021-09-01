package dev.farjana.chat;

/**
 * Created by Farjana on 1/13/2018.
 */

public class Dataset {
   private int imgID;
   private String NameOfReceipeint;
   private String lastText;

    public int getImgID() {
        return imgID;
    }

    public void setImgID(int imgID) {
        this.imgID = imgID;
    }

    public String getNameOfReceipeint() {
        return NameOfReceipeint;
    }

    public void setNameOfReceipeint(String nameOfReceipeint) {
        NameOfReceipeint = nameOfReceipeint;
    }

    public String getLastText() {
        return lastText;
    }

    public void setLastText(String lastText) {
        this.lastText = lastText;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    private String time;

    public Dataset(){

    }

    public Dataset(int ID, String Name, String LastText, String Time){
        imgID = ID;
        NameOfReceipeint = Name;
        lastText = LastText;
        time = Time;
    }



}
