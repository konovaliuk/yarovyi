package com.daoproject.dao;

import com.daoproject.connection.ConnectionPool;
import com.daoproject.dao.interfaces.ITicketDao;
import com.daoproject.entities.Ticket;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TicketDao implements ITicketDao {
    protected Connection conn;
    protected final String SQL_SELECT = "SELECT id, State, Creation_date, manager_id, master_id FROM " + getTableName();
    protected static final int COLUMN_ID = 1;
    protected static final int COLUMN_STATE = 2;
    protected static final int COLUMN_DATE = 3;
    protected static final int COLUMN_MANAGER_ID = 4;
    protected static final int COLUMN_MASTER_ID = 5;

    public TicketDao(final ConnectionPool connPool) {
        try{
            ConnectionPool connectionPool = ConnectionPool.create(connPool.getUrl(), connPool.getLogin(), connPool.getPassword());
            this.conn = connectionPool.getConnection();
        } catch (SQLException s){
            s.printStackTrace();
        }

    }

    public String getTableName() {
        return "tickets";
    }

    public List<Ticket> findAll() throws Exception {
        return findByDynamicSelect(SQL_SELECT, null);
    }

    public Ticket findById(long id){
        String query = SQL_SELECT + " WHERE id=" + id;
        List<Ticket> result = new ArrayList<>();
        try {
            result = findByDynamicSelect(query, null);
        }
        catch (Exception ex){
            System.out.println("Error executing " + query);
        }
        return result.get(0);
    }

    public Ticket post(Ticket ticket){
        String query = "CALL add_ticket('"+
                ticket.getTicketStatus()+"','"+
                ticket.getCreationDate()+"','"+
                ticket.getManager_id()+"')";
        List<Ticket> result = new ArrayList<>();
        try{
            result = findByDynamicSelect(query, null);
        }
        catch (Exception ex){
            System.out.println("Error executing " + query);
        }
        return result.get(0);
    }

    public Ticket update(long id, Ticket ticket){
        String query = "CALL update_ticket('"+
                id+"','"+
                ticket.getTicketStatus()+"','"+
                ticket.getCreationDate()+"','"+
                ticket.getManager_id()+"',)";
        List<Ticket> result = new ArrayList<>();
        try{
            result = findByDynamicSelect(query, null);
        }
        catch (Exception ex){
            System.out.println("Error executing " + query);
        }
        return result.get(0);
    }

    public Ticket assign(long id, long master_id){
        String query = "CALL assign_ticket_master('"+
                id+"','"+master_id+"',)";
        List<Ticket> result = new ArrayList<>();
        try{
            result = findByDynamicSelect(query, null);
        }
        catch (Exception ex){
            System.out.println("Error executing " + query);
        }
        return result.get(0);
    }

    public void delete(long id){
        String query = "CALL delete_ticket('"+id+"',)";
        List<Ticket> result = new ArrayList<>();
        try{
            result = findByDynamicSelect(query, null);
        }
        catch (Exception ex){
            System.out.println("Error executing " + query);
        }
    }

    protected Ticket fetchSingleResult(ResultSet rs) throws SQLException {
        if (rs.next()) {
            Ticket dto = new Ticket();
            populateDto(dto,rs);
            return dto;
        } else {
            return null;
        }
    }

    protected List fetchMultipleResults(ResultSet rs) throws SQLException {
        List resultList = new ArrayList();
        while (rs.next()) {
            Ticket dto = new Ticket();
            populateDto(dto,rs);
            resultList.add(dto);
        }
        return resultList;
    }

    protected void populateDto(Ticket dto, ResultSet rs) throws SQLException {
        dto.setId(rs.getLong(COLUMN_ID));
        dto.setTicketStatus(rs.getString(COLUMN_STATE));
        dto.setCreationDate(rs.getDate(COLUMN_DATE));
        dto.setManager_id(rs.getLong(COLUMN_MANAGER_ID));
        dto.setMaster_id(rs.getLong(COLUMN_MASTER_ID));
    }

    private List<Ticket> findByDynamicSelect(String sql, Object[] sqlParameters) throws Exception {
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
