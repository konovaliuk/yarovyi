package com.servletproject.dao;

import com.servletproject.connection.ConnectionPool;
import com.servletproject.dao.interfaces.IUserDao;
import com.servletproject.entities.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao implements IUserDao {
    protected final String SQL_SELECT = "SELECT id, Name, Email, Login, Password FROM " + getTableName();
    protected static final int COLUMN_ID = 1;
    protected static final int COLUMN_NAME = 2;
    protected static final int COLUMN_EMAIL = 3;
    protected static final int COLUMN_LOGIN = 4;
    protected static final int COLUMN_PASSWORD = 5;


    public String getTableName() {
        return "users";
    }

    public List<User> findAll() throws Exception {
        return findByDynamicSelect(SQL_SELECT, null);
    }

    public User findById(long id){
        String query = SQL_SELECT + " WHERE id=" + id;
        List<User> result = new ArrayList<>();
        try {
            result = findByDynamicSelect(query, null);
        }
        catch (Exception ex){
            System.out.println("Error executing " + query);
        }
        return result.get(0);
    }

    public User findByName(String name){
        String query = SQL_SELECT + " WHERE name=" + name;
        List<User> result = new ArrayList<>();
        try {
            result = findByDynamicSelect(query, null);
        }
        catch (Exception ex){
            System.out.println("Error executing " + query);
        }
        return result.get(0);
    }

    public User post(User user){
        PreparedStatement stmt = null;
        ResultSet rs = null;
        User result = new User();
        String query = "CALL add_user('"+
                user.getUserName()+"','"+
                user.getUserEmail()+"','"+
                user.getUserLogin()+"','"+
                user.getUserPassword()+"')";
        System.out.println("Executing " + query);
        try{
            Connection conn = ConnectionPool.getConnection();
            stmt = conn.prepareStatement(query);
            rs = stmt.executeQuery();
            result = fetchSingleResult(rs);
        }
        catch (Exception ex){
            System.out.println("Error executing " + query);
        }
        return result;
        }

    public User update(long id, User user){
        String query = "CALL update_user('"+
                id+"','"+
                user.getUserName()+"','"+
                user.getUserEmail()+"','"+
                user.getUserLogin()+"','"+
                user.getUserPassword()+"',)";
        List<User> result = new ArrayList<>();
        try{
            result = findByDynamicSelect(query, null);
        }
        catch (Exception ex){
            System.out.println("Error executing " + query);
        }
        return result.get(0);
    }

    public void delete(long id){
        String query = "CALL delete_user('"+id+"')";
        List<User> result = new ArrayList<>();
        try{
            result = findByDynamicSelect(query, null);
        }
        catch (Exception ex){
            System.out.println("Error executing " + query);
        }
    }

    public void delete_by_name(String name){
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String query = "CALL delete_user_by_name('"+name+"')";
        System.out.println("Executing " + query);
        try {
            Connection conn = ConnectionPool.getConnection();
            stmt = conn.prepareStatement(query);
            rs = stmt.executeQuery();
        }
        catch (Exception ex){
            System.out.println("Error executing " + query);
        }
    }


    protected User fetchSingleResult(ResultSet rs) throws SQLException {
        if (rs.next()) {
            User dto = new User();
            populateDto(dto,rs);
            return dto;
        } else {
            return null;
        }
    }

    protected List<User> fetchMultipleResults(ResultSet rs) throws SQLException {
        List<User> resultList = new ArrayList();
        while (rs.next()) {
            User dto = new User();
            populateDto(dto,rs);
            resultList.add(dto);
        }
        return resultList;
    }

    protected void populateDto(User dto, ResultSet rs) throws SQLException {
        dto.setId(rs.getLong(COLUMN_ID));
        dto.setUserName(rs.getString(COLUMN_NAME));
        dto.setUserEmail(rs.getString(COLUMN_EMAIL));
        dto.setUserName(rs.getString(COLUMN_LOGIN));
        dto.setUserName(rs.getString(COLUMN_PASSWORD));
    }

    private List<User> findByDynamicSelect(String sql, Object[] sqlParameters) throws Exception {
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
