package com.example.aop.domain.factory;

import static com.example.aop.domain.Utils.API_PRODUCT;
import static com.example.aop.domain.Utils.DB_PRODUCT;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

import com.example.aop.domain.participant.ApiParticipantRepositoryImpl;
import com.example.aop.domain.participant.DbParticipantRepositoryImpl;
import com.example.aop.domain.participant.api.ParticipantRepository;
import java.util.ArrayList;
import java.util.List;
import org.aspectj.lang.ProceedingJoinPoint;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FactoryAspectWithIntegrationMockTest {

  private final ApiParticipantRepositoryImpl apiParticipantRepository = mock(ApiParticipantRepositoryImpl.class);
  private final DbParticipantRepositoryImpl dbParticipantRepository = mock(DbParticipantRepositoryImpl.class);

  private final List<ParticipantRepository> participantRepositoryList = new ArrayList<>(
      asList(apiParticipantRepository, dbParticipantRepository));
  private final RepositoryFactory repositoryFactory = new RepositoryFactory(participantRepositoryList);

  private final ProceedingJoinPoint proceedingJoinPoint = mock(ProceedingJoinPoint.class);

  @BeforeEach
  public void init() {
    when(apiParticipantRepository.getProduct()).thenReturn(API_PRODUCT);
    when(dbParticipantRepository.getProduct()).thenReturn(DB_PRODUCT);
  }

  //Does not call Aspect
  @Test
  public void shouldCallAspectAndReturnApiOnApi() {
    repositoryFactory.init();
    final ParticipantRepository participantRepository = repositoryFactory.getParticipantRepository(API_PRODUCT);
    assertThat(participantRepository).isInstanceOf(ApiParticipantRepositoryImpl.class);
    verifyNoInteractions(proceedingJoinPoint);
  }

  //Does not call Aspect
  @Test
  public void shouldCallAspectAndReturnDbOnDb() {
    repositoryFactory.init();
    final ParticipantRepository participantRepository = repositoryFactory.getParticipantRepository(API_PRODUCT);
    assertThat(participantRepository).isInstanceOf(ApiParticipantRepositoryImpl.class);
    verifyNoInteractions(proceedingJoinPoint);
  }
}
