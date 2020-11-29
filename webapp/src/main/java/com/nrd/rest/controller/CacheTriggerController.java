package com.nrd.rest.controller;

import com.nrd.rest.serive.CacheTriggerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/nrd", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
public class CacheTriggerController {

    private static final Logger log = LoggerFactory.getLogger(CacheTriggerController.class);

    private final CacheTriggerService cacheTriggerService;

    public CacheTriggerController(CacheTriggerService cacheTriggerService) {
        this.cacheTriggerService = cacheTriggerService;
    }

    @PostMapping(value = "/cache-trigger")
    public ResponseEntity<String> triggerCache(){
        log.info("request for updating cache");
        return ResponseEntity.ok(cacheTriggerService.updateCache());
    }
}
