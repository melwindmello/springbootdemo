package com.melit.springbootdemo;

import com.melit.springbootdemo.persistence.dao.JpaOfficerDAO;
import com.melit.springbootdemo.persistence.dao.OfficerDAO;
import com.melit.springbootdemo.persistence.entities.Officer;
import com.melit.springbootdemo.persistence.entities.Rank;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Transactional
// @Transactional > In a test class like this, Spring will interpret that to mean that each test should run in a transaction that rolls back at the end of the test.
public class JpaOfficerDAOTest {

    @Autowired
    @Qualifier("jdbcOfficerDAO")
    private OfficerDAO dao;

    @Test
    public void save() throws Exception {
        Officer officer = new Officer(Rank.LIEUTENANT, "Nyota", "Uhuru");
        officer = dao.save(officer);
        assertNotNull(officer.getId());
    }

    @Test
    public void findByIdThatExists() throws Exception {
        Optional<Officer> officer = dao.findById(1);
        assertTrue(officer.isPresent());
        assertEquals(1, officer.get().getId().intValue());
    }

    @Test
    public void findByIdThatDoesNotExist() throws Exception {
        Optional<Officer> officer = dao.findById(999);
        assertFalse(officer.isPresent());
    }

    @Test
    public void count() throws Exception {
        assertEquals(5, dao.count());
    }

    @Test
    public void findAll() throws Exception {
        List<String> dbNames = dao.findAll().stream()
                .map(Officer::getLast)
                .collect(Collectors.toList());
        assertThat(dbNames).contains("Kirk", "Picard", "Sisko", "Janeway", "Archer");
    }

    @Test
    public void delete() throws Exception {
        IntStream.rangeClosed(1, 5)
                .forEach(id -> {
                    Optional<Officer> officer = dao.findById(id);
                    assertTrue(officer.isPresent());
                    dao.delete(officer.get());
                });
        assertEquals(0, dao.count());
    }

    @Test
    public void existsById() throws Exception {
        IntStream.rangeClosed(1, 5)
                .forEach(id -> assertTrue(dao.existsById(id)));
    }

}
