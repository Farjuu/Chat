package dev.farjana.chat;

/**
 * Created by Farjana on 1/14/2018.
 */

class BaseMessage {
    public String time;
    public String textbody;
    public boolean ismine;

    BaseMessage(  String time, String textbody,boolean ismine) {

        this.time = time;
        this.textbody = textbody;
        this.ismine = ismine;

    }

}
