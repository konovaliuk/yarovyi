package com.daoproject.entities;

import java.io.Serializable;

public class User implements Serializable {
    private long id;
    private String userName;
    private String userEmail;
    private String userLogin;
    private String userPassword;


    public User(){

    }

    public User(String userName, String userEmail,String userLogin, String userPassword){
        this.userName = userName;
        this.userEmail = userEmail;
        this.userLogin = userLogin;
        this.userPassword = userPassword;
    }


    public long getId() {return id;}
    public void setId(long id) {this.id = id;}

    public String getUserName() {return userName;}
    public void setUserName(String userName) {this.userName = userName;}

    public String getUserEmail() {return userEmail;}
    public void setUserEmail(String userEmail) {this.userEmail = userEmail;}

    public String getUserLogin() {return userLogin;}
    public void setUserLogin(String userLogin) {this.userLogin = userLogin;}

    public String getUserPassword() {return userPassword;}
    public void setUserPassword(String userPassword) {this.userPassword = userPassword;}

    @Override
    public int hashCode(){
        int hash = 7;
        hash = 17 * hash + (this.userName != null ? this.userName.hashCode() : 0);
        hash = 17 * hash + (this.userEmail != null ? this.userEmail.hashCode() : 0);
        hash = 17 * hash + (this.userLogin != null ? this.userLogin.hashCode() : 0);
        hash = 17 * hash + (this.userPassword != null ? this.userPassword.hashCode() : 0);
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
        if (this.id != ((User) obj).getId()){
            return false;
        }
        return true;
    }


}
