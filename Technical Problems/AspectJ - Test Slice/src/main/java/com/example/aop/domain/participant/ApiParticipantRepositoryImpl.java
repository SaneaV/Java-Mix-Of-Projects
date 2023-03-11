package com.example.aop.domain.participant;

import static com.example.aop.domain.Utils.API_PRODUCT;

import com.example.aop.domain.participant.api.ParticipantRepository;
import org.springframework.stereotype.Component;

@Component
public class ApiParticipantRepositoryImpl implements ParticipantRepository {

  @Override
  public String getProduct() {
    return API_PRODUCT;
  }
}
