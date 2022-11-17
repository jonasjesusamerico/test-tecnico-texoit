package com.texoit.testtecnico.src.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

interface ServiceTemplate<E> {

    Page<E> findAll(Pageable pageable);

    Optional<E> findById(Long id);

    E save(E e);

    E update(E e);

    boolean delete(Long id);

}
