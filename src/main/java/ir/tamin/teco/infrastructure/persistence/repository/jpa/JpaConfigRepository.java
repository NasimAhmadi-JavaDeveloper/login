package ir.tamin.teco.infrastructure.persistence.repository.jpa;

import ir.tamin.teco.infrastructure.persistence.entity.ConfigEntity;
import ir.tamin.teco.domain.enums.ConfigKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaConfigRepository extends JpaRepository<ConfigEntity, Long> {

    Optional<ConfigEntity> findByKey(ConfigKey configKey);

}