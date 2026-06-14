package ir.tamin.teco.application.service;

import ir.tamin.teco.application.port.out.ConfigRepository;
import ir.tamin.teco.application.port.in.ConfigService;
import ir.tamin.teco.domain.enums.ConfigKey;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ConfigServiceImpl implements ConfigService {

  private final ConfigRepository configRepository;

  @Override
  @Cacheable("config")
  @Transactional(readOnly = true)
  public String getString(ConfigKey key) {
    return configRepository.getValue(key);
  }

  @Override
  @Cacheable("config")
  @Transactional(readOnly = true)
  public Number getNumber(ConfigKey key) {
    return Integer.parseInt(getString(key));//TODO
  }

  @Override
  @Cacheable("config")
  @Transactional(readOnly = true)
  public boolean getBoolean(ConfigKey key) {
    return Boolean.parseBoolean(getString(key));//TODO
  }

}