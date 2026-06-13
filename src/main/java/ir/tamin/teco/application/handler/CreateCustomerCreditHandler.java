package ir.tamin.teco.application.handler;

import ir.tamin.teco.application.model.command.CreateCustomerCreditCommand;
import ir.tamin.teco.application.model.mapper.CustomerDomainMapper;
import ir.tamin.teco.application.model.result.CreateCustomerCreditResult;
import ir.tamin.teco.application.usecase.CreateCustomerCreditUseCase;
import ir.tamin.teco.domain.model.Branch;
import ir.tamin.teco.domain.model.CustomerCredit;
import ir.tamin.teco.domain.service.BranchService;
import ir.tamin.teco.domain.service.CustomerCreditService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateCustomerCreditHandler implements CreateCustomerCreditUseCase {

    private final CustomerDomainMapper customerMapper;
    private final CustomerCreditService service;
    private final BranchService branchService;

    @Override
     public CreateCustomerCreditResult handle(CreateCustomerCreditCommand command) {

         CustomerCredit model = customerMapper.toModel(command);

        //we can send whole branch command, but your code only includes BRH-CODE
          final Branch branchModel = branchService.getOrCreateBranchModel(command.getBranchCreateCommand().brhCode());

         CustomerCredit customerCredit = service.create(model);

        return customerMapper.toCreateResult(customerCredit, branchModel);
    }
}
