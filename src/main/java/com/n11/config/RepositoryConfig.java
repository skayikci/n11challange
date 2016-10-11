package com.n11.config;

import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * Created by serhat on 10/6/16.
 */
@EnableMongoRepositories(basePackages = "com.n11.repository.mongo")
public interface RepositoryConfig { }