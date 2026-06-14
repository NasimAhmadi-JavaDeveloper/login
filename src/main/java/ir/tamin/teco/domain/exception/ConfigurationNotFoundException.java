package ir.tamin.teco.domain.exception;

import ir.tamin.teco.shared.model.enums.ConfigKey;

public class ConfigurationNotFoundException extends RuntimeException {
    public ConfigurationNotFoundException(ConfigKey key) {
        super("Configuration not found key: " + key);
    }
}
