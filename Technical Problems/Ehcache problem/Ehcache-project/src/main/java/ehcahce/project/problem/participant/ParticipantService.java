package ehcahce.project.problem.participant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ParticipantService {

  private final ParticipantRepository participantRepository;

  public ParticipantService(@Autowired ParticipantRepository participantRepository) {
    this.participantRepository = participantRepository;
  }

  public void testCacheable() {
    participantRepository.getParticipant();
    participantRepository.getParticipant("Ivan");

    participantRepository.getParticipant();
    participantRepository.getParticipant("Ivan");
  }
}
