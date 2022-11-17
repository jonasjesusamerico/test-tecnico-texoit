package com.texoit.testtecnico.src.service;

import com.texoit.testtecnico.src.model.BaseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public abstract class AbstractService<E extends BaseEntity> implements ServiceTemplate<E> {

    @Autowired
    private JpaRepository<E, Long> repository;


    @Override
    public Page<E> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Optional<E> findById(Long id) {
        return repository.findById(id);

    }

    @Override
    public E save(E e) {
        return repository.save(e);
    }

    @Override
    public E update(E e) {
        return save(e);
    }

    @Override
    public boolean delete(Long id) {
        boolean exists = repository.existsById(id);
        if (exists) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}
