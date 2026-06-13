package ir.tamin.teco.application.model.mapper;

import ir.tamin.teco.application.model.command.CreateCustomerCreditCommand;
import ir.tamin.teco.application.model.result.GetCustomerCreditResult;
import ir.tamin.teco.application.model.result.CreateCustomerCreditResult;
import ir.tamin.teco.domain.model.CustomerCredit;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = UserMapper.class)
public interface CustomerMapper {

    @Mapping(target = "branch", ignore = true)
    CustomerCredit toModel(CreateCustomerCreditCommand command);

    CreateCustomerCreditResult toCreateResult(CustomerCredit model);

    GetCustomerCreditResult toGetResult(CustomerCredit model);

}