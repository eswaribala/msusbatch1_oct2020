package com.virtusa.banking.eventsourcingaxonspringboot.services.queries;

import java.util.List;

public interface AccountQueryService {
    public List<Object> listEventsForAccount(String accountNumber);
}
