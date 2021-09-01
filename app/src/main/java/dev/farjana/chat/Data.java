package dev.farjana.chat;

/**
 * Created by Farjana on 1/14/2018.
 */
public class Data {
    public int imageId;
    public String nameTxt,lastTxt,time;

    Data( int imageId, String nameTxt, String lastTxt, String time) {

        this.imageId = imageId;
        this.nameTxt=nameTxt;
        this.lastTxt = lastTxt;
        this.time = time;
    }
}
