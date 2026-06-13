package ir.tamin.teco.application.handler;

import ir.tamin.teco.application.model.command.CreateCustomerCreditCommand;
import ir.tamin.teco.application.model.mapper.CustomerMapper;
import ir.tamin.teco.application.model.mapper.UserMapper;
import ir.tamin.teco.application.model.result.CreateCustomerCreditResult;
import ir.tamin.teco.application.usecase.CreateCustomerCreditUseCase;
import ir.tamin.teco.domain.model.CustomerCredit;
import ir.tamin.teco.domain.service.CustomerCreditService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateCustomerCreditHandler implements CreateCustomerCreditUseCase {

    private final CustomerMapper customerMapper;
    private final UserMapper userMapper;
    private final CustomerCreditService service;

    @Override
    public CreateCustomerCreditResult handle(CreateCustomerCreditCommand command) {

        CustomerCredit model = customerMapper.toModel(command);

        // User user = userMapper.toDomainModel(command.getUserCreateCommand());

        // CustomerCredit result = service.create(model, user, command.getUnitCode());

        CustomerCredit customerCredit = service.create(model);

        return customerMapper.toCreateResult(customerCredit);
    }
}
