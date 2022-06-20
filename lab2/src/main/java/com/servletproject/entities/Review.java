package com.servletproject.entities;

import java.io.Serializable;

public class Review implements Serializable {
    private long id;
    private int score;
    private String text;
    private long request_id;

    public Review(){

    }

    public Review(int score, String text, long request_id){
        this.score = score;
        this.text = text;
        this.request_id = request_id;
    }

    public long getId() {return id;}
    public void setId(long id) {this.id = id;}

    public int getScore() {return score;}
    public void setScore(int score) {this.score = score;}

    public String getText() {return text;}
    public void setText(String text) {this.text = text;}

    public long getRequest_id() {return request_id;}
    public void setRequest_id(long request_id) {this.request_id = request_id;}

    @Override
    public int hashCode(){
        int hash = 7;
        hash = 17 * hash + (this.text != null ? this.text.hashCode() : 0);
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
        if (this.id != ((Review) obj).getId()){
            return false;
        }
        return true;
    }


}
