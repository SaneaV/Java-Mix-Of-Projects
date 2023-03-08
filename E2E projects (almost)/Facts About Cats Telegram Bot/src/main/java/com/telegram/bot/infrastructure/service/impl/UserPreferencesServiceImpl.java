package com.telegram.bot.infrastructure.service.impl;

import static java.util.Objects.nonNull;

import com.telegram.bot.infrastructure.jpa.model.UserPreferences;
import com.telegram.bot.infrastructure.jpa.repository.UserPreferencesRepository;
import com.telegram.bot.infrastructure.service.UserPreferencesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserPreferencesServiceImpl implements UserPreferencesService {

  private final UserPreferencesRepository userPreferencesRepository;

  @Override
  public boolean userPreferencesExist(Long userId) {
    return nonNull(userPreferencesRepository.findByUserId(userId));
  }

  @Override
  public String getLanguageByUserId(Long userId) {
    return userPreferencesRepository.findByUserId(userId).getLanguage();
  }

  @Override
  public void saveUserPreferences(Long userId, String language) {
    userPreferencesRepository.save(new UserPreferences(userId, language));
  }
}
