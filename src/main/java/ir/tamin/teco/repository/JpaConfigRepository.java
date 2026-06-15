package ir.tamin.teco.repository;

import ir.tamin.teco.model.entity.Config;
import ir.tamin.teco.model.enums.ConfigKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaConfigRepository extends JpaRepository<Config, Long> {

    Optional<Config> findByKey(ConfigKey configKey);

}