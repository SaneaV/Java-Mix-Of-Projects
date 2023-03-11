package com.example.aop.domain.participant;

import static com.example.aop.domain.Utils.DB_PRODUCT;

import com.example.aop.domain.participant.api.ParticipantRepository;
import org.springframework.stereotype.Component;

@Component
public class DbParticipantRepositoryImpl implements ParticipantRepository {

  @Override
  public String getProduct() {
    return DB_PRODUCT;
  }
}
