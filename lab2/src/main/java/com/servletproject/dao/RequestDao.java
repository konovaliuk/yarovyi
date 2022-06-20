package com.servletproject.dao;

import com.servletproject.connection.ConnectionPool;
import com.servletproject.dao.interfaces.IRequestDao;
import com.servletproject.entities.Request;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RequestDao implements IRequestDao {
    protected final String SQL_SELECT = "SELECT id, State, Creation_date, user_id, manager_id FROM " + getTableName();
    protected static final int COLUMN_ID = 1;
    protected static final int COLUMN_STATE = 2;
    protected static final int COLUMN_DATE = 3;
    protected static final int COLUMN_USER_ID = 4;
    protected static final int COLUMN_MANAGER_ID = 5;


    public String getTableName() {
        return "requests";
    }

    public List<Request> findAll() throws Exception {
        return findByDynamicSelect(SQL_SELECT, null);
    }

    public Request findById(long id){
        String query = SQL_SELECT + " WHERE id=" + id;
        List<Request> result = new ArrayList<>();
        try {
            result = findByDynamicSelect(query, null);
        }
        catch (Exception ex){
            System.out.println("Error executing " + query);
        }
        return result.get(0);
    }

    public Request post(Request request){
        String query = "CALL add_request('"+
                request.getRequestState()+"','"+
                request.getCreationDate()+"','"+
                request.getUser_id()+"')";
        List<Request> result = new ArrayList<>();
        try{
            result = findByDynamicSelect(query, null);
        }
        catch (Exception ex){
            System.out.println("Error executing " + query);
        }
        return result.get(0);
    }

    public Request update(long id, Request request){
        String query = "CALL update_request('"+
                id+"','"+
                request.getRequestState()+"','"+
                request.getCreationDate()+"','"+
                request.getUser_id()+"',)";
        List<Request> result = new ArrayList<>();
        try{
            result = findByDynamicSelect(query, null);
        }
        catch (Exception ex){
            System.out.println("Error executing " + query);
        }
        return result.get(0);
    }

    public Request assign(long id, long manager_id){
        String query = "CALL assign_request_manager('"+
                id+"','"+manager_id+"',)";
        List<Request> result = new ArrayList<>();
        try{
            result = findByDynamicSelect(query, null);
        }
        catch (Exception ex){
            System.out.println("Error executing " + query);
        }
        return result.get(0);
    }

    public void delete(long id){
        String query = "CALL delete_request('"+id+"',)";
        List<Request> result = new ArrayList<>();
        try{
            result = findByDynamicSelect(query, null);
        }
        catch (Exception ex){
            System.out.println("Error executing " + query);
        }
    }

    protected Request fetchSingleResult(ResultSet rs) throws SQLException {
        if (rs.next()) {
            Request dto = new Request();
            populateDto(dto,rs);
            return dto;
        } else {
            return null;
        }
    }

    protected List fetchMultipleResults(ResultSet rs) throws SQLException {
        List resultList = new ArrayList();
        while (rs.next()) {
            Request dto = new Request();
            populateDto(dto,rs);
            resultList.add(dto);
        }
        return resultList;
    }

    protected void populateDto(Request dto, ResultSet rs) throws SQLException {
        dto.setId(rs.getLong(COLUMN_ID));
        dto.setRequestState(rs.getString(COLUMN_STATE));
        dto.setCreationDate(rs.getDate(COLUMN_DATE));
        dto.setUser_id(rs.getLong(COLUMN_USER_ID));
        dto.setManager_id(rs.getLong(COLUMN_MANAGER_ID));
    }

    private List<Request> findByDynamicSelect(String sql, Object[] sqlParameters) throws Exception {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            final String SQL = sql;
            System.out.println("Executing " + SQL);
            Connection conn = ConnectionPool.getConnection();
            stmt = conn.prepareStatement(SQL);

            for (int i = 0; sqlParameters != null && i < sqlParameters.length; i++){
                stmt.setObject(i+1, sqlParameters[i]);
            }

            rs = stmt.executeQuery();

            return fetchMultipleResults(rs);
        }
        catch (Exception ex) {
            throw new Exception("Exception: " + ex.getMessage(), ex);
        }
    }

}
