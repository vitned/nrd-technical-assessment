package com.nrd.webapp.routconfig;

import com.nrd.webapp.model.CacheTriggerMessage;
import org.apache.camel.ExchangePattern;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.stereotype.Component;

@Component
public class WebAppRouteBuilder extends RouteBuilder {
    @Override
    public void configure() {
        from("direct:updateCache").routeId("producer-route")
                .marshal().json(JsonLibrary.Gson, CacheTriggerMessage.class).id("marshal json to PersonMessage")
                .log("Sent message - ${body} - to person-queue")
                .to(ExchangePattern.InOut,"jms:person-queue").id("direct to jms")
                .log("Receive ${body}")
                .unmarshal().json(JsonLibrary.Gson, CacheTriggerMessage.class).id("unmarshal json to PersonMessage")
                .end();
    }
}
