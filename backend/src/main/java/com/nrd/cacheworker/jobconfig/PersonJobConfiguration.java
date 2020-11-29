package com.nrd.cacheworker.jobconfig;

import com.nrd.cacheworker.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@EnableBatchProcessing
class PersonJobConfiguration {

    private static final Logger log = LoggerFactory.getLogger(PersonJobConfiguration.class);

    private final PersonService personService;

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Autowired
    public PersonJobConfiguration(PersonService personService, JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory) {
        this.personService = personService;
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean
    @StepScope
    public Tasklet tasklet(@Value("#{jobParameters['name']}") String name) {
        return (stepContribution, chunkContext) -> {
            log.info("Strart receivein {}", name);

            log.info("Finish receivein {}", personService.getAllPeople());
            return RepeatStatus.FINISHED;
        };

    }

    @Bean
    public Job job() {
        return jobBuilderFactory.get("job4")
                .start(stepBuilderFactory.get("step1")
                        .tasklet(tasklet(null))
                        .build())
                .build();
    }
}
