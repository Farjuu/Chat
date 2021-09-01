package dev.farjana.chat;

/**
 * Created by Farjana on 1/14/2018.
 */

class Contact {
    private int imageId;
    private String nameTxt;
    private String contactNumber;

    Contact(){

    }


    Contact( int imageId, String nameTxt, String contactNumber) {

        this.imageId = imageId;
        this.nameTxt=nameTxt;
        this.contactNumber= contactNumber;

    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getNameTxt() {
        return nameTxt;
    }

    public void setNameTxt(String nameTxt) {
        this.nameTxt = nameTxt;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }
}
