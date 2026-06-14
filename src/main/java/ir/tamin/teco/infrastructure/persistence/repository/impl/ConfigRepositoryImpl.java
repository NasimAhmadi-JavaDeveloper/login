package ir.tamin.teco.infrastructure.persistence.repository.impl;

import ir.tamin.teco.domain.exception.ConfigurationNotFoundException;
import ir.tamin.teco.domain.repository.ConfigRepository;
import ir.tamin.teco.infrastructure.persistence.entity.ConfigEntity;
import ir.tamin.teco.infrastructure.persistence.repository.jpa.JpaConfigRepository;
import ir.tamin.teco.shared.model.enums.ConfigKey;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ConfigRepositoryImpl implements ConfigRepository {

  private final JpaConfigRepository jpaConfigRepository;

  public String getValue(ConfigKey key) {

    return jpaConfigRepository.findByKey(key)
        .map(ConfigEntity::getValue)
        .orElseThrow(
            () -> new ConfigurationNotFoundException("optimus config not found: " + key)); //TODO define exception
  }

}