package com.servletproject.manager;

import java.util.ResourceBundle;

public class Message {
    private static Message instance;
    private ResourceBundle resource;
    private static final String BUNDLE_NAME = "com.servletproject.manager.messages";
    private static final String SERVLET_EXCEPTION = "SERVLET_EXCEPTION";
    private static final String IO_EXCEPTION = "IO_EXCEPTION";


    public static Message getInstance(){
        if (instance == null) {
            instance = new Message();
            instance.resource = ResourceBundle.getBundle(BUNDLE_NAME);
        }
        return instance;
    }

    public String getProperty(String key) {return (String) resource.getObject(key);}
}
