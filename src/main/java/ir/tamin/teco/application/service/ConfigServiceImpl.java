package ir.tamin.teco.application.service;

import ir.tamin.teco.domain.exception.ConfigurationNotFoundException;
import ir.tamin.teco.domain.repository.ConfigRepository;
import ir.tamin.teco.domain.service.ConfigService;
import ir.tamin.teco.shared.model.enums.ConfigKey;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ConfigServiceImpl implements ConfigService {

    private final ConfigRepository configRepository;

  @Override
  @Cacheable("config")
  public String getString(final ConfigKey key) {
    return configRepository.getValue(key);
  }

  @Override
  public Number getNumber(final ConfigKey key) {
    try {
      return new BigDecimal(configRepository.getValue(key));
    } catch (Exception e) {
      throw new ConfigurationNotFoundException(key);
    }
  }

  @Override
  public boolean getBoolean(final ConfigKey key) {
    try {
      return Boolean.parseBoolean(configRepository.getValue(key));
    } catch (Exception e) {
      throw new ConfigurationNotFoundException(key);
    }
  }
}