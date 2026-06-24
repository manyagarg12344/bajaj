package com.bfhl.controller;

import com.bfhl.dto.BfhlRequest;
import com.bfhl.dto.BfhlResponse;
import com.bfhl.service.BfhlService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/bfhl")
public class BfhlController {

    private final BfhlService bfhlService;

    public BfhlController(BfhlService bfhlService) {
        this.bfhlService = bfhlService;
    }

    @PostMapping
    public ResponseEntity<?> processData(@RequestBody BfhlRequest request) {
        if (request.getData() == null || request.getData().isEmpty()) {
            return ResponseEntity.badRequest()
                    .body(Map.of("is_success", false, "error", "data field is missing or empty"));
        }
        BfhlResponse response = bfhlService.processData(request);
        return ResponseEntity.ok(response);
    }
}
