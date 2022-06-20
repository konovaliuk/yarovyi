package com.servletproject.entities;

import java.io.Serializable;
import java.util.Date;

public class Ticket implements Serializable {
    private long id;
    private String ticketStatus;
    private Date creationDate;
    private long manager_id;
    private long master_id;

    public Ticket(){

    }

    public Ticket(String ticketStatus, Date creationDate, long manager_id){
        this.ticketStatus=ticketStatus;
        this.creationDate=creationDate;
        this.manager_id=manager_id;
    }

    public long getId() {return id;}
    public void setId(long id) {this.id = id;}

    public String getTicketStatus() {return ticketStatus;}
    public void setTicketStatus(String ticketStatus) {this.ticketStatus = ticketStatus;}

    public Date getCreationDate() {return creationDate;}
    public void setCreationDate(Date creationDate) {this.creationDate = creationDate;}

    public long getManager_id() {return manager_id;}
    public void setManager_id(long manager_id) {this.manager_id = manager_id;}

    public long getMaster_id() {return master_id;}
    public void setMaster_id(long master_id) {this.master_id = master_id;}

    @Override
    public int hashCode(){
        int hash = 7;
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
        if (this.id != ((Ticket) obj).getId()){
            return false;
        }
        return true;
    }

}
