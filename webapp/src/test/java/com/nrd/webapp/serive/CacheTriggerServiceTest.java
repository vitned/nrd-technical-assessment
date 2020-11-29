package com.nrd.webapp.serive;

import com.nrd.webapp.model.CacheTriggerMessage;
import com.nrd.webapp.model.enums.UpdateStatus;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.nrd.webapp.model.enums.UpdateStatus.NOT_UPDATED;
import static com.nrd.webapp.model.enums.UpdateStatus.UPDATED;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertTrue;

public class CacheTriggerServiceTest {

    @Mock
    private JmsProducerService jmsProducerService;

    @InjectMocks
    private CacheTriggerService cacheTriggerService;

    @BeforeClass
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeMethod
    public void beforeMethod() {
        reset(jmsProducerService);
    }

    @Test
    public void shouldReturnAnswerMessage() {
        //given
        final List<UpdateStatus> unmodifiableList = Collections.unmodifiableList(Arrays.asList(NOT_UPDATED, UPDATED));
        when(jmsProducerService.send()).thenReturn(CacheTriggerMessage.updateAction(UPDATED));

        //when
        CacheTriggerMessage cacheTriggerMessage = cacheTriggerService.updateCache();

        //then
        assertTrue(unmodifiableList.contains(cacheTriggerMessage.getUpdateCache()));
    }

}