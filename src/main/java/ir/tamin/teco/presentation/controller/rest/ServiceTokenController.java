package ir.tamin.teco.presentation.controller.rest;

import ir.tamin.teco.application.result.ServiceTokenResult;
import ir.tamin.teco.application.usecase.GetServiceTokenUseCase;
import ir.tamin.teco.presentation.model.mapper.ServiceTokenWebMapper;
import ir.tamin.teco.presentation.model.response.ServiceTokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/service-token")
public class ServiceTokenController {

    private final GetServiceTokenUseCase serviceTokenUseCase;
    private final ServiceTokenWebMapper serviceTokenWebMapper;

    @GetMapping
    public ResponseEntity<ServiceTokenResponse> getToken() {

        ServiceTokenResult result = serviceTokenUseCase.handle();

        return ResponseEntity.ok(serviceTokenWebMapper.toResponse(result));
    }

}
