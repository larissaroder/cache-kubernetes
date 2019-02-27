package com.example.hazelcast.repository;

import com.example.hazelcast.model.UserPayload;

import java.util.List;

public interface UserRepository {

    List<UserPayload> fetchAllUsers();

    UserPayload firstUser();

    UserPayload userByFirstNameAndLastName(String firstName,String lastName);

}