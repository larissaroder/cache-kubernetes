package com.example.hazelcast.repository;

import com.example.hazelcast.model.UserPayload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Qualifier("local")
@Profile("simple-cache")
public class UserRepositoryLocal implements UserRepository {

    @Autowired
    private List<UserPayload> payloadUsers;

    private static final Logger LOGGER = LoggerFactory.getLogger(UserRepositoryLocal.class);

    @Override
    @Cacheable("alluserscache")
    public List<UserPayload> fetchAllUsers() {

        LOGGER.info("Fetching all users");
        simulateSlowService();
        return payloadUsers;
    }

    @Override
    @Cacheable(cacheNames = "usercache",key = "#root.methodName")
    public UserPayload firstUser() {

        LOGGER.info("fetching firstUser");

        return payloadUsers.get(0);
    }

    @Override
    @Cacheable(cacheNames = "usercache",key = "{#firstName,#lastName}")
    public UserPayload userByFirstNameAndLastName(String firstName,String lastName) {

        LOGGER.info("fetching user by firstname and lastname");

            return payloadUsers.stream().filter(
                p-> p.getFirstName().equals(firstName)
                        &&p.getLastName().equals(lastName))
                .findFirst().orElse(null);

    }

    private void simulateSlowService() {
        try {
            Thread.sleep(3000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}