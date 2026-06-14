package ir.tamin.teco.infrastructure.persistence.repository.adapter;

import ir.tamin.teco.domain.exception.ConfigurationNotFoundException;
import ir.tamin.teco.application.port.out.ConfigRepository;
import ir.tamin.teco.infrastructure.persistence.entity.ConfigEntity;
import ir.tamin.teco.infrastructure.persistence.repository.jpa.JpaConfigRepository;
import ir.tamin.teco.domain.enums.ConfigKey;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ConfigRepositoryImpl implements ConfigRepository {

  private final JpaConfigRepository jpaConfigRepository;

  public String getValue(ConfigKey key) {
    return jpaConfigRepository.findByKey(key)
        .map(ConfigEntity::getValue)
        .orElseThrow(() -> new ConfigurationNotFoundException(key));//TODO Advice
  }

}