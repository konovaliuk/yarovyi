package com.servletproject.services;

import com.servletproject.dao.DaoFactory;
import com.servletproject.entities.Request;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

public class RequestService {
    public Request find(int id) {
        Request request;
        try {
            request = DaoFactory.createRequestDao().findById(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return request;
    }


    public List<Request> getAllRequests() {
        try {
            return DaoFactory.createRequestDao().findAll();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public long createRequest(Request request) {
        try {
            DaoFactory.createRequestDao().post(request);
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
        return request.getId();
    }

    public void assignRequest(long id, long manager){
        try{
            DaoFactory.createRequestDao().assign(id, manager);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
