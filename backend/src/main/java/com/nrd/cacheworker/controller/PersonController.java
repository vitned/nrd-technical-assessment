package com.nrd.cacheworker.controller;

import com.nrd.cacheworker.model.Person;
import com.nrd.cacheworker.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/nrd", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
public class PersonController {

    private final PersonService personService;

    private static final Logger log = LoggerFactory.getLogger(PersonController.class);

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping(value = "/people")
    public ResponseEntity<List<Person>> getAllPeople() {
        log.info("Receive all people");
        return ResponseEntity.ok(personService.getAllPeople());
    }
}
