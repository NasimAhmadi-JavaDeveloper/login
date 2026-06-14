package ir.tamin.teco.application.service;


import ir.tamin.teco.application.model.result.ServiceTokenResult;
import ir.tamin.teco.application.port.in.GetServiceTokenUseCase;
import ir.tamin.teco.application.port.in.ServiceTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ServiceTokenHandler implements GetServiceTokenUseCase {

    private final ServiceTokenService serviceToken;

    @Override
    public ServiceTokenResult handle() {
        return new ServiceTokenResult(serviceToken.getServiceToken());
    }
}
