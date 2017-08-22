package com.elorri.android.xyzreader.remote;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Elorri on 10/03/2016.
 */
public class Config {
    public static final URL BASE_URL;

    static {
        URL url = null;
        try {
            //url = new URL("https://dl.dropboxusercontent.com/u/231329/xyzreader_data/data.json"); //Old
            url = new URL("https://raw.githubusercontent.com/Protino/dump/master/Lego/data.json");
        } catch (MalformedURLException ignored) {
            // TODO: throw a real error
        }

        BASE_URL = url;
    }
}