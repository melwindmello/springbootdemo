package com.melit.springbootdemo.persistence.dao;

import com.melit.springbootdemo.persistence.entities.Officer;
import com.melit.springbootdemo.persistence.entities.Rank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

@Repository
public class JdbcOfficerDAO implements OfficerDAO {

    private JdbcTemplate jdbcTemplate;

    private SimpleJdbcInsert insertOfficer; // to fetch the generated PK

    private RowMapper<Officer> mapper = (rs, rowNum) -> {
        return new Officer(rs.getInt("id"),
                Rank.valueOf(rs.getString("rank")),
                rs.getString("first_name"),
                rs.getString("last_name"));
    };

    @Autowired
    public JdbcOfficerDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;

        insertOfficer = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("officers")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    public Officer save(Officer officer) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("rank", officer.getRank());
        parameters.put("first_name", officer.getFirst());
        parameters.put("last_name", officer.getLast());
        Integer newId = (Integer) insertOfficer.executeAndReturnKey(parameters);
        officer.setId(newId);
        return officer;
    }

    @Override
    public Optional<Officer> findById(Integer id) {

        try (Stream<Officer> stream = jdbcTemplate.queryForStream("select * from officers where id = ?", mapper, id)) {
            return stream.findFirst();
        }  // using try-with-resources since this stream is doing IO operation with DB connex

        //return Optional.empty();
    }

    @Override
    public List<Officer> findAll() {
        return jdbcTemplate.query("select * from officers", mapper);
    }

    @Override
    public long count() {
        return jdbcTemplate.queryForObject("select count(*) from officers", Long.class);
    }

    @Override
    public void delete(Officer officer) {
        jdbcTemplate.update("delete from officers where id = ?", officer.getId());
    }

    @Override
    public boolean existsById(Integer id) {
        return jdbcTemplate.queryForObject("select exists(select 1 from officers where id = ?)", Boolean.class, id);
    }
}
