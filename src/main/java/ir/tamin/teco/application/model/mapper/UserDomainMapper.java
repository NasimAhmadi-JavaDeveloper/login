package ir.tamin.teco.application.model.mapper;

import ir.tamin.teco.application.model.command.UserCommand;
import ir.tamin.teco.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedSourcePolicy= ReportingPolicy.ERROR )
public interface UserDomainMapper {

    User toDomainModel(UserCommand.create command);
}