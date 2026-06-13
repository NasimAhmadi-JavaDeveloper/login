package ir.tamin.teco.application.model.mapper;

import ir.tamin.teco.application.model.command.BranchCommand;
import ir.tamin.teco.application.model.result.BranchResult;
import ir.tamin.teco.domain.model.Branch;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedSourcePolicy= ReportingPolicy.ERROR )
public interface BranchDomainMapper {

    @Mapping(target = "brhName", ignore = true)
    @Mapping(target = "brhKind", ignore = true)
    Branch toDomainModel(BranchCommand.create command);

    BranchResult.create toResult(Branch result);
}