package com.virtusa.banking.eventsourcingaxonspringboot.events;

public class BaseEvent<T> {

    public final T id;

    public BaseEvent(T id) {
        this.id = id;
    }
}
