package com.melit.springbootdemo.persistence;

import com.melit.springbootdemo.persistence.dao.OfficerRepository;
import com.melit.springbootdemo.persistence.entities.Officer;
import com.melit.springbootdemo.persistence.entities.Rank;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
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
public class OfficerRepositoryTest {

    @Autowired
    private OfficerRepository repository;

    @Test
    public void save() throws Exception {
        Officer officer = new Officer(Rank.LIEUTENANT, "Nyota", "Uhuru");
        officer = repository.save(officer);
        assertNotNull(officer.getId());
    }

    @Test
    public void findByIdThatExists() throws Exception {
        Optional<Officer> officer = repository.findById(1);
        assertTrue(officer.isPresent());
        assertEquals(1, officer.get().getId().intValue());
    }

    @Test
    public void findByIdThatDoesNotExist() throws Exception {
        Optional<Officer> officer = repository.findById(999);
        assertFalse(officer.isPresent());
    }

    @Test
    public void count() throws Exception {
        assertEquals(5, repository.count());
    }

    @Test
    public void findAll() throws Exception {
        List<String> dbNames = repository.findAll().stream()
                .map(Officer::getLast)
                .collect(Collectors.toList());
        assertThat(dbNames).contains("Kirk", "Picard", "Sisko", "Janeway", "Archer");
    }

    @Test
    public void delete() throws Exception {
        IntStream.rangeClosed(1, 5)
                .forEach(id -> {
                    Optional<Officer> officer = repository.findById(id);
                    assertTrue(officer.isPresent());
                    repository.delete(officer.get());
                });
        assertEquals(0, repository.count());
    }

    @Test
    public void existsById() throws Exception {
        IntStream.rangeClosed(1, 5)
                .forEach(id -> assertTrue(repository.existsById(id)));
    }


    @Test
    public void findBasedOnFirsAndLastAndRank() {
        //'CAPTAIN', 'Kathryn', 'Janeway'
        List<Officer> list = repository.findAllByFirstLikeAndLastLikeAndRank("Kathryn", "Janeway", Rank.CAPTAIN);
        assertEquals(1, list.size());
    }

}
