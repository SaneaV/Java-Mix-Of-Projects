package com.telegram.bot.infrastructure.service;

public interface UserPreferencesService {

  boolean userPreferencesExist(Long userId);

  String getLanguageByUserId(Long userId);

  void saveUserPreferences(Long userId, String language);
}
