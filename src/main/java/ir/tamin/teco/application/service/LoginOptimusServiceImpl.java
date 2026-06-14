package ir.tamin.teco.application.service;

import ir.tamin.teco.application.port.out.OptimusOut;
import ir.tamin.teco.application.result.ServiceTokenResult;
import ir.tamin.teco.domain.service.ServiceTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginOptimusServiceImpl implements ServiceTokenService {

    private final OptimusOut optimusOut;

    @Override
    public ServiceTokenResult login() {
        return new ServiceTokenResult(optimusOut.getServiceToken());
    }
}
