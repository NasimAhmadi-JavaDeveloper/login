package ir.tamin.teco.application.model.mapper;

import ir.tamin.teco.application.model.command.CreateCustomerCreditCommand;
import ir.tamin.teco.application.model.result.CreateCustomerCreditResult;
import ir.tamin.teco.application.model.result.GetCustomerCreditResult;
import ir.tamin.teco.domain.model.Branch;
import ir.tamin.teco.domain.model.CustomerCredit;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.ERROR, uses = {UserDomainMapper.class, BranchDomainMapper.class})
public interface CustomerDomainMapper {

    //@Mapping(target = "branch", ignore = true)
    @Mapping(target = "branch", ignore = true)
    CustomerCredit toModel(CreateCustomerCreditCommand command);

    CreateCustomerCreditResult toCreateResult(CustomerCredit model, final Branch branch);

    @Mapping(target = "s", ignore = true)
    GetCustomerCreditResult toGetResult(CustomerCredit model);

}