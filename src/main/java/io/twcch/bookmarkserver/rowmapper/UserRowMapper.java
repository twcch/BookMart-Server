package io.twcch.bookmarkserver.rowmapper;

import io.twcch.bookmarkserver.constant.ProductCategory;
import io.twcch.bookmarkserver.model.Product;
import io.twcch.bookmarkserver.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet resultSet, int rowNum) throws SQLException {

        User user = new User();

        user.setUserId(resultSet.getInt("user_id"));
        user.setEmail(resultSet.getString("email"));
        user.setPassword(resultSet.getString("password"));
        user.setCreatedDate(resultSet.getDate("created_date"));
        user.setLastModifiedDate(resultSet.getDate("last_modified_date"));

        return user;

    }

}
