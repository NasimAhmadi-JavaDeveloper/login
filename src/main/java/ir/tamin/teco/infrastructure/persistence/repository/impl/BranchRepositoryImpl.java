package ir.tamin.teco.infrastructure.persistence.repository.impl;

import ir.tamin.teco.domain.model.Branch;
import ir.tamin.teco.domain.repository.BranchRepository;
import ir.tamin.teco.infrastructure.persistence.mapper.BranchPersistenceMapper;
import ir.tamin.teco.infrastructure.persistence.repository.jpa.JpaBranchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class BranchRepositoryImpl implements BranchRepository {

    private final JpaBranchRepository jpaBranchRepository;
    private final BranchPersistenceMapper branchPersistenceMapper;

    @Override
    public Optional<Branch> findByCode(String code) {
        return jpaBranchRepository.findByBrhCode(code).map(branchPersistenceMapper::toDomain);
    }
}
