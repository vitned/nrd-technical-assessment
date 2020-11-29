package com.nrd.cacheworker.routconfig;

import com.nrd.cacheworker.model.CacheTriggerMessage;
import com.nrd.cacheworker.model.enums.UpdateStatus;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.stereotype.Component;

@Component
public class PersonRouteBuilder extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("jms:person-queue").routeId("receiver-route")
                .log("Receive a message - ${body} - sending to person-queue")
                .unmarshal().json(JsonLibrary.Gson, CacheTriggerMessage.class).id("unmarshal json to CacheTriggerMessage")
                .bean(this.getClass(), "processMessage").id("Process message")
                .marshal().json(JsonLibrary.Gson, CacheTriggerMessage.class).id("marshal json to PersonMessage")
                .end();
    }

    public CacheTriggerMessage processMessage(CacheTriggerMessage cacheTriggerMessage) {
        if (UpdateStatus.UPDATE_REQUEST == cacheTriggerMessage.getUpdateCache()) {
            return CacheTriggerMessage.updateAction(UpdateStatus.UPDATED);
        } else {
            return CacheTriggerMessage.updateAction(UpdateStatus.NOT_UPDATED);
        }
    }
}
