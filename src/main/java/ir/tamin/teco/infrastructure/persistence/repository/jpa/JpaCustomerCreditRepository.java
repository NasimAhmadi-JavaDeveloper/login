package ir.tamin.teco.infrastructure.persistence.repository.jpa;

import ir.tamin.teco.infrastructure.persistence.entity.CustomerCreditEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaCustomerCreditRepository extends JpaRepository<CustomerCreditEntity, Long> {

}