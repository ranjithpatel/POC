package com.tkt.Controller;

import com.tkt.Service.RetrieveServiceImp;
import com.tkt.model.RetrieveResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/retrieve")
public class RetrieveBussesOrTrainsController {
    @Autowired
    private RetrieveServiceImp retrieveServiceImp;

    @GetMapping("/{source}/{destination}")
    public ResponseEntity<RetrieveResponse> getBySourceAndDestination(@PathVariable String source, @PathVariable String destination){
        return  retrieveServiceImp.getBYSourceAndDestination(source, destination);
    }

}
