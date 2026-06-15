package ir.tamin.teco.repository;

import ir.tamin.teco.model.entity.ConfigEntity;
import ir.tamin.teco.model.enums.ConfigKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaConfigRepository extends JpaRepository<ConfigEntity, Long> {

    Optional<ConfigEntity> findByKey(ConfigKey configKey);

}