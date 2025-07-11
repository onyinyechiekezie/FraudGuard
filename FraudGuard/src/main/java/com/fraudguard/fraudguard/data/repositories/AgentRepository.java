package com.fraudguard.fraudguard.data.repositories;

import com.fraudguard.fraudguard.data.models.Agent;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AgentRepository extends MongoRepository<Agent, String> {
}
