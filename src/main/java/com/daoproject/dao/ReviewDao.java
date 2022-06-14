package com.daoproject.dao;

import com.daoproject.connection.ConnectionPool;
import com.daoproject.dao.interfaces.IReviewDao;
import com.daoproject.entities.Review;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReviewDao implements IReviewDao {
    protected Connection conn;
    protected final String SQL_SELECT = "SELECT id, Score, Text, request_id FROM " + getTableName();
    protected static final int COLUMN_ID = 1;
    protected static final int COLUMN_SCORE = 2;
    protected static final int COLUMN_TEXT = 3;
    protected static final int COLUMN_REQUEST_ID = 4;

    public ReviewDao(final ConnectionPool connPool) {
        try{
            ConnectionPool connectionPool = ConnectionPool.create(connPool.getUrl(), connPool.getLogin(), connPool.getPassword());
            this.conn = connectionPool.getConnection();
        } catch (SQLException s){
            s.printStackTrace();
        }

    }

    public String getTableName() {
        return "reviews";
    }

    public List<Review> findAll() throws Exception {
        return findByDynamicSelect(SQL_SELECT, null);
    }

    public Review findById(long id){
        String query = SQL_SELECT + " WHERE id=" + id;
        List<Review> result = new ArrayList<>();
        try {
            result = findByDynamicSelect(query, null);
        }
        catch (Exception ex){
            System.out.println("Error executing " + query);
        }
        return result.get(0);
    }

    public Review post(Review review){
        String query = "CALL add_review('"+
                review.getText()+"','"+
                review.getScore()+"','"+
                review.getRequest_id()+"')";
        List<Review> result = new ArrayList<>();
        try{
            result = findByDynamicSelect(query, null);
        }
        catch (Exception ex){
            System.out.println("Error executing " + query);
        }
        return result.get(0);
    }

    public Review update(long id, Review review){
        String query = "CALL update_review('"+
                id+"','"+
                review.getText()+"','"+
                review.getScore()+"','"+
                review.getRequest_id()+"')";
        List<Review> result = new ArrayList<>();
        try{
            result = findByDynamicSelect(query, null);
        }
        catch (Exception ex){
            System.out.println("Error executing " + query);
        }
        return result.get(0);
    }

    public void delete(long id){
        String query = "CALL delete_review('"+id+"',)";
        List<Review> result = new ArrayList<>();
        try{
            result = findByDynamicSelect(query, null);
        }
        catch (Exception ex){
            System.out.println("Error executing " + query);
        }
    }

    protected Review fetchSingleResult(ResultSet rs) throws SQLException {
        if (rs.next()) {
            Review dto = new Review();
            populateDto(dto,rs);
            return dto;
        } else {
            return null;
        }
    }

    protected List fetchMultipleResults(ResultSet rs) throws SQLException {
        List resultList = new ArrayList();
        while (rs.next()) {
            Review dto = new Review();
            populateDto(dto,rs);
            resultList.add(dto);
        }
        return resultList;
    }

    protected void populateDto(Review dto, ResultSet rs) throws SQLException {
        dto.setId(rs.getLong(COLUMN_ID));
        dto.setScore(rs.getInt(COLUMN_SCORE));
        dto.setText(rs.getString(COLUMN_TEXT));
        dto.setRequest_id(rs.getLong(COLUMN_REQUEST_ID));
    }

    private List<Review> findByDynamicSelect(String sql, Object[] sqlParameters) throws Exception {
        final boolean hasConn = (conn != null);
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            final String SQL = sql;
            System.out.println("Executing " + SQL);
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
