package ir.tamin.teco.application.service;

import ir.tamin.teco.domain.model.CustomerCredit;
import ir.tamin.teco.domain.repository.CustomerCreditRepository;
import ir.tamin.teco.domain.service.BranchService;
import ir.tamin.teco.domain.service.CustomerCreditService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerCreditServiceImpl implements CustomerCreditService {
    private final BranchService branchService;
    private final CustomerCreditRepository customerCreditRepository;

    @Override
    public CustomerCredit create(CustomerCredit customerCredit) {

        return customerCreditRepository.save(customerCredit);
    }
}
