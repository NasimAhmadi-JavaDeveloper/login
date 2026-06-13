package ir.tamin.teco.domain.service;

import ir.tamin.teco.domain.model.Branch;

public interface BranchService {
    Branch getOrCreateBranchModel(String unitCode);
}
