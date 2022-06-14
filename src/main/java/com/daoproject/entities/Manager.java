package com.daoproject.entities;

import java.io.Serializable;

public class Manager implements Serializable {
    private long id;
    private String managerName;
    private String managerPhone;
    private String managerEmail;
    private String managerLogin;
    private String managerPassword;

    public Manager(){

    }

    public Manager(String managerName, String managerPhone, String managerEmail, String managerLogin, String managerPassword){
        this.managerName = managerName;
        this.managerPhone = managerPhone;
        this.managerEmail = managerEmail;
        this.managerLogin = managerLogin;
        this.managerPassword = managerPassword;
    }

    public long getId() {return id;}
    public void setId(long id) {this.id = id;}


    public String getManagerName() {return managerName;}
    public void setManagerName(String managerName) {this.managerName = managerName;}

    public String getManagerPhone() {return managerPhone;}
    public void setManagerPhone(String managerPhone) {this.managerPhone = managerPhone;}

    public String getManagerEmail() {return managerEmail;}
    public void setManagerEmail(String managerEmail) {this.managerEmail = managerEmail;}

    public String getManagerLogin() {return managerLogin;}
    public void setMasterLogin(String managerLogin) {this.managerLogin = managerLogin;}

    public String getManagerPassword() {return managerPassword;}
    public void setManagerPassword(String managerPassword) {this.managerPassword = managerPassword;}

    @Override
    public int hashCode(){
        int hash = 7;
        hash = 17 * hash + (this.managerName != null ? this.managerName.hashCode() : 0);
        hash = 17 * hash + (this.managerEmail != null ? this.managerEmail.hashCode() : 0);
        hash = 17 * hash + (this.managerPhone != null ? this.managerPhone.hashCode() : 0);
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
        if (this.id != ((Manager) obj).getId()){
            return false;
        }
        return true;
    }


}
