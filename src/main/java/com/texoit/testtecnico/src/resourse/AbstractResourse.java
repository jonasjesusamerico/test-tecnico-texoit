package com.texoit.testtecnico.src.resourse;

import com.texoit.testtecnico.src.model.BaseEntity;
import com.texoit.testtecnico.src.service.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public abstract class AbstractResourse<E extends BaseEntity> implements ResourseTemplate<E> {

    @Autowired
    private AbstractService<E> service;

    @Override
    @GetMapping
    public Page<E> findAll(Pageable pageable) {
        return service.findAll(pageable);
    }

    @Override
    @GetMapping("{id}")
    public ResponseEntity<E> findById(@PathVariable("id") Long id) {
        return service.findById(id)
                .map(e -> new ResponseEntity<>(e, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }

    @Override
    @PostMapping
    @ResponseBody
    public ResponseEntity<E> save(@RequestBody E t) {
        return new ResponseEntity<>(service.save(t), HttpStatus.CREATED);
    }

    @Override
    @PutMapping
    public ResponseEntity<E> update(@RequestBody E t) {
        return new ResponseEntity<>(service.update(t), HttpStatus.OK);
    }

    @Override
    @DeleteMapping("{id}")
    public ResponseEntity<E> delete(@PathVariable("id") Long id) {
        if (service.delete(id)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}