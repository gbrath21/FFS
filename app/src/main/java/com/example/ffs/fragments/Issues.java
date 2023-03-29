package com.example.ffs.fragments;

public class Issues {
    String issuename, location, soldierid;

    public Issues() {
    }

    public String getIssuename() {
        return issuename;
    }

    public void setIssuename(String issuename) {
        this.issuename = issuename;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getSoldierid() {
        return soldierid;
    }

    public void setSoldierid(String solderid) {
        this.soldierid = solderid;
    }

    public Issues(String issuename, String location, String solderid) {
        this.issuename = issuename;
        this.location = location;
        this.soldierid = solderid;
    }
}
