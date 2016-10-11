package com.n11.repository;

import com.n11.model.Conference;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

/**
 * Created by serhat on 10/4/16.
 */
public interface ConferenceRepository extends MongoRepository<Conference, String> {
    Optional<Conference> findById(String id);
}
