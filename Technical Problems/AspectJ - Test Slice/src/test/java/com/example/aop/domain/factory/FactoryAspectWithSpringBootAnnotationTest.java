package com.example.aop.domain.factory;

import static com.example.aop.domain.Utils.API_PRODUCT;
import static com.example.aop.domain.Utils.DB_PRODUCT;
import static org.assertj.core.api.Assertions.assertThat;

import com.example.aop.domain.participant.ApiParticipantRepositoryImpl;
import com.example.aop.domain.participant.DbParticipantRepositoryImpl;
import com.example.aop.domain.participant.api.ParticipantRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class FactoryAspectWithSpringBootAnnotationTest {

  private final RepositoryFactory repositoryFactory;

  public FactoryAspectWithSpringBootAnnotationTest(@Autowired RepositoryFactory repositoryFactory) {
    this.repositoryFactory = repositoryFactory;
  }

  @Test
  public void shouldCallAspectAndReturnDbOnApi() {
    final ParticipantRepository participantRepository = repositoryFactory.getParticipantRepository(API_PRODUCT);
    assertThat(participantRepository).isExactlyInstanceOf(DbParticipantRepositoryImpl.class);
  }

  @Test
  public void shouldCallAspectAndReturnApiOnDb() {
    final ParticipantRepository participantRepository = repositoryFactory.getParticipantRepository(DB_PRODUCT);
    assertThat(participantRepository).isExactlyInstanceOf(ApiParticipantRepositoryImpl.class);
  }
}
