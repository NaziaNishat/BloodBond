package com.example.nazia_000.account.classPack;

import android.location.Geocoder;
import android.location.Location;
import com.google.firebase.database.DatabaseReference;

import java.io.IOException;

public class LocationClass {

    private Location address;
    private Geocoder geocoder;

    public LocationClass(){

    }

    public LocationClass(String address) throws IOException {
        this.address = (Location) geocoder.getFromLocationName(address,1);
    }

    public Location getaddress() {
        return address;
    }
}
