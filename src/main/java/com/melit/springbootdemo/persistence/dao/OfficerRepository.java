package com.melit.springbootdemo.persistence.dao;

import com.melit.springbootdemo.persistence.entities.Officer;
import com.melit.springbootdemo.persistence.entities.Rank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OfficerRepository extends JpaRepository<Officer, Integer> {

    //The framework will now generate the implementations of about a dozen different methods,
    // including all the methods listed in the OfficerDAO interface (which is why those methods were chosen in the first place)

    //Use the Spring Data feature where it will will generate queries based on a naming convention as below:
    List<Officer> findAllByFirstLikeAndLastLikeAndRank(String firstNameLike, String lastNameLike, Rank rank);

}
