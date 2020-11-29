package com.nrd.webapp.model;

public class CacheTriggerMessage {

    private final String updateCache;

    private CacheTriggerMessage(final String updateCache) {
        this.updateCache = updateCache;
    }

    public static CacheTriggerMessage updateAction(final String updateAction) {
        return new CacheTriggerMessage(updateAction);
    }

    public String getUpdateCache() {
        return updateCache;
    }
}
