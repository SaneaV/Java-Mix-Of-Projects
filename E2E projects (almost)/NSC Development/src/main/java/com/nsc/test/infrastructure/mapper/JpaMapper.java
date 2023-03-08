package com.nsc.test.infrastructure.mapper;

import com.nsc.test.domain.user.User;
import com.nsc.test.infrastructure.jpa.user.UserJpa;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface JpaMapper {

  User toEntity(UserJpa userJpa);

  UserJpa toJpa(User user);
}
