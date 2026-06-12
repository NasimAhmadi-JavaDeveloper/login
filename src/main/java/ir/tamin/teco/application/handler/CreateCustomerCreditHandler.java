package ir.tamin.teco.application.handler;

import ir.tamin.teco.application.model.command.CreateCustomerCreditCommand;
import ir.tamin.teco.application.model.mapper.CustomerMapper;
import ir.tamin.teco.application.model.mapper.UserMapper;
import ir.tamin.teco.application.model.result.CreateCustomerCreditResult;
import ir.tamin.teco.application.usecase.CreateCustomerCreditUseCase;
import ir.tamin.teco.domain.exception.BranchNotFoundException;
import ir.tamin.teco.domain.model.Branch;
import ir.tamin.teco.domain.model.CustomerCredit;
import ir.tamin.teco.domain.model.User;
import ir.tamin.teco.domain.repository.BranchRepository;
import ir.tamin.teco.domain.repository.CustomerCreditRepository;
import ir.tamin.teco.domain.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateCustomerCreditHandler implements CreateCustomerCreditUseCase {

    private final UserService userService;
    private final BranchRepository branchRepository;
    private final CustomerCreditRepository customerCreditRepository;
    private final CustomerMapper customerMapper;
    private final UserMapper userMapper;

    @Override
    public CreateCustomerCreditResult handle(CreateCustomerCreditCommand command) {

        User user = userMapper.toDomainModel(command.getUserCreateCommand());
        userService.createUser(user);

        Branch branchModel = getBranchModel(command.getUnitCode());// 😂

        CustomerCredit model = customerMapper.toModel(command);

        model.setBranch(branchModel);

        CustomerCredit savedModel = customerCreditRepository.save(model);


        return customerMapper.toCreateResult(savedModel);
    }

    private Branch getBranchModel(String unitCode) {
        return branchRepository.findByCode(unitCode)
                .orElseThrow(() -> new BranchNotFoundException(unitCode));
    }

}
