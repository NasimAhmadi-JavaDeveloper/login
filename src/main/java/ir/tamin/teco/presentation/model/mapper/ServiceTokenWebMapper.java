package ir.tamin.teco.presentation.model.mapper;

import ir.tamin.teco.application.result.ServiceTokenResult;
import ir.tamin.teco.presentation.model.response.ServiceTokenResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ServiceTokenWebMapper {

    ServiceTokenResponse toResponse(ServiceTokenResult result);

}