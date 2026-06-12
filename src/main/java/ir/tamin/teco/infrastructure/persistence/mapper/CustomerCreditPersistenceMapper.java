package ir.tamin.teco.infrastructure.persistence.mapper;

import ir.tamin.teco.domain.model.CustomerCredit;
import ir.tamin.teco.infrastructure.persistence.entity.CustomerCreditEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CustomerCreditPersistenceMapper {

    @Mapping(source = "unitCode", target = "branch")
    CustomerCredit toDomain(CustomerCreditEntity entity);

    @Mapping(source = "branch", target = "unitCode")
    CustomerCreditEntity toEntity(CustomerCredit domain);

}