package ir.tamin.teco.controller.rest;

import ir.tamin.teco.application.port.in.ServiceTokenService;
import ir.tamin.teco.model.mapper.ServiceTokenWebMapper;
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

    private final ServiceTokenService serviceTokenUseCase;
    private final ServiceTokenWebMapper serviceTokenWebMapper;

    @GetMapping
    public ResponseEntity<ServiceTokenResponse> getToken() {

        ServiceTokenResult result = serviceTokenUseCase.getServiceToken();

        return ResponseEntity.ok(serviceTokenWebMapper.toResponse(result));
    }

}
