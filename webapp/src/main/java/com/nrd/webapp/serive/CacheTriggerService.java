package com.nrd.webapp.serive;

import com.nrd.webapp.model.CacheTriggerMessage;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CacheTriggerService {

    private final JmsProducerService jmsProducerService;

    public CacheTriggerService(JmsProducerService jmsProducerService) {
        this.jmsProducerService = jmsProducerService;
    }

    public CacheTriggerMessage updateCache() {
        return jmsProducerService.send();
    }
}
