package com.servletproject.dao;

import com.servletproject.connection.ConnectionPool;
import com.servletproject.dao.interfaces.IMasterDao;
import com.servletproject.entities.Master;
import com.servletproject.entities.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MasterDao implements IMasterDao {
    protected final String SQL_SELECT = "SELECT id, Name, Phone, Email, Login, Password FROM " + getTableName();
    protected static final int COLUMN_ID = 1;
    protected static final int COLUMN_NAME = 2;
    protected static final int COLUMN_PHONE = 3;
    protected static final int COLUMN_EMAIL = 4;
    protected static final int COLUMN_LOGIN = 5;
    protected static final int COLUMN_PASSWORD = 6;


    public String getTableName() {
        return "masters";
    }

    public List<Master> findAll() throws Exception {
        return findByDynamicSelect(SQL_SELECT, null);
    }

    public Master findById(long id){
        String query = SQL_SELECT + " WHERE id=" + id;
        List<Master> result = new ArrayList<>();
        try {
            result = findByDynamicSelect(query, null);
        }
        catch (Exception ex){
            System.out.println("Error executing " + query);
        }
        return result.get(0);
    }

    public Master findByName(String name){
        String query = SQL_SELECT + " WHERE name=" + name;
        List<Master> result = new ArrayList<>();
        try {
            result = findByDynamicSelect(query, null);
        }
        catch (Exception ex){
            System.out.println("Error executing " + query);
        }
        return result.get(0);
    }

    public Master post(Master master){
        String query = "CALL add_master('"+
                master.getMasterName()+"','"+
                master.getMasterPhone()+"','"+
                master.getMasterEmail()+"','"+
                master.getMasterLogin()+"','"+
                master.getMasterPassword()+"')";
        List<Master> result = new ArrayList<>();
        try{
            result = findByDynamicSelect(query, null);
        }
        catch (Exception ex){
            System.out.println("Error executing " + query);
        }
        return result.get(0);
    }

    public Master update(long id, Master master){
        String query = "CALL update_master('"+
                id+"','"+
                master.getMasterName()+"','"+
                master.getMasterPhone()+"','"+
                master.getMasterEmail()+"','"+
                master.getMasterLogin()+"','"+
                master.getMasterPassword()+"')";
        List<Master> result = new ArrayList<>();
        try{
            result = findByDynamicSelect(query, null);
        }
        catch (Exception ex){
            System.out.println("Error executing " + query);
        }
        return result.get(0);
    }

    public void delete(long id){
        String query = "CALL delete_master('"+id+"',)";
        List<Master> result = new ArrayList<>();
        try{
            result = findByDynamicSelect(query, null);
        }
        catch (Exception ex){
            System.out.println("Error executing " + query);
        }
    }

    protected Master fetchSingleResult(ResultSet rs) throws SQLException {
        if (rs.next()) {
            Master dto = new Master();
            populateDto(dto,rs);
            return dto;
        } else {
            return null;
        }
    }

    protected List fetchMultipleResults(ResultSet rs) throws SQLException {
        List resultList = new ArrayList();
        while (rs.next()) {
            Master dto = new Master();
            populateDto(dto,rs);
            resultList.add(dto);
        }
        return resultList;
    }

    protected void populateDto(Master dto, ResultSet rs) throws SQLException {
        dto.setId(rs.getLong(COLUMN_ID));
        dto.setMasterName(rs.getString(COLUMN_NAME));
        dto.setMasterPhone(rs.getString(COLUMN_PHONE));
        dto.setMasterEmail(rs.getString(COLUMN_EMAIL));
        dto.setMasterLogin(rs.getString(COLUMN_LOGIN));
        dto.setMasterPassword(rs.getString(COLUMN_PASSWORD));
    }

    private List<Master> findByDynamicSelect(String sql, Object[] sqlParameters) throws Exception {
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
