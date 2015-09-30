package com.taxi.service.daoImpl;

import com.taxi.service.entity.Review;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ReviewDaoImpl extends GenericDaoImpl<Review> {

    private final String REVIEW_TABLE = "jean_taxi_service.review;";
    private final String REVIEWS_ID = "jean_taxi_service.review WHERE id=?;";
    private final String INSERT_REVIEW = "INSERT INTO jean_taxi_service.review(id_client, client_name, note) VALUES(?,?,?);";
    private final String UPDATE_REVIEW = "jean_taxi_service.review SET note=? WHERE id=?;";

    public ReviewDaoImpl(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public String getSelectQuery() {
        return REVIEWS_ID;
    }

    @Override
    public String getInsertQuery() {
        return INSERT_REVIEW;
    }

    @Override
    public String getUpdateQuery() {
        return UPDATE_REVIEW;
    }

    @Override
    public String getDeleteQuery() {
        return REVIEWS_ID;
    }

    @Override
    public String getAllFromTableQuery() {
        return REVIEW_TABLE;
    }

    @Override
    public void getStatementForUpdateEntity(Review review, PreparedStatement preparedStatement) {
        convertUpdateReviewEntity(review, preparedStatement);
    }

    @Override
    public void getStatementForInsertEntity(Review review, PreparedStatement preparedStatement) {
        convertNewReviewEntity(review, preparedStatement);
    }

    @Override
    public Review parseSingleResultSet(ResultSet resultSet) {
        return convertReviewToEntity(resultSet);
    }

    @Override
    public List<Review> parseListResultSet(ResultSet resultSet) {
        return convertListReviewToEntity(resultSet);
    }

    @Override
    public Review addNew(Review review) {
        try (PreparedStatement preparedStatement = getDataSource().getConnection().prepareStatement(INSERT_REVIEW, Statement.RETURN_GENERATED_KEYS)) {
            convertNewReviewEntity(review, preparedStatement);
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                review.setId(resultSet.getLong(1));
                return review;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private void convertNewReviewEntity(Review review, PreparedStatement preparedStatement) {
        try {
            preparedStatement.setLong(1, review.getId());
            preparedStatement.setString(2, review.getClientName());
            preparedStatement.setString(3, review.getNote());
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public void convertUpdateReviewEntity(Review review, PreparedStatement preparedStatement) {
        try {
            preparedStatement.setString(1, review.getNote());
            preparedStatement.setLong(2, review.getId());
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public Review convertReviewToEntity(ResultSet resultSet) {
        try {
            while (resultSet.next()) {
                Review review = new Review();
                review.setId(resultSet.getLong("id"));
                review.setNote(resultSet.getString("note"));
                review.setCreateDate(resultSet.getDate("create_date"));
                review.setClientName(resultSet.getString("client_name"));
                return review;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }

    public List<Review> convertListReviewToEntity(ResultSet resultSet) {
        ArrayList reviewList = new ArrayList();
        try {
            while (resultSet.next()) {
                Review review = new Review();
                review.setId(resultSet.getLong("id"));
                review.setNote(resultSet.getString("note"));
                review.setCreateDate(resultSet.getDate("create_date"));
                review.setClientName(resultSet.getString("client_name"));
                reviewList.add(review);
            }
            return reviewList;
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }
}
