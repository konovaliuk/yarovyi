package com.servletproject.entities;

import java.io.Serializable;

public class Master implements Serializable {
    private long id;
    private String masterName;
    private String masterPhone;
    private String masterEmail;
    private String masterLogin;
    private String masterPassword;

    public Master(){

    }

    public Master(String masterName, String masterPhone, String masterEmail, String masterLogin, String masterPassword){
        this.masterName = masterName;
        this.masterPhone = masterPhone;
        this.masterEmail = masterEmail;
        this.masterLogin = masterLogin;
        this.masterPassword = masterPassword;
    }

    public long getId() {return id;}
    public void setId(long id) {this.id = id;}

    public String getMasterName() {return masterName;}
    public void setMasterName(String masterName) {this.masterName = masterName;}

    public String getMasterPhone() {return masterPhone;}
    public void setMasterPhone(String masterPhone) {this.masterPhone = masterPhone;}

    public String getMasterEmail() {return masterEmail;}
    public void setMasterEmail(String masterEmail) {this.masterEmail = masterEmail;}

    public String getMasterLogin() {return masterLogin;}
    public void setMasterLogin(String masterLogin) {this.masterLogin = masterLogin;}

    public String getMasterPassword() {return masterPassword;}
    public void setMasterPassword(String masterPassword) {this.masterPassword = masterPassword;}


    @Override
    public int hashCode(){
        int hash = 7;
        hash = 17 * hash + (this.masterName != null ? this.masterName.hashCode() : 0);
        hash = 17 * hash + (this.masterEmail != null ? this.masterEmail.hashCode() : 0);
        hash = 17 * hash + (this.masterPhone != null ? this.masterPhone.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj){
        if (obj == null){
            return false;
        }
        if (getClass() != obj.getClass()){
            return false;
        }
        if (this.id != ((Master) obj).getId()){
            return false;
        }
        return true;
    }

}
