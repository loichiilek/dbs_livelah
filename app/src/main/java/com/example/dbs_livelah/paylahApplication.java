package com.example.dbs_livelah;

import android.app.Application;

public class paylahApplication extends Application {
    private String UID = "3";

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }
}
