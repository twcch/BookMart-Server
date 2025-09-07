package io.twcch.bookmarkserver.dao.impl;

import io.twcch.bookmarkserver.dao.UserDao;
import io.twcch.bookmarkserver.dto.UserRegisterRequest;
import io.twcch.bookmarkserver.model.User;
import io.twcch.bookmarkserver.rowmapper.UserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class UserDaoImpl implements UserDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Integer createUser(UserRegisterRequest userRegisterRequest) {

        String sql = "INSERT INTO users (email, password, created_date, last_modified_date) " +
                "VALUES (:email, :password, :createdDate, :lastModifiedDate)";

        Map<String, Object> map = new HashMap<>();
        map.put("email", userRegisterRequest.getEmail());
        map.put("password", userRegisterRequest.getPassword());

        Date now = new Date();
        map.put("createdDate", now);
        map.put("lastModifiedDate", now);

        namedParameterJdbcTemplate.update(sql, map);

        // 使用 SQLite 的 last_insert_rowid() 函數取得最後插入的主鍵
        String getIdSql = "SELECT last_insert_rowid()";
        Integer userId = namedParameterJdbcTemplate.queryForObject(getIdSql, new HashMap<>(), Integer.class);

        return userId;

    }

    @Override
    public User getUserById(Integer userId) {

        String sql = "SELECT user_id, email, password, created_date, last_modified_date " +
                "FROM users WHERE user_id = :userId";

        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);

        List<User> query = namedParameterJdbcTemplate.query(sql, map, new UserRowMapper());

        if (!query.isEmpty()) {
            return query.get(0);
        }

        return null;

    }

}
