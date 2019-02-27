package com.example.hazelcast.schedule;

import com.example.hazelcast.repository.LocalCacheEvict;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Profile("simple-cache")
public class EvictScheduler {

    @Autowired
    private LocalCacheEvict localCacheEvict;

    private static final Logger LOGGER = LoggerFactory.getLogger(EvictScheduler.class);

    @Scheduled(fixedDelay=10000)
    public void clearCaches() {

        LOGGER.info("Invalidating caches");

        localCacheEvict.evictUserCache();
        localCacheEvict.evictAllUsersCache();
    }


}
