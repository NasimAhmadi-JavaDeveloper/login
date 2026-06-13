package ir.tamin.teco.domain.repository;

import ir.tamin.teco.domain.model.CustomerCredit;

import java.util.Optional;

public interface CustomerCreditRepository {

    CustomerCredit save(CustomerCredit customerCredit);

    Optional<CustomerCredit> findById(Long id);

}