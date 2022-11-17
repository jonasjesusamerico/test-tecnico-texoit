package com.texoit.testtecnico.src.repository;

import com.texoit.testtecnico.src.model.Producer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProducerRepository extends JpaRepository<Producer, Long> {
    @Query(value = "select * from \"producer\" where \"id\"  in ( select \"i_producer\" from \"movie\" group by \"i_producer\"  having count(*) > 1)  order by \"id\"", nativeQuery = true)
    List<Producer> findProducerWithMoreThanOneMovie();
}
