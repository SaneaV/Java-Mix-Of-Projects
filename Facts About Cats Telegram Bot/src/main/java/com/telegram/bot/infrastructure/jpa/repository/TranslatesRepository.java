package com.telegram.bot.infrastructure.jpa.repository;

import com.telegram.bot.infrastructure.jpa.model.Translates;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TranslatesRepository extends JpaRepository<Translates, String> {

}
