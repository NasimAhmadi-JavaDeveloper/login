package ir.tamin.teco.application.service;

import ir.tamin.teco.application.model.mapper.UserDomainMapper;
import ir.tamin.teco.domain.model.CustomerCredit;
import ir.tamin.teco.domain.repository.CustomerCreditRepository;
import ir.tamin.teco.domain.service.BranchService;
import ir.tamin.teco.domain.service.CustomerCreditService;
import ir.tamin.teco.domain.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerCreditServiceImpl implements CustomerCreditService {
    private final UserService userService;
    private final BranchService branchService;
    private final CustomerCreditRepository customerCreditRepository;
    private final UserDomainMapper userMapper;

    @Override
    public CustomerCredit create(CustomerCredit customerCredit) {
//        userService.create(user);
        //customerCredit.setBranch(branchService.getBranchModel(branchCode));

        return customerCreditRepository.save(customerCredit);
    }
}
