package com.servletproject.dao.interfaces;

import com.servletproject.entities.Review;

import java.sql.SQLException;
import java.util.List;

public interface IReviewDao {
    List<Review> findAll() throws Exception;
    Review findById(long id) throws SQLException;
    Review post(Review review) throws SQLException;
    Review update(long id, Review review) throws SQLException;
    void delete(long id) throws SQLException;
}
