package com.tkt.Service;

import com.tkt.model.RetrieveResponse;
import org.springframework.http.ResponseEntity;

public interface RetrieveService {

    ResponseEntity<RetrieveResponse> getBYSourceAndDestination(String source, String destination);

}
