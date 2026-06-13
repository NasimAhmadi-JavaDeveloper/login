package ir.tamin.teco.domain.repository;

import ir.tamin.teco.domain.model.Branch;

import java.util.Optional;

public interface BranchRepository {

    Optional<Branch> findByCode(String code);

}