package com.example.aop.domain.factory;

import com.example.aop.domain.participant.api.ParticipantRepository;

public interface IRepositoryFactory {

  ParticipantRepository getParticipantRepository(String product);
}
