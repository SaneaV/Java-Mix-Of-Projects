package com.example.aop.domain.factory;

import static com.example.aop.domain.Utils.API_PRODUCT;
import static com.example.aop.domain.Utils.DB_PRODUCT;
import static org.assertj.core.api.Assertions.assertThat;

import com.example.aop.AspectJTestSliceApplication;
import com.example.aop.domain.participant.ApiParticipantRepositoryImpl;
import com.example.aop.domain.participant.DbParticipantRepositoryImpl;
import com.example.aop.domain.participant.api.ParticipantRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = AspectJTestSliceApplication.class)
public class FactoryAspectWithContextConfigurationTest {

  private final RepositoryFactory repositoryFactory;

  public FactoryAspectWithContextConfigurationTest(@Autowired RepositoryFactory repositoryFactory) {
    this.repositoryFactory = repositoryFactory;
  }

  @Test
  public void shouldCallAspectAndReturnApiOnDb() {
    final ParticipantRepository participantRepository = repositoryFactory.getParticipantRepository(DB_PRODUCT);
    assertThat(participantRepository).isExactlyInstanceOf(ApiParticipantRepositoryImpl.class);
  }

  @Test
  public void shouldCallAspectAndReturnDbOnApi() {
    final ParticipantRepository participantRepository = repositoryFactory.getParticipantRepository(API_PRODUCT);
    assertThat(participantRepository).isExactlyInstanceOf(DbParticipantRepositoryImpl.class);
  }
}
