package ir.tamin.teco.domain.repository;

import ir.tamin.teco.shared.model.enums.ConfigKey;

public interface ConfigRepository {

    String getValue(ConfigKey key);
}