package com.servletproject.manager;

import java.util.ResourceBundle;


public class Config {
    private static Config instance;
    private ResourceBundle resource;
    private static final String BUNDLE_NAME = "config";


    public static Config getInstance(){
        if (instance == null) {
            instance = new Config();
            instance.resource = ResourceBundle.getBundle(BUNDLE_NAME);
        }
        return instance;
    }

    public String getProperty(String key) {return (String) resource.getObject(key);}
}
