package ir.tamin.teco.application.model.mapper;

import ir.tamin.teco.application.model.command.CreateCustomerCreditCommand;
import ir.tamin.teco.application.model.command.UserCommand;
import ir.tamin.teco.application.model.result.CreateCustomerCreditResult;
import ir.tamin.teco.application.model.result.GetCustomerCreditResult;
import ir.tamin.teco.domain.model.CustomerCredit;
import ir.tamin.teco.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedSourcePolicy= ReportingPolicy.ERROR )
public interface UserMapper {

    User toDomainModel(UserCommand.create command);
}