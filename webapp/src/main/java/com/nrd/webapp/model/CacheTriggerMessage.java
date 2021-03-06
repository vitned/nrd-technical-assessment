package com.nrd.webapp.model;

import com.nrd.webapp.model.enums.UpdateStatus;

import java.io.Serializable;

public class CacheTriggerMessage implements Serializable {

    private final UpdateStatus updateCache;

    public CacheTriggerMessage(UpdateStatus updateCache) {
        this.updateCache = updateCache;
    }

    public static CacheTriggerMessage updateAction(final UpdateStatus updateAction) {
        return new CacheTriggerMessage(updateAction);
    }

    public UpdateStatus getUpdateCache() {
        return updateCache;
    }
}
