package com.nrd.cacheworker.service;

import com.nrd.cacheworker.model.Person;
import com.nrd.cacheworker.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@CacheConfig(cacheNames={"people"})
public class PersonService {

    private static final Logger log = LoggerFactory.getLogger(PersonService.class);

    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Cacheable
    public List<Person> getAllPeople() {
        log.info("Get all people");
        simulateSlowService();
        return personRepository.findAll();
    }

    private void simulateSlowService() {
        try {
            Thread.sleep(3000L);
        } catch (InterruptedException e) {
            log.error("An error occurred while simulating slow service.", e);
            Thread.currentThread().interrupt();
        }
    }
}
