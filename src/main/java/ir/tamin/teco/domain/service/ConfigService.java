package ir.tamin.teco.domain.service;

import ir.tamin.teco.shared.model.enums.ConfigKey;

public interface ConfigService {

  String getString(ConfigKey key);

  Number getNumber(ConfigKey key);

  boolean getBoolean(ConfigKey key);
}