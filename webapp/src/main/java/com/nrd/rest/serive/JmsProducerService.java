package com.nrd.rest.serive;

import com.nrd.rest.model.CacheTriggerMessage;
import org.apache.camel.ProducerTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class JmsProducerService {
    private final ProducerTemplate producerTemplate;

    public JmsProducerService(ProducerTemplate producerTemplate) {
        this.producerTemplate = producerTemplate;
    }

    public void send() {
        producerTemplate.requestBody("direct:updateCache", CacheTriggerMessage.updateAction("update"));
    }
}
