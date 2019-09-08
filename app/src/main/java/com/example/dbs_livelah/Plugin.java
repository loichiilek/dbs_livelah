package com.example.dbs_livelah;

public class Plugin {
    private final int pluginID;
    private final String name;
    private final String imageURL;
    private final String description;
    private final String pluginURL;
    private final int permissions;


    public int getPluginID() {
        return pluginID;
    }

    public String getName() {
        return name;
    }

    public String getImageURL() {
        return imageURL;
    }

    public String getDescription() {
        return description;
    }

    public String getPluginURL() {
        return pluginURL;
    }

    public int getPermissions() {
        return permissions;
    }

    public Plugin(int pluginID, String name, String imageURL, String description, int permissions, String pluginURL) {
        this.pluginID = pluginID;
        this.name = name;
        this.imageURL = imageURL;
        this.description = description;
        this.pluginURL = pluginURL;
        this.permissions = permissions;
    }
}
