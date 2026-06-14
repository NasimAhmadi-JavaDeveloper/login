package ir.tamin.teco.application.handler;


import ir.tamin.teco.application.result.ServiceTokenResult;
import ir.tamin.teco.application.usecase.GetServiceTokenUseCase;
import ir.tamin.teco.domain.service.ServiceTokenService;
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
