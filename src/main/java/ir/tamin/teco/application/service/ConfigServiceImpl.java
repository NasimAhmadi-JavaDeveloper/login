package ir.tamin.teco.application.service;

import ir.tamin.teco.domain.repository.ConfigRepository;
import ir.tamin.teco.domain.service.ConfigService;
import ir.tamin.teco.shared.model.enums.ConfigKey;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ConfigServiceImpl implements ConfigService {

    private final ConfigRepository configRepository;

    @Override
    public String getString(ConfigKey key) {
        return configRepository.getValue(key);
    }

    @Override
    public Number getNumber(ConfigKey key) {
        return Integer.parseInt(getString(key));//TODO
    }

    @Override
    public boolean getBoolean(ConfigKey key) {
        return Boolean.parseBoolean(getString(key));//TODO
    }

}