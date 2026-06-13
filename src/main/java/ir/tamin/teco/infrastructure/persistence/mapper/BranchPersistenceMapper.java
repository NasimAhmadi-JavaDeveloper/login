package ir.tamin.teco.infrastructure.persistence.mapper;

import ir.tamin.teco.domain.model.Branch;
import ir.tamin.teco.infrastructure.persistence.entity.BranchEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BranchPersistenceMapper {

    Branch toDomain(BranchEntity entity);

    BranchEntity toEntity(Branch domain);

}