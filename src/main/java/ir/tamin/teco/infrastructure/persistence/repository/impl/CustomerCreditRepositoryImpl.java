package ir.tamin.teco.infrastructure.persistence.repository.impl;

import ir.tamin.teco.domain.model.CustomerCredit;
import ir.tamin.teco.domain.repository.CustomerCreditRepository;
import ir.tamin.teco.infrastructure.persistence.entity.CustomerCreditEntity;
import ir.tamin.teco.infrastructure.persistence.mapper.CustomerCreditPersistenceMapper;
import ir.tamin.teco.infrastructure.persistence.repository.jpa.JpaCustomerCreditRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CustomerCreditRepositoryImpl implements CustomerCreditRepository {

    private final JpaCustomerCreditRepository jpaCustomerCreditRepository;
    private final CustomerCreditPersistenceMapper customerCreditPersistenceMapper;

    @Override
    public CustomerCredit save(CustomerCredit domain) {

        CustomerCreditEntity entity = customerCreditPersistenceMapper.toEntity(domain);

        CustomerCreditEntity saved = jpaCustomerCreditRepository.save(entity);

        return customerCreditPersistenceMapper.toDomain(saved);
    }

    @Override
    public Optional<CustomerCredit> findById(Long id) {
        return jpaCustomerCreditRepository.findById(id).map(customerCreditPersistenceMapper::toDomain);
    }
}
