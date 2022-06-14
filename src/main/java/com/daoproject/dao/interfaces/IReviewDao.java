package com.daoproject.dao.interfaces;

import com.daoproject.entities.Review;

import java.util.List;

public interface IReviewDao {
    List<Review> findAll() throws Exception;
    Review findById(long id);
    Review post(Review review);
    Review update(long id, Review review);
    void delete(long id);
}
