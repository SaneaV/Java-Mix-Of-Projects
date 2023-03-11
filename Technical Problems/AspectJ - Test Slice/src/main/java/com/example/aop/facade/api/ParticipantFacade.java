package com.example.aop.facade.api;

import com.example.aop.domain.participant.api.ParticipantRepository;

public interface ParticipantFacade {

  ParticipantRepository getParticipantRepository(String product);
}
