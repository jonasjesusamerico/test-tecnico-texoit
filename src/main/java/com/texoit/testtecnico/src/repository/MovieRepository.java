package com.texoit.testtecnico.src.repository;

import com.texoit.testtecnico.src.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    List<Movie> findAllById(Long id);

}
