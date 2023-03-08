package ehcahce.project.problem.participant;

import org.ehcache.core.spi.service.ServiceFactory.RequiresConfiguration;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
@RequiresConfiguration
public class ParticipantRepository {

  @Cacheable(value = "participant", cacheManager = "projectJCacheCacheManager")
  public Participant getParticipant() {
    return new Participant("Alex", 170);
  }

  @Cacheable(value = "participant", key = "#value", cacheManager = "projectJCacheCacheManager")
  public Participant getParticipant(String name) {
    return new Participant(name, 180);
  }
}
