package com.daoproject.entities;

import java.io.Serializable;
import java.util.Date;

public class Request implements Serializable {
    private long id;
    private String requestState;
    private Date creationDate;
    private long user_id;
    private long manager_id;

    public Request(){

    }

    public Request(String requestState,Date creationDate,long user_id){
        this.requestState = requestState;
        this.creationDate = creationDate;
        this.user_id = user_id;
    }

    public long getId() {return id;}
    public void setId(long id) {this.id = id;}

    public String getRequestState() {return requestState;}
    public void setRequestState(String requestState) {this.requestState = requestState;}

    public Date getCreationDate() {return creationDate;}
    public void setCreationDate(Date creationDate) {this.creationDate = creationDate;}

    public long getUser_id() {return user_id;}
    public void setUser_id(long user_id) {this.user_id = user_id;}

    public long getManager_id() {return manager_id;}
    public void setManager_id(long manager_id) {this.manager_id = manager_id;}

    @Override
    public int hashCode(){
        int hash = 7;
        hash = 17 * hash + (this.requestState != null ? this.requestState.hashCode() : 0);
        hash = 17 * hash + (this.creationDate != null ? this.creationDate.hashCode() : 0);
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
        if (this.id != ((Request) obj).getId()){
            return false;
        }
        return true;
    }


}
