package com.nrd.webapp.serive;

import com.nrd.webapp.model.CacheTriggerMessage;
import org.apache.camel.ProducerTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.nrd.webapp.model.CacheTriggerMessage.updateAction;
import static com.nrd.webapp.model.enums.UpdateStatus.UPDATE_REQUEST;

@Service
@Transactional
public class JmsProducerService {
    private final ProducerTemplate producerTemplate;

    public JmsProducerService(ProducerTemplate producerTemplate) {
        this.producerTemplate = producerTemplate;
    }

    public CacheTriggerMessage send() {
        return (CacheTriggerMessage) producerTemplate.requestBody("direct:updateCache", updateAction(UPDATE_REQUEST));
    }
}
