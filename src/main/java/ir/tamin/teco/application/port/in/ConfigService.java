package ir.tamin.teco.application.port.in;

import ir.tamin.teco.domain.enums.ConfigKey;

public interface ConfigService {

    String getString(ConfigKey key);

    Number getNumber(ConfigKey key);

    boolean getBoolean(ConfigKey key);

}