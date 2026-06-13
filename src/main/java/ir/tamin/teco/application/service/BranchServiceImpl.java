package ir.tamin.teco.application.service;

import ir.tamin.teco.domain.exception.BranchNotFoundException;
import ir.tamin.teco.domain.model.Branch;
import ir.tamin.teco.domain.repository.BranchRepository;
import ir.tamin.teco.domain.service.BranchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BranchServiceImpl implements BranchService {

    private final BranchRepository branchRepository;

    @Override
    public Branch getOrCreateBranchModel(String unitCode) {
        return branchRepository.findByCode(unitCode)
                .orElseGet(() -> branchRepository.save(Branch.fromCode(unitCode)));
    }
}
