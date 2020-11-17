package com.lambdaschool.crudyorders.services;

import com.lambdaschool.crudyorders.models.Agent;

public interface AgentService {
    Agent save(Agent agent);

    Agent findById(long id);
}
