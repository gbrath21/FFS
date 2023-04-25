// Your package name can be different depending
// on your project name
package com.example.ffs;

public class Issue
{
    // Variable to store data corresponding
    // to issuename keyword in database
    private String issuename;

    // Variable to store data corresponding
    // to location keyword in database
    private String location;

    // Variable to store data corresponding
    // to soldierid keyword in database
    private String soldierid;
    private String imageurl;



    // Mandatory empty constructor
    // for use of FirebaseUI
    public Issue() {}

    public Issue(String issuename, String location, String soldierid, String imageurl) {
        this.issuename = issuename;
        this.location = location;
        this.soldierid = soldierid;
        this.imageurl = imageurl;
    }

    // Getter and setter method
    public String getIssuename()
    {
        return this.issuename;
    }
    public void setIssuename(String issuename)
    {
        this.issuename = issuename;
    }
    public String getLocation()
    {
        return this.location;
    }
    public void setLocation(String location)
    {
        this.location = location;
    }
    public String getSoldierid()
    {
        return this.soldierid;
    }
    public void setSoldierid(String soldierid)
    {
        this.soldierid = soldierid;
    }
    public String getImageurl() {return imageurl;}

    public void setImageurl(String imageurl) {this.imageurl = imageurl;}
}
