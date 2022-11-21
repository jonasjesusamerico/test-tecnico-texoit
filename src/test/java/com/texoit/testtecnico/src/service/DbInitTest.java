package com.texoit.testtecnico.src.service;

import com.texoit.testtecnico.src.model.Producer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class DbInitTest {

    @Value("${filename}")
    private String filename;

    @Autowired
    private DbInit dbInit;

    @Test
    public void mustTestAutorun() throws FileNotFoundException {
        List<Producer> producersSaved = dbInit.init();
        Set<Producer> notSaveProducers = dbInit.dataExtractor(filename);
        assertEquals(producersSaved.size(), notSaveProducers.size(), "The size of the lists must contain the same amount.");

    }

}