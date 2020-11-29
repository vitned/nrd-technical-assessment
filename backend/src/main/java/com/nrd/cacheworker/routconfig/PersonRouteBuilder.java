package com.nrd.cacheworker.routconfig;

import com.nrd.cacheworker.model.CacheTriggerMessage;
import com.nrd.cacheworker.model.enums.UpdateStatus;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.stereotype.Component;

@Component
public class PersonRouteBuilder extends RouteBuilder {

    private final JobLauncher jobLauncher;
    private final Job job;

    public PersonRouteBuilder(JobLauncher jobLauncher, Job job) {
        this.jobLauncher = jobLauncher;
        this.job = job;
    }

    @Override
    public void configure() throws Exception {
        from("jms:person-queue").routeId("receiver-route")
                .log("Receive a message - ${body} - sending to person-queue")
                .unmarshal().json(JsonLibrary.Gson, CacheTriggerMessage.class).id("unmarshal json to CacheTriggerMessage")
                .bean(this.getClass(), "processMessage").id("Process message")
                .marshal().json(JsonLibrary.Gson, CacheTriggerMessage.class).id("marshal json to PersonMessage")
                .end();
    }

    public CacheTriggerMessage processMessage(CacheTriggerMessage cacheTriggerMessage)  {
        if (UpdateStatus.UPDATE_REQUEST == cacheTriggerMessage.getUpdateCache()) {
            try {
                this.jobLauncher.run(job, new JobParametersBuilder().addString("name", cacheTriggerMessage.toString()).toJobParameters());
            }catch (JobParametersInvalidException | JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException e){
                log.error("Error occurred while job running", e);
            }
            return CacheTriggerMessage.updateAction(UpdateStatus.UPDATED);
        } else {
            return CacheTriggerMessage.updateAction(UpdateStatus.NOT_UPDATED);
        }
    }
}
