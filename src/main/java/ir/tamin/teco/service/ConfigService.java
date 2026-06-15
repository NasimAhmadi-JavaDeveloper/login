package ir.tamin.teco.service;

import ir.tamin.teco.controller.exception.ConfigurationNotFoundException;
import ir.tamin.teco.model.enums.ConfigKey;
import ir.tamin.teco.repository.JpaConfigRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ConfigService {

  private final JpaConfigRepository configRepository;

  @Cacheable("config")
  @Transactional(readOnly = true)
  public String getString(ConfigKey key) {
    return configRepository.findByKey(key)
        .orElseThrow(() -> new ConfigurationNotFoundException(key))
        .getValue();
  }

  @Cacheable("config")
  @Transactional(readOnly = true)
  public Number getNumber(ConfigKey key) {
    return Integer.parseInt(getString(key));//TODO
  }

  @Cacheable("config")
  @Transactional(readOnly = true)
  public boolean getBoolean(ConfigKey key) {
    return Boolean.parseBoolean(getString(key));//TODO
  }
}