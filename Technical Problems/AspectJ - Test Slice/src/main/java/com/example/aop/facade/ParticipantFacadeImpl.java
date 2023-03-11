package com.example.aop.facade;

import com.example.aop.domain.factory.RepositoryFactory;
import com.example.aop.domain.participant.api.ParticipantRepository;
import com.example.aop.facade.api.ParticipantFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ParticipantFacadeImpl implements ParticipantFacade {

  private final RepositoryFactory repositoryFactory;

  @Override
  public ParticipantRepository getParticipantRepository(String product) {
    final ParticipantRepository participantRepository = repositoryFactory.getParticipantRepository(product);

    System.out.println("Original product: " + product);
    System.out.println("Product from repository: " + participantRepository.getProduct() + "\n");

    return participantRepository;
  }
}
