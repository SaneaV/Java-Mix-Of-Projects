package com.telegram.bot.infrastructure.jpa.repository;

import com.telegram.bot.infrastructure.jpa.model.UserPreferences;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface UserPreferencesRepository extends JpaRepository<UserPreferences, Long> {

  UserPreferences findByUserId(Long userId);
}
