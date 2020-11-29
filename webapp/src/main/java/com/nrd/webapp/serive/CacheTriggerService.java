package com.nrd.webapp.serive;

import org.springframework.stereotype.Service;

@Service
public class CacheTriggerService {

    private final JmsProducerService jmsProducerService;

    public CacheTriggerService(JmsProducerService jmsProducerService) {
        this.jmsProducerService = jmsProducerService;
    }

    public String updateCache() {
        jmsProducerService.send();
        return "Ok";
    }
}
