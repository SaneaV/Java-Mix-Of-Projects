package ehcahce.project.problem.participant;

import java.io.Serializable;

public class Participant implements Serializable {

  private final String name;
  private final Integer height;

  public Participant(String name, Integer height) {
    this.name = name;
    this.height = height;
  }
}
