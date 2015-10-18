package com.jean.taxi.serviceImpl;

import com.jean.taxi.daoImpl.DaoFactoryImpl;
import com.jean.taxi.daoImpl.ReviewDaoImpl;
import com.jean.taxi.entity.Review;
import com.jean.taxi.service.ReviewService;

public class ReviewServiceImpl extends GenericServiceImpl<Review, ReviewDaoImpl> implements ReviewService {
    private ReviewDaoImpl reviewDao = DaoFactoryImpl.getInstance().getReviewDao();

    ReviewServiceImpl() {
        super.setDao(reviewDao);
    }

    @Override
    public void activateReview(Long reviewId) {

    }
}
