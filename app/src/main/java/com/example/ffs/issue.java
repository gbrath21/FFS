// Your package name can be different depending
// on your project name
package com.example.ffs;

public class issue
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

    // Mandatory empty constructor
    // for use of FirebaseUI
    public issue() {}

    // Getter and setter method
    public String getIssuename()
    {
        return issuename;
    }
    public void setFirstname(String firstname)
    {
        this.issuename = issuename;
    }
    public String getLocation()
    {
        return location;
    }
    public void setLastname(String lastname)
    {
        this.location = location;
    }
    public String getSoldierid()
    {
        return soldierid;
    }
    public void setAge(String age)
    {
        this.soldierid = soldierid;
    }
}
