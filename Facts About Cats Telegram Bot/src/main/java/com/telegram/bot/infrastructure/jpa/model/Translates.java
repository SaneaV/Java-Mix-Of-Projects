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
@Table(name = "translates")
@AllArgsConstructor
@NoArgsConstructor
public class Translates {

  @Id
  @Column(name = "translation_code", unique = true)
  private String translationCode;

  @Column(name = "eng", unique = true)
  private String eng;

  @Column(name = "rus", unique = true)
  private String rus;

  @Column(name = "ukr", unique = true)
  private String ukr;
}
