package com.example.aop.domain.factory;

import static java.util.stream.Collectors.toMap;

import com.example.aop.domain.participant.api.ParticipantRepository;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RepositoryFactory implements IRepositoryFactory{

  private final List<ParticipantRepository> participantRepository;
  private Map<String, ParticipantRepository> participantRepositoryByProduct;

  @PostConstruct
  public void init() {
    participantRepositoryByProduct = participantRepository.stream()
        .collect(toMap(ParticipantRepository::getProduct, Function.identity()));
  }

  public ParticipantRepository getParticipantRepository(String product) {
    if (participantRepository == null) {
      throw new RuntimeException("Repository not found");
    }
    return participantRepositoryByProduct.get(product);
  }
}
