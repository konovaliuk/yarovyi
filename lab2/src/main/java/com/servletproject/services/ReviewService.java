package com.servletproject.services;

import com.servletproject.dao.DaoFactory;
import com.servletproject.entities.Review;

import java.sql.SQLException;
import java.util.List;

public class ReviewService {
    public Review find(int id) {
        Review review;
        try {
            review = DaoFactory.createRewievDao().findById(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return review;
    }


    public List<Review> getAllReviews() {
        try {
            return DaoFactory.createRewievDao().findAll();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public long createReview(Review review) {
        try {
            DaoFactory.createRewievDao().post(review);
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
        return review.getId();
    }
}
