package ir.tamin.teco.presentation.model.mapper;

import ir.tamin.teco.application.model.command.CreateCustomerCreditCommand;
import ir.tamin.teco.application.model.result.CreateCustomerCreditResult;
import ir.tamin.teco.application.model.result.GetCustomerCreditResult;
import ir.tamin.teco.presentation.model.request.CreateCustomerCreditRequest;
import ir.tamin.teco.presentation.model.response.CustomerCreditResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerCreditWebMapper {

    CreateCustomerCreditCommand toCommand(CreateCustomerCreditRequest request);

    CustomerCreditResponse toCreateResponse(CreateCustomerCreditResult result);

    CustomerCreditResponse toGetResponse(GetCustomerCreditResult result);

}