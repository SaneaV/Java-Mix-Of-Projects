package com.telegram.bot.infrastructure.jpa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "user_preferences")
@NoArgsConstructor
@AllArgsConstructor
public class UserPreferences {

  @Id
  @Column(name = "user_id")
  private Long userId;

  @Column(name = "language")
  private String language;
}
