package com.taxi.service.serviceImpl;

import com.taxi.service.daoImpl.DaoFactoryImpl;
import com.taxi.service.daoImpl.ReviewDaoImpl;
import com.taxi.service.entity.Review;
import com.taxi.service.service.ReviewService;

public class ReviewServiceImpl extends GenericServiceImpl<Review, ReviewDaoImpl> implements ReviewService {
    private ReviewDaoImpl reviewDao = DaoFactoryImpl.getInstance().getReviewDao();

    ReviewServiceImpl() {
        super.setDao(reviewDao);
    }

    @Override
    public void activateReview(Long reviewId) {

    }
}
