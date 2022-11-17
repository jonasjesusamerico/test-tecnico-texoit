package com.texoit.testtecnico.src.resourse;

import com.texoit.testtecnico.src.model.Producer;
import com.texoit.testtecnico.src.model.ResponseInterval;
import com.texoit.testtecnico.src.service.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/producers")
public class ProducerResource extends AbstractResourse<Producer> {
    private final ProducerService service;

    public ProducerResource(ProducerService service) {
        this.service = service;
    }


    @GetMapping("intervals")
    public ResponseInterval get() {
        return service.findIntervals();
    }


}
