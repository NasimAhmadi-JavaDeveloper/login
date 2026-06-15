package ir.tamin.teco.application.service;

import ir.tamin.teco.application.port.out.ConfigRepository;
import ir.tamin.teco.domain.enums.ConfigKey;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ConfigService {

  private final ConfigRepository configRepository;


  @Cacheable("config")
  @Transactional(readOnly = true)
  public String getString(ConfigKey key) {
    return configRepository.getValue(key);
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