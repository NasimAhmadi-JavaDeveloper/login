package ir.tamin.teco.model.mapper;

import ir.tamin.teco.presentation.model.response.ServiceTokenResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ServiceTokenWebMapper {

    ServiceTokenResponse toResponse(ServiceTokenResult result);

}