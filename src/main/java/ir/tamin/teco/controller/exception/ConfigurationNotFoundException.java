package ir.tamin.teco.controller.exception;

import ir.tamin.teco.model.enums.ConfigKey;

public class ConfigurationNotFoundException extends RuntimeException {
    public ConfigurationNotFoundException(ConfigKey key) {
        super("Configuration not found key: " + key);
    }
}
