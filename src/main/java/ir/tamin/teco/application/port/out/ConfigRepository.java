package ir.tamin.teco.application.port.out;

import ir.tamin.teco.domain.enums.ConfigKey;

public interface ConfigRepository {

    String getValue(ConfigKey key);

}